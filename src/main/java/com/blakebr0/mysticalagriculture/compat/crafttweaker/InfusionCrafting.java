package com.blakebr0.mysticalagriculture.compat.crafttweaker;

import com.blakebr0.cucumber.helper.RecipeHelper;
import com.blakebr0.mysticalagriculture.crafting.recipe.InfusionRecipe;
import com.blakebr0.mysticalagriculture.init.ModRecipeTypes;
import com.blamejared.crafttweaker.api.CraftTweakerAPI;
import com.blamejared.crafttweaker.api.action.base.IRuntimeAction;
import com.blamejared.crafttweaker.api.annotation.ZenRegister;
import com.blamejared.crafttweaker.api.ingredient.IIngredient;
import com.blamejared.crafttweaker.api.item.IItemStack;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import org.openzen.zencode.java.ZenCodeType;

import java.util.HashMap;

@ZenCodeType.Name("mods.mysticalagriculture.InfusionCrafting")
@ZenRegister
public final class InfusionCrafting {
    @ZenCodeType.Method
    public static void addRecipe(String id, IItemStack output, IIngredient[] inputs) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                var recipe = new InfusionRecipe(new ResourceLocation("crafttweaker", id), toIngredientsList(inputs), output.getInternal());

                RecipeHelper.addRecipe(recipe);
            }

            @Override
            public String describe() {
                return "Adding Infusion Crafting recipe for " + output.getCommandString();
            }
        });
    }

    @ZenCodeType.Method
    public static void remove(IItemStack stack) {
        CraftTweakerAPI.apply(new IRuntimeAction() {
            @Override
            public void apply() {
                var recipes = RecipeHelper.getRecipes()
                        .getOrDefault(ModRecipeTypes.INFUSION.get(), new HashMap<>())
                        .values().stream()
                        .filter(r -> r.getResultItem().sameItem(stack.getInternal()))
                        .map(Recipe::getId)
                        .toList();

                recipes.forEach(r -> {
                    RecipeHelper.getRecipes().get(ModRecipeTypes.INFUSION.get()).remove(r);
                });
            }

            @Override
            public String describe() {
                return "Removing Infusion Crafting recipes for " + stack.getCommandString();
            }
        });
    }

    private static NonNullList<Ingredient> toIngredientsList(IIngredient... iingredients) {
        var ingredients = NonNullList.withSize(InfusionRecipe.RECIPE_SIZE, Ingredient.EMPTY);

        for (int i = 0; i < iingredients.length; i++) {
            ingredients.set(i, iingredients[i].asVanillaIngredient());
        }

        return ingredients;
    }
}
