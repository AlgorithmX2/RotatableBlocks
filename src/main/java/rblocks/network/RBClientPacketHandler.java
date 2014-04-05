package rblocks.network;



public class RBClientPacketHandler extends RBPacketHandlerBase //implements IPacketHandler
{
	/*

	@Override
	public void onPacketData(FMLProxyPacket packet, EntityPlayer player)
	{
		ByteBuf stream = packet.payload();
		int packetType = -1;

		player = Minecraft.getMinecraft().thePlayer;

		try
		{
			packetType = stream.readInt();
			RBPacket pack = PacketTypes.getPacket( packetType ).parsePacket( stream );
			pack.clientPacketData( pack, player );
		}
		catch (InstantiationException e)
		{
			RBLog.error( e );
		}
		catch (IllegalAccessException e)
		{
			RBLog.error( e );
		}
		catch (IllegalArgumentException e)
		{
			RBLog.error( e );
		}
		catch (InvocationTargetException e)
		{
			RBLog.error( e );
		}

	}
	*/
}
