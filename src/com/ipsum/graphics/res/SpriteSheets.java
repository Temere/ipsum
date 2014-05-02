package com.ipsum.graphics.res;

import com.ipsum.graphics.SpriteSheet;

public class SpriteSheets
{

	public static SpriteSheet player = new SpriteSheet("/player_sheet.png", 128, 96).loadSprites(32);
	public static SpriteSheet dummy = new SpriteSheet("/player_sheet.png", 128, 96).negative(0xffff00ff).loadSprites(32);
}
