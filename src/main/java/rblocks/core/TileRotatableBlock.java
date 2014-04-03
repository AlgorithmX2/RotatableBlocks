package rblocks.core;

import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import rblocks.network.packets.PacketOrientation;
import rblocks.util.IOrientable;

public class TileRotatableBlock extends TileEntity implements IOrientable
{

	private ForgeDirection forward = ForgeDirection.SOUTH;
	private ForgeDirection up = ForgeDirection.UP;

	@Override
	public boolean canUpdate()
	{
		return false;
	}

	@Override
	public void readFromNBT(NBTTagCompound data)
	{
		super.readFromNBT( data );
		byte orientation = data.getByte( "orientation" );

		forward = ForgeDirection.getOrientation( orientation & 0x7 );
		up = ForgeDirection.getOrientation( orientation >> 3 );
	}

	@Override
	public void writeToNBT(NBTTagCompound data)
	{
		super.writeToNBT( data );
		byte orientation = (byte) ((up.ordinal() << 3) | forward.ordinal());
		data.setByte( "orientation", orientation );
	}

	@Override
	public ForgeDirection getForward()
	{
		return forward;
	}

	@Override
	public ForgeDirection getUp()
	{
		return up;
	}

	@Override
	public void setOrientation(ForgeDirection inForward, ForgeDirection inUp)
	{
		forward = inForward;
		up = inUp;
		markDirty();
		markForUpdate();
	}

	public byte getOrientationByte()
	{
		return (byte) ((up.ordinal() << 3) | forward.ordinal());
	}

	public void setOrientationByte(byte orientation)
	{
		ForgeDirection old_Forward = forward;
		ForgeDirection old_Up = up;

		forward = ForgeDirection.getOrientation( orientation & 0x7 );
		up = ForgeDirection.getOrientation( orientation >> 3 );

		if ( !forward.equals( old_Forward ) || !up.equals( old_Up ) )
			markForUpdate();
	}

	@Override
	public Packet getDescriptionPacket()
	{
		try
		{
			return (new PacketOrientation( xCoord, yCoord, zCoord, getOrientationByte() )).getProxy();
		}
		catch (IOException e)
		{
			RBLog.error( e );
		}

		return null;
	}

	public void markForUpdate()
	{
		worldObj.markBlockForUpdate( xCoord, yCoord, zCoord );
	}

	public Block getBlock()
	{
		return worldObj.getBlock( xCoord, yCoord, zCoord );
	}

	public int getMeta()
	{
		return worldObj.getBlockMetadata( xCoord, yCoord, zCoord );
	}

}
