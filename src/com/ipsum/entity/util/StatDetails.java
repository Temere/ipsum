package com.ipsum.entity.util;

public class StatDetails
{
	public double rate;
	public String abr;
	public String name;
	public double base = 10;

	public StatDetails(double rate, String abr, String name)
	{
		this.rate = rate;
		this.abr = abr;
		this.name = name;
	}

	public StatDetails setBase(double base)
	{
		this.base = base;
		return this;
	}

	public Stat getStat(int level)
	{
		return new Stat(name, abr, base +  rate * ((double) level));
	}
}
