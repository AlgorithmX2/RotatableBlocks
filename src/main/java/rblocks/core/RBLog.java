package rblocks.core;

import java.util.logging.Level;

import cpw.mods.fml.relauncher.FMLRelaunchLog;

public class RBLog
{

	public static cpw.mods.fml.relauncher.FMLRelaunchLog instance = cpw.mods.fml.relauncher.FMLRelaunchLog.log;

	private RBLog() {
	}

	private static void log(Level level, String format, Object... data)
	{
		FMLRelaunchLog.log( "RB", level, format, data );
	}

	public static void severe(String format, Object... data)
	{
		log( Level.SEVERE, format, data );
	}

	public static void warning(String format, Object... data)
	{
		log( Level.WARNING, format, data );
	}

	public static void info(String format, Object... data)
	{
		log( Level.INFO, format, data );
	}

	public static void error(Throwable e)
	{
		severe( "Error: " + e.getMessage() );
		e.printStackTrace();
	}

}
