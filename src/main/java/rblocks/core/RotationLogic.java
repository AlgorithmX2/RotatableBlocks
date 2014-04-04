package rblocks.core;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import rblocks.api.IOrientable;
import rblocks.api.IRBMethods;
import rblocks.api.RotatableBlockDisable;
import rblocks.api.RotatableBlockEnable;
import rblocks.util.Platform;

public class RotationLogic
{

	public static RotationLogic instance = new RotationLogic();

	private RotationLogic() {
	}

	public boolean isSupported(Object object)
	{
		if ( object instanceof IRBMethods )
		{
			IRBMethods obj = (IRBMethods) object;
			Boolean cachedValue = obj.isRotableBlockSupported();

			if ( cachedValue == null )
				return obj.setRotableBlockSupported( blockIsEnabled( (Block) object ) );

			return cachedValue;
		}
		return false;
	}

	private boolean blockIsEnabled(Block object)
	{
		Class c = object.getClass();

		if ( c.isAnnotationPresent( RotatableBlockEnable.class ) )
			return true;

		if ( c.isAnnotationPresent( RotatableBlockDisable.class ) )
			return false;

		return object.isOpaqueCube() && object.renderAsNormalBlock() && object.getRenderType() == 0;
	}

	private ThreadLocal<Boolean> enableTileSet = new ThreadLocal<Boolean>();

	public boolean useOrConvert(World worldObj, int x, int y, int z, ForgeDirection face)
	{
		Block blk = Block.blocksList[worldObj.getBlockId( x, y, z )];

		if ( blk == Block.grass || blk == Block.mycelium )
		{
			worldObj.setBlock( x, y, z, Block.dirt.blockID, 0, 3 );
		}

		TileEntity te = worldObj.getBlockTileEntity(x, y, z);
		if ( te instanceof IOrientable )
		{
			IOrientable ori = (IOrientable) te;

			if ( ori.canBeRotated() )
				return rotateBlockAround( ori, face );

			return false;
		}

		int meta = worldObj.getBlockMetadata( x, y, z );

		enableTileSet.set( true );
		if ( blk.hasTileEntity( meta ) )
		{
			enableTileSet.set( false );
			return false;
		}

		if ( blk.isAirBlock(worldObj, x, y, z) || !blk.isOpaqueCube() )
		{
			enableTileSet.set( false );
			return false;
		}

		TileRotatableBlock tr = new TileRotatableBlock();
		enableTileSet.set( false );

		worldObj.setBlockTileEntity(x, y, z, tr);

		return rotateBlockAround( tr, face );
	}

	private boolean rotateBlockAround(IOrientable rotateable, ForgeDirection axis)
	{
		ForgeDirection forward = rotateable.getForward();
		ForgeDirection up = rotateable.getUp();

		forward = Platform.rotateAround( forward, axis );
		up = Platform.rotateAround( up, axis );

		rotateable.setOrientation( forward, up );
		return true;
	}

	public boolean getEnableTile(Block block)
	{
		if ( isSupported( block ) && !isOpen() )
			return true;
		return false;
	}

	public boolean isOpen()
	{
		Boolean val = enableTileSet.get();
		return val == null ? false : val;
	}

	public ForgeDirection mapRotation(IOrientable ori, ForgeDirection dir)
	{
		// case DOWN: return bottomIcon;
		// case UP: return blockIcon;
		// case NORTH: return northIcon;
		// case SOUTH: return southIcon;
		// case WEST: return sideIcon;
		// case EAST: return sideIcon;

		ForgeDirection forward = ori.getForward();
		ForgeDirection up = ori.getUp();
		ForgeDirection west = ForgeDirection.UNKNOWN;

		if ( forward == null || up == null )
			return dir;

		int west_x = forward.offsetY * up.offsetZ - forward.offsetZ * up.offsetY;
		int west_y = forward.offsetZ * up.offsetX - forward.offsetX * up.offsetZ;
		int west_z = forward.offsetX * up.offsetY - forward.offsetY * up.offsetX;

		for (ForgeDirection dx : ForgeDirection.VALID_DIRECTIONS)
			if ( dx.offsetX == west_x && dx.offsetY == west_y && dx.offsetZ == west_z )
				west = dx;

		if ( dir.equals( forward ) )
			return ForgeDirection.SOUTH;
		if ( dir.equals( forward.getOpposite() ) )
			return ForgeDirection.NORTH;

		if ( dir.equals( up ) )
			return ForgeDirection.UP;
		if ( dir.equals( up.getOpposite() ) )
			return ForgeDirection.DOWN;

		if ( dir.equals( west ) )
			return ForgeDirection.WEST;
		if ( dir.equals( west.getOpposite() ) )
			return ForgeDirection.EAST;

		return ForgeDirection.UNKNOWN;
	}

	int mapRotation(IOrientable ori, int s)
	{
		return mapRotation( ori, ForgeDirection.getOrientation( s ) ).ordinal();
	}

	public Icon getIcon(Block blk, IBlockAccess w, int x, int y, int z, int s)
	{
		if ( isOpen() )
			return null;

		Icon ico = null;
		enableTileSet.set( true );

		TileEntity te = w.getBlockTileEntity(x, y, z);

		if ( te instanceof IOrientable )
			ico = blk.getIcon(s, w.getBlockMetadata(x, y, z));
		else
			ico = blk.getIcon(s, w.getBlockMetadata(x, y, z));

		enableTileSet.set( false );

		return ico;
	}
}
