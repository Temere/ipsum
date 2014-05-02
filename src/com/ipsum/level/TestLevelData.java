package com.ipsum.level;

import com.ipsum.level.tile.res.Tiles;

public class TestLevelData extends LevelData
{

	public TestLevelData()
	{
		super("/spawn.png");

		add(0xff00ff00, Tiles.test.grass);
		add(0xff404040, Tiles.test.wall1);
		add(0xff303030, Tiles.test.wall2);
		add(0xff7F3300, Tiles.test.floor);
	}


}
