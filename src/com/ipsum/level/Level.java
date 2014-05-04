package com.ipsum.level;

import com.ipsum.entity.Entity;
import com.ipsum.entity.mob.Mob;
import com.ipsum.entity.mob.player.Player;
import com.ipsum.entity.util.Hitbox;
import com.ipsum.entity.particle.Particle;
import com.ipsum.entity.projectile.Projectile;
import com.ipsum.graphics.Screen;
import com.ipsum.level.tile.Tile;
import com.ipsum.level.tile.res.Tiles;

import java.util.ArrayList;
import java.util.List;

public abstract class Level
{
	protected int width, height;
	protected int[] tiles;
	protected List<Entity> entities = new ArrayList<Entity>();
	protected List<Projectile> projectiles = new ArrayList<Projectile>();
	protected List<Particle> particles = new ArrayList<Particle>();
	protected List<Player> players = new ArrayList<Player>();

	//public static Level spawn = new FileLevel("/levels/spawn.png");

	public Level(int width, int height)
	{
		this.width = width;
		this.height = height;
		tiles = new int[width * height];

		generateLevel();

	}

	public Level(String path)
	{
		loadLevel(path);
		generateLevel();

//		new ParticleSpawner(16 * 16, 62 * 16, 50, 50, this);
	}

	protected void generateLevel()
	{	}


	protected void loadLevel(String path)
	{

	}

	public List<Entity> getEntities()
	{
		return entities;
	}

	public List<Player> getPlayers()
	{
		return players;
	}

	public Player getPlayer(int index)
	{
		return players.get(index);
	}

	public Player getClientPlayer()
	{
		return players.get(0);
	}

	public List<Projectile> getProjectiles()
	{
		return projectiles;
	}

	public void update()
	{

		for (int i = 0; i < entities.size(); i++)
		{
			if(entities.get(i).isRemoved())
			{
				entities.remove(i);
				i--;
				continue;
			}
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++)
		{
			if(projectiles.get(i).isRemoved())
			{
				projectiles.remove(i);
				i--;
				continue;
			}
			projectiles.get(i).update();
			projectileMobCollision(projectiles.get(i));

		}
		for (int i = 0; i < particles.size(); i++)
		{
			if(particles.get(i).isRemoved())
			{
				particles.remove(i);
				i--;
				continue;
			}
			particles.get(i).update();
		}

		for (int i = 0; i < players.size(); i++)
		{
			if(players.get(i).isRemoved())
			{
				players.remove(i);
				i--;
				continue;
			}
			players.get(i).update();
		}


	}
	public void render(int xScroll, int yScroll, Screen screen)
	{
		int size = Screen.TILE_SIZE;
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll / size;
		int x1 = (xScroll + screen.width + size) / size;
		int y0 = yScroll / size;
		int y1 = (yScroll + screen.height + size) / size;

		for (int y = y0; y < y1; y++)
			for(int x = x0; x < x1; x++)
				getTile(x, y).render(x, y, screen);

		for (int i = 0; i < entities.size(); i++)
			entities.get(i).render(screen);

		for (int i = 0; i < projectiles.size(); i++)
			projectiles.get(i).render(screen);

		for (int i = 0; i < particles.size(); i++)
			particles.get(i).render(screen);

		for (int i = 0; i < players.size(); i++)
			players.get(i).render(screen);

	}

	public void add(Entity e)
	{
		e.init(this);

		if(e instanceof Particle) particles.add((Particle) e);
		else if(e instanceof Projectile) projectiles.add((Projectile) e);
		else if(e instanceof Player) players.add((Player) e);
		else entities.add(e);

		e.init(this);
	}

	public Tile getTile(int x, int y)
	{
		return Tiles.voidTile;
	}

	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset)
	{
		boolean solid = false;

		for (int c = 0; c < 4; c++)
		{
			double xt = (x  - c % 2 * size + xOffset) >> 4;
			double yt = (y  - c / 2 * size + yOffset) >> 4;
			if(getTile((int) xt, (int) yt).solid()) solid = true;
		}

		return solid;
	}

	public Mob projectileMobCollision(Projectile projectile)
	{

		double px = projectile.getX();
		double py = projectile.getY();

		Hitbox hitbox = projectile.getHitbox();

		for (int i = 0; i < entities.size(); i++)
		{
			if(entities.get(i) instanceof Mob)
			{
				Mob mob = (Mob) entities.get(i);
				int ps = projectile.getSize();
//
//
////				System.out.println("px " + px + ", py " + py);
//
//				double mx = mob.getX();
//				double my = mob.getY();
//				int mw = mob.getWidth();
//				int mh = mob.getHeight();
//
//				boolean collided = false;
//
//				for (int c = 0; c < 4; c++)
//				{
//					int xt =(int) (px  + (c % 2) * ps);
//					int yt =(int) (py  + (c / 2) * ps);
//
////					System.out.println("xt " + xt + ", yt " + yt + ", mx " + mx + ", my " + my + ", mw " + mw + ", mh " + mh );
//
//					if((xt > mx && xt < mx + mw) && (yt > my && yt < my + mh))
//					{
//						collided = true;
//						break;
//					}
//
//				}
////				System.exit(0);

				if(hitbox.collision(mob.getHitbox()))
				{
					return mob;
				}
			}
		}

		return null;
	}

	public String getDebug()
	{
		return entities.size() + " e, " + projectiles.size() + " proj, " + particles.size() + " part, " + (entities.size() + particles.size() + projectiles.size() + " tot");
	}

}