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
	
	public Range craftBy = new Range(8);
	
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
		
		LogHelper.info("craftBy");
		this.craftBy = config.getRange("craftBy", config.currentCategory, this.craftBy, 1, 64, "Global number of colored torches craft with on 'crafting item'");
		LogHelper.stopIndent();
	}
	
}
