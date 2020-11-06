package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Enemy extends Entity{
	
	public Enemy(int x, int y, int width, int height,int speed, BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	}

	public void tick(){

	}
	
	public void render(Graphics g) {
							
	}
	
	public static int xEscape(int xplayer, int xenemy) {
		int Dx, dx;
		
		Dx = (xplayer - xenemy);
		
		if(Dx > 0)
			dx = 1;
		
		else
			dx = -1;
		
		return (xenemy + (dx*Dx));
	}
	
	public static int yEscape(int yplayer, int yenemy) {
		
		int Dy, dy;
		
		Dy = (yplayer - yenemy);
		
		if(Dy > 0)
			dy = 1;
		
		else
			dy = -1;
		
		return (yenemy + (dy*Dy));
	}
	
	public void collisionWithPlayer() {
		
	}
}
