package io.github.ytg1234.recipeconditions.impl.condition;

import io.github.ytg1234.recipeconditions.RecipeCondsConstants;
import io.github.ytg1234.recipeconditions.api.RecipeConds;
import io.github.ytg1234.recipeconditions.api.condition.SingleCondition;
import io.github.ytg1234.recipeconditions.api.condition.base.RecipeCondition;
import io.github.ytg1234.recipeconditions.api.condition.base.RecipeConditionParameter;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.recipe.Recipe;
import net.minecraft.util.collection.DefaultedList;

@ApiStatus.Internal
public final class SingleConditionImpl implements SingleCondition {
    private final RecipeCondition condition;
    private final RecipeConditionParameter param;
    private final DefaultedList<RecipeConditionParameter> params;
    private final Recipe<?> recipe;

    private final boolean negated;

    public SingleConditionImpl(@NotNull RecipeCondition condition, @NotNull RecipeConditionParameter param, @NotNull Recipe<?> recipe) {
        this(condition, param, false, recipe);
    }

    public SingleConditionImpl(
            @NotNull RecipeCondition condition, @NotNull DefaultedList<RecipeConditionParameter> params, @NotNull Recipe<?> recipe
    ) {
        this(condition, params, false, recipe);
    }

    public SingleConditionImpl(
            @NotNull RecipeCondition condition, @NotNull DefaultedList<RecipeConditionParameter> params, boolean negated, @NotNull Recipe<?> recipe
    ) {
        this.condition = condition;
        this.params = params;
        this.param = null;
        this.negated = negated;
        this.recipe = recipe;
    }

    public SingleConditionImpl(
            @NotNull RecipeCondition condition, @NotNull RecipeConditionParameter param, boolean negated, @NotNull Recipe<?> recipe
    ) {
        this.condition = condition;
        this.param = param;
        this.params = null;
        this.negated = negated;
        this.recipe = recipe;
    }

    @Override
    public boolean check() {
        RecipeCondsConstants.LOGGER.debug("Checking condition " +
                                          RecipeConds.RECIPE_CONDITION.getId(condition) +
                                          " , SingleCondition inverted: " +
                                          negated);
        if (getParam() != null) {
            RecipeCondsConstants.LOGGER.debug("Param is not null, " + param.toString());
            return negated != condition.check(param, recipe);
        } else if (getParams() != null) {
            RecipeCondsConstants.LOGGER.debug("Params is not null, " + params.toString());
            return negated != getParams().stream().allMatch(param -> condition.check(param, recipe));
        } else {
            throw new IllegalStateException("How did this happen? params and param are null!");
        }
    }

    @Nullable
    @Override
    public RecipeConditionParameter getParam() {
        return param;
    }

    @Nullable
    @Override
    public DefaultedList<RecipeConditionParameter> getParams() {
        return params;
    }

    @NotNull
    @Override
    public RecipeCondition getCondition() {
        return condition;
    }

    @Override
    public @NotNull Recipe<?> getRecipe() {
        return recipe;
    }

    @Override
    public boolean isNegated() {
        return negated;
    }
}
