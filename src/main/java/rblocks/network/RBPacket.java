package rblocks.network;


public abstract class RBPacket
{
	/*

	private ByteBuf p;

	RBPacketHandlerBase.PacketTypes id;

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

	protected void configureWrite(ByteBuf data)
	{
		data.capacity( data.readableBytes() );
		p = data;
	}

	public FMLProxyPacket getProxy()
	{
		if ( p.array().length > 10000 )
			throw new IllegalArgumentException( "Sorry RotatableBlocks made a huge packet by accident!" );

		return new FMLProxyPacket( p, NetworkHandler.instance.getChannel() );
	}
	*/

}
