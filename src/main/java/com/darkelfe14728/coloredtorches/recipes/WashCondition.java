package com.darkelfe14728.coloredtorches.recipes;

import java.util.function.BooleanSupplier;

import com.darkelfe14728.coloredtorches.config.ModConfig;
import com.google.gson.JsonObject;

import net.minecraftforge.common.crafting.IConditionFactory;
import net.minecraftforge.common.crafting.JsonContext;

/**
 * Recipe Condition : torch washing enabled ?
 * 
 * @author Julien Rosset
 */
public class WashCondition
	implements IConditionFactory 
{
	@Override
	public BooleanSupplier parse(JsonContext context, JsonObject json)
	{
		return () -> ModConfig.getInstance().getGeneral().canWash();
	}
}
