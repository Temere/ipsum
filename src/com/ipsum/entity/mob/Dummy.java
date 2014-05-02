package com.ipsum.entity.mob;

import com.ipsum.entity.mob.ai.RoamerAI;
import com.ipsum.graphics.res.SpriteSheets;

public class Dummy extends Mob
{

	public Dummy(int x, int y)
	{
		super(x, y, SpriteSheets.dummy);
		showHealthBar = true;
		healthBar.setOffset(-20, -20).setFollow(true).setXY(x, y);
		addAI(new RoamerAI(120, 2).setDiff(20).setStopRate(4));
	}
}
