package com.ipsum.level.tile;

import com.ipsum.graphics.Screen;
import com.ipsum.graphics.Sprite;

public class Tile
{

	public int x, y;
	public Sprite sprite;
	protected boolean solid = false;


	public Tile(Sprite sprite)
	{
		this.sprite = sprite;
	}

	public void render(int x, int y, Screen screen)
	{
		screen.renderTile(x << 4, y << 4, this.sprite);
	}

	public boolean solid()
	{
		return solid;
	}

}
