package com.darkelfe14728.coloredtorches.torch;

import java.util.Random;

import com.darkelfe14728.coloredtorches.config.ModConfig;
import com.darkelfe14728.coloredtorches.log.LogHelper;
import com.darkelfe14728.coloredtorches.utils.PropertyColor;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
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
	public static final PropertyColor COLOR = new PropertyColor("color");
	
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
				.withProperty(COLOR, ModConfig.instance.colors.colors.keySet().iterator().next())	// First color
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
		String colorProperty = null;
		
		TileEntity te = worldIn instanceof ChunkCache ? ((ChunkCache)worldIn).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK) : worldIn.getTileEntity(pos);
		if(te instanceof TorchTileEntity)
		{
			TorchTileEntity tile = (TorchTileEntity)te;			
			colorProperty = tile.getColorID();
		}
		
		LogHelper.stopIndent();
		return state.withProperty(COLOR, colorProperty);
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return ModConfig.instance.colors.colors.get(state.getValue(COLOR)).getMetadata();
	}
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state,	EntityLivingBase placer, ItemStack stack)
	{
		TileEntity te = worldIn.getTileEntity(pos);
		if(te instanceof TorchTileEntity)
			((TorchTileEntity)te).setColor(ModConfig.instance.colors.colorsMeta.get(stack.getItemDamage()));
	}
}
