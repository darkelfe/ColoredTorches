package com.darkelfe14728.coloredtorches.config;

import com.darkelfe14728.coloredtorches.log.LogHelper;
import com.darkelfe14728.coloredtorches.utils.Range;

/**
 * Category : /crafting  
 * 
 * @author Julien Rosset
 */
public class CraftingCategory 
	implements ICategory 
{
	public WashingCategory washing = new WashingCategory();
	
	public Range craftRange = new Range(8);
	
	@Override
	public String getName()
	{
		return "crafting";
	}

	@Override
	public void load(ImprovedConfiguration config)
	{
		LogHelper.info("Category \"" + getName() + "\"");
		LogHelper.startIndent();
		
		LogHelper.info("Loading sub-categories");
		LogHelper.startIndent();
		config.loadCategory(washing);
		LogHelper.stopIndent();
		
		LogHelper.info("craftRange");
		this.craftRange = config.getRange("craftRange", config.currentCategory, this.craftRange, 1, 64, "Number of colored torches craft by on 'crafting item'");
		LogHelper.stopIndent();
	}
	
}
