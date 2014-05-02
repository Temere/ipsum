package com.ipsum.graphics.res;

import com.ipsum.graphics.Sprite;

public class Sprites
{

	public static Sprite voidSprite = new Sprite(16, 0);

	public static class test
	{
		public static Sprite red = new Sprite(16, 0xff0000);
		public static Sprite green = new Sprite(16, 0xff00);
		public static Sprite brown = new Sprite(16, 0x7F3300);
		public static Sprite gray = new Sprite(16, 0x404040);
		public static Sprite darkgray = new Sprite(16, 0x303030);
	}

	public static class particle
	{
		public static Sprite normal = new Sprite(3, 0xaaaaaa);
	}

}
