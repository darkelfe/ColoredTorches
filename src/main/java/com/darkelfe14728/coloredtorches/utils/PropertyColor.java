package com.darkelfe14728.coloredtorches.utils;

import java.util.Collection;

import com.darkelfe14728.coloredtorches.config.ModConfig;
import com.google.common.base.Optional;

import net.minecraft.block.properties.PropertyHelper;

/**
 * Block state property for colors.
 * 
 * @author Julien Rosset
 */
public class PropertyColor
	extends PropertyHelper<String> 
{
	public PropertyColor(String name) {
		super(name, String.class);
	}

	@Override
	public Collection<String> getAllowedValues()
	{
		return ModConfig.getInstance().getGeneral().getColors().keySet();
	}

	@Override
	public Optional<String> parseValue(String value)
	{
		return Optional.of(value);
	}

	@Override
	public String getName(String value)
	{
		return value;
	}

}
