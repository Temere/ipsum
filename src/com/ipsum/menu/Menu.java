package com.ipsum.menu;

import com.ipsum.Game;
import com.ipsum.graphics.Screen;
import com.ipsum.graphics.Sprite;
import com.ipsum.graphics.filter.Filter;
import com.ipsum.graphics.filter.FilterDarken;
import com.ipsum.graphics.res.Sprites;
import com.ipsum.input.Keyboard;
import com.ipsum.interfaces.IScreenable;
import com.ipsum.interfaces.IUpdatable;

import java.awt.event.KeyEvent;

public class Menu implements IScreenable, IUpdatable
{

	public enum Current
	{
		NONE, MAIN, OPTIONS, PAUSE
	}

	public Current current = Current.NONE;

	private int width = Game.getScreenWidth();
	private int height = Game.getScreenHeight();
	private int[] pixels;

	private Sprite pauseSprite = Sprites.menu.pause.paused;

	private Filter darken = new FilterDarken(2);

	public Menu()
	{
		pixels = new int[width * height];
	}

	@Override
	public void render(Screen screen)
	{
		screen.applyFilter(darken);
		if(current == Current.PAUSE)
		{
			//drawing PAUSE
			screen.renderSprite(width / 2 - pauseSprite.getWidth() / 2, height / 2 - pauseSprite.getHeight() / 2, pauseSprite, false);

		}
	}

	@Override
	public int getWidth()
	{
		return width;
	}

	@Override
	public int getHeight()
	{
		return height;
	}

	@Override
	public int[] getPixels()
	{
		return pixels;
	}

	@Override
	public void update()
	{
		if(Keyboard.isPressed(KeyEvent.VK_PAUSE))
		{
			current = (current == Current.NONE) ? Current.PAUSE : Current.NONE;
		}


	}

}
