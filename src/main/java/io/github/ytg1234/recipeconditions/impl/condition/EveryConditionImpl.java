package io.github.ytg1234.recipeconditions.impl.condition;

import io.github.ytg1234.recipeconditions.RecipeCondsConstants;
import io.github.ytg1234.recipeconditions.api.condition.EveryCondition;
import io.github.ytg1234.recipeconditions.api.condition.SingleCondition;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

import net.minecraft.recipe.Recipe;
import net.minecraft.util.collection.DefaultedList;

@ApiStatus.Internal
public final class EveryConditionImpl implements EveryCondition {
    private final DefaultedList<SingleCondition> conditions;
    private final Recipe<?> recipe;

    public EveryConditionImpl(@NotNull DefaultedList<SingleCondition> conditions, @NotNull Recipe<?> recipe) {
        this.conditions = conditions;
        this.recipe = recipe;
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

    @Override
    public @NotNull Recipe<?> getRecipe() {
        return recipe;
    }
}
