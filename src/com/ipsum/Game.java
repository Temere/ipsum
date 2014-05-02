package com.ipsum;

import com.ipsum.entity.mob.player.Player;
import com.ipsum.graphics.Screen;
import com.ipsum.graphics.res.Sprites;
import com.ipsum.input.Keyboard;
import com.ipsum.input.Mouse;
import com.ipsum.level.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Game extends Canvas implements Runnable
{

	private static int scale = 3;
	private static int width = 900 / scale;
	private static int height = width / 16 * 9;

	public static String title = "Ipsum";

	private JFrame frame;
	private Screen screen;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	private Level level;

	private Thread thread;

	private boolean running = true;

	private Keyboard keyboard;
	private Mouse mouse;


	Player player;

	public Game()
	{
		setPreferredSize(new Dimension(width * scale, height * scale));

		keyboard = new Keyboard();
		mouse = new Mouse();

		initFrame();
		screen = new Screen(width, height);

		player = new Player(100, 30, keyboard);

		addKeyListener(keyboard);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
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
				frame.setTitle(title + " " + updates + " ups, " + frames + " fps, ");// + level.getDebug());

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

		screen.renderSprite(10, 10, Sprites.test.red, true);

		player.render(screen);

		// not after here

		for(int i = 0; i< pixels.length; i++)
			pixels[i] = screen.pixels[i];


		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

		g.dispose();
		bs.show();
	}

	public void update()
	{
		player.update();
	}

	public static void main(String[] args)
	{
		Game game = new Game();
		game.start();
	}


	public static int getWindowHeight()
	{
		return height * scale;
	}

	public static int getWindowWidth()
	{
		return width * scale;
	}

}