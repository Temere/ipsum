package com.ipsum.graphics;

import com.ipsum.entity.mob.Mob;

public class Screen
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
}
