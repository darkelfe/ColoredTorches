package com.darkelfe14728.coloredtorches.config;

import java.util.HashMap;
import java.util.Map;

import com.darkelfe14728.coloredtorches.log.LogHelper;

import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;

/**
 * Category : /colors 
 * 
 * @author Julien Rosset
 */
public class ColorsCategory
	implements ICategory 
{
	public Map<String , ColorsObjectCategory> colors     = new HashMap<String , ColorsObjectCategory>();
	public Map<Integer, ColorsObjectCategory> colorsMeta = new HashMap<Integer, ColorsObjectCategory>();
	
	@Override
	public String getName() 
	{
		return "colors";
	}

	@Override
	public void load(ImprovedConfiguration config)
	{
		LogHelper.info("Category \"" + getName() + "\"");
		LogHelper.startIndent();
				
		if(!config.existCategory())
		{
			LogHelper.info("Missing category: providing a default one");
			
			createDefault(config);
			return;
		}
		
		int metadata = 0;
		
		LogHelper.info("Loading children");
		LogHelper.startIndent();
		for(String category : config.getSubCategories())
		{
			LogHelper.info("Child " + category);
			LogHelper.startIndent();
			
			if(colors.containsKey(category))
			{
				LogHelper.warn("Configuration for color " + category + " : already existing, skipping");
				continue;
			}
			
			ColorsObjectCategory color = new ColorsObjectCategory(category, metadata);
			config.loadCategory(color);
			
			if(!color.isValid())
			{
				LogHelper.warn("Configuration for color " + color.getId() + " : invalid related config, skipping");
				continue;
			}
			LogHelper.stopIndent();
			
			metadata++;
			addColor(color);
		}
		LogHelper.stopIndent();
	}
	public void createDefault(ImprovedConfiguration config)
	{
		for(EnumDyeColor dye : EnumDyeColor.values())
		{
			ColorsObjectCategory color = new ColorsObjectCategory(
				dye.getUnlocalizedName(), 
				dye.getMetadata(), 
				new ItemStack(Items.DYE, 1, dye.getDyeDamage())
			);
			config.loadCategory(color);
			
			if(!color.isValid())
			{
				LogHelper.warn("Configuration for color " + dye.getUnlocalizedName() + " : invalid related config, skipping");
				continue;
			}

			addColor(color);
		}
	}
	
	protected void addColor(ColorsObjectCategory color)
	{
		colors.put(color.getId(), color);
		colorsMeta.put(color.getMetadata(), color);
	}
}
