package com.ipsum.entity.spawner;

import com.ipsum.entity.Entity;
import com.ipsum.entity.particle.Particle;
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

}
