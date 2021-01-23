package io.github.ytg1234.recipeconditions.impl.condition;

import io.github.ytg1234.recipeconditions.RecipeCondsConstants;
import io.github.ytg1234.recipeconditions.api.condition.AnyCondition;
import io.github.ytg1234.recipeconditions.api.condition.EveryCondition;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import net.minecraft.util.collection.DefaultedList;

@ApiStatus.Internal
public final class AnyConditionImpl implements AnyCondition {
    @NotNull
    private final DefaultedList<EveryCondition> conditions;

    public AnyConditionImpl(@NotNull DefaultedList<EveryCondition> conditions) {
        this.conditions = conditions;
    }

    @Override
    public boolean check() {
        RecipeCondsConstants.LOGGER.debug("Checking an AnyCondition...");
        return getConditions().stream().anyMatch(EveryCondition::check);
    }

    @NotNull
    @Override
    public DefaultedList<EveryCondition> getConditions() {
        return conditions;
    }
}
