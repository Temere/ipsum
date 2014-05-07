package com.ipsum.entity.spawner;

import com.ipsum.entity.Entity;
import com.ipsum.graphics.Screen;
import com.ipsum.level.Level;

import java.util.ArrayList;
import java.util.List;


public class Spawner extends Entity
{


	public enum Type
	{
		PARTICLE, MOB
	}

	private List<Entity> entities = new ArrayList<Entity>();

	private Type type;

	public Spawner(int x, int y, Type type, int amount, Level level)
	{
		this.x = x;
		this.y = y;
		this.level = level;

		this.type = type;
	}

	@Override
	public int getWidth()
	{
		return 0;
	}

	@Override
	public int getHeight()
	{
		return 0;
	}

	@Override
	public int[] getPixels()
	{
		return new int[0];
	}

	@Override
	public void render(Screen screen)
	{

	}

	@Override
	public void update()
	{

	}

}
