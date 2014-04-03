package rblocks.network;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public interface IPacketHandler
{

	void onPacketData(FMLProxyPacket packet, EntityPlayer player);

}
