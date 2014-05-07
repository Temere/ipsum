package com.ipsum.entity.projectile;

import com.ipsum.entity.Entity;
import com.ipsum.entity.mob.Mob;
import com.ipsum.entity.util.Hitbox;
import com.ipsum.interfaces.ICollidable;

import com.ipsum.entity.spawner.ParticleSpawner;
import com.ipsum.graphics.Screen;
import com.ipsum.graphics.Sprite;
import com.ipsum.graphics.res.Sprites;
import com.ipsum.level.Level;

public abstract class Projectile extends Entity implements ICollidable
{

	protected final int xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double distance;

	protected boolean didDamage = false;

	protected double speed, rateOfFire, range, damage;
	protected int size;

	protected Hitbox hitbox;

	protected Projectile(int xOrigin, int yOrigin, double angle)
	{
		this.angle = angle;
		this.yOrigin = yOrigin;
		this.xOrigin = xOrigin;
		this.x = (double) xOrigin;
		this.y = (double) yOrigin;

		damage = 1.0;
	}

	@Override
	public void init(Level level)
	{
		super.init(level);

		hitbox = new Hitbox(this);
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
				m.damage(damage);
				System.out.println("Hit for " + damage + " " + m.getHealth() + "/" + m.getMaxHealth());
				didDamage = true;
			}

			level.add(new ParticleSpawner((int)(x + size / 2), (int) (y + size / 2), 55, 20, level, Sprites.particle.blood));
			remove();
		}
		hitbox.update();
	}

	public int getSize()
	{
		return size;
	}

	public Hitbox getHitbox() {
		return hitbox;
	}


	@Override
	public int getWidth()
	{
		return size;
	}

	@Override
	public int getHeight()
	{
		return size;
	}

	@Override
	public int[] getPixels()
	{
		return sprite.pixels;
	}
}
