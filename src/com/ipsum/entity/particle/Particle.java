package com.ipsum.entity.particle;

import com.ipsum.entity.Entity;
import com.ipsum.graphics.Screen;
import com.ipsum.graphics.Sprite;
import com.ipsum.graphics.res.Sprites;


public class Particle extends Entity
{

	private Sprite sprite;
	private int life;

	protected double xx, yy, zz;
	protected double xa, ya, za;

	protected int width;
	protected int height;


	public Particle(int x, int y, int life)
	{
		this(x, y, life, Sprites.particle.normal);
	}

	public Particle(int x, int y, int life, Sprite sprite)
	{
		this.sprite = sprite;

		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.zz = random.nextFloat() + 2.0;

		this.life = life + (random.nextInt(20) - 10);

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
	}


	@Override
	public void update()
	{

		this.za -= 0.1;

		if(zz < 0)
		{
			zz = 0;
			za *= -.6;
			xa *= .6;
			ya *= .6;
		}

		move(xx + xa, (yy + ya) + (zz + za));




		life--;
		if(life <= 0)
			remove();
	}

	private void move(double x, double y)
	{
		if(collision(x, y))
		{
			this.xa *= -.6;
			this.ya *= -.6;
			this.za *= -.6;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int[] getPixels() {
		return sprite.pixels;
	}

	@Override
	public void render(Screen screen)
	{
		screen.renderSprite((int) xx, (int) yy  - (int) zz - 2, sprite, true);
	}


	public boolean collision(double x, double y)
	{
		boolean solid = false;

		for (int c = 0; c < 4; c++)
		{
			double xt = (x  - c % 2 * 16) / 16;
			double yt = (y  - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);

			if (c % 2 == 0) ix--;
			if (c / 2 == 0) iy--;
			if(level.getTile(ix, iy).solid()) solid = true;
		}

		return solid;
	}
}
