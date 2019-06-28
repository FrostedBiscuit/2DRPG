package dev.game.tiles;

import dev.game.gfx.Assets;

public class RockTile extends Tile {

	public RockTile(int id) {
		super(Assets.Rock, id);
	}
	
	@Override
	public boolean isSolid() {
		
		return true;
	}
}
