package com.ipsum.graphics.filter;

import com.ipsum.interfaces.IRenderable;

public class FilterDarken extends Filter
{

	private double rate = 2;

	public FilterDarken(double rate)
	{
		this.rate = rate;
	}

	public void setRate(double rate)
	{
		this.rate = rate;
	}

	@Override
	public void apply(IRenderable renderable)
	{
		int[] pixels = renderable.getPixels();
		for(int i = 0; i < pixels.length; i++)
		{
			int r = (int) (((pixels[i] >> 16) & 0xff) / rate);
			int g = (int) (((pixels[i] >> 8) & 0xff) / rate);
			int b = (int) ((pixels[i] & 0xff) / rate);

			pixels[i] = (r << 16) + (g << 8) + b;
		}
	}
}
