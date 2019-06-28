package dev.game.states;

import java.awt.Graphics;

import dev.game.Handler;
import dev.game.worlds.World;

public class GameState extends State {

	private World world;

	public GameState(Handler handler) {
		super(handler);
		
		handler.setWorld(world);
		
		world = new World(handler, "res/worlds/world1.txt", "world1");
	}
	
	@Override
	public void tick() {
		
		world.tick();
	}
	
	@Override
	public void render(Graphics g) {
		
		world.render(g);
	}
	
	public World getWorld() {
		
		return world;
	}
}
