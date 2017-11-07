/**
 * 
 */
package com.darkelfe14728.coloredtorches.torch;

import javax.annotation.Nullable;

import com.darkelfe14728.coloredtorches.config.ColorsObjectCategory;
import com.darkelfe14728.coloredtorches.config.ModConfig;
import com.darkelfe14728.coloredtorches.log.LogHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * The colored torch block's attach informations.
 * 
 * @author Julien Rosset
 */
public class TorchTileEntity 
	extends TileEntity 
{
	private static final String KEY_COLOR = "color";
	
	private String color;

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.color = compound.getString(KEY_COLOR);
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		compound.setString(KEY_COLOR, this.color);
		
		return compound;
	}
	
	/*
	 * Correct synchronization between client and server tile entity.
	 */
	@Override
	@Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }
	@Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }
	
	public String getColorID()
	{
		return this.color;
	}
	public ColorsObjectCategory getColor()
	{
		return ModConfig.instance.colors.colors.get(getColorID());
	}
	public void setColorID(String id)
	{
		this.color = id;
	}
	public void setColor(ColorsObjectCategory color)
	{
		setColorID(color.getId());
	}
}
