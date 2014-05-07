package com.ipsum.graphics.filter;

import com.ipsum.interfaces.IRenderable;

public class FilterReplace extends Filter
{
	int search = 0;
	int replace = 0;

	public FilterReplace(int search, int replace)
	{
		this.search = search;
		this.replace = replace;
	}

	@Override
	public void apply(IRenderable renderable)
	{
		int[] pixels = renderable.getPixels();
		for(int i = 0; i < pixels.length; i++)
			if(pixels[i] == search) pixels[i] = replace;
	}
}
