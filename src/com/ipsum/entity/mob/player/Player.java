package com.ipsum.entity.mob.player;

import com.ipsum.Game;
import com.ipsum.entity.mob.Mob;
import com.ipsum.entity.projectile.TestProjectile;
import com.ipsum.graphics.res.SpriteSheets;
import com.ipsum.input.Keyboard;
import com.ipsum.input.Mouse;
import com.ipsum.util.TileCoordinate;

public class Player extends Mob
{
	private int fireRate = 0;

	public Player(TileCoordinate coordinate)
	{
		this(coordinate.getX(), coordinate.getY());
	}

	public Player(int x, int y)
	{
		super(x, y, SpriteSheets.player);

		fireRate = TestProjectile.FIRERATE;
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

		if(Mouse.getButton() == 1 && fireRate <= 0)
		{

			double dx = mx - Game.getWindowWidth() / 2;
			double dy = my - Game.getWindowHeight() / 2;
			double angle = Math.atan2(dy, dx);
			shoot(x, y, angle);

			fireRate = TestProjectile.FIRERATE;
		}

	}

//	@Override
//	public void render(Screen screen)
//	{
//		int xx = x - 16;
//		int yy = y - 16;
//
//		renderMob(xx, yy, screen);
//
//	}
}
