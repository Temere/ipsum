package com.ipsum.audio;

import java.io.File;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sound
{

	static
	{
		new JFXPanel();
	}

	protected volatile MediaPlayer sound;
	private String soundFile = "";

	public Sound(String file)
	{

		final File soundFile = new File(file);

		if (!soundFile.exists())
		{
			System.err.println("Sound File \"" + file + "\" not found! " + soundFile.getAbsolutePath() + " " + soundFile.getPath());
			return;
		}

		String[] strings = file.split("/");
		this.soundFile = strings[strings.length - 1];

		sound = new MediaPlayer(new Media(soundFile.toURI().toString()));
	}

	public void play()
	{
		new Thread("Sound")
		{
			public void run()
			{
				sound.stop();
				sound.setCycleCount(0);
				sound.play();
			}
		}.start();
	}

	public void loop()
	{
		new Thread("Sound: " + soundFile)
		{
			public void run()
			{
				sound.stop();
				sound.setCycleCount(MediaPlayer.INDEFINITE);
				sound.play();
			}
		}.start();
	}

	public void stop()
	{
		sound.stop();
	}

	public void setVolume(double volume)
	{
		sound.setVolume(volume);
	}
}