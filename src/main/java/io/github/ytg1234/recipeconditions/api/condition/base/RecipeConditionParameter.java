package io.github.ytg1234.recipeconditions.api.condition.base;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.ytg1234.recipeconditions.impl.condition.base.RecipeConditionParameterImpl;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a parameter that can be fed into a {@link RecipeCondition}
 * to later match.
 * <p>
 * It contains some helper methods as well.
 * </p>
 *
 * @author YTG1234
 */
public interface RecipeConditionParameter {
    /**
     * A utility method, shortcut, to create a new
     * instance using a {@link JsonPrimitive}.
     *
     * @param i integer that is the param
     *
     * @return new parameter instance.
     */
    static RecipeConditionParameter createInt(int i) {
        return createJsonElement(new JsonPrimitive(i));
    }

    /**
     * I don't know why I made this, it's actually <em>longer</em> than
     * calling the constructor, but I made it anyway.
     *
     * @param json the param's internal value
     *
     * @return new parameter instance
     */
    @NotNull
    static RecipeConditionParameter createJsonElement(@NotNull JsonElement json) {
        return new RecipeConditionParameterImpl(json);
    }


    /**
     * A utility method, shortcut, to create a new
     * instance using a {@link JsonPrimitive}.
     *
     * @param string string that is the param
     *
     * @return new parameter instance
     */
    @NotNull
    static RecipeConditionParameter createString(@NotNull String string) {
        return createJsonElement(new JsonPrimitive(string));
    }

    JsonElement getValue();

    String string();

    int integer();

    double doublePrecision();

    float floatingPoint();

    JsonObject object();

    boolean bool();

    JsonArray array();
}
