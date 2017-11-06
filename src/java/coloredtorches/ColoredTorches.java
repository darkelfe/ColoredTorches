package coloredtorches;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import coloredtorches.config.ModConfig;
import coloredtorches.log.LogHelper;
import coloredtorches.proxy.CommonProxy;
import coloredtorches.registers.Blocks;
import coloredtorches.registers.Items;

/**
 * Mod entry point.
 * 
 * This mod add "colored" torches. Useful to create colored ways in caverns.
 * 
 * @author Julien Rosset
 */
@Mod(
	modid                     = ColoredTorches.MOD_ID,
	name                      = ColoredTorches.MOD_NAME,
	version                   = ColoredTorches.MOD_VERSION,
	acceptedMinecraftVersions = "[1.11.2]",
	canBeDeactivated          = true
)
public class ColoredTorches
{
	/**
	 * Mod unique ID. Also used has identifier in Forge/Minecraft.
	 */
	public static final String MOD_ID = "coloredtorches";
	/**
	 * Mod name.
	 */
	public static final String MOD_NAME = "Colored Torches";
	/**
	 * Mod version.
	 */
	public static final String MOD_VERSION = "1.1.0";
	
	@Mod.Instance(MOD_ID)
	public static ColoredTorches instance;
	
	/**
	 * Current proxy.
	 */
	@SidedProxy(
		clientSide = "coloredtorches.proxy.ClientProxy",
		serverSide = "coloredtorches.proxy.CommonProxy"
	)
	public static CommonProxy proxy;
		
	/**
	 * At pre-initialization.
	 * 
	 * @param event Pre-initialization event.
	 */
	@Mod.EventHandler
	public void preInit  (FMLPreInitializationEvent  event)
	{
		LogHelper.info("Hello !");
    	

		LogHelper.info("Loading configuration");
    	ModConfig.load(event.getSuggestedConfigurationFile());
		LogHelper.info("Configuration OK");
    	
    	Items.init();
    	Blocks.init();
	}
	/**
	 * At initialization.
	 * 
	 * @param event Initialization event.
	 */
	@Mod.EventHandler
	public void init     (FMLInitializationEvent     event)
	{}
	/**
	 * At post-initialization.
	 * 
	 * @param event Post-initialization event.
	 */
	@Mod.EventHandler
	public void postInit (FMLPostInitializationEvent event)
	{
		
	}
}
