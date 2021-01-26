package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy extends Entity{
	
	public static int xEscape = 306, yEscape = 160;
	
	public Enemy(int x, int y, int width, int height,int speed, BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	}

	public void tick(){

	}
	
	public void render(Graphics g) {
							
	}
	
	public void collisionWithPlayer() {
		
	}
}
