package com.darkelfe14728.coloredtorches;

import java.awt.Color;

import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.world.World;

/**
 * Particles for colored flame.
 * 
 * @author Julien Rosset
 */
public class ParticleColoredFlame
	extends ParticleFlame
{
	/**
	 * Create new particles.
	 * 
	 * @param color		The particles/flame color
	 * @param world		The world to display particles.
	 * @param x			X coordinate for display.
	 * @param y			Y coordinate for display.
	 * @param z			Z coordinate for display.
	 * @param velX		X initial velocity for particles.
	 * @param velY		Y initial velocity for particles.
	 * @param velZ		Z initial velocity for particles.
	 */
	public ParticleColoredFlame(Color color, World world, double x,	double y, double z,
								double velX, double velY, double velZ)
	{
		super(world, x, y, z, velX,	velY, velZ);
		
		//this.particleAlpha = color.getAlpha() / 255;
		this.particleRed   = (float)color.getRed()   / 255;
		this.particleGreen = (float)color.getGreen() / 255;
		this.particleBlue  = (float)color.getBlue()  / 255; 
	}
}
