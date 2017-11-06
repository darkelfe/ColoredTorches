package coloredtorches.config;

import java.util.HashMap;
import java.util.Map;

import coloredtorches.log.LogHelper;

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
	public Map<Integer, ColorsObjectCategory> colors = new HashMap<Integer, ColorsObjectCategory>();
	public int colors_min, colors_max;
	
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
		
		LogHelper.info("Loading children");
		LogHelper.startIndent();
		for(String category : config.getSubCategories(ColorsObjectCategory.NAME_PREFIX))
		{
			LogHelper.info("Child " + category);
			LogHelper.startIndent();
			
			int id = 0;
			try
			{
				id = Integer.parseInt(category.substring(ColorsObjectCategory.NAME_PREFIX.length()));
			}
			catch(NumberFormatException e)
			{
				LogHelper.error("Configuration for color \"" + category + "\" : invalid ID (integer), skipping");
				continue;
			}
			LogHelper.info("ID : " + id);
			
			if(colors.containsKey(id))
			{
				LogHelper.warn("Configuration for color " + id + " : duplicate ID, skipping");
				continue;
			}
			
			ColorsObjectCategory color = new ColorsObjectCategory(id);
			config.loadCategory(color);

			if(!color.isValid())
			{
				LogHelper.warn("Configuration for color " + id + " : invalid related config, skipping");
				continue;
			}
			LogHelper.stopIndent();
			
			addColor(id, color);
		}
		LogHelper.stopIndent();
	}
	public void createDefault(ImprovedConfiguration config)
	{
		for(EnumDyeColor dye : EnumDyeColor.values())
		{
			ColorsObjectCategory color = new ColorsObjectCategory(dye.getMetadata(), new ItemStack(Items.DYE, 1, dye.getDyeDamage()));
			config.loadCategory(color);
			
			if(!color.isValid())
			{
				LogHelper.warn("Configuration for color " + dye.getMetadata() + " : invalid related config, skipping");
				continue;
			}
			
			addColor(dye.getMetadata(), color);
		}
	}

	private void addColor(int id, ColorsObjectCategory color)
	{
		if(id < this.colors_min)
			this.colors_min = id;
		if(id > this.colors_max)
			this.colors_max = id;
		
		colors.put(id, color);
	}
}
