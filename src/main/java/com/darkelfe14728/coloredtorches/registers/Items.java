package com.darkelfe14728.coloredtorches.registers;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Items registry.
 * 
 * @author Julien Rosset
 */
@Mod.EventBusSubscriber
public class Items
{
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		
	}

	private static void registerItem(RegistryEvent.Register<Item> event, Item item)
	{
		event.getRegistry().register(item);
	}
	@SideOnly(Side.CLIENT)
	public static void registerModels()
	{}
}
