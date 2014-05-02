package com.ipsum.entity.mob.ai;

import com.ipsum.entity.mob.Mob;

public class RoamerAI extends AI
{

	private int freq;
	private int speed;
	private int diff = 0;

	private int xa = 0, ya = 0;
	private int interval = 2;
	private int stopRate = 5;

	private int next = 60;

	private int timer = 0;

	public RoamerAI(int freq, int speed)
	{
		this.freq = freq;
		this.speed = speed;
	}

	public RoamerAI setDiff(int diff)
	{
		this.diff = diff;
		return this;
	}

	public RoamerAI setStopRate(int stopRate)
	{
		this.stopRate = stopRate;
		return this;
	}

	public RoamerAI moveInterval(int interval)
	{
		this.interval = interval;
		return this;
	}

	@Override
	public void update(Mob mob)
	{
		timer++;
		if(timer % next == 0)
		{
			next = (freq + random.nextInt(diff));
			xa = random.nextInt(speed * 2 + 1) - speed;
			ya = random.nextInt(speed * 2 + 1) - speed;
			if(random.nextInt(stopRate) == 0)
			{
				xa = 0;
				ya = 0;
			}

			System.out.println("Changing!" + xa + " ," + ya);


		}
		if(interval == 0 || timer % interval == 0)
		{
			mob.move(xa, ya);

		}
	}
}
