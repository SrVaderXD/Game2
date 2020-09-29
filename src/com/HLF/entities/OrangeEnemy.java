package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class OrangeEnemy extends Enemy{

	public OrangeEnemy(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void render(Graphics g) {
		
		if(!Enemy.vulnerable) {
			
			if(Enemy.dir == 1) {
				g.drawImage(Entity.ENEMY_ORANGE_R,this.getX(),this.getY(),null);
			}
			
			else if(Enemy.dir == 2) {
				g.drawImage(Entity.ENEMY_ORANGE_L,this.getX(),this.getY(),null);
			}
			
			else if(Enemy.dir == 3) {
				g.drawImage(Entity.ENEMY_ORANGE_D,this.getX(),this.getY(),null);
			}
			
			else if(Enemy.dir == 4) {
				g.drawImage(Entity.ENEMY_ORANGE_U,this.getX(),this.getY(),null);
			}
		}
		
		else {
			super.render(g);
		}
	}

}
