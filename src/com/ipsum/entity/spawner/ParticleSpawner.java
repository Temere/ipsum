package com.ipsum.entity.spawner;


import com.ipsum.entity.particle.Particle;
import com.ipsum.graphics.Sprite;
import com.ipsum.level.Level;

public class ParticleSpawner extends Spawner
{
	private int life;

	public ParticleSpawner(int x, int y, int life, int amount, Level level)
	{
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;

		for (int i = 0; i < amount; i++)
			level.add(new Particle(x, y, life));


		remove();
	}

	public ParticleSpawner(int x, int y, int life, int amount, Level level, Sprite sprite)
	{
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;

		for (int i = 0; i < amount; i++)
			level.add(new Particle(x, y, life, sprite));


		remove();
	}
}