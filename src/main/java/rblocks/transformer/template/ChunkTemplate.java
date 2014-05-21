package rblocks.transformer.template;

import rblocks.core.RotationLogic;

public class ChunkTemplate
{

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
