package rblocks.util;

import net.minecraftforge.common.ForgeDirection;

public class Platform
{

	public static ForgeDirection crossProduct(ForgeDirection forward, ForgeDirection up)
	{
		int west_x = forward.offsetY * up.offsetZ - forward.offsetZ * up.offsetY;
		int west_y = forward.offsetZ * up.offsetX - forward.offsetX * up.offsetZ;
		int west_z = forward.offsetX * up.offsetY - forward.offsetY * up.offsetX;

		switch (west_x + west_y * 2 + west_z * 3)
		{
		case 1:
			return ForgeDirection.EAST;
		case -1:
			return ForgeDirection.WEST;

		case 2:
			return ForgeDirection.UP;
		case -2:
			return ForgeDirection.DOWN;

		case 3:
			return ForgeDirection.SOUTH;
		case -3:
			return ForgeDirection.NORTH;
		}

		return ForgeDirection.UNKNOWN;
	}

	public static ForgeDirection rotateAround(ForgeDirection forward, ForgeDirection axis)
	{
		if ( axis == ForgeDirection.UNKNOWN || forward == ForgeDirection.UNKNOWN )
			return forward;

		switch (forward)
		{
		case DOWN:
			switch (axis)
			{
			case DOWN:
				return forward;
			case UP:
				return forward;
			case NORTH:
				return ForgeDirection.EAST;
			case SOUTH:
				return ForgeDirection.WEST;
			case EAST:
				return ForgeDirection.NORTH;
			case WEST:
				return ForgeDirection.SOUTH;
			default:
				break;
			}
			break;
		case UP:
			switch (axis)
			{
			case NORTH:
				return ForgeDirection.WEST;
			case SOUTH:
				return ForgeDirection.EAST;
			case EAST:
				return ForgeDirection.SOUTH;
			case WEST:
				return ForgeDirection.NORTH;
			default:
				break;
			}
			break;
		case NORTH:
			switch (axis)
			{
			case UP:
				return ForgeDirection.WEST;
			case DOWN:
				return ForgeDirection.EAST;
			case EAST:
				return ForgeDirection.UP;
			case WEST:
				return ForgeDirection.DOWN;
			default:
				break;
			}
			break;
		case SOUTH:
			switch (axis)
			{
			case UP:
				return ForgeDirection.EAST;
			case DOWN:
				return ForgeDirection.WEST;
			case EAST:
				return ForgeDirection.DOWN;
			case WEST:
				return ForgeDirection.UP;
			default:
				break;
			}
			break;
		case EAST:
			switch (axis)
			{
			case UP:
				return ForgeDirection.NORTH;
			case DOWN:
				return ForgeDirection.SOUTH;
			case NORTH:
				return ForgeDirection.UP;
			case SOUTH:
				return ForgeDirection.DOWN;
			default:
				break;
			}
		case WEST:
			switch (axis)
			{
			case UP:
				return ForgeDirection.SOUTH;
			case DOWN:
				return ForgeDirection.NORTH;
			case NORTH:
				return ForgeDirection.DOWN;
			case SOUTH:
				return ForgeDirection.UP;
			default:
				break;
			}
		default:
			break;
		}
		return forward;
	}

}
