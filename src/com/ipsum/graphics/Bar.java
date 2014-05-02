package com.ipsum.graphics;

public class Bar
{
	protected int foreground, background;
	protected int max, current;
	protected int x, y, width, height;
	public int[] pixels;

	public Bar(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		max = 100;
		current = max;

		foreground = 0xff00ff00;
		background = 0xffff0000;

		pixels = new int[width * height];
	}

	public Bar update()
	{
		float perc = (current / (float) max);
		int w = (int) (perc * width);

		for(int y = 0; y < height; y++)
		{
			for(int x = 0; x < width; x++)
			{
				pixels[x + y * width] = (x < w) ? foreground : background;
			}
		}

		return this;
	}

	public Bar setForeground(int foreground)
	{
		this.foreground = foreground;
		return this;
	}

	public Bar setBackground(int background)
	{
		this.background = background;
		return this;
	}

	public Bar setColors(int foreground, int background)
	{
		this.foreground = foreground;
		this.background = background;

		return this;
	}

	public Bar setMax(int max)
	{
		this.max = max;
		this.current = max;
		return this;
	}

	public Bar setCurrent(int current)
	{
		this.current = current;
		return this;
	}

	public Bar setValues(int max, int current)
	{
		this.max = max;
		this.current = current;

		return this;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public void setXY(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}
}
