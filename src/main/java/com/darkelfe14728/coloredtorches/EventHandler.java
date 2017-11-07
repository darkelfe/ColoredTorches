package com.darkelfe14728.coloredtorches;

import com.darkelfe14728.coloredtorches.particles.ParticleColoredFlame;
import com.darkelfe14728.coloredtorches.registers.Blocks;
import com.darkelfe14728.coloredtorches.registers.Items;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * General event handler.
 * 
 * @author Julien Rosset
 */
@Mod.EventBusSubscriber
public class EventHandler
{
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onTextureStitchPre(TextureStitchEvent.Pre event)
	{
		ParticleColoredFlame.registerSprite(event.getMap());
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onModelRegistration(ModelRegistryEvent event)
	{
		Items.registerModels();
		Blocks.registerModels();
	}
}
