package com.ipsum.entity.projectile;


import com.ipsum.entity.mob.Mob;
import com.ipsum.entity.spawner.ParticleSpawner;
import com.ipsum.graphics.res.Sprites;

public class TestProjectile extends Projectile
{
	public static final int FIRERATE = 10;

	public TestProjectile(int xOrigin, int yOrigin, double angle)
	{
		super(xOrigin, yOrigin, angle);

		range = 200;
		speed = 4;
		damage = 20;
		rateOfFire = 10;

		sprite = Sprites.projectile.test.wizard;
		size = sprite.getWidth();

		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);

	}

	@Override
	public void update()
	{
		move();
		distance = distanceTraveled();
		if(distance > range) remove();
		if(level.tileCollision((int)(x + nx),(int)(y + ny), size, 4, 4))
		{
			level.add(new ParticleSpawner((int)(x + size / 2), (int) (y + size / 2), 55, 20, level, Sprites.particle.blergh));
			remove();
		}

		Mob m = level.projectileMobCollision(this);
		if(m != null)
		{
			if(!didDamage)
			{
				m.damage((int) damage);
				didDamage = true;
			}

			level.add(new ParticleSpawner((int)(x + size / 2), (int) (y + size / 2), 55, 20, level, Sprites.particle.blood));
			remove();
		}
	}

	@Override
	protected void move()
	{
		x += nx;
		y += ny;
	}

}
