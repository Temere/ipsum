package com.ipsum.graphics.gui;

import com.ipsum.Game;
import com.ipsum.entity.mob.player.Player;
import com.ipsum.graphics.Screen;
import com.ipsum.graphics.Bar;
import com.ipsum.graphics.Sprite;

public class Gui
{

	Bar healthBar;

	int amount = 50;
	int add = -1;

	private Sprite background = new Sprite(Game.getWindowWidth() / 3, 30, 0xffa0a0a0);

	private Player player;

	public Gui(Player player)
	{
		this.player = player;
		healthBar = new Bar(5, Game.getWindowHeight() / 3 - background.getHeight() + 2, 100, 10);
	}

	public void update()
	{
		amount += add;
		if(amount == 100 || amount == 0)
			add *= -1;

		healthBar.setValues(player.getMaxHealth(), player.getHealth());
		healthBar.update();

	}

	public void render(Screen screen)
	{

		screen.renderSprite(0, Game.getWindowHeight() / 3 - background.getHeight(), background, false);

		screen.renderBar(healthBar, false);
	}

}
