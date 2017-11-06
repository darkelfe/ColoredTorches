package com.darkelfe14728.coloredtorches.torch;


import java.util.List;

import javax.annotation.Nullable;

import com.darkelfe14728.coloredtorches.config.ModConfig;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The colored torch item.
 * 
 * @author Julien Rosset
 */
public class TorchItem
	extends ItemBlock
{
	public TorchItem(Block block)
	{
		super(block);
		this.setRegistryName(this.block.getRegistryName());
		
		setHasSubtypes(true);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(!this.isInCreativeTab(tab))
        	return;
        
    	for(Integer colorID : ModConfig.instance.colors.colors.keySet())
    		items.add(new ItemStack(this, 1, colorID));
    }
	@SideOnly(Side.CLIENT)
	public void registerModels()
	{
		ModelResourceLocation loc = new ModelResourceLocation(this.getRegistryName(), "inventory");
		for(Integer colorID : ModConfig.instance.colors.colors.keySet())
			ModelLoader.setCustomModelResourceLocation(this, colorID, loc);
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		return super.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + stack.getMetadata();
    }
	@Override
	@SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag)
    {
        super.addInformation(stack, world, tooltip, flag);
        
        String color_str = I18n.format("colors.color." + stack.getItemDamage());
        tooltip.add(I18n.format("interface.color", color_str));
    }
}
