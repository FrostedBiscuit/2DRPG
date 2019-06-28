package dev.game.entities.statics;

import java.awt.Graphics;

import dev.game.Handler;
import dev.game.gfx.Assets;
import dev.game.items.Item;
import dev.game.tiles.Tile;

public class Boulder extends StaticEntity {

	public Boulder(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);

		bounds.x = 0;
		bounds.y = 0;
		bounds.width = Tile.TILE_WIDTH;
		bounds.height = Tile.TILE_HEIGHT;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void die() {
		
		handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x,(int) y));
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(Assets.Boulder, (int) (x - handler.getGameCamera().getxOffset()), (int) (y - handler.getGameCamera().getyOffset()), Width, Height, null);
	}

}
