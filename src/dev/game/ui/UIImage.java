package dev.game.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImage extends UIObject {

	private BufferedImage image;
	
	public UIImage(float x, float y, int width, int height, BufferedImage image) {
		super(x, y, width, height);

		this.image = image;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		
		g.drawImage(image, (int) x, (int) y, null);
	}

	@Override
	public void onClick() {
		// TODO Auto-generated method stub
		
	}
	
	public BufferedImage getImage() {
		
		return image;
	}

}
