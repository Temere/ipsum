package com.ipsum.graphics;

import com.ipsum.graphics.filter.Filter;
import com.ipsum.interfaces.IRenderable;

public class Sprite implements IRenderable
{
	public final int width, height;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;

	public int transparentColor = 0xffff00ff;
	public boolean transparent = true;

	public Sprite(SpriteSheet sheet, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.sheet = sheet;
		pixels = new int[width * height];
		load();
	}

	public Sprite(int[] pixels, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet)
	{
		this.x = x * size;
		this.y = y * size;

		this.width = size;
		this.height = size;

		this.sheet = sheet;

		pixels = new int[size * size];

		load();
	}

	public Sprite(int width, int height, int color)
	{
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		loadColor(color);
	}

	public Sprite(int size, int color)
	{
		this.width = size;
		this.height = size;

		pixels = new int[width * height];

		loadColor(color);
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	@Override
	public int[] getPixels()
	{
		return pixels;
	}

	private void loadColor(int color)
	{
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = color;
	}

	private void load()
	{
		for (int yy = 0; yy < height; yy++)
		{
			for (int xx = 0; xx < width; xx++)
			{
				pixels[xx + yy * width] = sheet.pixels[(xx + x) + (y + yy) * sheet.WIDTH];
			}
		}
	}

	public Sprite applyFilter(Filter filter)
	{
		filter.apply(this);
		return this;
	}

}
