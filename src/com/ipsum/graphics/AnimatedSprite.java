package com.ipsum.graphics;

public class AnimatedSprite extends Sprite
{
	private int frame = 0;
	private Sprite sprite;
	private int length;
	private int time;
	private int frameRate = 5;
	private Sprite[] sprites;

	public AnimatedSprite(Sprite[] sprites)
	{
		super((SpriteSheet) null, 0, 0);
		this.sprites = sprites;
		sprite = sprites[0];
		length = sprites.length;

//		new ShowError(false, sprites);
	}

	public AnimatedSprite(SpriteSheet sheet, int width, int height)
	{
		this(sheet, width, height, -1);
	}

	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length)
	{
		super(sheet, width, height);
		sprites = sheet.getSprites();
		this.length = (length == -1) ? sprites.length : length;
		if(length > sprites.length)
		{
			System.out.println("ShowError: Animation length to long, defaulting to guessed length.");
			this.length = sprites.length;
		}
		sprite = sprites[0];
	}

	public void update()
	{
		time++;
		if(time % frameRate == 0 && ++frame >= length)
			frame = 0;
		sprite = sprites[frame];
	}

	public Sprite getSprite()
	{
		return sprite;
	}

	public AnimatedSprite setFrameRate(int frameRate)
	{
		this.frameRate = frameRate;
		return this;
	}

	public void restart()
	{
		frame = 0;
		sprite = sprites[frame];
	}

	public AnimatedSprite setFrame(int frame)
	{
		this.frame = frame;

		if(frame < 0) frame = 0;
		if(frame >= length) frame = length;

		sprite = sprites[frame];

		return this;
	}
}
