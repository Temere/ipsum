package com.ipsum.graphics;

import com.ipsum.graphics.filter.Filter;
import com.ipsum.interfaces.IRenderable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet implements IRenderable
{

	public String path;
	public final int WIDTH, HEIGHT;
	public int[] pixels;
	public int spriteSize = -1;

	private Sprite[] sprites;

	public SpriteSheet(String path, int size)
	{
		this(path, size, size);
	}

	public SpriteSheet(String path, int width, int height)
	{
		long time = System.nanoTime();
		System.out.print("loading sheet: " + path);
		WIDTH = width;
		HEIGHT = height;
		this.path = path;

		pixels = new int[width * height];

		load();
		System.out.println(" finished " + ((System.nanoTime() - time) / 1000000) + "ms");
	}

	public SpriteSheet(SpriteSheet sheet, int x, int y, int w, int h, int spriteSize)
	{
		this.spriteSize = spriteSize;
		long time = System.nanoTime();
		System.out.print("loading sub-sheet: " + sheet.path);
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int ww = w * spriteSize;
		int hh = h * spriteSize;

		WIDTH = ww;
		HEIGHT = hh;
		pixels = new int[ww * hh];

		for(int yp = 0; yp < hh; yp++)
		{
			int ys = yy + yp;
			for (int xp = 0; xp < ww; xp++)
			{
				int xs = xx + xp;
				pixels[xp + yp * ww] = sheet.pixels[xs + ys * sheet.WIDTH];
			}
		}

		sprites = new Sprite[w * h];

		for (int y0 = 0; y0 < h; y0++)
			for (int x0 = 0; x0 < w; x0++)
			{
				sprites[x0 + y0 * w] = (new Sprite(spriteSize, x0, y0, this));
			}

		System.out.println(" finished " + ((System.nanoTime() - time) / 1000000) + "ms");
	}

	public Sprite[] getSprites()
	{
		if(sprites == null)
		{
			System.err.println("Sprites are not initialized yet! " + path);
			System.exit(0);
		}
		return sprites;
	}

	private void load()
	{
		BufferedImage image;

		try
		{
			image = ImageIO.read(SpriteSheet.class.getResource(path)); //new File(path))

			int w = image.getWidth();
			int h = image.getHeight();

			image.getRGB(0, 0, w, h, pixels, 0, w);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(0);
		}

	}

	public void setSpriteSize(int spriteSize)
	{
		this.spriteSize = (spriteSize < 0 || spriteSize > WIDTH || spriteSize > HEIGHT) ? -1 : spriteSize;
	}

	public SpriteSheet loadSprites(int spriteSize)
	{
		setSpriteSize(spriteSize);
		int w = (WIDTH  / spriteSize);
		int h = (HEIGHT / spriteSize);

		sprites = new Sprite[w * h];


		for (int y0 = 0; y0 < h; y0++)
			for (int x0 = 0; x0 < w; x0++)
			{
				sprites[x0 + y0 * w] = (new Sprite(spriteSize, x0, y0, this));
			}
		return this;
	}



	@Override
	public int getWidth()
	{
		return WIDTH;
	}

	@Override
	public int getHeight()
	{
		return HEIGHT;
	}

	@Override
	public int[] getPixels()
	{
		return pixels;
	}

	public SpriteSheet applyFilter(Filter filter)
	{
		filter.apply(this);
		return this;
	}
}