package dev.game.states;

import java.awt.Graphics;

import dev.game.Handler;
import dev.game.gfx.Assets;
import dev.game.ui.ClickListener;
import dev.game.ui.UIImage;
import dev.game.ui.UIImageButton;
import dev.game.ui.UIManager;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	public MenuState(Handler handler) {
		super(handler);
		
		uiManager = new UIManager(handler);
		
		handler.getMouseManager().setUIManager(uiManager);
		
		uiManager.addObject(new UIImage(handler.getWidth() / 2 - 32, 10, 48, 16, Assets.Title_banner));
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 16, 30, 16, 8, Assets.Btn_start, new ClickListener() {

			@Override
			public void onClick() {
			
				handler.getMouseManager().setUIManager(null);
				
				State.setState(handler.getGame().gameState);
			}
		}));
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 16, 40, 16, 8, Assets.Btn_options, new ClickListener() {

			@Override
			public void onClick() {
				
				System.out.println("Insert options menu here");
			}
		}));
		
		uiManager.addObject(new UIImageButton(handler.getWidth() / 2 - 16, 50, 16, 8, Assets.Btn_exit, new ClickListener() {

			@Override
			public void onClick() {
				
				System.exit(0);
			}
		}));
	}

	@Override
	public void tick() {

		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {

		uiManager.render(g);
	}

}
