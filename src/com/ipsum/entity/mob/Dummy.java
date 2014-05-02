package com.ipsum.entity.mob;

import com.ipsum.graphics.res.SpriteSheets;

public class Dummy extends Mob
{

	public Dummy(int x, int y)
	{
		super(x, y, SpriteSheets.dummy);
		showHealthBar = true;
		healthBar.setOffset(48, 320).setFollow(true).setXY(x, y);
	}

	@Override
	public void update()
	{
		healthBar.setXY(x, y);
	}
}
