package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.HLF.main.Game;
import com.HLF.world.AStar;
import com.HLF.world.Camera;
import com.HLF.world.Vector2i;

public class RedEnemy extends Enemy{
	
	public static int rDir = 1;
	
	public static boolean rVulnerable = false;
	public int rVulFrames = 0, rBlinkFrames = 0;
	public boolean rAnimation = false, rBlink = false;
	
	public static boolean rDead = false;
	public static int redTime = 300; // red respawn time
	public static int curRedTime = 0;

	public RedEnemy(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		depth = 1;
		
		if(rVulnerable == false) {
	
			if(path == null || path.size() == 0) {
					Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
					Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
					path = AStar.findPath(Game.world, start, end);
				}
			
				followPath(path);
				
				if(x % 16 == 0 && y % 16 == 0) {
					Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
					Vector2i end = new Vector2i(((int)(Game.player.x/16)),((int)(Game.player.y/16)));
					path = AStar.findPath(Game.world, start, end);
				}
				
			rAnimation = false;	
			rVulFrames = 0;
		}
		
		else {
			
			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
				Vector2i end = new Vector2i(((int)(Enemy.xEscape/16)),((int)(Enemy.yEscape/16)));
				path = AStar.findPath(Game.world, start, end);
			}
		
			followPath(path);
			
			if(x % 16 == 0 && y % 16 == 0) {
				Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
				Vector2i end = new Vector2i(((int)(Enemy.xEscape/16)),((int)(Enemy.yEscape/16)));
				path = AStar.findPath(Game.world, start, end);
			}
			
			rVulFrames++;
			
			if(rVulFrames == 120)
				rAnimation = true;
			
			if(rVulFrames == 360) {
				rVulnerable = false;
				rBlink = false;
			}
		}
		
		collisionWithPlayer();
		if(Player.death) {
			Game.entities.remove(this);
		}
	}
	
	public void render(Graphics g) {

		if(!rVulnerable) {
		
			if(rDir == 1) {
				g.drawImage(Entity.ENEMY_RED_R,this.getX(),this.getY(),null);
			}
			
			else if(rDir == 2) {
				g.drawImage(Entity.ENEMY_RED_L,this.getX(),this.getY(),null);
			}
			
			else if(rDir == 3) {
				g.drawImage(Entity.ENEMY_RED_D,this.getX(),this.getY(),null);
			}
			
			else if(rDir == 4) {
				g.drawImage(Entity.ENEMY_RED_U,this.getX(),this.getY(),null);
			}
		}
		
		if(rVulnerable) {
			
			if(rAnimation) {
				rBlinkFrames++;
				
				if(rBlinkFrames == 30) {
					rBlinkFrames = 0;
					
					if(rBlink)
						rBlink = false;
					
					else
						rBlink = true;
				}
			}
			
			if(rBlink == false) {
				g.drawImage(Entity.ENEMY_GHOST_TYPE_1,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}

			if(rBlink == true) {
				g.drawImage(Entity.ENEMY_GHOST_TYPE_2,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}
		}
		
	}
	
	public void collisionWithPlayer() {
		
		if(!rVulnerable && Entity.isColliding(this, Game.player)) {
			Player.death = true;
		}
		
		else if(rVulnerable && Entity.isColliding(this, Game.player)) {
			
			rDead = true;
			Game.entities.remove(this);
			Game.enemies.remove(this);
			Game.score += 600;
		}
	}
}
