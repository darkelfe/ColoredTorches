package coloredtorches.config;

import coloredtorches.log.LogHelper;
import coloredtorches.utils.Range;

/**
 * Category : /crafting/washing  
 * 
 * @author Julien Rosset
 */
public class WashingCategory 
	implements ICategory 
{
	public WashingObjectCategory bottle = new WashingObjectCategory("bottle", new Range(1,  8));
	public WashingObjectCategory bucket = new WashingObjectCategory("bucket", new Range(9, 64));
	
	public boolean washOnRightClick = false;
	
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
		
		LogHelper.info("Loading sub-categories");
		LogHelper.startIndent();
		config.loadCategory(bottle);
		config.loadCategory(bucket);
		LogHelper.stopIndent();

		LogHelper.info("washOnRightClick");
		this.washOnRightClick = config.getBoolean("washOnRightClick", config.currentCategory, this.washOnRightClick, "True if right-click on water with a colored torches washed it");
		LogHelper.stopIndent();
	}
}
