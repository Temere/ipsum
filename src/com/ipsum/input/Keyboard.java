package com.ipsum.input;

import com.ipsum.interfaces.IUpdatable;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener, IUpdatable
{

	public static final int SIZE = 1024;

	private static boolean[] keysDown = new boolean[SIZE];
	private static boolean[] keysReleased = new boolean[SIZE];
	private static boolean[] keysPressed = new boolean[SIZE];
	public static boolean up, down, left, right;

	@Override
	public void update()
	{
		up = keysDown[KeyEvent.VK_UP] || keysDown[KeyEvent.VK_W];
		down = keysDown[KeyEvent.VK_DOWN] || keysDown[KeyEvent.VK_S];
		left = keysDown[KeyEvent.VK_LEFT] || keysDown[KeyEvent.VK_A];
		right = keysDown[KeyEvent.VK_RIGHT] || keysDown[KeyEvent.VK_D];

		for (int i = 0; i < SIZE; i++)
		{
			keysPressed[i] = false;
			keysReleased[i] = false;
		}
	}

	public static boolean isPressed(int keyCode)
	{
		if(keyCode < 0 || keyCode > keysDown.length)
			throw new IllegalArgumentException();

		return keysPressed[keyCode];
	}

	public static boolean isDown(int keyCode)
	{
		if(keyCode < 0 || keyCode > keysDown.length)
			throw new IllegalArgumentException();

		return keysDown[keyCode];
	}

	public static boolean isReleased(int keyCode)
	{
		if(keyCode < 0 || keyCode > keysDown.length)
			throw new IllegalArgumentException();

		return keysReleased[keyCode];
	}


	@Override
	public void keyPressed(KeyEvent e)
	{
		keysDown[e.getKeyCode()] = true;
		keysPressed[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		keysDown[e.getKeyCode()] = false;
		keysReleased[e.getKeyCode()] = true;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{

	}
}
