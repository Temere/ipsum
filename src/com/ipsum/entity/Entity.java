package com.ipsum.entity;

import com.ipsum.interfaces.IScreenable;
import com.ipsum.interfaces.IUpdatable;
import com.ipsum.level.Level;

import java.util.Random;

public abstract class Entity implements IUpdatable, IScreenable
{
	protected double x, y;
	protected boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	public void remove()
	{
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

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public Level getLevel()
	{
		return level;
	}
}
