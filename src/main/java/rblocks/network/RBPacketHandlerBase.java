package rblocks.network;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import rblocks.core.RBLog;
import rblocks.network.packets.PacketOrientation;


public class RBPacketHandlerBase implements IPacketHandler
{

	public static Map<Class, PacketTypes> reverseLookup = new HashMap<Class, RBPacketHandlerBase.PacketTypes>();

	public enum PacketTypes
	{
		PACKET_TILE_DETAILS(PacketOrientation.class), ;

		final public Class pc;
		final public Constructor con;

		private PacketTypes(Class c) {
			pc = c;

			Constructor x = null;
			try
			{
				x = pc.getConstructor( ByteArrayDataInput.class );
			}
			catch (NoSuchMethodException e)
			{
				RBLog.error( e );
			}
			catch (SecurityException e)
			{
				RBLog.error( e );
			}

			con = x;
			RBPacketHandlerBase.reverseLookup.put( pc, this );

			if ( con == null )
				throw new RuntimeException( "Invalid Packet Class, must be constructable on DataInputStream" );
		}

		public RBPacket parsePacket(ByteArrayDataInput in) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException 
		{
			return (RBPacket) con.newInstance( in );
		}

		public static PacketTypes getPacket(int id)
		{
			return (values())[id];
		}

		public static PacketTypes getID(Class<? extends RBPacket> c)
		{
			return RBPacketHandlerBase.reverseLookup.get( c );
		}

	}

	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload payload, Player player) {

		EntityPlayer entityPlayer = (EntityPlayer) player;
		ByteArrayDataInput reader = ByteStreams.newDataInput(payload.data);
		RBPacket packet = null;
		try
		{
			packet = (RBPacket) PacketTypes.values()[reader.readInt()].con.newInstance(reader);
		}
		catch (Exception e)
		{
			RBLog.error(e);
		}
		
		if (FMLCommonHandler.instance().getSide().isClient())
			packet.clientPacketData(packet, entityPlayer);
		else
			packet.serverPacketData(packet, entityPlayer);
		
	};

}
