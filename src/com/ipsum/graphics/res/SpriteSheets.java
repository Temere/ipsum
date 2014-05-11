package com.ipsum.graphics.res;

import com.ipsum.graphics.SpriteSheet;
import com.ipsum.graphics.filter.FilterNegative;

public class SpriteSheets
{

	public static SpriteSheet player = new SpriteSheet("/player_sheet.png", 128, 96).loadSprites(32);
	public static SpriteSheet paused = new SpriteSheet("/paused.png", 120, 48);

	public static class mobs
	{
		public static SpriteSheet crawler = new SpriteSheet("/crawler.png", 128).loadSprites(32);
		public static SpriteSheet dummy = new SpriteSheet("/player_sheet.png", 128, 96).applyFilter(new FilterNegative(0xffff00ff)).loadSprites(32);
	}
}
