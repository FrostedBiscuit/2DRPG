package dev.game.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int WIDTH = 8;
	private static final int HEIGHT = 8;

	public static BufferedImage Stone;
	public static BufferedImage Grass;
	public static BufferedImage Dirt;
	public static BufferedImage Rock;
	public static BufferedImage Tree;
	public static BufferedImage Boulder;
	
	public static BufferedImage Wood;
	public static BufferedImage Rock_drop;
	
	public static BufferedImage Title_banner;
	
	public static BufferedImage[] Btn_start;
	public static BufferedImage[] Btn_options;
	public static BufferedImage[] Btn_exit;
	
	public static BufferedImage[] Player_down;
	public static BufferedImage[] Player_up;
	public static BufferedImage[] Player_left;
	public static BufferedImage[] Player_right;
	
	public static BufferedImage[] Neutral_down;
	public static BufferedImage[] Neutral_up;
	public static BufferedImage[] Neutral_left;
	public static BufferedImage[] Neutral_right;
	
	public static BufferedImage[] Enemy_down;
	public static BufferedImage[] Enemy_up;
	public static BufferedImage[] Enemy_left;
	public static BufferedImage[] Enemy_right;
	
	public static void init() {
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/CharacterSheet.png"));

		Stone = sheet.crop(WIDTH * 3, 0, WIDTH, HEIGHT);
		Grass = sheet.crop(WIDTH * 4, 0, WIDTH, HEIGHT);
		Dirt = sheet.crop(WIDTH * 5, 0, WIDTH, HEIGHT);
		Rock = sheet.crop(WIDTH * 3, HEIGHT, WIDTH, HEIGHT);
		Tree = sheet.crop(WIDTH * 7, 0, WIDTH, HEIGHT * 2);
		Boulder = sheet.crop(WIDTH * 6, 0, WIDTH, HEIGHT);
		
		Wood = sheet.crop(WIDTH * 3, HEIGHT * 2, WIDTH, HEIGHT);
		Rock_drop = sheet.crop(WIDTH * 3, HEIGHT * 3, WIDTH, HEIGHT);
		
		Title_banner = sheet.crop(WIDTH * 8, HEIGHT * 2, WIDTH * 6, HEIGHT * 2);
		
		Btn_start = new BufferedImage[2];
		Btn_options = new BufferedImage[2];
		Btn_exit = new BufferedImage[2];
		
		Btn_start[0] = sheet.crop(WIDTH * 8, 0, WIDTH * 2, HEIGHT);
		Btn_start[1] = sheet.crop(WIDTH * 8, HEIGHT, WIDTH * 2, HEIGHT);
		
		Btn_options[0] = sheet.crop(WIDTH * 10, 0, WIDTH * 2, HEIGHT);
		Btn_options[1] = sheet.crop(WIDTH * 10, HEIGHT, WIDTH * 2, HEIGHT);
		
		Btn_exit[0] = sheet.crop(WIDTH * 12, 0, WIDTH * 2, HEIGHT);
		Btn_exit[1] = sheet.crop(WIDTH * 12, HEIGHT, WIDTH * 2, HEIGHT);
		
		Player_down = new BufferedImage[1];
		Player_up = new BufferedImage[1];
		Player_left = new BufferedImage[1];
		Player_right = new BufferedImage[1];
		
		Neutral_down = new BufferedImage[1];
		Neutral_up = new BufferedImage[1];
		Neutral_left = new BufferedImage[1];
		Neutral_right = new BufferedImage[1];
		
		Enemy_down = new BufferedImage[1];
		Enemy_up = new BufferedImage[1];
		Enemy_left = new BufferedImage[1];
		Enemy_right = new BufferedImage[1];
		
		Player_down[0] = sheet.crop(0, 0, WIDTH, HEIGHT);
		Player_up[0] = sheet.crop(0, HEIGHT, WIDTH, HEIGHT);
		Player_left[0] = sheet.crop(0, HEIGHT * 2, WIDTH, HEIGHT);
		Player_right[0] = sheet.crop(0, HEIGHT * 3, WIDTH, HEIGHT);
				
		Neutral_down[0] = sheet.crop(WIDTH, 0, WIDTH, HEIGHT);
		Neutral_up[0] = sheet.crop(WIDTH, HEIGHT, WIDTH, HEIGHT);
		Neutral_left[0] = sheet.crop(WIDTH, HEIGHT * 2, WIDTH, HEIGHT);
		Neutral_right[0] = sheet.crop(WIDTH, HEIGHT * 3, WIDTH, HEIGHT);
		
		Enemy_down[0] = sheet.crop(WIDTH * 2, 0, WIDTH, HEIGHT);
		Enemy_up[0] = sheet.crop(WIDTH * 2, HEIGHT, WIDTH, HEIGHT);
		Enemy_left[0] = sheet.crop(WIDTH * 2, HEIGHT * 2, WIDTH, HEIGHT);
		Enemy_right[0] = sheet.crop(WIDTH * 2, HEIGHT * 3, WIDTH, HEIGHT);
	}
	
}
