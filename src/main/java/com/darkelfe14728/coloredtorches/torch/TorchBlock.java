package com.darkelfe14728.coloredtorches.torch;

import java.util.Random;

import com.darkelfe14728.coloredtorches.config.ModConfig;
import com.darkelfe14728.coloredtorches.log.LogHelper;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * The colored torch block.
 * 
 * Except about color, it IS a (vanilla) torch. So extends it.
 * 
 * @author Julien Rosset
 */
public class TorchBlock
	extends BlockTorch 
{
	/**
	 * BlockState property about color.
	 * 
	 * <b>WARNING :</b> this is NOT only vanilla colors (like dye).
	 */
	public static final PropertyInteger COLOR = PropertyInteger.create("color", ModConfig.instance.colors.colors_min, ModConfig.instance.colors.colors_max);
	
	public TorchBlock()
	{
		super();
		setHardness(0.0F);
		setLightLevel(0.9375F);
		setSoundType(SoundType.WOOD);
		
		// Name
		final String name = "torch";
		setUnlocalizedName(name);
		setRegistryName(name);
		
		// State
		setDefaultState(this.blockState.getBaseState()
				.withProperty(FACING, EnumFacing.UP)
				.withProperty(COLOR, 0)
		);
	}
	
	@SideOnly(Side.CLIENT)
	public void registerModels()
	{
		((TorchItem)Item.getItemFromBlock(this)).registerModels();
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state)
	{
		return true;
	}
	@Override
	public TorchTileEntity createTileEntity(World world, IBlockState state)
	{
		return new TorchTileEntity();
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        double d3 = 0.22D;
        double d4 = 0.27D;

        if (enumfacing.getAxis().isHorizontal())
        {
            EnumFacing enumfacing1 = enumfacing.getOpposite();
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4 * (double)enumfacing1.getFrontOffsetX(), d1 + d3, d2 + d4 * (double)enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D, new int[0]);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4 * (double)enumfacing1.getFrontOffsetX(), d1 + d3, d2 + d4 * (double)enumfacing1.getFrontOffsetZ(), 0.0D, 0.0D, 0.0D, new int[0]);
        }
        else
        {
            worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
            worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
        }
    }
	
	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] {FACING, COLOR});
	}
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)	// getExtendedState ?
	{
		LogHelper.info("Get actual state at " + pos.toString() + " on " + LogHelper.side() + " side");
		LogHelper.startIndent();
		
		int colorProperty = 0;
		
		TileEntity te = worldIn instanceof ChunkCache ? ((ChunkCache)worldIn).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK) : worldIn.getTileEntity(pos);
		if(te instanceof TorchTileEntity)
		{
			LogHelper.info("TileEntity OK");
			TorchTileEntity tile = (TorchTileEntity)te;
			
			colorProperty = tile.getColorID();
			LogHelper.info("ColorID = " + colorProperty);
		}
		else
			LogHelper.info("TileEntity KO");
		
		LogHelper.stopIndent();
		return state.withProperty(COLOR, colorProperty);
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return state.getValue(COLOR);
	}
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state,	EntityLivingBase placer, ItemStack stack)
	{
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TorchTileEntity)
			((TorchTileEntity)te).setColorID(stack.getItemDamage());
	}
}
