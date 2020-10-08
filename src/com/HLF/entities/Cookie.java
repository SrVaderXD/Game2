package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Cookie extends Entity{
	
	private boolean animation = true;
	private int frames = 0;
	
	public Cookie(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		depth = 0;
	}
	
	public void render(Graphics g) {
		
		frames++;
		if(frames == 32) {
			frames = 0;
			
			if(animation)
				animation = false;
			
			else
				animation = true;
		}
		
		if(animation)
			super.render(g);
	}
}
