package rblocks.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import rblocks.core.RBConfig;

public class ItemWrench extends Item
{

	public ItemWrench() {
		setMaxStackSize( 1 );
		setTextureName( "RotatableBlocks:ItemWrench" );
		setUnlocalizedName( "RotatableBlocks.ItemWrench" );
		setCreativeTab( CreativeTabs.tabTools );
		if ( RBConfig.instance.damageWrench() )
			setMaxDamage( 300 );
	}

	@Override
	public boolean onItemUseFirst(ItemStack is, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		Block b = world.getBlock( x, y, z );
		if ( b != null && !player.isSneaking() )
		{
			ForgeDirection mySide = ForgeDirection.getOrientation( side );
			if ( b.rotateBlock( world, x, y, z, mySide ) )
			{
				if ( RBConfig.instance.damageWrench() )
					is.damageItem( 1, player );
				b.onNeighborBlockChange( world, x, y, z, Blocks.air );
				player.swingItem();
				return !world.isRemote;
			}
		}
		return false;
	}

}
