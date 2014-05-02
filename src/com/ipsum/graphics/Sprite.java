package com.ipsum.graphics;

public class Sprite
{
	public final int width, height;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;

	public Sprite(SpriteSheet sheet, int width, int height)
	{
		this.width = width;
		this.height = height;
		this.sheet = sheet;
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

	public Sprite replaceColor(int search, int replace)
	{
		for(int i = 0; i < pixels.length; i++)
			if(pixels[i] == search) pixels[i] = replace;

		return this;
	}

	public Sprite swapColor(int col1, int col2)
	{
		for(int i = 0; i < pixels.length; i++)
		{
			if(pixels[i] == col1) pixels[i] = col2;
			else if(pixels[i] == col2) pixels[i] = col1;
		}

		return this;
	}

	public Sprite negative()
	{

		for(int i = 0; i < pixels.length; i++)
		{
			int col = pixels[i];

			int r = (col >> 16) & 0xff;
			int g = (col >>  8) & 0xff;
			int b = col & 0xff;

			r = (r > 128) ? r - 128 : r + 128;
			g = (g > 128) ? g - 128 : g + 128;
			b = (b > 128) ? b - 128 : b + 128;

			col = (r << 16) + (g << 8) + b;
			pixels[i] = col;
		}

		return this;
	}

	public Sprite negative(int transparency)
	{

		for(int i = 0; i < pixels.length; i++)
		{
			int col = pixels[i];

			if(col == transparency) continue;

			int r = (col >> 16) & 0xff;
			int g = (col >>  8) & 0xff;
			int b = col & 0xff;

			r = (r > 128) ? r - 128 : r + 128;
			g = (g > 128) ? g - 128 : g + 128;
			b = (b > 128) ? b - 128 : b + 128;

			col = (r << 16) + (g << 8) + b;
			pixels[i] = col;
		}

		return this;
	}

}
