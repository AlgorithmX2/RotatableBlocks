package rblocks.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;


public abstract class RBPacket
{
	RBPacketHandlerBase.PacketTypes id;
	byte[] data;

	final public int getPacketID()
	{
		return RBPacketHandlerBase.PacketTypes.getID( this.getClass() ).ordinal();
	}

	public void serverPacketData(RBPacket packet, EntityPlayer player)
	{
		throw new RuntimeException( "This packet ( " + getPacketID() + " does not implement a server side handler." );
	}

	public void clientPacketData(RBPacket packet, EntityPlayer player)
	{
		throw new RuntimeException( "This packet ( " + getPacketID() + " does not implement a client side handler." );
	}

	protected void configureWrite(byte[] data)
	{
		this.data = data;
	}

	public Packet250CustomPayload getProxy()
	{
		if ( data.length > 10000 )
			throw new IllegalArgumentException( "Sorry RotatableBlocks made a huge packet by accident!" );

		return new Packet250CustomPayload( "RBlks", data );
	}

}
