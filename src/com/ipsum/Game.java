package com.ipsum;

import com.ipsum.entity.Entity;
import com.ipsum.entity.mob.Dummy;
import com.ipsum.entity.mob.Mob;
import com.ipsum.entity.mob.player.Player;
import com.ipsum.entity.util.Hitbox;
import com.ipsum.entity.projectile.Projectile;
import com.ipsum.graphics.Screen;
import com.ipsum.graphics.gui.Gui;
import com.ipsum.input.Keyboard;
import com.ipsum.input.Mouse;
import com.ipsum.level.FileLevel;
import com.ipsum.level.Level;
import com.ipsum.level.TestLevelData;
import com.ipsum.util.TileCoordinate;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable
{

	private static int scale = 1;
	private static int width = 1920 / scale;
	private static int height = width / 16 * 9;

	public static Game game;

	public static String title = "Ipsum";

	private JFrame frame;
	private Screen screen;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Level level;

	private Thread thread;

	private boolean running = false;

	private Keyboard keyboard;
	private Mouse mouse;

	private Gui gui;


	private boolean showHitboxes = false;

	private Color color = new Color(0xffffff);

	private Player player;

	public Game()
	{
		setPreferredSize(new Dimension(width * scale, height * scale));

		keyboard = new Keyboard();
		mouse = new Mouse();

		initFrame();
		screen = new Screen(width, height);

		player = new Player(new TileCoordinate(10, 10));

		level = new FileLevel(new TestLevelData());
		level.add(player);

		gui = new Gui(player);

		addKeyListener(keyboard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);

		level.add(new Dummy(12 * 16, 20 * 16));
		level.add(new Dummy(14 * 16, 15 * 16));
		level.add(new Dummy(16 * 16, 18 * 16));
	}

	private void initFrame()
	{
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle(title);
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void run()
	{
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;

		requestFocus();

		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 0)
			{
				update();
				delta--;
				updates++;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer >= 1000)
			{
				timer = System.currentTimeMillis();

				//System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + " " + updates + " ups, " + frames + " fps, " + level.getDebug());

				frames = 0;
				updates = 0;
			}
		}

		stop();
	}

	public synchronized void start()
	{
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop()
	{
		running = false;
		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}

		screen.clear();

		// all rendering here


		int xScroll =(int) player.getX() - screen.width / 2;
		int yScroll =(int) player.getY() - screen.height / 2;

		level.render(xScroll, yScroll, screen);

		//render gui last

		gui.render(screen);

		if(showHitboxes) player.getHitbox().renderCorners(screen);

		// not after here

		for(int i = 0; i< pixels.length; i++)
			pixels[i] = screen.pixels[i];


		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		renderHitboxes(g, xScroll, yScroll);

		g.dispose();
		bs.show();
	}

	public void renderHitboxes(Graphics g, int xScroll, int yScroll)
	{
		if(!showHitboxes) return;


		//drawing hitboxes

		for(Entity e : level.getEntities())
		{
			if(e instanceof Mob)
			{
				Mob mob = (Mob) e;
				Hitbox hitbox = mob.getHitbox();

				int hx = (int) (hitbox.getXWithOffset() - xScroll) * scale;
				int hy = (int) (hitbox.getYWithOffset() - yScroll) * scale;
				int hw = (int) hitbox.getWidthWithOffset() * scale;
				int hh = (int) hitbox.getHeightWithOffset() * scale;

				g.setColor(color);
				g.drawRect(hx, hy, hw, hh);
			}
		}

		for(Projectile p : level.getProjectiles())
		{
			Hitbox hitbox = p.getHitbox();

			int hx = (int) (hitbox.getXWithOffset() - xScroll) * scale;
			int hy = (int) (hitbox.getYWithOffset() - yScroll) * scale;
			int hw = (int) hitbox.getWidthWithOffset() * scale;
			int hh = (int) hitbox.getHeightWithOffset() * scale;

			g.setColor(color);
			g.drawRect(hx, hy, hw, hh);
		}

		for(Player p : level.getPlayers())
		{
			Hitbox hitbox = p.getHitbox();

			int hx = (int) (hitbox.getXWithOffset() - xScroll) * scale;
			int hy = (int) (hitbox.getYWithOffset() - yScroll) * scale;
			int hw = (int) hitbox.getWidthWithOffset() * scale;
			int hh = (int) hitbox.getHeightWithOffset() * scale;

			g.setColor(color);
			g.drawRect(hx, hy, hw, hh);
		}

	}

	public void update()
	{
		keyboard.update();

		level.update();

		gui.update();
	}



	public static int getWindowHeight()
	{
		return height * scale;
	}

	public static int getWindowWidth()
	{
		return width * scale;
	}

	public static int getScreenWidth()
	{
		return width;
	}

	public static int getScreenHeight()
	{
		return height;
	}

	public static void main(String[] args)
	{
		game = new Game();
		game.start();
	}

}
