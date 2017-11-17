package com.darkelfe14728.coloredtorches.recipes;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

/**
 * Dye ingredient with named color (and not integer metadata).
 * 
 * @author Julien Rosset
 */
public class DyeIngredient
	implements IIngredientFactory 
{
	@Override
	public Ingredient parse(JsonContext context, JsonObject json) {
		String color = JsonUtils.getString(json, "color");
		
		int metadata = -1;
		for(EnumDyeColor dye : EnumDyeColor.values())
		{
			if(dye.getName().equalsIgnoreCase(color))
			{
				metadata = dye.getDyeDamage();
				break;
			}
		}
		if(metadata == -1)
			throw new JsonSyntaxException("Invalid dye color \"" + color + "\"");
		
		return Ingredient.fromStacks(new ItemStack(Items.DYE, 1, metadata));
	}
}
