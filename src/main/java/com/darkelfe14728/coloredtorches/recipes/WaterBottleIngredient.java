package com.darkelfe14728.coloredtorches.recipes;

import com.google.gson.JsonObject;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

/**
 * Water bottle.
 * 	
 * @author Julien Rosset
 */
public class WaterBottleIngredient
	implements IIngredientFactory 
{
	@Override
	public Ingredient parse(JsonContext context, JsonObject json)
	{
		ItemStack bottle = new ItemStack(Items.POTIONITEM, 1, 0);		
		PotionUtils.addPotionToItemStack(bottle, PotionTypes.WATER);
		
		return Ingredient.fromStacks(bottle);
	}
}
