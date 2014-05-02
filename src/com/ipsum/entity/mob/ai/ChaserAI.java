package com.ipsum.entity.mob.ai;

import com.ipsum.entity.mob.Mob;

public class ChaserAI extends AI
{

	private Mob target;

	public ChaserAI(Mob target)
	{
		this.target = target;
	}

	public void setTarget(Mob target)
	{
		this.target = target;
	}

	@Override
	public void update(Mob mob)
	{
		if(target != null)
		{
			xa = 0;
			ya = 0;

			if(mob.getX() < target.getX()) xa = 1;
			if(mob.getX() > target.getX()) xa = -1;
			if(mob.getY() < target.getY()) ya = 1;
			if(mob.getY() > target.getY()) ya = -1;

			mob.move(xa, ya);

		}
	}
}
