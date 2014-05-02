package com.ipsum.entity.mob;


import com.ipsum.entity.Entity;
import com.ipsum.entity.projectile.Projectile;
import com.ipsum.entity.projectile.TestProjectile;
import com.ipsum.graphics.*;

public abstract class Mob extends Entity
{

	protected enum Direction
	{
		UP, DOWN, LEFT, RIGHT
	}

	protected Direction dir;
	protected boolean moving = false;
	protected int xa = 0, ya = 0;

	protected Sprite sprite;
	protected AnimatedSprite[] animatedSprites;
	protected int animSpeed = 15;

	protected int maxHealth = 50;
	protected int health = maxHealth;
	protected HealthBar healthBar;
	protected boolean showHealthBar = false;


	protected Mob(int x, int y, SpriteSheet animSheet)
	{
		this.x = x;
		this.y = y;
		initAnimation(animSheet);

		dir = Direction.DOWN;

		healthBar = new HealthBar(x, y, 25, 3, this);
	}

	protected void initAnimation(SpriteSheet sheet)
	{
		int spriteSize = sheet.spriteSize;
		if(spriteSize == -1) System.err.println("Invalid Spritesize: " + spriteSize);

		int w = sheet.WIDTH  / spriteSize;
		int h = sheet.HEIGHT / spriteSize;
		if(w < 4)
		{
			System.err.println("A mob requires a spritesheet width a with of at least 4 sprites!");
			System.exit(0);
		}

		animatedSprites = new AnimatedSprite[w];

		for (int x = 0; x < w; x++)
		{
			Sprite[] sprites = new Sprite[h];
			for(int y = 0; y < h; y++)
				sprites[y] = sheet.getSprites()[x + y * w];
			animatedSprites[x] = new AnimatedSprite(sprites);
		}
	}

	public void move(int xa, int ya)
	{
		if(xa != 0 || ya != 0)
		{
			moving = true;
		}
		else
		{
			moving = false;
			return;
		}

		if(xa != 0 && ya != 0)
		{
			move(xa, 0);
			move(0, ya);

			return;
		}

		if(xa > 0) dir = Direction.RIGHT;
		if(xa < 0) dir = Direction.LEFT;
		if(ya > 0) dir = Direction.DOWN;
		if(ya < 0) dir = Direction.UP;

		if(!collision(xa, ya))
		{
			x += xa;
			y += ya;
		}
	}

	public void updateAnim()
	{
		if(moving)
			animatedSprites[dir.ordinal()].update();
		else
			animatedSprites[dir.ordinal()].restart();
		animatedSprites[dir.ordinal()].setFrameRate(animSpeed);
	}

	@Override
	public void update()
	{
		healthBar.update();
	}

	@Override
	public void render(Screen screen)
	{
		renderMob(x - (animatedSprites[0].getSprite().getWidth() / 2), y - (animatedSprites[0].getSprite().getHeight() / 2), screen);
	}

	public Sprite getSprite()
	{
		return animatedSprites[dir.ordinal()].getSprite();
	}

	protected void renderMob(int x, int y, Screen screen)
	{
		screen.renderMob(x, y, this);
		if(showHealthBar) screen.renderBar(healthBar, true);
	}

	public void shoot(int x, int y, double direction)
	{
		int size = TestProjectile.SIZE;
		Projectile p = new TestProjectile(x - size, y - size, direction);
		level.add(p);
	}

	public void damage(int amount)
	{
		health -= amount;

		if(health <= 0)
			remove();
		else if(health > maxHealth)
			health = maxHealth;
	}

	public int getHealth()
	{
		return health;
	}

	public int getMaxHealth()
	{
		return maxHealth;
	}

	private boolean collision(int xa, int ya)
	{
		boolean solid = false;

		for (int c = 0; c < 4; c++)
		{
			int xt = ((x + xa) + c % 2 * 12 - 7 ) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3 ) / 16;
			if(level.getTile(xt, yt).solid()) solid = true;
		}

		return solid;
	}




}
