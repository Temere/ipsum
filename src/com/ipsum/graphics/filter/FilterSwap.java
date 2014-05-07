package com.ipsum.graphics.filter;

import com.ipsum.interfaces.IRenderable;

public class FilterSwap extends Filter
{

	int col1, col2;

	public FilterSwap(int col1, int col2)
	{
		this.col1 = col1;
		this.col2 = col2;
	}

	@Override
	public void apply(IRenderable renderable)
	{
		int[] pixels = renderable.getPixels();
		for(int i = 0; i < pixels.length; i++)
		{
			if(pixels[i] == col1) pixels[i] = col2;
			else if(pixels[i] == col2) pixels[i] = col1;
		}

	}
}
