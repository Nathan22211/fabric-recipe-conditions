package io.github.ytg1234.recipeconditions.api.condition;

import java.util.Map;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.github.ytg1234.recipeconditions.impl.condition.EveryConditionImpl;
import org.jetbrains.annotations.NotNull;

import net.minecraft.util.collection.DefaultedList;

/**
 * Represents a list of {@link SingleCondition}s, that all
 * of them have to match for this list to match.
 *
 * @author YTG1234
 */
public interface EveryCondition {
    /**
     * Parses a Json object to a list of {@link SingleCondition}s.
     *
     * @param object the object to be parsed
     *
     * @return the parsed representation
     */
    @NotNull
    static EveryCondition fromJson(JsonObject object) {
        DefaultedList<SingleCondition> list = DefaultedList.of();
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            list.add(SingleCondition.fromJson(entry));
        }
        return new EveryConditionImpl(list);
    }

    /**
     * Checks if all the conditions in the list match.
     *
     * @return whether they match
     */
    boolean check();

    @NotNull DefaultedList<SingleCondition> getConditions();
}
