/**
 * 
 */
package coloredtorches.config;

import coloredtorches.log.LogHelper;
import coloredtorches.utils.Range;

/**
 * Category : /crafting/washing/<object>
 * 
 * @author Julien Rosset
 */
public class WashingObjectCategory
	implements ICategory 
{
	private final String name;
	
	public boolean canWash = true;
	public boolean consumeWater = true;
	public boolean consumeObject = false;
	public Range washBy = null;
	
	public WashingObjectCategory(String name, Range washBy) 
	{
		this.name = name;
		this.washBy = washBy;
	}

	@Override
	public String getName() 
	{
		return this.name;
	}

	@Override
	public void load(ImprovedConfiguration config)
	{
		LogHelper.info("Category \"" + getName() + "\"");
		LogHelper.startIndent();
		
		LogHelper.info("canWash");
		this.canWash = config.getBoolean("canWash", config.currentCategory, this.canWash, "Can '" + getName() + "' be used to clean colored torches ?");
		
		LogHelper.info("consumeWater");
		this.consumeWater  = config.getBoolean("consumeWater" , config.currentCategory, this.consumeWater , "Is water consumed in cleaning operation ?");
		LogHelper.info("consumeObject");
		this.consumeObject = config.getBoolean("consumeObject", config.currentCategory, this.consumeObject, "Is object (" + getName() + ") consumed in cleaning operation ?");

		LogHelper.info("washBy");
		this.washBy = config.getRange("washBy", config.currentCategory, this.washBy, 1, 64, "How many colored torches can by washed at the same time ?");

		LogHelper.stopIndent();
	}
}
