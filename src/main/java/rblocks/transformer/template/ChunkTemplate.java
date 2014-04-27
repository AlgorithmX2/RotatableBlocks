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

}
