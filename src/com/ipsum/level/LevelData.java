package com.ipsum.level;

import com.ipsum.level.tile.Tile;
import com.ipsum.level.tile.res.Tiles;

import java.util.HashMap;
import java.util.Map;

public class LevelData
{
	private String path;
	private Map<Integer, Tile> colorLink = new HashMap<Integer, Tile>();

	protected LevelData(String path)
	{
		this.path = path;
		colorLink = new HashMap<Integer, Tile>();
	}

	protected LevelData add(int color, Tile tile)
	{
		colorLink.put(color, tile);
		return this;
	}

	public Tile getTileByColor(int color)
	{
		if(colorLink.containsKey(color))
			return colorLink.get(color);
		return Tiles.voidTile;
	}

	public String getPath()
	{
		return path;
	}

}
