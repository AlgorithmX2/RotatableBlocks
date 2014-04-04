package rblocks.network164.Packets;

import com.google.common.io.ByteArrayDataInput;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author tgame14
 * @since 04/04/14
 */
public abstract class RBPacket extends Packet
{
	public RBPacket(ByteArrayDataInput stream)
	{

	}

	public RBPacket()
	{

	}

	@Override
	public void readPacketData(DataInput datainput) throws IOException
	{

	}

	@Override
	public void writePacketData(DataOutput dataoutput) throws IOException
	{

	}

	@Override
	public void processPacket(NetHandler nethandler)
	{

	}

	@Override
	public int getPacketSize()
	{
		return 0;
	}

	public void clientPacketData(RBPacket packet, EntityPlayer player)
	{

	}

	public void serverPacketData(RBPacket packet)
	{

	}
}
