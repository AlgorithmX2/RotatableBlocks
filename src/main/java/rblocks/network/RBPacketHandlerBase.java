package rblocks.network;


public class RBPacketHandlerBase
{
	/*

	public static Map<Class, PacketTypes> reverseLookup = new HashMap<Class, RBPacketHandlerBase.PacketTypes>();

	public enum PacketTypes
	{
		PACKET_TILE_DETAILS(PacketOrientation.class), ;

		final public Class pc;
		final public Constructor con;

		private PacketTypes(Class c) {
			pc = c;

			Constructor x = null;
			try
			{
				x = pc.getConstructor( ByteBuf.class );
			}
			catch (NoSuchMethodException e)
			{
				RBLog.error( e );
			}
			catch (SecurityException e)
			{
				RBLog.error( e );
			}

			con = x;
			RBPacketHandlerBase.reverseLookup.put( pc, this );

			if ( con == null )
				throw new RuntimeException( "Invalid Packet Class, must be constructable on DataInputStream" );
		}

		public RBPacket parsePacket(ByteBuf in) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
		{
			return (RBPacket) con.newInstance( in );
		}

		public static PacketTypes getPacket(int id)
		{
			return (values())[id];
		}

		public static PacketTypes getID(Class<? extends RBPacket> c)
		{
			return RBPacketHandlerBase.reverseLookup.get( c );
		}

	};
	*/

}
