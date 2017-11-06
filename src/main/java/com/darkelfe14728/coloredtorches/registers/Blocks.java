package com.darkelfe14728.coloredtorches.registers;

import com.darkelfe14728.coloredtorches.torch.TorchBlock;
import com.darkelfe14728.coloredtorches.torch.TorchItem;
import com.darkelfe14728.coloredtorches.torch.TorchTileEntity;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Blocks registry.
 * 
 * @author Julien Rosset
 */
@Mod.EventBusSubscriber
@ObjectHolder("coloredtorches")
public class Blocks
{
	@ObjectHolder("torch")
	public static final TorchBlock torch = null;
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event)
	{
		registerBlockAndTE(event, new TorchBlock(), TorchTileEntity.class);
	}
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event)
	{
		registerItemBlock(event, new TorchItem(torch));
	}
	
	private static void registerBlock(RegistryEvent.Register<Block> event, Block block)
	{
		event.getRegistry().register(block);
	}
	private static void registerBlockAndTE(RegistryEvent.Register<Block> event, Block block, Class<? extends TileEntity> te)
	{
		registerBlock(event, block);
		GameRegistry.registerTileEntity(te, block.getRegistryName().toString());
	}

	private static void registerItemBlock(RegistryEvent.Register<Item> event, ItemBlock item)
	{
		event.getRegistry().register(item);
	}
	@SideOnly(Side.CLIENT)
	public static void registerModels()
	{
		torch.registerModels();
	}
}
