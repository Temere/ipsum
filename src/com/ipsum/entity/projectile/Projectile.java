package com.ipsum.entity.projectile;

import com.ipsum.entity.Entity;
import com.ipsum.entity.mob.Mob;
import com.ipsum.graphics.Screen;
import com.ipsum.graphics.Sprite;

public abstract class Projectile extends Entity
{

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double distance;

	protected boolean didDamage = false;

	protected double speed, rateOfFire, range, damage;
	protected int size;

	protected Projectile(int xOrigin, int yOrigin, double angle)
	{
		this.angle = angle;
		this.yOrigin = yOrigin;
		this.xOrigin = xOrigin;
		this.x = (double) xOrigin;
		this.y = (double) yOrigin;

		damage = 5.0;
	}

	public double distanceTraveled()
	{
		return Math.sqrt((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y));
	}

	protected void move()
	{

	}

	@Override
	public void render(Screen screen)
	{
		screen.renderSprite((int)x, (int)y, sprite, true);
	}


	public int getSize()
	{
		return size;
	}
}
