package rblocks.client.render;

import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockRenderInfo
{

	public BlockRenderInfo(RotateableBlockRender inst) {
		rendererInstance = inst;
	}

	final public RotateableBlockRender rendererInstance;

	private IconFlipWrapper tmpTopIcon = new IconFlipWrapper();
	private IconFlipWrapper tmpBottomIcon = new IconFlipWrapper();
	private IconFlipWrapper tmpSouthIcon = new IconFlipWrapper();
	private IconFlipWrapper tmpNorthIcon = new IconFlipWrapper();
	private IconFlipWrapper tmpEastIcon = new IconFlipWrapper();
	private IconFlipWrapper tmpWestIcon = new IconFlipWrapper();

	public void setTemporaryRenderIcons(IIcon nTopIcon, IIcon nBottomIcon, IIcon nSouthIcon, IIcon nNorthIcon, IIcon nEastIcon, IIcon nWestIcon)
	{
		tmpTopIcon.setOriginal( nTopIcon == null ? getTexture( ForgeDirection.UP ) : nTopIcon );
		tmpBottomIcon.setOriginal( nBottomIcon == null ? getTexture( ForgeDirection.DOWN ) : nBottomIcon );
		tmpSouthIcon.setOriginal( nSouthIcon == null ? getTexture( ForgeDirection.SOUTH ) : nSouthIcon );
		tmpNorthIcon.setOriginal( nNorthIcon == null ? getTexture( ForgeDirection.NORTH ) : nNorthIcon );
		tmpEastIcon.setOriginal( nEastIcon == null ? getTexture( ForgeDirection.EAST ) : nEastIcon );
		tmpWestIcon.setOriginal( nWestIcon == null ? getTexture( ForgeDirection.WEST ) : nWestIcon );
	}

	public IconFlipWrapper getTexture(ForgeDirection dir)
	{
		switch (dir)
		{
		case DOWN:
			return tmpBottomIcon;
		case UP:
			return tmpTopIcon;
		case EAST:
			return tmpEastIcon;
		case WEST:
			return tmpWestIcon;
		case NORTH:
			return tmpNorthIcon;
		case SOUTH:
		default:
			return tmpSouthIcon;
		}
	}

}
