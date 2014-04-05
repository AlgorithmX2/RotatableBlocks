package rblocks.network;


public class NetworkHandler
{
	/*

	public static NetworkHandler instance;

	final FMLEventChannel ec;
	final String myChannelName;

	final IPacketHandler clientHandler;
	final IPacketHandler serveHandler;

	public NetworkHandler(String channelName) {
		MinecraftForge.EVENT_BUS.register(this);
		ec = NetworkRegistry.instance().newEventDrivenChannel( myChannelName = channelName );

		ec.register( this );

		clientHandler = createClientSide();
		serveHandler = createServerSide();
	}

	private IPacketHandler createServerSide()
	{
		try
		{
			return new RBServerPacketHandler();
		}
		catch (Throwable t)
		{
			return null;
		}
	}

	private IPacketHandler createClientSide()
	{
		try
		{
			return new RBClientPacketHandler();
		}
		catch (Throwable t)
		{
			return null;
		}
	}

	@ForgeSubscribe
	public void serverPacket(ServerCustomPacketEvent ev)
	{
		NetHandlerPlayServer srv = (NetHandlerPlayServer) ev.packet.handler();
		if ( serveHandler != null )
			serveHandler.onPacketData( ev.packet, srv.playerEntity );
	}

	@SubscribeEvent
	public void clientPacket(ClientCustomPacketEvent ev)
	{
		if ( clientHandler != null )
			clientHandler.onPacketData( ev.packet, null );
	}

	public String getChannel()
	{
		return myChannelName;
	}

	public void sendToAll(RBPacket message)
	{
		ec.sendToAll( message.getProxy() );
	}

	public void sendTo(RBPacket message, EntityPlayerMP player)
	{
		ec.sendTo( message.getProxy(), player );
	}

	public void sendToAllAround(RBPacket message, NetworkRegistry.TargetPoint point)
	{
		ec.sendToAllAround( message.getProxy(), point );
	}

	public void sendToDimension(RBPacket message, int dimensionId)
	{
		ec.sendToDimension( message.getProxy(), dimensionId );
	}

	public void sendToServer(RBPacket message)
	{
		ec.sendToServer( message.getProxy() );
	}
	*/

}
