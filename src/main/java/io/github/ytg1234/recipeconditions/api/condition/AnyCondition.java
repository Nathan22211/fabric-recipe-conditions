package io.github.ytg1234.recipeconditions.api.condition;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.github.ytg1234.recipeconditions.impl.condition.AnyConditionImpl;
import org.jetbrains.annotations.NotNull;

import net.minecraft.recipe.Recipe;
import net.minecraft.util.collection.DefaultedList;

/**
 * Represents an array of {@link EveryCondition}s - The highest level
 * of condition representation.
 *
 * @author YTG1234
 */
public interface AnyCondition {
    /**
     * Parses a Json array into a simple list of {@link EveryCondition}s.
     *
     * @param array the array to be parsed
     * @param recipe the recipe that this condition array is in
     *
     * @return the parsed form of the array
     */
    @NotNull
    static AnyCondition fromJson(@NotNull JsonArray array, @NotNull Recipe<?> recipe) {
        DefaultedList<EveryCondition> list = DefaultedList.of();
        for (JsonElement element : array) {
            if (!element.isJsonObject()) throw new JsonParseException("Conditions must be objects!");
            list.add(EveryCondition.fromJson(element.getAsJsonObject(), recipe));
        }
        return new AnyConditionImpl(list, recipe);
    }

    /**
     * Checks if the array matches - if any of its elements match.
     *
     * @return whether the array matches
     */
    boolean check();

    @NotNull DefaultedList<EveryCondition> getConditions();

    @NotNull Recipe<?> getRecipe();
}
