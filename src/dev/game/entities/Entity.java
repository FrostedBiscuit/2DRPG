package dev.game.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import dev.game.Handler;

public abstract class Entity {
	
	public static final int DEFAULT_HEALTH = 10;
	
	protected Handler handler;
	
	protected float x;
	protected float y;
	
	protected int Width;
	protected int Height;
	
	protected int health;
	
	protected boolean active = true;
	
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		
		this.handler = handler;
		
		this.x = x;
		this.y = y;
		
		this.Width = width;
		this.Height = height;
		
		health = DEFAULT_HEALTH;
		
		bounds = new Rectangle(0, 0, Width, Height);
	}
	
	public void hurt(int amt) {
		
		health -= amt;
		
		if(health <= 0) {
			
			active = false;
			
			die();
		}
	}
	
	public boolean checkEntityCollisions(float xOffset, float yOffset) {
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			
			if(e.equals(this)) {
				
				continue;
			}
			
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset))) {
				
				return true;
			}
		}
		
		return false;
	}
	
	public Rectangle getCollisionBounds(float xOffset, float yOffset) {
		
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
	}
	
	public float getX() {
		
		return x;
	}

	public void setX(float x) {
		
		this.x = x;
	}

	public float getY() {
		
		return y;
	}

	public void setY(float y) {
		
		this.y = y;
	}

	public int getWidth() {
		
		return Width;
	}

	public void setWidth(int width) {
		
		Width = width;
	}

	public int getHeight() {
		
		return Height;
	}

	public void setHeight(int height) {
		
		Height = height;
	}

	public int getHealth() {
	
		return health;
	}

	public void setHealth(int health) {
	
		this.health = health;
	}

	public boolean isActive() {
	
		return active;
	}

	public void setActive(boolean active) {
	
		this.active = active;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();

}
