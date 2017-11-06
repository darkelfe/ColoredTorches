package com.darkelfe14728.coloredtorches.config;

import java.io.File;

import com.darkelfe14728.coloredtorches.log.LogHelper;

/**
 * Mod main configuration.
 * 
 * @author Julien Rosset
 */
public class ModConfig
{	
	public static ModConfig instance = null;
	
	public static boolean isLoaded()
	{
		return (ModConfig.instance != null);
	}
	public static void load(File file)
	{
		if(!isLoaded())
			ModConfig.instance = new ModConfig();
		
		ModConfig.instance.loadConfig(file);
	}

	
	private ImprovedConfiguration config = null;
	
	public CraftingCategory crafting = new CraftingCategory();
	public   ColorsCategory   colors = new   ColorsCategory();
	
	private ModConfig()
	{}
	private void loadConfig(File file)
	{
		LogHelper.startBlock("Configuration");
		LogHelper.info("File : " + file.getAbsolutePath());
		config = new ImprovedConfiguration(file);
		LogHelper.info("Loading defaults");
		config.load();
		
		LogHelper.info("Loading categories");
		LogHelper.startIndent();
		config.loadCategory(crafting);
		config.loadCategory(colors);
		LogHelper.stopIndent();
		LogHelper.stopBlock();
		
		config.save();
	}
}
