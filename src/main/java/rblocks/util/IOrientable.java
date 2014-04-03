package rblocks.util;

import net.minecraftforge.common.util.ForgeDirection;

public interface IOrientable
{

	/**
	 * @return the direction the tile is facing
	 */
	ForgeDirection getForward();

	/**
	 * @return the direction top of the tile
	 */
	ForgeDirection getUp();

	/**
	 * Update the orientation
	 * 
	 * @param Forward
	 * @param Up
	 */
	void setOrientation(ForgeDirection Forward, ForgeDirection Up);

}