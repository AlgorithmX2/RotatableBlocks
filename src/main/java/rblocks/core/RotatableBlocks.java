package rblocks.core;

import net.minecraftforge.oredict.ShapedOreRecipe;
import rblocks.api.IRotatableBlocksApi;
import rblocks.client.render.RBBlockRender;
import rblocks.item.ItemWrench;
import rblocks.network.NetworkHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = RotatableBlocks.modid, name = RotatableBlocks.name, version = "@version@", dependencies = RotatableBlocks.dependencies)
public class RotatableBlocks implements IRotatableBlocksApi
{

	public final static String modid = "RotatableBlocks";
	public final static String name = "Rotatable Blocks";

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
		RBConfig.instance = new RBConfig( event.getSuggestedConfigurationFile() );
		GameRegistry.registerTileEntity( TileRotatableBlock.class, "RotateableBlocks.TileRotatable" );
		NetworkHandler.instance = new NetworkHandler( "RBlks" );

		if ( RBConfig.instance.isWrenchEnabled() )
		{
			ItemWrench wrench = new ItemWrench();
			GameRegistry.registerItem( wrench, "RotatableBlocks.ItemWrench" );
			GameRegistry.addRecipe( new ShapedOreRecipe( wrench, " O ", "OS ", "  S", 'O', "plankWood", 'S', "stickWood" ) );
		}

		// register Tiles with AE2.
		FMLInterModComms.sendMessage( "appliedenergistics2", "whitelist-spatial", TileRotatableBlock.class.getName() );

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
