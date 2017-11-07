package com.darkelfe14728.coloredtorches.torch;

import java.util.Random;

import com.darkelfe14728.coloredtorches.config.ModConfig;
import com.darkelfe14728.coloredtorches.log.LogHelper;
import com.darkelfe14728.coloredtorches.registers.Particles;
import com.darkelfe14728.coloredtorches.utils.PropertyColor;

import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
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
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
        EnumFacing face = (EnumFacing)state.getValue(FACING);
        double xCoord = (double)pos.getX() + 0.5D;
        double yCoord = (double)pos.getY() + 0.7D;
        double zCoord = (double)pos.getZ() + 0.5D;
        
        String color = state.getValue(COLOR);
        int metadata = ModConfig.instance.colors.colors.get(color).getMetadata();
        
        int[] args = {metadata};

        if (face.getAxis().isHorizontal())
        {
            face = face.getOpposite();
            double vOffset = 0.22D;
            double hOffset = 0.27D;
            
            world.spawnParticle(
        		EnumParticleTypes.SMOKE_NORMAL, 
        		xCoord + hOffset * (double)face.getFrontOffsetX(), 
        		yCoord + vOffset, 
        		zCoord + hOffset * (double)face.getFrontOffsetZ(), 
        		0.0D, 0.0D, 0.0D, 
        		new int[0]
            );
            world.spawnParticle(
        		Particles.COLORED_FLAME, 
        		xCoord + hOffset * (double)face.getFrontOffsetX(), 
        		yCoord + vOffset, 
        		zCoord + hOffset * (double)face.getFrontOffsetZ(), 
        		0.0D, 0.0D, 0.0D, 
        		args
        	);
        }
        else
        {
        	world.spawnParticle(
        		EnumParticleTypes.SMOKE_NORMAL, 
        		xCoord, 
        		yCoord, 
        		zCoord, 
        		0.0D, 0.0D, 0.0D, 
        		new int[0]
            );
            world.spawnParticle(
        		Particles.COLORED_FLAME, 
        		xCoord, 
        		yCoord, 
        		zCoord, 
        		0.0D, 0.0D, 0.0D, 
        		args
        	);
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
