package com.ipsum.graphics;

import com.ipsum.entity.mob.Mob;
import com.ipsum.graphics.filter.Filter;
import com.ipsum.interfaces.IRenderable;

public class Screen implements IRenderable
{
	public static final int TILE_SIZE = 16;

	public int width;
	public int height;

	public int[] pixels;

	public int xOffset, yOffset;

	private int clearColor = 	0;

	public Screen(int width, int height)
	{
		this.width = width;
		this.height = height;

		pixels = new int[width * height];
	}

	public void clear()
	{

		int color = clearColor;
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = color;
	}

	public void setClearColor(int clearColor)
	{
		this.clearColor = clearColor;
	}

	public void setOffset(int xOffset, int yOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed)
	{
		if(fixed)
		{
			xp -= xOffset;
			yp -= yOffset;
		}

		for(int y = 0; y < sprite.getHeight(); y++)
		{
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++)
			{
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				if(sprite.transparent && sprite.pixels[x + y * sprite.getWidth()] == sprite.transparentColor) continue;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}


	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed)
	{
		if(fixed)
		{
			xp -= xOffset;
			yp -= yOffset;
		}

		for(int y = 0; y < sheet.HEIGHT; y++)
		{
			int ya = y + yp;
			for (int x = 0; x < sheet.WIDTH; x++)
			{
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
			}
		}
	}

	public void renderTile(int xp, int yp, Sprite sprite)
	{

		xp -= xOffset;
		yp -= yOffset;

		for (int y = 0; y < sprite.height; y++)
		{
			int ya = y + yp;
			for (int x = 0; x < sprite.width; x++)
			{
				int xa = x + xp;
				if(xa < -sprite.width  || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.width];
			}

		}
	}

	public void renderMob(int xp, int yp, Mob mob)
	{

		Sprite sprite = mob.getSprite();

		if(sprite == null) {
			System.err.println("sprite is null");
			return;
		}

		xp -= xOffset;
		yp -= yOffset;

		for (int y = 0; y < sprite.height; y++)
		{
			int ya = y + yp;
			for (int x = 0; x < sprite.width; x++)
			{
				int xa = x + xp;
				if(xa < -sprite.width  || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = sprite.pixels[x + y * sprite.width];

				if(col == 0xffff00ff) continue; //transparency

				pixels[xa + ya * width] = col;
			}

		}

	}

	public void renderBar(Bar bar, boolean fixed)
	{
		int xp = bar.getX();
		int yp = bar.getY();

		if(fixed)
		{
			xp -= xOffset;
			yp -= yOffset;
		}

		for (int y = 0; y < bar.getHeight(); y++)
		{
			int ya = y + yp;
			for (int x = 0; x < bar.getWidth(); x++)
			{
				int xa = x + xp;
				if(xa < -bar.getWidth()  || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = bar.pixels[x + y * bar.getWidth()];

				pixels[xa + ya * width] = col;
			}

		}
	}

	@Override
	public int getWidth()
	{
		return width;
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public int[] getPixels()
	{
		return pixels;
	}

	public void applyFilter(Filter filter)
	{
		filter.apply(this);
	}

	public void fillRect(int x, int y, int w, int h, int color,  boolean fixed)
	{
		if(fixed)
		{
			x -= xOffset;
			y -= yOffset;
		}

		for(int yp = 0; yp <= h; yp++)
		{
			for (int xp = 0; xp <= w; xp++)
			{
				int xt = x + xp;
				int yt = y + yp;
				if(xt < 0 || xt >= width || yt < 0 || yt >= height) continue;

				pixels[xt + yt * width] = color;

			}
		}

	}

	public void rect(int x, int y, int w, int h, int color, boolean fixed)
	{
		rect(x, y, w, h, color, fixed, 1);
	}

	public void rect(int x, int y, int w, int h, int color, boolean fixed, int lineSize)
	{
		if(fixed)
		{
			x -= xOffset;
			y -= yOffset;
		}

		for(int yp = 0; yp <= h; yp++)
		{
			for (int xp = 0; xp <= w; xp++)
			{
				if(xp < lineSize || yp < lineSize || xp > w - lineSize || yp > h - lineSize)
				{
					int xt = x + xp;
					int yt = y + yp;
					if(xt < 0 || xt >= width || yt < 0 || yt >= height) continue;

					pixels[xt + yt * width] = color;
				}
			}
		}
	}

}
