/**
 * 
 */
package com.darkelfe14728.coloredtorches.torch;

import javax.annotation.Nullable;

import com.darkelfe14728.coloredtorches.config.ModConfig;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
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

	/**
	 * After creation, color property (so the whole TileEntity) will *not* change.
	 * So refresh only if the block itself change.
	 */
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
	{
		return (oldState.getBlock() != newState.getBlock());
	}
	
	/*
	 * TileEntity will not update *except* at creation : color must be send to server and all other clients
	 */
	/**
	 * We have an update packet (for color transmission).
	 */
	@Override
	@Nullable
    public SPacketUpdateTileEntity getUpdatePacket()
    {
        return new SPacketUpdateTileEntity(this.pos, 0, this.getUpdateTag());
    }
	/**
	 * Sent data also contains color (not only "internal" data of TileEntity).
	 */
	@Override
    public NBTTagCompound getUpdateTag()
    {
        return this.writeToNBT(new NBTTagCompound());
    }
	/**
	 * When received update packet (with color).
	 * 
	 * <p>NOTE : {@link #handleUpdateTag(NBTTagCompound)} is always defined to set TileEntity through {@link #readFromNBT(NBTTagCompound)}.</p> 
	 */
	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet)
	{
		this.handleUpdateTag(packet.getNbtCompound());
	}
	
	public String getColorName()
	{
		return this.color;
	}
	public Integer getColorMetadata()
	{
		return ModConfig.getInstance().getGeneral().getColors().get(this.color);
	}
	public void setColor(String colorName)
	{
		this.color = colorName;
	}
	public void setColor(Integer colorMetadata)
	{
		this.setColor(ModConfig.getInstance().getGeneral().getColors().inverse().get(colorMetadata));
	}
}
