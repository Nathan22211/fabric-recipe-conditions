package io.github.ytg1234.recipeconditions.impl.condition;

import io.github.ytg1234.recipeconditions.RecipeCondsConstants;
import io.github.ytg1234.recipeconditions.api.RecipeConds;
import io.github.ytg1234.recipeconditions.api.condition.SingleCondition;
import io.github.ytg1234.recipeconditions.api.condition.base.RecipeCondition;
import io.github.ytg1234.recipeconditions.api.condition.base.RecipeConditionParameter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.util.collection.DefaultedList;

/**
 * Represents a Json entry that specifies a condition identifier
 * and either one parameter or an array of them.
 *
 * @author YTG1234
 */
public final class SingleConditionImpl implements SingleCondition {
    @NotNull
    private final RecipeCondition condition;
    @Nullable
    private final RecipeConditionParameter param;
    @Nullable
    private final DefaultedList<RecipeConditionParameter> params;

    private final boolean negated;

    public SingleConditionImpl(@NotNull RecipeCondition condition, @NotNull RecipeConditionParameter param) {
        this(condition, param, false);
    }

    public SingleConditionImpl(
            @NotNull RecipeCondition condition, @NotNull RecipeConditionParameter param, boolean negated
    ) {
        this.condition = condition;
        this.param = param;
        this.params = null;
        this.negated = negated;
    }

    public SingleConditionImpl(
            @NotNull RecipeCondition condition, @NotNull DefaultedList<RecipeConditionParameter> params
    ) {
        this(condition, params, false);
    }

    public SingleConditionImpl(
            @NotNull RecipeCondition condition, @NotNull DefaultedList<RecipeConditionParameter> params, boolean negated
    ) {
        this.condition = condition;
        this.params = params;
        this.param = null;
        this.negated = negated;
    }

    @Override
    public boolean check() {
        RecipeCondsConstants.LOGGER.debug("Checking condition " +
                                          RecipeConds.RECIPE_CONDITION.getId(condition) +
                                          " , SingleCondition inverted: " +
                                          negated);
        if (getParam() != null) {
            RecipeCondsConstants.LOGGER.debug("Param is not null, " + param.toString());
            return negated != condition.check(param);
        } else if (getParams() != null) {
            RecipeCondsConstants.LOGGER.debug("Params is not null, " + params.toString());
            return negated != getParams().stream().allMatch(condition::check);
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
    public boolean isNegated() {
        return negated;
    }
}
