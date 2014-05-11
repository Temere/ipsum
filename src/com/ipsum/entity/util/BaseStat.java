package com.ipsum.entity.util;

import java.util.ArrayList;

public class BaseStat
{
	public ArrayList<StatDetails> statDetails;
	public int level;

	public BaseStat(int level)
	{
		this.level = level;
		statDetails = new ArrayList<StatDetails>();

		statDetails.add(new StatDetails(1.1, "sta", "stamina").setBase(4));
		statDetails.add(new StatDetails(1.3, "atk", "attack").setBase(2.0));
		statDetails.add(new StatDetails(0.8, "def", "defense").setBase(6.0));

	}

	public ArrayList<StatDetails> getStatDetails()
	{
		return statDetails;
	}
}
