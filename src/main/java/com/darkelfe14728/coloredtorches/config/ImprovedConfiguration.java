package com.darkelfe14728.coloredtorches.config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.darkelfe14728.coloredtorches.log.LogHelper;

import net.minecraftforge.common.config.Configuration;

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
}
