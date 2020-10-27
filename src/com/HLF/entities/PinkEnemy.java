package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.HLF.main.Game;

public class PinkEnemy extends Enemy{
	
	public static boolean pDead = false;
	
	public static int pinkTime = 360; // pink respawn time
	public static int curPinkTime = 0;

	public PinkEnemy(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void render(Graphics g) {
		
		if(!Enemy.vulnerable) {
			
			if(Enemy.dir == 1) {
				g.drawImage(Entity.ENEMY_PINK_R,this.getX(),this.getY(),null);
			}
			
			else if(Enemy.dir == 2) {
				g.drawImage(Entity.ENEMY_PINK_L,this.getX(),this.getY(),null);
			}
			
			else if(Enemy.dir == 3) {
				g.drawImage(Entity.ENEMY_PINK_D,this.getX(),this.getY(),null);
			}
			
			else if(Enemy.dir == 4) {
				g.drawImage(Entity.ENEMY_PINK_U,this.getX(),this.getY(),null);
			}
		}
		
		else {
			super.render(g);
		}
	}
	
	public void collisionWithPlayer() {
		
		if(!vulnerable && Entity.isColliding(this, Game.player)) {
			//TODO player death animation
			
			//World.restartGanes("/level1.png");
		}
		
		else if(vulnerable && Entity.isColliding(this, Game.player)) {
			//TODO ENEMY DISAPPEAR, SCORE WILL INCREASE AND THEY WILL RESPAWN
			
			pDead = true;
			Game.entities.remove(this);
			Game.enemies.remove(this);
		}
	}

}



