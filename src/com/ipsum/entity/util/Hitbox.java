package com.ipsum.entity.util;

import com.ipsum.entity.Entity;
import com.ipsum.entity.mob.Mob;
import com.ipsum.entity.projectile.Projectile;
import com.ipsum.graphics.Screen;
import com.ipsum.graphics.res.Sprites;
import com.ipsum.interfaces.ICollidable;

import com.ipsum.level.Level;

public class Hitbox
{


	protected enum FollowType
	{
		MOB, PROJECTILE
	}

	protected double x;
	protected double y;
	protected double width;
	protected double height;

	protected double xOffset = 0;
	protected double yOffset = 0;
	protected double wOffset = 0;
	protected double hOffset = 0;

	protected int[] cornorPos;

	protected Entity following;
	protected FollowType followType;

	public Hitbox(double x, double y, double width, double height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		cornorPos = new int[12];
		for(int i = 0; i < 12; i++)
			cornorPos[i] = 0;
	}

	public Hitbox(ICollidable collidable)
	{
		if(collidable instanceof Mob)
		{
			following = (Mob) collidable;
			followType = FollowType.MOB;

			this.x = following.getX();
			this.y = following.getY();
			this.width =  following.getWidth();
			this.height = following.getHeight();

		}
		else if(collidable instanceof Projectile)
		{
			following = (Projectile) collidable;
			followType = FollowType.PROJECTILE;

			this.x = following.getX();
			this.y = following.getY();
			this.width =  ((Projectile)following).getSize();
			this.height = ((Projectile)following).getSize();
		}
		else
		{
			throw new IllegalArgumentException("Wrong class!");
		}

		int cornersX = ((int)width / Screen.TILE_SIZE) * 2;
		int cornersY = ((int)height / Screen.TILE_SIZE) * 2;

		int corners = cornersX + cornersY + 1;
		cornorPos = new int[corners * 3];
		for(int i = 0; i < cornorPos.length; i++)
			cornorPos[i] = 0;
	}

	public void update()
	{
		if(following != null)
		{
			this.x = following.getX();
			this.y = following.getY();
		}
	}

