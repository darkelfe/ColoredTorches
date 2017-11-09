package com.darkelfe14728.coloredtorches.particles;

import java.util.HashMap;
import java.util.Map;

import com.darkelfe14728.coloredtorches.config.ModConfig;
import com.google.common.collect.BiMap;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFlame;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Particles for colored flame.
 * 
 * @author Julien Rosset
 */
public class ParticleColoredFlame
	extends ParticleFlame
{
	private static Map<Integer, TextureAtlasSprite> textures = new HashMap<Integer, TextureAtlasSprite>();
	
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
	public ParticleColoredFlame(
			World world,
			double x,	double y, double z,
			double velX, double velY, double velZ,
			int colorMetadata
	)
	{
		super(world, x, y, z, velX,	velY, velZ);
		setParticleTexture(textures.get(colorMetadata));
	}

	public static void registerSprite(TextureMap map)
	{
		for(BiMap.Entry<String, Integer> color : ModConfig.getInstance().getGeneral().getColors().entrySet())
		{
			textures.put(
				color.getValue(), 
				map.registerSprite(
					new ResourceLocation("coloredtorches:particle/torch_flame_" + color.getKey())
				)
			);
		}
	}
	
	@Override
    @SideOnly(Side.CLIENT)
	public int getFXLayer()
    {
        return 1;
    }
	@Override
	public void setParticleTextureIndex(int particleTextureIndex)
	{ /* Inhibit exception when setting texture index in ParticleFlame constructor */ }
	
	public static class Factory
    	implements IParticleFactory
    {
        public Particle createParticle(
        		int particleID, World world, 
        		double xCoord, double yCoord, double zCoord, 
        		double xSpeed, double ySpeed, double zSpeed,
        		int... extraArgs
        )
        {
        	final int colorMetadata = extraArgs[0];
            return new ParticleColoredFlame(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed, colorMetadata);
        }
    }
}
