package dev.game.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	public static Tile[] Tiles = new Tile[256];
	
	public static Tile grassTile = new GrassTile(0);
	public static Tile dirtTile = new DirtTile(1);
	public static Tile rockTile = new RockTile(2);

	public static final int TILE_WIDTH = 16;
	public static final int TILE_HEIGHT = 16;
	
	protected BufferedImage texture;
	
	protected final int ID;
	
	public Tile(BufferedImage texture, int id) {
		
		this.texture = texture;
		this.ID = id;
		
		Tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid() {
		
		return false;
	}
	
	public int getID() {
		
		return ID;
	}
}
