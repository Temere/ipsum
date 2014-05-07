package com.ipsum.graphics.filter;

import com.ipsum.interfaces.IRenderable;

public class FilterNegative extends Filter
{

	boolean transparency = false;
	int transparent = 0;

	public FilterNegative()
	{}

	public FilterNegative(int transparent)
	{
		this.transparency = true;
		this.transparent = transparent;
	}

	@Override
	public void apply(IRenderable renderable)
	{
		int[] pixels = renderable.getPixels();
		for(int i = 0; i < pixels.length; i++)
		{
			int col = pixels[i];

			if(transparency && transparent == col) continue;

			int r = (col >> 16) & 0xff;
			int g = (col >>  8) & 0xff;
			int b = col & 0xff;

			r = (r > 128) ? r - 128 : r + 128;
			g = (g > 128) ? g - 128 : g + 128;
			b = (b > 128) ? b - 128 : b + 128;

			col = (r << 16) + (g << 8) + b;
			pixels[i] = col;
		}
	}
}
