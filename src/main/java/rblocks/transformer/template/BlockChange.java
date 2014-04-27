package rblocks.transformer.template;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import rblocks.api.IRBMethods;
import rblocks.client.render.RBBlockRender;
import rblocks.core.RotationLogic;
import rblocks.transformer.annotations.RBClientMethod;
import rblocks.transformer.annotations.RBCoreCopy;

public abstract class BlockChange extends Block implements ITileEntityProvider, IRBMethods
{
	public BlockChange(int id, Material material)
	{
		super(id, material);
	}

	Boolean rotatableBlockSupport;

	@RBCoreCopy
	@Override
	public Boolean isRotableBlockSupported()
	{
		return rotatableBlockSupport;
	}

	@RBCoreCopy
	@Override
	public boolean setRotableBlockSupported(boolean supported)
	{
		rotatableBlockSupport = supported;
		return supported;
	}

	@Override
	@RBCoreCopy
	/**
	 * Gets appended to the top of the default method.
	 */
	public boolean hasTileEntity(int metadata)
	{
		if ( RotationLogic.instance.getEnableTile( (Block) (Object) this ) )
			return true;

		// Final return is removed.
		return false;
	}

	@Override
	@RBCoreCopy
	/**
	 * Gets appended to the top of the default method.
	 */
	protected boolean canSilkHarvest()
	{
		if ( RotationLogic.instance.getEnableTile( (Block) (Object) this ) )
			return renderAsNormalBlock();

		// Final return is removed.
		return false;
	}

	@RBClientMethod
	@RBCoreCopy
	/**
	 * Gets appended to the top of the default method.
	 */
	public Icon getIcon(IBlockAccess w, int x, int y, int z, int s)
	{
		Icon ico = RotationLogic.instance.getIcon( (Block) (Object) this, w, x, y, z, s );
		if ( ico != null )
			return ico;

		// Final return is removed.
		return null;
	}

	@Override
	@RBCoreCopy
	/**
	 * Gets appended to the top of the default method.
	 */
	public boolean rotateBlock(World worldObj, int x, int y, int z, ForgeDirection axis)
	{
		if ( RotationLogic.instance.isSupported( (Object) this ) )
			return RotationLogic.instance.useOrConvert( worldObj, x, y, z, axis );

		// Final return is removed.
		return false;
	}

	@Override
	@RBCoreCopy
	/**
	 * New method to overwrite renderer in render blocks.
	 */
	public int getRealRenderType()
	{
		if ( RotationLogic.instance.isSupported( (Object) this ) )
			return RBBlockRender.instance.getRenderId();

		return getRenderType();
	}

	// ignored.
	/*protected BlockChange(Material p_i45394_1_) {
		super( p_i45394_1_ );
	}*/

}
