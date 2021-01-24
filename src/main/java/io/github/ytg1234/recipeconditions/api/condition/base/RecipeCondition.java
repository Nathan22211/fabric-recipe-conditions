package io.github.ytg1234.recipeconditions.api.condition.base;

import net.minecraft.recipe.Recipe;

/**
 * Represents a recipe condition that can be registered.
 *
 * @author YTG1234
 */
@FunctionalInterface
public interface RecipeCondition {
    /**
     * Checks if the condition matches a certain parameter (also provided the recipe for context).
     * <p>
     * For exmaple, a condition that would match a mod ID if loaded would look like:
     * <code>
     * (modid, recipe) -> FabricLoader.getInstance().isModLoaded(modid.string())
     * </code>
     * </p>
     *
     * @param param the parameter to match against
     * @param recipe the recipe that this condition is ran on
     *
     * @return if the value matched
     */
    boolean check(RecipeConditionParameter param, Recipe<?> recipe);
}
