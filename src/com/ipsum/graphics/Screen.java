package com.ipsum.graphics;

import java.awt.*;

public class Screen
{
	public static final int TILE_SIZE = 16;

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
}
