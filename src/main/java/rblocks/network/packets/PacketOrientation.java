package rblocks.network.packets;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;

import com.google.common.io.ByteArrayDataInput;

import rblocks.core.TileRotatableBlock;
import rblocks.network.RBPacket;

/**
 * @author tgame14
 * @since 04/04/14
 */
public class PacketOrientation extends RBPacket
{
	int x, y, z;
	byte orientation;
	
	// automatic.
	public PacketOrientation(ByteArrayDataInput stream)
	{
		x = stream.readInt();
		y = stream.readInt();
		z = stream.readInt();
		orientation = stream.readByte();
	}

	@Override
	public void clientPacketData(RBPacket packet, EntityPlayer player)
	{
		TileRotatableBlock tr = new TileRotatableBlock();
		player.worldObj.setBlockTileEntity(x, y, z, tr);
		tr.setOrientationByte( orientation );
	}

	// api
	public PacketOrientation(int x, int y, int z, byte orientation) throws IOException
	{
		super();
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		DataOutputStream dataStream = new DataOutputStream(byteStream);

		dataStream.writeInt( this.getPacketID() );
		dataStream.writeInt( this.x = x );
		dataStream.writeInt( this.y = y );
		dataStream.writeInt( this.z = z );
		dataStream.writeByte( this.orientation = orientation );

		configureWrite(byteStream.toByteArray());
	}
}
