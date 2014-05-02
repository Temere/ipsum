package com.ipsum.graphics;

import com.ipsum.entity.mob.Mob;

public class HealthBar extends Bar
{

	private boolean follow = true;
	private Mob mob;

	private int xOffset = 0;
	private int yOffset = 0;

	public HealthBar(int x, int y, int width, int height, Mob mob)
	{
		super(x, y, width, height);
		this.mob = mob;
	}

	public HealthBar setxOffset(int xOffset)
	{
		this.xOffset = xOffset;
		return this;
	}

	public HealthBar setyOffset(int yOffset)
	{
		this.yOffset = yOffset;
		return this;
	}

	public HealthBar setOffset(int xOffset, int yOffset)
	{

		this.xOffset = xOffset;
		this.yOffset = yOffset;

		return this;
	}

	public HealthBar setFollow(boolean follow)
	{
		this.follow = follow;
		return this;
	}

	@Override
	public HealthBar update()
	{
		if(follow)
		{
			x = mob.getX() + xOffset;
			y = mob.getY() + yOffset;
		}

		max = mob.getMaxHealth();
		current = mob.getHealth();

		return (HealthBar) super.update();
	}
}
