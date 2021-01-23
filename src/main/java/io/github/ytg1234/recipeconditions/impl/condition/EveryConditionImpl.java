package io.github.ytg1234.recipeconditions.impl.condition;

import io.github.ytg1234.recipeconditions.RecipeCondsConstants;
import io.github.ytg1234.recipeconditions.api.condition.EveryCondition;
import io.github.ytg1234.recipeconditions.api.condition.SingleCondition;
import org.jetbrains.annotations.NotNull;

import net.minecraft.util.collection.DefaultedList;

public final class EveryConditionImpl implements EveryCondition {
    @NotNull
    private final DefaultedList<SingleCondition> conditions;

    public EveryConditionImpl(@NotNull DefaultedList<SingleCondition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public boolean check() {
        RecipeCondsConstants.LOGGER.debug("Checking an EveryCondition...");
        return getConditions().stream().allMatch(SingleCondition::check);
    }

    @NotNull
    @Override
    public DefaultedList<SingleCondition> getConditions() {
        return conditions;
    }
}
