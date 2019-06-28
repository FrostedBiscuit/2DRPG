package dev.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import dev.game.display.Display;
import dev.game.gfx.Assets;
import dev.game.gfx.GameCamera;
import dev.game.input.KeyManager;
import dev.game.input.MouseManager;
import dev.game.states.GameState;
import dev.game.states.MenuState;
import dev.game.states.State;

public class Game implements Runnable {

	private Display display;
	
	private Thread thread;
	
	private boolean Running = false;
	
	private BufferStrategy bs;
	
	private Graphics g;
	
	//States
	public State gameState;
	public State menuState;
	
	private KeyManager keyManager;
	
	private MouseManager mouseManager;
	
	private GameCamera gameCamera;
	
	private Handler handler;
	
	private String Title;
	
	private int Width;
	private int Height;
	
	public Game(String title, int width, int height) {
		
		this.Title = title;
		this.Width = width;
		this.Height = height;
		
		keyManager = new KeyManager();
		
		mouseManager = new MouseManager();
	}
	
	private void init() {
		
		display = new Display(Title, Width, Height);
		
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		
		handler = new Handler(this);
		
		gameCamera = new GameCamera(handler, 0, 0);		

		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		
		State.setState(menuState);
	}

	private void tick() {
		
		if(State.getState() != null) {
			
			State.getState().tick();
		}
		
		keyManager.tick();
	}
	
	private void render() {
		
		bs = display.getCanvas().getBufferStrategy();
		
		if(bs == null) {
			
			display.getCanvas().createBufferStrategy(3);
			
			return;
		}
		
		g = bs.getDrawGraphics();
		
		g.clearRect(0, 0, Width, Height);
		
		if(State.getState() != null) {
			
			State.getState().render(g);
		}
		
		bs.show();
		
		g.dispose();
	}
	
	//game loop
	public void run() {
		
		init();
		
		int fps = 60;
		int ticks = 0;
		
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		
		while(Running) {
			
			now = System.nanoTime();
			
			delta += (now - lastTime) / timePerTick;
			
			timer += now - lastTime;
			
			lastTime = now;
			
			if(delta >= 1) {
				
				tick();
				render();
				
				ticks++;
				
				delta--;
			}
			
			if(timer >= 1000000000) {
				
				System.out.println("Ticks and frames: " + ticks);
				
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	public Display getDisplay() {
		
		return display;
	}
	public KeyManager getKeyManager() {
		
		return keyManager;
	}
	
	public MouseManager getMouseManager() {
		
		return mouseManager;
	}
	
	public GameCamera getGameCamera() {
		
		return gameCamera;
	}
	
	public int getWidth() {
		
		return Width;
	}
	
	public int getHeight() {
		
		return Height;
	}
	
	public synchronized void start() {
		
		if(Running == true) {
			return;
		}
		
		Running = true;
		
		thread = new Thread(this);
		
		thread.start();
	}
	
	public synchronized void stop() {
		
		if(Running == false) {
			return;
		}
		
		Running = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
