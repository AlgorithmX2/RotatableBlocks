package rblocks.network;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;

public interface IPacketHandler
{

	void onPacketData(Packet packet, EntityPlayer player);

}
