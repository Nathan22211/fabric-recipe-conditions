package io.github.ytg1234.recipeconditions.api.condition.util;

import java.util.function.Predicate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.ytg1234.recipeconditions.api.condition.EveryCondition;
import io.github.ytg1234.recipeconditions.api.condition.SingleCondition;
import io.github.ytg1234.recipeconditions.api.condition.base.RecipeCondition;
import io.github.ytg1234.recipeconditions.impl.util.MapEntryImpl;
import org.jetbrains.annotations.NotNull;

/**
 * Various public utilities.
 *
 * @author YTG1234
 * @since 0.3.0
 */
public final class RecipeCondsUtil {
    /**
     * <p>
     * Generates and returns a {@link RecipeCondition}
     * that automatically converts its param to a {@link String}.
     * </p>
     * <p>
     * Useful for when you want to do things like
     * <code>
     * RecipeCondsUtil.stringParam(modid -> FabricLoader.getInstance().isModLoaded(modid));
     * </code>
     * </p>
     *
     * @param cond a predicate processing a String
     *
     * @return the generated {@link RecipeCondition} instance
     */
    @NotNull
    public static RecipeCondition stringParam(Predicate<String> cond) {
        return (param, recipe) -> cond.test(param.string());
    }

    /**
     * Generates and returns a {@link RecipeCondition}
     * that automatically converts its param to a {@link Boolean boolean}.
     *
     * @param cond a predicate processing the aforementioned {@code boolean}
     *
     * @return the generated condition
     */
    @NotNull
    public static RecipeCondition boolParam(Predicate<Boolean> cond) {
        return (param, recipe) -> cond.test(param.bool());
    }

    /**
     * Generates and returns a {@link RecipeCondition}
     * that automatically converts its param to an {@link Integer int}.
     *
     * @param cond a predicate processing the converted {@code int}
     *
     * @return the generated  condition
     */
    @NotNull
    public static RecipeCondition intParam(Predicate<Integer> cond) {
        return (param, recipe) -> cond.test(param.integer());
    }

    /**
     * Generates and returns a {@link RecipeCondition}
     * that automatically converts its param to a {@link Float float}.
     *
     * @param cond a predicate processing the {@code float}
     *
     * @return the generated condition
     */
    @NotNull
    public static RecipeCondition floatParam(Predicate<Float> cond) {
        return (param, recipe) -> cond.test(param.floatingPoint());
    }

    /**
     * Generates and returns a {@link RecipeCondition}
     * that automatically converts its param to a {@link JsonArray}.
     *
     * @param cond a predicate processing the array
     *
     * @return the generated condition
     */
    @NotNull
    public static RecipeCondition arrayParam(Predicate<JsonArray> cond) {
        return (param, recipe) -> cond.test(param.array());
    }

    /**
     * Generates and returns a {@link RecipeCondition}
     * that automatically converts its param to a {@link JsonObject}.
     *
     * @param cond a predicate for processing the object
     *
     * @return the generated condition
     */
    @NotNull
    public static RecipeCondition objectParam(Predicate<JsonObject> cond) {
        return (param, recipe) -> cond.test(param.object());
    }

    /**
     * Generates and returns a {@link RecipeCondition}
     * that automatically converts its param to a {@link SingleCondition}.
     *
     * @param cond a predicate for processing the condition
     *
     * @return the generated condition
     */
    @NotNull
    public static RecipeCondition singleConditionParam(Predicate<SingleCondition> cond) { // higher-order conditions
        return (param, recipe) -> {
            JsonObject obj = param.object();
            SingleCondition singleCondition = SingleCondition.fromJson(new MapEntryImpl<>(obj.get("id").getAsString(), obj.get("param")), recipe);
            return cond.test(singleCondition);
        };
    }

    /**
     * Generates and returns a {@link net.minecraft.recipe.Recipe}
     * that automatically converts its param to a {@link EveryCondition}.
     *
     * @param cond a predicate for processing the condition
     *
     * @return the generated condition
     */
    @NotNull
    public static RecipeCondition everyConditionParam(Predicate<EveryCondition> cond) { // This will probably allow XOR condition if someone smart will do that
        return (param, recipe) -> cond.test(EveryCondition.fromJson(param.object(), recipe));
    }
}
