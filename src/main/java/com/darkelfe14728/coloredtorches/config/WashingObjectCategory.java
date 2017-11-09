/**
 * 
 */
package com.darkelfe14728.coloredtorches.config;

import com.darkelfe14728.coloredtorches.log.LogHelper;
import com.darkelfe14728.coloredtorches.utils.Range;

import net.minecraft.item.ItemStack;

/**
 * Category : /crafting/washing/<object>
 * 
 * @author Julien Rosset
 */
public class WashingObjectCategory
	implements ICategory 
{
	private final String name;
	
	public ItemStack washingItem = null;
	public boolean consumeContent = false;
	public boolean consumeContainer = false;
	public Range washRange = null;
	
	public WashingObjectCategory(String name, ItemStack washingItem, Range washRange) 
	{
		this.name = name;
		
		this.washingItem = washingItem;
		this.washRange = washRange;
	}

	@Override
	public String getName() 
	{
		return this.name;
	}

	@Override
	public void load(ImprovedConfiguration config)
	{
		LogHelper.info("washingItem");
		this.washingItem = config.getItem("washingItem", config.currentCategory, this.washingItem, "Item used to wash colored torches");
		
		LogHelper.info("consumeContent");
		this.consumeContent  = config.getBoolean("consumeContent" , config.currentCategory, this.consumeContent , "Is object content consumed in cleaning operation ?");
		LogHelper.info("consumeContainer");
		this.consumeContainer = config.getBoolean("consumeContainer", config.currentCategory, this.consumeContainer, "Is object container consumed in cleaning operation ?");

		LogHelper.info("washRange");
		this.washRange = config.getRange("washBy", config.currentCategory, this.washRange, 1, 8*64, "How many colored torches can by washed at the same time ?");
	}
}
