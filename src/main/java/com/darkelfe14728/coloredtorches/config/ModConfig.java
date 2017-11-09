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
	private static ModConfig instance = null;
	
	protected ImprovedConfiguration config = null;
	protected GeneralCategory general = new GeneralCategory();
	
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
	
	public static ModConfig getInstance()
	{
		return instance;
	}
	
	private ModConfig()
	{}
	private void loadConfig(File file)
	{
		LogHelper.startBlock("Configuration");
		
		LogHelper.info("File : " + file.getAbsolutePath());
		config = new ImprovedConfiguration(file);
		config.load();
		
		LogHelper.info("Loading categories");
		LogHelper.startIndent();
		config.loadCategory(general);
		LogHelper.stopIndent();
		
		config.save();
		LogHelper.stopBlock();
	}

	public GeneralCategory getGeneral()
	{
		return this.general;
	}
}
