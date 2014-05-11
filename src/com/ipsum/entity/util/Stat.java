package com.ipsum.entity.util;

public class Stat
{
	public String name;
	public String abr;
	public double value;

	public Stat(String name, String abr, double value)
	{
		this.name = name;
		this.abr = abr;
		this.value = value;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getAbr()
	{
		return abr;
	}

	public void setAbr(String abr)
	{
		this.abr = abr;
	}

	public double getValue()
	{
		return value;
	}

	public void setValue(double value)
	{
		this.value = value;
	}
}
