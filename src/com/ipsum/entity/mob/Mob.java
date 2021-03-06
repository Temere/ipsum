package com.ipsum.entity.mob;

import com.ipsum.entity.Entity;
import com.ipsum.entity.mob.ai.AI;
import com.ipsum.entity.util.Hitbox;

import com.ipsum.entity.projectile.Projectile;
import com.ipsum.entity.projectile.TestProjectile;
import com.ipsum.entity.util.Stats;
import com.ipsum.graphics.*;
import com.ipsum.interfaces.ICollidable;
import com.ipsum.level.Level;

public abstract class Mob extends Entity implements ICollidable
{
	protected boolean mobCollision = true;
	protected boolean tileCollision = true;

	protected enum Direction
	{
		UP, DOWN, LEFT, RIGHT
	}

	protected double speed = 1.0;

	protected Direction dir;
	protected boolean moving = false;

	protected int width;
	protected int height;

	protected Sprite sprite;
	protected AnimatedSprite[] animatedSprites;
	protected int animSpeed = 15;

	protected double maxHealth = 50;
	protected double health = maxHealth;
	protected HealthBar healthBar;
	protected boolean showHealthBar = false;

	protected Hitbox hitbox;

	protected AI ai = null;

	protected int lvl;
	protected Stats stats;
	protected int xpValue = 5;


	protected Mob(int x, int y, SpriteSheet animSheet)
	{
		this.x = x;
		this.y = y;
		initAnimation(animSheet);
		width = animSheet.spriteSize;
		height = animSheet.spriteSize;

		hitbox = new Hitbox(this);

		dir = Direction.DOWN;

		healthBar = new HealthBar(x, y, 25, 3, this);
	}

	@Override
	public void init(Level level)
	{
		super.init(level);
		stats = new Stats(lvl);
		setMaxHealth(stats.getStat("stamina").getValue() * 5);
		xpValue =(int) (lvl * 1.2 * 5);

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

	public void move(double xa, double ya)
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


		xa *= speed;
		ya *= speed;

//		System.err.println("__ " + xa + " , " + ya);

		if(xa > 0) dir = Direction.RIGHT;
		if(xa < 0) dir = Direction.LEFT;
		if(ya > 0) dir = Direction.DOWN;
		if(ya < 0) dir = Direction.UP;

		while (xa != 0)
		{
			if(Math.abs(xa) > 1 )
			{
				if(!collision(abs(xa), ya))
				{
					this.x += abs(xa);
				}
				xa -= abs(xa);
			}
			else
			{
				if(!collision(abs(xa), ya))
				{
					this.x += xa;
				}
				xa = 0;
			}
		}
		while (ya != 0)
		{
			if(Math.abs(ya) > 1 )
			{
				if(!collision(xa, abs(ya)))
				{
					this.y += abs(ya);
				}
				ya -= abs(ya);
			}
			else
			{
				if(!collision(xa, abs(ya)))
				{
					this.y += ya;
				}
				ya = 0;
			}
		}
	}
	private int abs(double value)
	{
		return (value > 0) ? 1 : -1;
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
		if(ai != null)
			ai.update(this);
	}

	@Override
	public void render(Screen screen)
	{
		renderMob((int)x - (animatedSprites[0].getSprite().getWidth() / 2), (int) y - (animatedSprites[0].getSprite().getHeight() / 2), screen);
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
		int size = 8;
		Projectile p = new TestProjectile(x - size, y - size, direction);
		level.add(p);
	}

	public void damage(double amount)
	{
		health -= amount;

		if(health <= 0)
			remove();
		else if(health > maxHealth)
			health = maxHealth;
	}

	@Override
	public int[] getPixels()
	{
		return animatedSprites[dir.ordinal()].getPixels();
	}

	public double getHealth()
	{
		return health;
	}

	public double getMaxHealth()
	{
		return maxHealth;
	}

	private boolean collision(double xa, double ya)
	{

		hitbox.update();
		if(tileCollision && hitbox.tileCollision(xa, ya, level))
			return true;


		if(mobCollision)
		{
			for(int i = 0; i < level.getEntities().size(); i++)
			{
				Entity e = level.getEntities().get(i);
				if(e instanceof Mob)
				{
					if(hitbox.collision(((Mob) e).getHitbox(), xa, ya)) return true;
				}
			}

		}

		return false;
	}

	protected void addAI(AI ai)
	{
		this.ai = ai;
	}

	public Direction getDir()
	{
		return dir;
	}

	public HealthBar getHealthBar()
	{
		return healthBar;
	}

	public AI getAi()
	{
		return ai;
	}

	public boolean isShowHealthBar()
	{
		return showHealthBar;
	}

	public boolean isMoving()
	{
		return moving;
	}

	public Hitbox getHitbox()
	{
		return hitbox;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Stats getStats()
	{
		return stats;
	}

	public int getLvl()
	{
		return lvl;
	}

	public void setMaxHealth(double maxHealth)
	{
		this.maxHealth = maxHealth;

		health = maxHealth;
	}

	@Override
	public void remove()
	{
		super.remove();

		//death anim

		//add xp to nearby players
		level.getClientPlayer().addXp(xpValue);
	}
}
