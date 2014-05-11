package com.ipsum.entity.util;

import java.util.HashMap;
import java.util.Map;

public class Stats
{
	private Map<String, Stat> stats;

	private int level;
	BaseStat baseStat;

	public Stats(int level)
	{
		this.level = level;
		stats = new HashMap<String, Stat>();
		baseStat = new BaseStat(level);

		updateStats();
	}

	public void updateStats()
	{
		for(StatDetails details : baseStat.getStatDetails())
		{
			Stat stat = details.getStat(level);
			stats.put(stat.getName(), stat);
		}
	}

	public int getLevel()
	{
		return level;
	}



	public void setLevel(int level)
	{
		this.level = level;
	}

	public Stat getStat(String name)
	{
		if(stats.containsKey(name))
			return stats.get(name);
		return null;
	}
}
