package com.ipsum.entity.mob.player;

import com.ipsum.Game;
import com.ipsum.entity.mob.Mob;
import com.ipsum.graphics.Screen;
import com.ipsum.graphics.SpriteSheet;
import com.ipsum.graphics.res.SpriteSheets;
import com.ipsum.input.Keyboard;
import com.ipsum.input.Mouse;
import com.ipsum.util.TileCoordinate;

public class Player extends Mob
{
	private Keyboard input;

	private int anim = 0;

	private int fireRate = 0;

	public Player(TileCoordinate coordinate, Keyboard input)
	{
		this(coordinate.getX(), coordinate.getY(), input);
	}

	public Player(int x, int y, Keyboard input)
	{
		super(x, y, SpriteSheets.player);

		//fireRate = WizardProjectile.FIRERATE;
		this.input = input;

	}

	@Override
	public void update()
	{
		updateAnim();
		int xa = 0, ya = 0;

		if(anim++ > 7500) anim -= 7500;

		if(input.up)
		{
			ya--;
		}
		else if(input.down)
		{
			ya++;
		}

		if(input.left)
		{
			xa--;
		}
		else if(input.right)
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

//			fireRate = WizardProjectile.FIRERATE;
		}

	}

	@Override
	public void render(Screen screen)
	{
		int xx = x - 16;
		int yy = y - 16;

		renderMob(xx, yy, screen);
	}
}
