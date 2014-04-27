package rblocks.core;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class RBConfig extends Configuration
{

	public static RBConfig instance;

	public RBConfig(File f) {
		super( f );
	}

	public boolean isWrenchEnabled()
	{
		boolean out = this.get( "settings", "enableWrench", false ).getBoolean( false );

		if ( hasChanged() )
			save();

		return out;
	}

	public boolean damageWrench()
	{
		boolean out = this.get( "settings", "damageWrench", true ).getBoolean( true );

		if ( hasChanged() )
			save();

		return out;
	}

	public boolean isBlockDisabled(Class obj, boolean autoValue)
	{
		if ( autoValue )
		{
			String name = obj.getName();

			boolean out = this.get( "disabledBlocks", name, false ).getBoolean( false ) ? false : autoValue;

			if ( hasChanged() )
				save();

			return out;
		}
		return autoValue;
	}

	public int getWrenchID() {
		return getItem("ItemWrench", 8001 ).getInt();
	}
}
