package com.ipsum.graphics.gui;

import com.ipsum.Game;
import com.ipsum.entity.mob.player.Player;
import com.ipsum.graphics.Screen;
import com.ipsum.graphics.Bar;
import com.ipsum.graphics.Sprite;

public class Gui
{

	Bar healthBar;
	Bar xpBar;

	private Sprite background = new Sprite(Game.getScreenWidth(), 30, 0xffa0a0a0);

	private Player player;

	public Gui(Player player)
	{
		this.player = player;
		healthBar = new Bar(5, Game.getScreenHeight() - background.getHeight() + 2, 100, 10);
		xpBar = new Bar(5, Game.getScreenHeight() - background.getHeight() + 14, 100, 3).setColors(0x7E89EE , 0x9E937E);
	}

	public void update()
	{
		healthBar.setValues((int) player.getMaxHealth(),(int) player.getHealth());
		xpBar.setValues(player.getNextLevelXp(), player.getXp() + 5);

		healthBar.update();
		xpBar.update();
	}

	public void render(Screen screen)
	{

		screen.renderSprite(0, Game.getScreenHeight() - background.getHeight(), background, false);


//		screen.fillRect(0, Game.getScreenHeight() - 30, Game.getScreenWidth(), 30, 0xffa0a0a0, true);
		screen.renderBar(healthBar, false);
		screen.renderBar(xpBar, false);
	}

}
