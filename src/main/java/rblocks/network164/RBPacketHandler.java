package rblocks.network164;

import java.lang.reflect.Constructor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import rblocks.core.RBLog;
import rblocks.network164.Packets.PacketOrientation;
import rblocks.network164.Packets.RBPacket;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;

/**
 * @author tgame14
 * @since 04/04/14
 */
public class RBPacketHandler implements IPacketHandler
{
	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload payload, Player player)
	{
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
		{
			packet.clientPacketData(packet, entityPlayer);
		}
		else
			packet.serverPacketData(packet);
	}

	public enum PacketTypes
	{
		PACKET_TILE_DETAILS(PacketOrientation.class),;

		final public Class pc;
		final public Constructor con;

		private PacketTypes(Class c)
		{
			pc = c;

			Constructor x = null;

			try
			{
				x = pc.getConstructor(ByteArrayDataInput.class);

			}
			catch (Exception e)
			{
				RBLog.error(e);
			}

			con = x;

		}

		public Class<? extends RBPacket> getPacketTypeClass()
		{
			return pc;
		}

		public static final int getPacketId(Class<? extends RBPacket> c)
		{
			for (PacketTypes type : values())
			{
				if (type.getPacketTypeClass().equals(c))
					return type.ordinal();
			}
			return -1;
		}
	}
}
