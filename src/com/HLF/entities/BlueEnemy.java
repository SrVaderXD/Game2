package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BlueEnemy extends Enemy{

	public BlueEnemy(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void render(Graphics g) {
		
		if(!Enemy.vulnerable) {
			
			if(Enemy.dir == 1) {
				g.drawImage(Entity.ENEMY_BLUE_R,this.getX(),this.getY(),null);
			}
			
			else if(Enemy.dir == 2) {
				g.drawImage(Entity.ENEMY_BLUE_L,this.getX(),this.getY(),null);
			}
			
			else if(Enemy.dir == 3) {
				g.drawImage(Entity.ENEMY_BLUE_D,this.getX(),this.getY(),null);
			}
			
			else if(Enemy.dir == 4) {
				g.drawImage(Entity.ENEMY_BLUE_U,this.getX(),this.getY(),null);
			}
		}
		
		else {
			super.render(g);
		}
	}

}
