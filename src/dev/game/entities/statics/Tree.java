package dev.game.entities.statics;

import java.awt.Graphics;

import dev.game.Handler;
import dev.game.gfx.Assets;
import dev.game.items.Item;
import dev.game.tiles.Tile;

public class Tree extends StaticEntity {

	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT * 2);
		
		bounds.x = Tile.TILE_WIDTH / 2;
		bounds.y = Tile.TILE_HEIGHT;
		bounds.width = 2;
		bounds.height = Tile.TILE_HEIGHT;
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void die() {
		
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x,(int) y));
	}
	
	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.Tree, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), Width, Height, null);
	}
}
