package dev.game.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import dev.game.Handler;
import dev.game.entities.Entity;
import dev.game.gfx.Animation;
import dev.game.gfx.Assets;
import dev.game.tiles.Tile;

public class Player extends Creature {

	private Animation animDown;
	private Animation animUp;
	private Animation animLeft;
	private Animation animRight;
	
	private enum Direction {
		
		LEFT, RIGHT, UP, DOWN
	}
	
	private Direction direction;
	
	private long lastAttackTimer;
	private long attackCooldown = 800;
	private long attackTimer = attackCooldown;
	
	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);	
		
		bounds.x = 0;
		bounds.y = 0;
		bounds.width = 16;
		bounds.height = 16;
		
		animDown = new Animation(500, Assets.Player_down);
		animUp = new Animation(500, Assets.Player_up);
		animLeft = new Animation(500, Assets.Player_left);
		animRight = new Animation(500, Assets.Player_right);
		
		direction = direction.UP;
		
		health = 1;
	}

	@Override
	public void tick() {

		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		
		getInput();
		
		move();
		
		handler.getGameCamera().centerOnEnitiy(this);
		
		checkAttack();
		
		System.out.println("X: " + (x / Tile.TILE_WIDTH) + " Y: " + (y / Tile.TILE_WIDTH));
	}
	
	@Override
	public void die() {
		
		System.out.println("You lose");
	}
	
	private void getInput() {
		
		xMove = 0;
		yMove = 0;
		
		if(handler.getKeyManager().w) {
			
			yMove = -Speed;
			
			direction = direction.UP;

			System.out.println(direction);
		}
		
		if(handler.getKeyManager().s) {
			
			yMove = Speed;
			
			direction = direction.DOWN;
			
			System.out.println(direction);
		}
		
		if(handler.getKeyManager().a) {
			
			xMove = -Speed;
			
			direction = direction.LEFT;
			
			System.out.println(direction);
		}
		
		if(handler.getKeyManager().d) {
			
			xMove = Speed;
			
			direction = direction.RIGHT;
			
			System.out.println(direction);
		}
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(this.getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), Width, Height, null);	
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
	
	private void checkAttack() {
		
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		
		lastAttackTimer = System.currentTimeMillis();
		
		if(attackTimer < attackCooldown) {
			
			return;
		}
		
		Rectangle cb = getCollisionBounds(0, 0);
		Rectangle ar = new Rectangle();
		
		int arSize = Tile.TILE_WIDTH;
		
		ar.width = arSize;
		ar.height = arSize;
		
		if(handler.getKeyManager().l) {
			
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
		}
		
		attackTimer = 0;
		
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
