package com.ipsum.entity;

import com.ipsum.graphics.Screen;

public class LevelUpAnim extends Entity
{

	private int x, y, width, height;
	private int lifeTime = 60;

	public LevelUpAnim(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void render(Screen screen)
	{
		screen.rect(x, y, width, height, 0xffffff, true);
	}

	@Override
	public int getWidth()
	{
		return width;
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public int[] getPixels()
	{
		return new int[0];
	}

	@Override
	public void update()
	{
		lifeTime--;
		if(lifeTime == 0) remove();

		x--;
		y--;
		width += 2;
		height += 2;

	}
}
