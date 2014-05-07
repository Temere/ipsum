package com.ipsum.entity.projectile;

import com.ipsum.graphics.res.Sprites;

public class TestProjectile extends Projectile
{
	public static final int FIRERATE = 10;

	public TestProjectile(int xOrigin, int yOrigin, double angle)
	{
		super(xOrigin, yOrigin, angle);

		range = 200;
		speed = 4;
		damage = 4.5;
		rateOfFire = 10;

		sprite = Sprites.projectile.test.wizard;
		size = sprite.getWidth();

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);

	}

	@Override
	protected void move()
	{
		x += nx;
		y += ny;
	}
}
