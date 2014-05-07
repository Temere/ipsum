package com.ipsum.interfaces;

import com.ipsum.graphics.Screen;

public interface IRenderable
{
	public int getWidth();

	public int getHeight();

	public int[] getPixels();

	public void render(Screen screen);
}
