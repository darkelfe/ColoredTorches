package com.darkelfe14728.coloredtorches.registers;

import com.darkelfe14728.coloredtorches.torch.TorchBlock;
import com.darkelfe14728.coloredtorches.torch.TorchItem;
import com.darkelfe14728.coloredtorches.torch.TorchTileEntity;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Blocks registry.
 * 
 * @author Julien Rosset
 */
public class Blocks
{
	public static TorchBlock torch;
	
	public static void init()
	{
		torch = new TorchBlock();
		register(torch, new TorchItem(torch), TorchTileEntity.class);
	}
	
	private static <T extends Block> T register(T block, ItemBlock itemBlock) {
		//GameRegistry.register(block);
		//GameRegistry.register(itemBlock);

		return block;
	}
	private static <T extends Block> T register(T block) {
		ItemBlock itemBlock = new ItemBlock(block);
		itemBlock.setRegistryName(block.getRegistryName());
		return register(block, itemBlock);
	}
	private static <T extends Block> T register(T block, ItemBlock itemBlock, Class<? extends TileEntity> tileEntity) {
		T ret = register(block, itemBlock);
		GameRegistry.registerTileEntity(tileEntity, block.getRegistryName().toString());

		return ret;
	}
}
