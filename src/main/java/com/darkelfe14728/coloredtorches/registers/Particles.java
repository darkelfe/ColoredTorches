package com.darkelfe14728.coloredtorches.registers;

import com.darkelfe14728.coloredtorches.particles.ParticleColoredFlame;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Registry about new particles.
 * 
 * @author Julien Rosset
 */
public class Particles
{
	private static final Class<?>[] SIGNATURE = {String.class, int.class, boolean.class};
	private static int enumId = 50;
	
	public static EnumParticleTypes COLORED_FLAME;
	
	@SideOnly(Side.CLIENT)
	public static void registerParticles()
	{
		COLORED_FLAME = registerParticle("COLORED_FLAME", "coloredFlame", new ParticleColoredFlame.Factory());
	}
	private static EnumParticleTypes registerParticle(String enumName, String particleName, IParticleFactory particleFactory)
	{
		int id = enumId++;
		
		Object[] values = {"coloredFlame", id, false};
		EnumParticleTypes enumValue = EnumHelper.addEnum(EnumParticleTypes.class, "COLORED_FLAME", SIGNATURE, values);
		
		Minecraft.getMinecraft().effectRenderer.registerParticle(id, particleFactory);
		
		return enumValue;
	}
}
