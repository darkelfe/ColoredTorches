package coloredtorches.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import coloredtorches.log.LogHelper;
import coloredtorches.utils.Range;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * Improved configuration.
 * 
 * @author Julien Rosset
 */
public class ImprovedConfiguration 
	extends Configuration 
{
	/**
	 * The current category.
	 */
	public String currentCategory;
	
	public ImprovedConfiguration(File file)
	{
		super(file);
	}

	/**
	 * Load a (sub)category.
	 * 
	 * @param	cat		The category to load.
	 */
	public void loadCategory(ICategory cat)
	{
		final String oldCategory = this.currentCategory;
		if(this.currentCategory != null)
			this.currentCategory += CATEGORY_SPLITTER + cat.getName();
		else
			this.currentCategory = cat.getName();

		cat.load(this);
		
		this.currentCategory = oldCategory;
	}

	/**
	 * @return True if current category exists else False.
	 */
	public boolean existCategory()
	{
		LogHelper.info("Category ? " + this.currentCategory);
		return hasCategory(this.currentCategory);
	}
	
	public List<String> getSubCategories()
	{
		return getSubCategories(null);
	}
	public List<String> getSubCategories(String prefix)
	{
		String fullPrefix = currentCategory + CATEGORY_SPLITTER;
		int length = fullPrefix.length();
		if(prefix != null)
			fullPrefix += prefix;
		
		List<String> list = new ArrayList<String>();
		for(String category : getCategoryNames())
		{
			if(category.startsWith(fullPrefix))
				list.add(category.substring(length));
		}
		
		return list;
	}
	
	/**
     * Creates a Range property.
     *
     * @param	name			Name of the property.
     * @param	category		Category of the property.
     * @param	defaultValue	Default value of the property.
     * @param	minRangeValue	Minimum range value of the property.
     * @param	maxRangeValue	Maximum range value of the property.
     * @param	comment			A brief description what the property does.
     * 
     * @return	The value of the new Range property.
     */
    public Range getRange(String name, String category, Range defaultValue, int minRangeValue, int maxRangeValue, String comment)
    {
        return getRange(name, category, defaultValue, minRangeValue, maxRangeValue, comment, name);
    }
    /**
     * Creates a Range property.
     *
     * @param	name			Name of the property.
     * @param	category		Category of the property.
     * @param	defaultValue	Default value of the property.
     * @param	minRangeValue	Minimum range value of the property.
     * @param	maxRangeValue	Maximum range value of the property.
     * @param	comment			A brief description what the property does.
     * @param	langKey 		A language key used for localization of GUIs.
     * 
     * @return	The value of the new Range property.
     */
    public Range getRange(String name, String category, Range defaultValue, int minRangeValue, int maxRangeValue, String comment, String langKey)
    {
    	int[] defaultValueArray = new int[] { defaultValue.lowerBound, defaultValue.upperBound };
    	
    	Property prop = this.get(category, name, defaultValueArray);
        prop.setLanguageKey(langKey);
        prop.setComment(comment + " [range: " + minRangeValue + " ~ " + maxRangeValue + ", default: " + defaultValue + "]");
        prop.setMinValue(minRangeValue);
        prop.setMaxValue(maxRangeValue);
        prop.setIsListLengthFixed(true);
        prop.setMaxListLength(2);        
        
        int[] boundaries = prop.getIntList();
        
        Range range;
        switch(boundaries.length)
        {
        	case 0:
        		LogHelper.warn("Invalid config for " + category + CATEGORY_SPLITTER + name + " : no values provided, assume default value (" + defaultValue + ")");
        		range = defaultValue;
        		break;
        		
        	case 1:
        		range = new Range(boundaries[0]);
        		break;
        		
        	case 2:
        		range = new Range(boundaries[0], boundaries[1]);
        		break;
        		
        	default:
        		LogHelper.warn("Invalid config for " + category + CATEGORY_SPLITTER + name + " : too much values, two firsts values used");
        		range = new Range(boundaries[0], boundaries[0]);
        		break;
        }
        
        if(range.lowerBound < minRangeValue)
        	range.lowerBound = minRangeValue;
        if(range.lowerBound > maxRangeValue)
        	range.lowerBound = maxRangeValue;

        if(range.upperBound < minRangeValue)
        	range.upperBound = minRangeValue;
        if(range.upperBound > maxRangeValue)
        	range.upperBound = maxRangeValue;
        
        if(range.upperBound < range.lowerBound)
        {
        	int bound = range.lowerBound;
        	range.lowerBound = range.upperBound;
        	range.upperBound = bound;
        }
                
        return range;
    }

    /**
     * Creates a ItemStack property.
     * 
	 * <b>WARNING:</b> Ensure the referenced item is registered <i>before</i> ColoredTorches.
	 * 
     * @param	name			Name of the property.
     * @param	category		Category of the property.
     * @param	defaultValue	Default value of the property.
     * @param	comment			A brief description what the property does.
     * 
     * @return	The value of the new ItemStack property.
	 */
	public ItemStack getItem(String name, String category, ItemStack defaultValue, String comment)
	{
		return getItem(name, category, defaultValue, false, comment);
	}
    /**
     * Creates a ItemStack property.
     * 
	 * <b>WARNING:</b> Ensure the referenced item is registered <i>before</i> ColoredTorches.
	 * 
     * @param	name			Name of the property.
     * @param	category		Category of the property.
     * @param	defaultValue	Default value of the property.
     * @param	withQuantity	Deal with ItemStack size too ?
     * @param	comment			A brief description what the property does.
     * 
     * @return	The value of the new ItemStack property.
	 */
	public ItemStack getItem(String name, String category, ItemStack defaultValue, boolean withQuantity, String comment)
	{
		return getItem(name, category, defaultValue, withQuantity, comment, name);
	}
    /**
     * Creates a ItemStack property.
     * 
	 * <b>WARNING:</b> Ensure the referenced item is registered <i>before</i> ColoredTorches.
	 * 
     * @param	name			Name of the property.
     * @param	category		Category of the property.
     * @param	defaultValue	Default value of the property.
     * @param	withQuantity	Deal with ItemStack size too ?
     * @param	comment			A brief description what the property does.
     * @param	langKey 		A language key used for localization of GUIs.
     * 
     * @return	The value of the new ItemStack property.
	 */
	public ItemStack getItem(String name, String category, ItemStack defaultValue, boolean withQuantity, String comment, String langKey)
	{
		String defaultValueName = "";
		if(defaultValue != null)
		{
			defaultValueName = defaultValue.getItem().getRegistryName().toString();
			defaultValueName += ":" + defaultValue.getItemDamage();
			if(withQuantity)
				defaultValueName += "@" + defaultValue.getCount();
		}
		
		Property prop = this.get(category, name, defaultValueName);
        prop.setLanguageKey(langKey);
        prop.setComment(comment + " [format: <mod>:<item>[:<metadata>]" + (withQuantity ? "[@<quantity>]" : "")); 
        
		String itemstack_name = prop.getString();
		
		int pos_1 = itemstack_name.indexOf(':');
		if(pos_1 == -1)
		{
			LogHelper.error("Invalid config for " + category + CATEGORY_SPLITTER + name + " : invalid item, missing first ':'");
			return null;
		}
		
		String mod_id = itemstack_name.substring(0, pos_1);		
		String item_part = itemstack_name.substring(pos_1 + 1);
		int pos_2 = item_part.indexOf(':');
		
		String item_name = (pos_2 == -1 ? item_part : item_part.substring(0, pos_2));
		ResourceLocation location = new ResourceLocation(mod_id, item_name);
		
		if(!ForgeRegistries.ITEMS.containsKey(location))
		{
			LogHelper.error("Invalid config for " + category + CATEGORY_SPLITTER + name + " : invalid item, '" + location.toString() + "' is not registered");
			return null;
		}
		
		Item item = ForgeRegistries.ITEMS.getValue(location);
		if(item == null)
		{
			LogHelper.error("Invalid config for " + category + CATEGORY_SPLITTER + name + " : invalid item, '" + location.toString() + "' is registered, but with a bad item");
			return null;
		}

		int pos_3 = item_part.indexOf('@');
		
		int metadata = 0;
		if(pos_2 != -1)
		{
			try
			{				
				String item_meta;
				if(pos_3 == -1)
					item_meta = item_part.substring(pos_2 + 1);
				else
					item_meta = item_part.substring(pos_2 + 1, pos_3);
				
				metadata = Integer.parseInt(item_meta);
				if(!item.getHasSubtypes() && metadata != 0)
				{
					LogHelper.warn("Invalid config for " + category + CATEGORY_SPLITTER + name + " : item doesn't allow metadat, assume raw item");
					metadata = 0;
				}
			}
			catch(NumberFormatException e)
			{
				LogHelper.error("Invalid config for " + category + CATEGORY_SPLITTER + name + " : invalid metadata (not a number) for item");
				return null;
			}
		}
		
		int quantity = 1;
		if(withQuantity && pos_3 != -1)
		{
			try
			{
				quantity = Integer.parseInt(item_part.substring(pos_3 + 1));
				if(quantity < 1)
				{
					LogHelper.warn("Invalid config for " + category + CATEGORY_SPLITTER + name + " : invalid quantity for item, assume 1");
					quantity = 1;
				}
			}
			catch(NumberFormatException e)
			{
				LogHelper.error("Invalid config for " + category + CATEGORY_SPLITTER + name + " : invalid quantity (not a number) for item");
				return null;
			}
		}
		
		return new ItemStack(item, quantity, metadata);
	}
}
