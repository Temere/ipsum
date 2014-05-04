package com.ipsum.entity.mob.player;

import com.ipsum.Game;
import com.ipsum.entity.mob.Dummy;
import com.ipsum.entity.mob.Mob;
import com.ipsum.entity.projectile.TestProjectile;
import com.ipsum.graphics.res.SpriteSheets;
import com.ipsum.input.Keyboard;
import com.ipsum.input.Mouse;
import com.ipsum.util.TileCoordinate;

public class Player extends Mob
{
	private int fireRate = 0;
	private int timer = 0;

	public Player(TileCoordinate coordinate)
	{
		this(coordinate.getX(), coordinate.getY());
	}

	public Player(int x, int y)
	{
		super(x, y, SpriteSheets.player);

		fireRate = TestProjectile.FIRERATE;

		hitbox.setOffset(-16, -16);
		speed = 2.5;
	}

	@Override
	public void update()
	{
		updateAnim();
		int xa = 0, ya = 0;

		if(Keyboard.up)
		{
			ya--;
		}
		else if(Keyboard.down)
		{
			ya++;
		}

		if(Keyboard.left)
		{
			xa--;
		}
		else if(Keyboard.right)
		{
			xa++;
		}

		move(xa, ya);

		updateShooting();
	}


	private void updateShooting()
	{
		int mx = Mouse.getX();
		int my = Mouse.getY();
		fireRate--;
		timer--;

		if(Mouse.getButton() == 1 && fireRate <= 0)
		{

			double dx = mx - Game.getWindowWidth() / 2;
			double dy = my - Game.getWindowHeight() / 2;
			double angle = Math.atan2(dy, dx);
			shoot((int)x,(int) y, angle);

			fireRate = TestProjectile.FIRERATE;
		}
		if(Mouse.getButton() == 3 && timer == 0)
		{
			level.add(new Dummy((int)x + mx,(int) y + my));
			timer = 60;
		}

	}

}
