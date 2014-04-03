package rblocks.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetHandlerPlayServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

public class NetworkHandler
{

	public static NetworkHandler instance;

	final FMLEventChannel ec;
	final String myChannelName;

	final IPacketHandler clientHandler;
	final IPacketHandler serveHandler;

	public NetworkHandler(String channelName) {
		FMLCommonHandler.instance().bus().register( this );
		ec = NetworkRegistry.INSTANCE.newEventDrivenChannel( myChannelName = channelName );
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

	@SubscribeEvent
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

}
