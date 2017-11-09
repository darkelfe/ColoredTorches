package com.darkelfe14728.coloredtorches.config;

import com.darkelfe14728.coloredtorches.log.LogHelper;
import com.darkelfe14728.coloredtorches.utils.Range;

import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;

/**
 * Category : /crafting/washing  
 * 
 * @author Julien Rosset
 */
public class WashingCategory 
	implements ICategory 
{
	public boolean canWash = true;
	public WashingObjectCategory bottle = new WashingObjectCategory("bottle", PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER), new Range(1, 32));
	public WashingObjectCategory bucket = new WashingObjectCategory("bucket", new ItemStack(Items.WATER_BUCKET), new Range(32, 64));
	
	@Override
	public String getName() 
	{
		return "washing";
	}

	@Override
	public void load(ImprovedConfiguration config)
	{
		LogHelper.info("Category \"" + getName() + "\"");
		LogHelper.startIndent();
		
		LogHelper.info("canWash");
		this.canWash = config.getBoolean("canWash", config.currentCategory, this.canWash, "True if colored torches can be washed to get regular torches back");
				
		LogHelper.info("Loading sub-categories");
		LogHelper.startIndent();
		config.loadCategory(bottle);
		config.loadCategory(bucket);
		LogHelper.stopIndent();

		LogHelper.stopIndent();
	}
}
