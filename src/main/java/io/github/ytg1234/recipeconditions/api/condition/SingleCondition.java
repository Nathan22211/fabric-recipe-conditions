package io.github.ytg1234.recipeconditions.api.condition;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.github.ytg1234.recipeconditions.api.RecipeConds;
import io.github.ytg1234.recipeconditions.api.condition.base.RecipeCondition;
import io.github.ytg1234.recipeconditions.api.condition.base.RecipeConditionParameter;
import io.github.ytg1234.recipeconditions.impl.condition.SingleConditionImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;

/**
 * Represents a Json entry that specifies a condition identifier
 * and either one parameter or an array of them.
 *
 * @author YTG1234
 */
public interface SingleCondition {
    /**
     * Parses a Json entry to get the condition and value(s) from it.
     *
     * @param entry the Json entry to parse
     *
     * @return the new representation of the entry
     */
    @NotNull
    static SingleCondition fromJson(@NotNull Map.Entry<String, JsonElement> entry) {
        if (entry.getValue().isJsonArray()) {
            DefaultedList<RecipeConditionParameter> values = DefaultedList.of();
            for (JsonElement element : entry.getValue().getAsJsonArray()) {
                values.add(RecipeConditionParameter.createJsonElement(element));
            }
            boolean negated = false;
            if (entry.getKey().startsWith("!")) negated = true;
            Identifier conditionId = new Identifier(entry.getKey().replace("!", ""));
            RecipeCondition condition = RecipeConds.RECIPE_CONDITION.get(conditionId);
            if (condition == null) {
                throw new JsonParseException(new IllegalArgumentException("Unknown condition " + conditionId.toString() + "!"));
            }
            return new SingleConditionImpl(condition, values, negated);
        } else {
            JsonElement value = entry.getValue();
            boolean negated = false;
            if (entry.getKey().startsWith("!")) negated = true;
            Identifier conditionId = new Identifier(entry.getKey().replace("!", ""));
            RecipeCondition condition = RecipeConds.RECIPE_CONDITION.get(conditionId);
            if (condition == null) {
                throw new JsonParseException(new IllegalArgumentException("Unknown condition " + conditionId.toString() + "!"));
            }
            return new SingleConditionImpl(condition, RecipeConditionParameter.createJsonElement(value), negated);
        }
    }

    /**
     * A convenient way to check if the internal condition matched for all values.
     *
     * @return whether the condition matched
     */
    boolean check();

    @Nullable RecipeConditionParameter getParam();

    @Nullable DefaultedList<RecipeConditionParameter> getParams();

    @NotNull RecipeCondition getCondition();

    /**
     * Checks if this condition should be inverted when checking.
     * @return if the condition is negated
     */
    boolean isNegated();
}
