package rblocks.core;

import rblocks.api.IRotatableBlocksApi;
import rblocks.client.render.RBBlockRender;
import rblocks.network.NetworkHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = RotateableBlocks.modid, name = RotateableBlocks.name, version = "0.0.0.1", dependencies = RotateableBlocks.dependencies)
public class RotateableBlocks implements IRotatableBlocksApi
{

	public final static String modid = "RotateableBlocks";
	public final static String name = "Rotateable Blocks";

	public static RotateableBlocks instance;

	public final static String dependencies =

	// depend on version of forge used for build.
	"required-after:Forge@[" // require forge.
			+ net.minecraftforge.common.ForgeVersion.majorVersion + "." // majorVersion
			+ net.minecraftforge.common.ForgeVersion.minorVersion + "." // minorVersion
			+ net.minecraftforge.common.ForgeVersion.revisionVersion + "." // revisionVersion
			+ net.minecraftforge.common.ForgeVersion.buildVersion + ",)"; // buildVersion

	public RotateableBlocks() {
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
		NetworkHandler.instance = new NetworkHandler( "RBlks" );
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
