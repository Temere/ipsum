package com.ipsum.graphics.gui;

import com.ipsum.graphics.Screen;
import com.ipsum.graphics.Sprite;
import com.ipsum.graphics.gui.components.Bar;

public class Gui
{

	Bar healthBar = new Bar(100, 100, 100, 10);
	Sprite sprite;

	int amount = 50;
	int add = -1;

	public Gui()
	{
		sprite = new Sprite(healthBar.pixels, healthBar.getWidth(), healthBar.getHeight());
	}

	public void update()
	{
		amount += add;
		if(amount == 100 || amount == 0)
			add *= -1;

		healthBar.setCurrent(amount);
		healthBar.update();

	}

	public void render(Screen screen)
	{
		screen.renderBar(healthBar, false);
		//screen.renderSprite(100, 100, sprite, false);
	}

}
