package com.darkelfe14728.coloredtorches.recipes;

import com.darkelfe14728.coloredtorches.config.ModConfig;
import com.darkelfe14728.coloredtorches.registers.Blocks;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.IIngredientFactory;
import net.minecraftforge.common.crafting.JsonContext;

/**
 * Recipe Ingredient : colored torch.
 * 
 * @author Julien Rosset
 */
public class TorchIngredient
	implements IIngredientFactory 
{
	@Override
	public Ingredient parse(JsonContext context, JsonObject json)
	{
		String color = json.get("color").getAsString();
		
		if(!ModConfig.getInstance().getGeneral().getColors().containsKey(color))
			throw new JsonSyntaxException("Invalid color \"" + color + "\" for ColoredTorch ingredient");
		
		int metadata = ModConfig.getInstance().getGeneral().getColors().get(color);
		return Ingredient.fromStacks(new ItemStack(Blocks.torch, 1, metadata));
	}
}
