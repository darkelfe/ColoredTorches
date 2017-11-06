package coloredtorches.config;

import net.minecraft.item.ItemStack;
import coloredtorches.log.LogHelper;
import coloredtorches.utils.Range;

/**
 * Category : /colors/color_XXX
 * 
 * @author Julien Rosset
 */
public class ColorsObjectCategory 
	implements ICategory 
{
	public static final String NAME_PREFIX = "color_";
	
	public int id = -1;
	public Range craftBy = null;
	public ItemStack craftingItem = null;

	public ColorsObjectCategory(int ID)
	{
		this.id = ID;
	}
	public ColorsObjectCategory(int ID, ItemStack craftingItem)
	{
		this(ID);
		this.craftingItem = craftingItem;
	}
	
	@Override
	public String getName()
	{
		return NAME_PREFIX + this.id;
	}

	@Override
	public void load(ImprovedConfiguration config)
	{
		LogHelper.info("craftBy");
		if(config.hasKey(config.currentCategory, "craftBy"))
			this.craftBy = config.getRange("craftBy", config.currentCategory, ModConfig.instance.crafting.craftBy, 1, 64, "Number of colored torches craft with on 'crafting item'");
		else
		{
			this.craftBy = ModConfig.instance.crafting.craftBy;
		}
		
		LogHelper.info("craftingItem");
		this.craftingItem = config.getItem("craftingItem", config.currentCategory, this.craftingItem, "Item used to craft the colored torches");
	}
	
	public boolean isValid()
	{
		return (this.craftingItem != null);
	}
}
