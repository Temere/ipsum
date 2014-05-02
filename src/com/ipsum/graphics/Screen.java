package com.ipsum.graphics;

import java.awt.*;

public class Screen
{
	public int width;
	public int height;

	public int[] pixels;

	public int xOffset, yOffset;

	private int clearColor = 	new Color(120, 180, 230).getRGB();

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
}
