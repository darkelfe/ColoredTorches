package com.darkelfe14728.coloredtorches.recipes;

import com.google.gson.JsonObject;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Item with automatic "wildcard" metadata.  
 * 
 * @author Julien Rosset
 */
public class WildcardItemIngredient
	implements IIngredientFactory 
{
	@Override
	public Ingredient parse(JsonContext context, JsonObject json) {
		json.addProperty("data", OreDictionary.WILDCARD_VALUE);
		
		ItemStack ingredient = CraftingHelper.getItemStack(json, context);
		return Ingredient.fromStacks(ingredient);
	}
}
