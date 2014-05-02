package com.ipsum.entity.mob.ai;

import com.ipsum.entity.mob.Mob;

import java.util.Random;

public abstract class AI
{
	protected final Random random = new Random();

	public abstract void update(Mob mob);

}
