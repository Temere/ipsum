package com.ipsum.level;

import com.ipsum.level.tile.Tile;
import com.ipsum.level.tile.res.Tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;



public class FileLevel extends Level
{
	private LevelData data;

	public FileLevel(LevelData data)
	{
		super(data.getPath());

		this.data = data;
	}

	@Override
	protected void loadLevel(String path)
	{
		try
		{
			BufferedImage image = ImageIO.read(FileLevel.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];

			image.getRGB(0, 0, width, height, tiles, 0, width);
		} catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Could not load level file");
		}

	}

	@Override
	protected void generateLevel()
	{

	}

	@Override
	public Tile getTile(int x, int y)
	{
		if(x < 0 || x >= width || y < 0 || y >= height)
			return Tiles.voidTile;

		int col = tiles[x + y * width];

		return data.getTileByColor(col);
	}
}
