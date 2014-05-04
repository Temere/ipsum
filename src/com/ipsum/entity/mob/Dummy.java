package com.ipsum.entity.mob;

import com.ipsum.entity.mob.ai.ChaserAI;
import com.ipsum.graphics.res.SpriteSheets;
import com.ipsum.level.Level;

public class Dummy extends Mob
{

	public Dummy(int x, int y)
	{
		super(x, y, SpriteSheets.dummy);
		showHealthBar = true;
		healthBar.setOffset(-20, -20).setFollow(true).setXY(x, y);
		//addAI(new RoamerAI(120, 2).setDiff(20).setStopRate(4));
		addAI(new ChaserAI(null));

		maxHealth = 50;
		health = maxHealth;

		hitbox.setOffset(-16, -16);
	}

	@Override
	public void init(Level level) {
		super.init(level);
//		((ChaserAI)ai).setTarget(level.getClientPlayer());
	}
}
