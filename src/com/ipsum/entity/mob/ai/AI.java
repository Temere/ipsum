package com.ipsum.entity.mob.ai;

import com.ipsum.entity.mob.Mob;

import java.util.Random;

public abstract class AI
{
	protected final Random random = new Random();

	protected int xa =0, ya = 0;

	protected int timer = 0;

	public abstract void update(Mob mob);

}
