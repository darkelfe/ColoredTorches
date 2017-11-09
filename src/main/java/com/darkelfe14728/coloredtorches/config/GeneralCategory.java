package com.darkelfe14728.coloredtorches.config;

import java.util.stream.Stream;

import com.darkelfe14728.coloredtorches.log.LogHelper;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import net.minecraft.item.EnumDyeColor;

/**
 * Category : /general 
 * 
 * @author Julien Rosset
 */
public class GeneralCategory
	implements ICategory 
{
	protected BiMap<String, Integer> colors = HashBiMap.create();
	protected boolean canWash = true;

	@Override
	public String getName()
	{
		return "general";
	}
	@Override
	public void load(ImprovedConfiguration config)
	{
		LogHelper.info("colors");
		LogHelper.startIndent();
		String[] color_list = config.getStringList(
			"colors", 
			config.currentCategory, 
			Stream.of(EnumDyeColor.values()).map(dye -> dye.getUnlocalizedName()).toArray(String[]::new), 
			"List of all possible colors (String)"
		);
		
		int metadata = 0;
		for(String color : color_list)
		{
			LogHelper.info("Color : " + color);
			this.colors.put(color.toLowerCase(), metadata++);
		}
		LogHelper.stopIndent();
		
		LogHelper.info("canWash");
		LogHelper.startIndent();
		this.canWash = config.getBoolean(
			"canWash", 
			config.currentCategory, 
			this.canWash, 
			"True if colored torches can be washed to get regular torches back"
		);
		LogHelper.stopIndent();
	}

	public BiMap<String, Integer> getColors()
	{
		return this.colors;
	}
	public boolean canWash()
	{
		return this.canWash;
	}
}
