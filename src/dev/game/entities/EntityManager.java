package dev.game.entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

import dev.game.Handler;
import dev.game.entities.creatures.Enemy;
import dev.game.entities.creatures.Player;

public class EntityManager {

	private Handler handler;
	
	private Player player;
	
	private ArrayList<Entity> entities;
	
	private Comparator<Entity> renderSorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity a, Entity b) {

			if(a.getY() + a.getHeight() < b.getY() + b.getHeight()) {
				
				return -1;
			}
			
			return 1;
		}
	};
	
	public EntityManager(Handler handler, Player player, ArrayList<Enemy> enemies) {
		
		this.handler = handler;
				
		this.player = player;
		
		entities = new ArrayList<Entity>();
		
		addEntity(player);
		
		for(Entity e : enemies) {
			
			addEntity(e);
		}
	}
	
	public void tick() {
		
		Iterator<Entity> it = entities.iterator();
		
		while(it.hasNext()) {
			
			Entity e = it.next();
			
			e.tick();
			
			if(!e.isActive()) {
				
				it.remove();
			}
		}
		
		entities.sort(renderSorter);
	}
	
	public void render(Graphics g) {
		
		for(Entity e : entities) {
			
			e.render(g);
		}
	}
	
	public void addEntity(Entity e) {
		
		entities.add(e);
	}

	public Handler getHandler() {
	
		return handler;
	}

	public void setHandler(Handler handler) {
	
		this.handler = handler;
	}

	public Player getPlayer() {
	
		return player;
	}

	public void setPlayer(Player player) {
	
		this.player = player;
	}

	public ArrayList<Entity> getEntities() {
	
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
	
		this.entities = entities;
	}
}
