package com.ipsum.level.tile.res;

import com.ipsum.graphics.res.Sprites;
import com.ipsum.level.tile.GrassTile;
import com.ipsum.level.tile.Tile;
import com.ipsum.level.tile.VoidTile;
import com.ipsum.level.tile.WallTile;

public class Tiles
{
	public static Tile voidTile = new VoidTile(Sprites.voidSprite);

	public static class test
	{
		public static Tile grass = new GrassTile(Sprites.test.green);
		public static Tile wall1 = new WallTile(Sprites.test.gray);
		public static Tile wall2 = new WallTile(Sprites.test.darkgray);
		public static Tile floor = new GrassTile(Sprites.test.brown);
	}

}
