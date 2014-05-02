package com.ipsum.entity;

import com.ipsum.graphics.Screen;
import com.ipsum.level.Level;

import java.util.Random;

public abstract class Entity
{
	protected int x, y;
	protected boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	public void update(){}

	public void render(Screen screen){}

	public void remove()
	{
		//remove from level
		removed = true;
	}

	public boolean isRemoved()
	{
		return removed;
	}

	public void init(Level level)
	{
		this.level = level;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public Level getLevel()
	{
		return level;
	}
}
