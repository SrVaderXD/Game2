package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.HLF.main.Game;

public class RedEnemy extends Enemy{
	
	public static boolean rDead = false;
	public static int redTime = 360; // red respawn time
	public static int curRedTime = 0;

	public RedEnemy(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void render(Graphics g) {

		if(!Enemy.vulnerable) {
		
			if(Enemy.dir == 1) {
				g.drawImage(Entity.ENEMY_RED_R,this.getX(),this.getY(),null);
			}
			
			else if(Enemy.dir == 2) {
				g.drawImage(Entity.ENEMY_RED_L,this.getX(),this.getY(),null);
			}
			
			else if(Enemy.dir == 3) {
				g.drawImage(Entity.ENEMY_RED_D,this.getX(),this.getY(),null);
			}
			
			else if(Enemy.dir == 4) {
				g.drawImage(Entity.ENEMY_RED_U,this.getX(),this.getY(),null);
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
			
			rDead = true;
			Game.entities.remove(this);
			Game.enemies.remove(this);
		}
	}
}
