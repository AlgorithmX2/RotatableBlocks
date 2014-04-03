package rblocks.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class RBBlockRender extends RotateableBlockRender implements ISimpleBlockRenderingHandler
{

	private final int renderID = RenderingRegistry.getNextAvailableRenderId();
	private final BlockRenderInfo rinfo = new BlockRenderInfo( this );

	public static final RBBlockRender instance = new RBBlockRender();

	private RBBlockRender() {
		RenderingRegistry.registerBlockHandler( renderID, this );
	}

	protected BlockRenderInfo getRendererInstance(Object block)
	{
		return rinfo;
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		renderer.setRenderBoundsFromBlock( block );
		return renderInWorld( block, world, x, y, z, renderer );
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return renderID;
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		// nothing
	}
}
