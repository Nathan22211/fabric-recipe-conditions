package io.github.ytg1234.recipeconditions.api.condition;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import io.github.ytg1234.recipeconditions.api.RecipeConds;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Represents a Json entry that specifies a condition identifier
 * and either one value or an array of them.
 *
 * @author YTG1234
 */
public final class SingleCondition {
    @NotNull
    private final RecipeCondition condition;
    @Nullable
    private final String value;
    @Nullable
    private final DefaultedList<String> values;

    public SingleCondition(@NotNull RecipeCondition condition, @NotNull String value) {
        this.condition = condition;
        this.value = value;
        this.values = null;
    }

    public SingleCondition(@NotNull RecipeCondition condition, @NotNull DefaultedList<String> values) {
        this.condition = condition;
        this.values = values;
        this.value = null;
    }

    /**
     * Parses a Json entry to get the condition and value(s) from it.
     *
     * @param entry the Json entry to parse
     *
     * @return the new representation of the entry
     */
    public static SingleCondition fromJson(@NotNull Map.Entry<String, JsonElement> entry) {
        if (entry.getValue().isJsonArray()) {
            DefaultedList<String> values = DefaultedList.of();
            for (JsonElement element : entry.getValue().getAsJsonArray()) {
                values.add(element.getAsString());
            }
            Identifier conditionId = new Identifier(entry.getKey());
            RecipeCondition condition = RecipeConds.RECIPE_CONDITION.get(conditionId);
            if (condition == null) throw new JsonParseException("Unknown condition " + conditionId.toString() + "!");
            return new SingleCondition(condition, values);
        } else {
            String value = entry.getValue().getAsString();
            Identifier conditionId = new Identifier(entry.getKey());
            RecipeCondition condition = RecipeConds.RECIPE_CONDITION.get(conditionId);
            if (condition == null) throw new JsonParseException("Unknown condition " + conditionId.toString() + "!");
            return new SingleCondition(condition, value);
        }
    }

    /**
     * A convenient way to check if the internal condition matched for all values.
     *
     * @return whether the condition matched
     */
    public boolean check() {
        if (getValue() != null) {
            return getValue().startsWith("!") ?
                   !getCondition().check(getValue().replace("!", "")) :
                   getCondition().check(getValue());
        } else if (getValues() != null) {
            return getValues().stream()
                    .allMatch(val -> val.startsWith("!") ?
                                     !getCondition().check(val.replace("!", "")) :
                                     getCondition().check(val));
        } else {
            throw new IllegalStateException("How did this happen? values and value are null!");
        }
    }

    @Nullable
    public String getValue() {
        return value;
    }

    @NotNull
    public RecipeCondition getCondition() {
        return condition;
    }

    @Nullable
    public DefaultedList<String> getValues() {
        return values;
    }
}
