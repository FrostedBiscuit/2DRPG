package dev.game.worlds;

import java.awt.Graphics;
import java.util.ArrayList;

import dev.game.Handler;
import dev.game.entities.EntityManager;
import dev.game.entities.creatures.Enemy;
import dev.game.entities.creatures.Player;
import dev.game.entities.statics.Boulder;
import dev.game.entities.statics.Tree;
import dev.game.items.ItemManager;
import dev.game.tiles.Tile;
import dev.game.utils.Utils;

public class World {

	private Handler handler;
	
	private int width;
	private int height;

	private int spawnX;
	private int spawnY;
	
	private int[][] tiles;
	
	private ArrayList<Enemy> enemies;
	
	private EntityManager entityManager;
	
	private ItemManager itemManager;
	
	private String name;
	
	public World(Handler handler, String path, String name) {
		
		this.handler = handler;
		
		this.name = name;
		
		enemies = new ArrayList<Enemy>();
		
		enemies.add(new Enemy(handler, 10, 10, Tile.TILE_WIDTH, Tile.TILE_HEIGHT, 5));
		
		entityManager = new EntityManager(handler, new Player(handler, 100, 100), enemies);
		
		itemManager = new ItemManager(handler);
		
		entityManager.addEntity(new Tree(handler, 100, 150));
		entityManager.addEntity(new Boulder(handler, (float) Tile.TILE_WIDTH * 4, (float) Tile.TILE_HEIGHT * 2)); 
		
		handler.setWorld(this);

		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick() {
		
		entityManager.tick();
		
		itemManager.tick();
	}
	
	public void render(Graphics g) {
		
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_WIDTH + 1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				
				getTile(x, y).render(g, (int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()), (int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		
		itemManager.render(g);
		
		entityManager.render(g);
	}
	
	private void loadWorld(String path) {
		
		String file = Utils.loadFileAsString(path);
		
		String[] tokens = file.split("\\s+");
		
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		
		spawnX = Utils.parseInt(tokens[2]) * Tile.TILE_WIDTH;
		spawnY = Utils.parseInt(tokens[3]) * Tile.TILE_HEIGHT;
		
		tiles = new int[width][height];
		
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		
		if(x < 0 || y < 0 || x >= width || y >= height) {
			
			return Tile.grassTile;
		}
		
//		Tile t = Tile.Tiles[tiles[x][y]];
		
		if(Tile.Tiles[tiles[x][y]] == null) {
			
			return Tile.dirtTile;
		}
		
		return Tile.Tiles[tiles[x][y]];
	}
	
	public String getName() {
		
		return name;
	}
	
	public void setName(String name) {
		
		this.name = name;
	}
	
	public int getWidth() {
		
		return width;
	}
	
	public int getHeight() {
		
		return height;
	}
	
	public EntityManager getEntityManager() {
	
		return entityManager;
	}

	public Handler getHandler() {
	
		return handler;
	}

	public void setHandler(Handler handler) {
	
		this.handler = handler;
	}

	public ItemManager getItemManager() {
	
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
	
		this.itemManager = itemManager;
	}
}
