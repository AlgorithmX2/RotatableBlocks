package rblocks.network.packets;
/*
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import rblocks.core.TileRotatableBlock;
*/
import rblocks.network.RBPacket;

public class PacketOrientation extends RBPacket
{
	/*

	int x, y, z;
	byte orientation;

	// automatic.
	public PacketOrientation(ByteBuf stream) throws IOException {
		x = stream.readInt();
		y = stream.readInt();
		z = stream.readInt();
		orientation = stream.readByte();
	}

	@Override
	public void clientPacketData(RBPacket packet, EntityPlayer player)
	{
		TileRotatableBlock tr = new TileRotatableBlock();
		player.worldObj.setTileEntity( x, y, z, tr );
		tr.setOrientationByte( orientation );
	}

	// api
	public PacketOrientation(int x, int y, int z, byte orientation) throws IOException {

		ByteBuf data = Unpooled.buffer();

		data.writeInt( getPacketID() );
		data.writeInt( this.x = x );
		data.writeInt( this.y = y );
		data.writeInt( this.z = z );
		data.writeByte( this.orientation = orientation );

		configureWrite( data );
	}
	*/
}