	public double getX()
	{
		return x;
	}

	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}

	public void setY(double y)
	{
		this.y = y;
	}

	public double getWidth()
	{
		return width;
	}

	public void setWidth(double width)
	{
		this.width = width;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public double getxOffset()
	{
		return xOffset;
	}

	public void setxOffset(double xOffset)
	{
		this.xOffset = xOffset;
	}

	public double getyOffset()
	{
		return yOffset;
	}

	public void setyOffset(double yOffset)
	{
		this.yOffset = yOffset;
	}

	public double getwOffset()
	{
		return wOffset;
	}

	public void setwOffset(double wOffset)
	{
		this.wOffset = wOffset;
	}

	public double gethOffset()
	{
		return hOffset;
	}

	public void sethOffset(double hOffset)
	{
		this.hOffset = hOffset;
	}

	public void setOffset(double xOffset, double yOffset)
	{
		setOffset(xOffset, yOffset, 0, 0);
	}

	public void setOffset(double xOffset, double yOffset, double wOffset, double hOffset)
	{
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.wOffset = wOffset;
		this.hOffset = hOffset;
	}

	public double getXWithOffset()
	{
		return x + xOffset;
	}
	public double getYWithOffset()
	{
		return y + yOffset;
	}
	public double getWidthWithOffset()
	{
		return width + wOffset;
	}
	public double getHeightWithOffset()
	{
		return height + hOffset;
	}

	public void setPosition(double x, double y)
	{
		this.x = x;
		this.y = y;
	}



	public boolean tileCollision(double xa, double ya, Level level)
	{
		boolean solid = false;

		int w = (int) (width + wOffset);
		int h = (int) (height + hOffset);

		int x = (int) (this.x + xOffset + xa);
		int y = (int) (this.y + yOffset + ya);


		int c = 0;

		int xc = x;
		int yc = 0;

		int xt, yt;

		for(; xc < x + w; xc += Screen.TILE_SIZE)
		{
			yc = y;
			xt = xc / Screen.TILE_SIZE;
			for(; yc < y + h; yc += Screen.TILE_SIZE)
			{
				c++;
				int p = c * 3;
				yt = yc / Screen.TILE_SIZE;

				if(level.getTile(xt, yt).solid()) solid = true;
				cornorPos[p] = xc;
				cornorPos[p + 1] = yc;
				cornorPos[p + 2] = (level.getTile(xt, yt).solid()) ? 1 : 0;
			}
			if(yc > y + h)
				yc = y + h;
			c++;
			int p = c * 3;
			yt = yc / Screen.TILE_SIZE;

			if(level.getTile(xt, yt).solid()) solid = true;
			cornorPos[p] = xc;
			cornorPos[p + 1] = yc;
			cornorPos[p + 2] = (level.getTile(xt, yt).solid()) ? 1 : 0;
		}

		if(xc > x + w)
			xc = x + w;
		c++;
		int p = c * 3;
		yt = yc / Screen.TILE_SIZE;
		xt = xc / Screen.TILE_SIZE;

		if(level.getTile(xt, yt).solid()) solid = true;
		cornorPos[p] = xc;
		cornorPos[p + 1] = yc;
		cornorPos[p + 2] = (level.getTile(xt, yt).solid()) ? 1 : 0;

		c++;
		p = c * 3;
		yt = y / Screen.TILE_SIZE;
		xt = xc / Screen.TILE_SIZE;

		if(level.getTile(xt, yt).solid()) solid = true;
		cornorPos[p] = xc;
		cornorPos[p + 1] = y;
		cornorPos[p + 2] = (level.getTile(xt, yt).solid()) ? 1 : 0;


//		for (int c = 0; c < corners; c++)
//		{
//			int p = c * 3;
//
//			int cx =(int) ((x + xa)  + (c % (corners / 2)) * Screen.TILE_SIZE);
//			int cy =(int) ((y + ya)  + (c / (corners / 2)) * Screen.TILE_SIZE);
//			System.out.println(cx + "," + cy);
//
//			int tx = cx / Screen.TILE_SIZE;
//			int ty = cy / Screen.TILE_SIZE;
////
////			double xt = ((x + xa)  + (c % 2) * w);
////			double yt = ((y + ya)  + (c / 2) * h);
////			int ix = (int) Math.ceil(((int)xt) / Screen.TILE_SIZE);
////			int iy = (int) Math.ceil(((int)yt) / Screen.TILE_SIZE);
////
////			if (c % 2 == 0) ix = (int) Math.floor(((int)xt) / Screen.TILE_SIZE);
////			if (c / 2 == 0) iy = (int) Math.floor(((int) yt) / Screen.TILE_SIZE);
////
//////			System.out.println("c " + c + " ,x " + x + " ,y " + y + " ,xa " + xa + " ,ya " + ya + " ,xt " + xt + " ,yt " + yt + " ,ix " + ix + " ,iy " + iy
//////					+ " ,width " + width + " ,height " + height + " " + (c % 2) + " " + (c / 2) + " " + level.getTile(ix, iy).solid());
////			if(level.getTile(ix, iy).solid()) solid = true;
//
//			if(level.getTile(tx, ty).solid()) solid = true;
//
//			cornorPos[p] = cx;
//			cornorPos[p + 1] = cy;
//			cornorPos[p + 2] =  (level.getTile(tx, ty).solid()) ? 1 : 0;

//		int w = (int) (width + wOffset);
//		int h = (int) (height + hOffset);
//		double x = this.x + xOffset;
//		double y = this.y + yOffset;
//
//		for (int c = 0; c < 4; c++)
//		{
//			int p = c * 3;
//
//			double xt = ((x + xa)  + (c % 2) * w);
//			double yt = ((y + ya)  + (c / 2) * h);
//			int ix = (int) Math.ceil(((int)xt) / Screen.TILE_SIZE);
//			int iy = (int) Math.ceil(((int)yt) / Screen.TILE_SIZE);
//
//			if (c % 2 == 0) ix = (int) Math.floor(((int)xt) / Screen.TILE_SIZE);
//			if (c / 2 == 0) iy = (int) Math.floor(((int) yt) / Screen.TILE_SIZE);
//
////			System.out.println("c " + c + " ,x " + x + " ,y " + y + " ,xa " + xa + " ,ya " + ya + " ,xt " + xt + " ,yt " + yt + " ,ix " + ix + " ,iy " + iy
////					+ " ,width " + width + " ,height " + height + " " + (c % 2) + " " + (c / 2) + " " + level.getTile(ix, iy).solid());
//			if(level.getTile(ix, iy).solid()) solid = true;
//
//			cornorPos[p] = (int) xt;
//			cornorPos[p + 1] = (int) yt;
//			cornorPos[p + 2] =  (level.getTile(ix, iy).solid()) ? 1 : 0;
//		}

		return solid;
	}

	public boolean collision(Hitbox other)
	{
		return collision(other, true, 0, 0);
	}

	public boolean collision(Hitbox other, boolean checking)
	{
		return collision(other, checking, 0, 0);
	}

	public boolean collision(Hitbox other, double xa, double ya)
	{
		return collision(other, true, xa, ya);
	}

	public boolean collision(Hitbox other, boolean checking, double xa, double ya)
	{
		double x = getXWithOffset() + xa;
		double y = getYWithOffset() + ya;
		double w = getWidthWithOffset() + xa;
		double h = getHeightWithOffset() + ya;


		for(int c = 0; c < 4; c++)
		{
			double cx = (other.getXWithOffset() + c % 2 * other.getWidth());
			double cy = (other.getYWithOffset() + c / 2 * other.getHeight());

			if((cx > x && cx < x + w) && (cy > y && cy < y + h))
				return true;
		}

		return checking && other.collision(this, false);
	}

	public void renderCorners(Screen screen)
	{
		for(int c = 0; c < cornorPos.length / 3; c++)
		{
			int p = c * 3;
			screen.renderSprite(cornorPos[p], cornorPos[p + 1], (cornorPos[p + 2] == 0) ? Sprites.test.small.green : Sprites.test.small.red, true);
		}

	}
}
