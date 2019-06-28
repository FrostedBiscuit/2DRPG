package dev.game.entities.creatures;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.tiles.Tile;

public abstract class Creature extends Entity {

	public static final int DEFAULT_CREATURE_WIDTH = 16;
	public static final int DEFAULT_CREATURE_HEIGHT = 16;
	
	public static final float DEFAULT_SPEED = 3.0f;
	
	protected float Speed;
	protected float xMove;
	protected float yMove;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		
		Speed = DEFAULT_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public void move() {
		
		if(!checkEntityCollisions(xMove, 0f)) {
			
			moveX();
		}
		
		if(!checkEntityCollisions(0f, yMove)) {
			
			moveY();
		}
	}
	
	public void moveX() {
		
		if(xMove > 0) { // moving right
			
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				
				x += xMove;
			}
			
			else {
				
				x = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
			}
		}
		
		else if(xMove < 0) { // moving left
			
			int tx = (int) (x + xMove + bounds.x) / Tile.TILE_WIDTH;
			
			if(!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILE_HEIGHT) && !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILE_HEIGHT)) {
				
				x += xMove;
			}
			
			else {
				
				x = tx * Tile.TILE_WIDTH + Tile.TILE_WIDTH - bounds.x;
			}
		}
	}
	
	public void moveY() {
		
		if(yMove < 0) {
			
			int ty = (int) (y + yMove + bounds.y) / Tile.TILE_HEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				
				y += yMove;
			}
			
			else {
				
				y = ty * Tile.TILE_HEIGHT + Tile.TILE_HEIGHT - bounds.y;
			}
		}
		
		else if(yMove > 0) {
			
			int ty = (int) (y + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;
			
			if(!collisionWithTile((int) (x + bounds.x) / Tile.TILE_WIDTH, ty) && !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				
				y += yMove;
			}
			
			else {
				
				y = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
			}
		}

	}
	
	protected boolean collisionWithTile(int x, int y) {

		System.out.println("Checking for collision at " + x + " and " + y);
			
		if(handler.getWorld().getTile(x, y) != null) {
			
			return handler.getWorld().getTile(x, y).isSolid();
		}
		
		else {
			
			System.out.println("No tile at " + x + " " + y);
			
			return Tile.dirtTile.isSolid();
		}
	}
	
	public float getxMove() {
		
		return xMove;
	}

	public void setxMove(float xMove) {
		
		this.xMove = xMove;
	}

	public float getYMove() {
		
		return yMove;
	}

	public void setYMove(float yMove) {
		
		this.yMove = yMove;
	}

	public float getSpeed() {
		
		return Speed;
	}

	public void setSpeed(float speed) {
		
		Speed = speed;
	}
	
	
}
