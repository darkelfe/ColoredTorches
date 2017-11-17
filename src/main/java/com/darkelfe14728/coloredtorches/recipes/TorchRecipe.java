package com.darkelfe14728.coloredtorches.recipes;

import com.darkelfe14728.coloredtorches.config.ModConfig;
import com.darkelfe14728.coloredtorches.registers.Blocks;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;

/**
 * Recipe Factory : ColoredTorch recipe.
 * 
 * @author Julien Rosset
 */
public class TorchRecipe
	implements IRecipeFactory
{
	@Override
	public IRecipe parse(JsonContext context, JsonObject json)
	{
        String group = JsonUtils.getString(json, "group", "");
        
		NonNullList<Ingredient> colorants = NonNullList.create();
		for(JsonElement elem : JsonUtils.getJsonArray(json, "colorants"))
			colorants.add(CraftingHelper.getIngredient(elem, context));
		
		int nbTorch = JsonUtils.getInt(json, "torchQuantity", 1);
		
		JsonObject result = JsonUtils.getJsonObject(json, "result");
		if(result == null)
			throw new JsonSyntaxException("Missing result part");

		float ratio = JsonUtils.getFloat(result, "ratio", 1);
		
		String color = JsonUtils.getString(result, "color");
		if(!ModConfig.getInstance().getGeneral().getColors().containsKey(color))
			throw new JsonSyntaxException("Invalid result color");
		int metadata = ModConfig.getInstance().getGeneral().getColors().get(color);
		
		for(int curr = 0; curr < nbTorch; curr++)
			colorants.add(Ingredient.fromStacks(new ItemStack(net.minecraft.init.Blocks.TORCH)));
		
		return new ShapelessRecipes(group, new ItemStack(Blocks.torch, (int)(ratio * nbTorch), metadata), colorants);
	}

}
