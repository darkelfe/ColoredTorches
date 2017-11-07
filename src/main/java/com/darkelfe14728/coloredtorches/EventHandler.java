package com.darkelfe14728.coloredtorches;

import com.darkelfe14728.coloredtorches.particles.ParticleColoredFlame;

import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * General event handler.
 * 
 * @author Julien Rosset
 */
@EventBusSubscriber
public class EventHandler
{
	@SubscribeEvent
	public static void onTextureStitchPre(TextureStitchEvent.Pre event)
	{
		ParticleColoredFlame.registerSprite(event.getMap());
	}
}
