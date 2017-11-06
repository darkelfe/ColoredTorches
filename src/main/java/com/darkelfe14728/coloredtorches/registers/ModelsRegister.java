package com.darkelfe14728.coloredtorches.registers;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * General items model registering.
 * Need separate class because it is client side only.
 * 
 * @author Julien Rosset
 */
@Mod.EventBusSubscriber(Side.CLIENT)
public class ModelsRegister
{
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event)
	{
		Items.registerModels();
		Blocks.registerModels();
	}
}
