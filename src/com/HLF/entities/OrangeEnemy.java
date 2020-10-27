package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.HLF.main.Game;

public class OrangeEnemy extends Enemy{
	
	public static boolean oDead = false;
	public static int orangeTime = 360; // orange respawn time
	public static int curOrangeTime = 0;

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
	
	public void collisionWithPlayer() {
		
		if(!vulnerable && Entity.isColliding(this, Game.player)) {
			//TODO player death animation
			
			//World.restartGanes("/level1.png");
		}
		
		else if(vulnerable && Entity.isColliding(this, Game.player)) {
			//TODO ENEMY DISAPPEAR, SCORE WILL INCREASE AND THEY WILL RESPAWN
			
			oDead = true;
			Game.entities.remove(this);
			Game.enemies.remove(this);
		}
	}
}
