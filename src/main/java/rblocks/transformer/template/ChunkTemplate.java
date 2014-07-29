package rblocks.transformer.template;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import rblocks.core.RotationLogic;
import rblocks.transformer.annotations.RBCoreCopy;

public class ChunkTemplate extends Chunk
{
	// junk
	public ChunkTemplate(World p_i1995_1_, int p_i1995_2_, int p_i1995_3_) {
		super( p_i1995_1_, p_i1995_2_, p_i1995_3_ );
	}

	@RBCoreCopy
	@Override
	public boolean func_150807_a(int x, int y, int z, Block blk, int meta)
	{
		RotationLogic.destroyRotatedTile( this, x, y, z, blk, meta );
		return false; 
	}

	public void onStart()
	{
		RotationLogic.disable();
	}

	public void onEnd()
	{
		RotationLogic.enable();
	}

	public void onHasTileStart()
	{
		RotationLogic.enableHasTile();
	}

	public void onHasTileEnd()
	{
		RotationLogic.disableHasTile();
	}

}
