package dev.game.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.gfx.Animation;
import dev.game.gfx.Assets;
import dev.game.tiles.Tile;

public class Enemy extends Creature {
	
	private Animation animDown;
	private Animation animUp;
	private Animation animLeft;
	private Animation animRight;
	
	private enum Direction {
		
		LEFT, RIGHT, UP, DOWN
	}
	
	private enum Agro { 
		
		IDLE, MOVING, ATTACKING
	}
	
	private Direction direction;
	
	private Agro agro;

	private boolean canMove = true;
	
	public Enemy(Handler handler, float x, float y, int width, int height, int health) {
		super(handler, (float) x * Tile.TILE_WIDTH, (float) y * Tile.TILE_HEIGHT, width, height);
		
		this.health = health;
		
		animDown = new Animation(500, Assets.Enemy_down);
		animUp = new Animation(500, Assets.Enemy_up);
		animLeft = new Animation(500, Assets.Enemy_left);
		animRight = new Animation(500, Assets.Enemy_right);
		
		Speed = 1f;
		
		direction = direction.DOWN;
	
		agro = agro.IDLE;
	}

	@Override
	public void tick() {
		
		AI();
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(this.getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), Width, Height, null);
	}

	@Override
	public void die() {
		
		System.out.println("Ughh!");
	}
	
	private BufferedImage getCurrentAnimationFrame() {
		
		switch(direction) {
		
		case UP:
			return animUp.getCurrentFrame();
		case DOWN:
			return animDown.getCurrentFrame();
		case LEFT:
			return animLeft.getCurrentFrame();
		case RIGHT:
			return animRight.getCurrentFrame();
		default:
			return animDown.getCurrentFrame();	
		}
	}
	
	private void AI() {
		
		float plyX = handler.getWorld().getEntityManager().getPlayer().getX();
		float plyY = handler.getWorld().getEntityManager().getPlayer().getY();
		
		if(Math.abs(plyX - this.x) < 100 && Math.abs(plyY - this.y) < 100) {
			
			agro = agro.MOVING;
		}
		
		else if(Math.abs(plyX - this.x) < 20 && Math.abs(plyY - this.y) < 20){
			
			agro = agro.ATTACKING;
		}
		
		else {
			
			agro = agro.IDLE;
		}
		
		switch(agro) {
		case IDLE:
			break;
		case MOVING:
			moveTowardsPlayer(plyX, plyY);
			lookTowardsPlayer(plyX, plyY);
			break;
		case ATTACKING:
			moveTowardsPlayer(plyX, plyY);
			lookTowardsPlayer(plyX, plyY);
			attack(plyX, plyY);
			break;
		default:
			break;
		}
	}
	
	private void moveTowardsPlayer(float playerX, float playerY) {
		
		if(Math.abs(playerX - this.x) < 10 && Math.abs(playerY - this.y) < 10) {
			
			System.out.println(Math.abs(playerX - this.x));
			
			canMove = false;
		}
		
		else {
			
			canMove = true;
		}
		
		if(canMove) {
		
			move();
			
			if(playerX < this.x) {
				
				this.xMove = -Speed;
			}
			
			else if(playerX > this.x) {
				
				this.xMove = Speed;
			}
			
			if(playerY < this.y) {
				
				this.yMove = -Speed;
			}
			
			else if(playerY > this.y) {
				
				this.yMove = Speed;
			}
		}
	}
	
	private void lookTowardsPlayer(float playerX, float playerY) {
		
		if(playerX < this.x) {
			
			direction = direction.LEFT;
		}
		
		else if(playerX > this.x) {

			direction = direction.RIGHT;
		}
		
		if(playerY == this.y) {

			direction = direction.UP;
		}
		
		else if(playerY == this.y) {

			direction = direction.DOWN;
		}
	}
	
	private void attack(float playerX, float playerY) {
		
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		
		int arSize = Tile.TILE_WIDTH;
		
		ar.width = arSize;
		ar.height = arSize;
		
		switch(direction) {
		
		case UP:
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
			break;
		case DOWN:
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
			break;
		case LEFT:
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
			break;
		case RIGHT:
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
			break;
		default:
			return;
		}
		
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			
			if(e.equals(this)) {
				
				continue;
			}
			
			if(e.getCollisionBounds(0, 0).intersects(ar)) {
				
				e.hurt(1);
				
				return;
			}
		}
	}
}
