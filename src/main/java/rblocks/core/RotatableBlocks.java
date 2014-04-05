package rblocks.core;

import rblocks.api.IRotatableBlocksApi;
import rblocks.client.render.RBBlockRender;
import rblocks.network164.RBPacketHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = RotatableBlocks.modid, name = RotatableBlocks.name, version = "0.0.0.1", dependencies = RotatableBlocks.dependencies)
@NetworkMod(channels = { RotatableBlocks.channel }, packetHandler = RBPacketHandler.class)
public class RotatableBlocks implements IRotatableBlocksApi
{

	public final static String modid = "RotatableBlocks";
	public final static String name = "Rotatable Blocks";
	public final static String channel = "RBLKCHANNEL";

	public static RotatableBlocks instance;

	public final static String dependencies =

	// depend on version of forge used for build.
	"required-after:Forge@[" // require forge.
			+ net.minecraftforge.common.ForgeVersion.majorVersion + "." // majorVersion
			+ net.minecraftforge.common.ForgeVersion.minorVersion + "." // minorVersion
			+ net.minecraftforge.common.ForgeVersion.revisionVersion + "." // revisionVersion
			+ net.minecraftforge.common.ForgeVersion.buildVersion + ",)"; // buildVersion

	public RotatableBlocks() {
		instance = this;
	}

	@Override
	public int getRotatableRenderType()
	{
		return RBBlockRender.instance.getRenderId();
	}

	@EventHandler
	void PreInit(FMLPreInitializationEvent event)
	{
		RBLog.info( "PreInit" );
		GameRegistry.registerTileEntity( TileRotatableBlock.class, "RotateableBlocks.TileRotatable" );
		RBLog.info( "PreInit - end" );
	}

	@EventHandler
	void Init(FMLInitializationEvent event)
	{

	}

	@EventHandler
	void PostInit(FMLPostInitializationEvent event)
	{

	}

}
