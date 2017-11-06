/**
 * 
 */
package coloredtorches.torch;

import javax.annotation.Nullable;

import coloredtorches.config.ColorsObjectCategory;
import coloredtorches.config.ModConfig;
import coloredtorches.log.LogHelper;
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
	private static final String KEY_COLOR_ID = "color_id";
	
	private int colorID;

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		this.colorID = compound.getInteger(KEY_COLOR_ID);
		LogHelper.info("Read TE (" + this.getPos().toString() + ") on " + LogHelper.side() + " side : colorID = " + this.colorID);
	}
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		
		LogHelper.info("Write TE (" + this.getPos().toString() + ") on " + LogHelper.side() + " side : colorID = " + this.colorID);
		compound.setInteger(KEY_COLOR_ID, this.colorID);
		
		return compound;
	}
	
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
	
	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState)
	{
		LogHelper.info("Check TE in " + pos);
		LogHelper.startIndent();
		
		LogHelper.info("Old state : " + oldState);
		LogHelper.info("New state : " + newState);
		
		LogHelper.stopIndent();
		return super.shouldRefresh(world, pos, oldState, newState);
	}
	
	public int getColorID()
	{
		return this.colorID;
	}
	public ColorsObjectCategory getColor()
	{
		return ModConfig.instance.colors.colors.get(getColorID());
	}
	public void setColorID(int id)
	{
		this.colorID = id;
	}
	public void setColor(ColorsObjectCategory color)
	{
		setColorID(color.id);
	}
}
