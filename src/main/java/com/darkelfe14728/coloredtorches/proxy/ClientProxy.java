package com.darkelfe14728.coloredtorches.proxy;

import com.darkelfe14728.coloredtorches.registers.Particles;

/**
 * Proxy for client side.
 * 
 * @author Julien Rosset
 */
public class ClientProxy
	extends CommonProxy
{
	@Override
	public void registerParticles()
	{
		Particles.registerParticles();
	}
}
