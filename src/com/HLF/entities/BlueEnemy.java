package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.HLF.main.Game;
import com.HLF.world.AStar;
import com.HLF.world.Camera;
import com.HLF.world.Vector2i;

public class BlueEnemy extends Enemy{
	
	public static int bDir = 1 ;
	
	public static boolean bVulnerable = false;
	public int bVulFrames = 0, bBlinkFrames = 0;
	public boolean bAnimation = false, bBlink = false;

	public static boolean bDead = false;
	public static int blueTime = 300; // blue respawn time
	public static int curBlueTime = 0;
	
	public BlueEnemy(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		depth = 1;
		
		if(bVulnerable == false) {
	
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
				
			bAnimation = false;	
			bVulFrames = 0;
		}
		
		else {
			
			if(path == null || path.size() == 0) {
				Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
				Vector2i end = new Vector2i(xEscape(((int)(Game.player.x/16)),((int)(x/16))), 
						yEscape(((int)(Game.player.y/16)),((int)(y/16))));
				
				path = AStar.findPath(Game.world, start, end);
			}
		
			followPath(path);
			
			if(x % 16 == 0 && y % 16 == 0) {
				Vector2i start = new Vector2i(((int)(x/16)),((int)(y/16)));
				Vector2i end = new Vector2i(xEscape(((int)(Game.player.x/16)),((int)(x/16))), 
						yEscape(((int)(Game.player.y/16)),((int)(y/16))));
				path = AStar.findPath(Game.world, start, end);
			}
			
			bVulFrames++;
			
			if(bVulFrames == 120)
				bAnimation = true;
			
			if(bVulFrames == 360) {
				bVulnerable = false;
				bBlink = false;
			}
		}
		
		collisionWithPlayer();
	}
	
	public void render(Graphics g) {
		
		if(!bVulnerable) {
			
			if(bDir == 1) {
				g.drawImage(Entity.ENEMY_BLUE_R,this.getX(),this.getY(),null);
			}
			
			else if(bDir == 2) {
				g.drawImage(Entity.ENEMY_BLUE_L,this.getX(),this.getY(),null);
			}
			
			else if(bDir == 3) {
				g.drawImage(Entity.ENEMY_BLUE_D,this.getX(),this.getY(),null);
			}
			
			else if(bDir == 4) {
				g.drawImage(Entity.ENEMY_BLUE_U,this.getX(),this.getY(),null);
			}
		}
		
		if(bVulnerable) {
			
			if(bAnimation) {
				bBlinkFrames++;
				
				if(bBlinkFrames == 30) {
					bBlinkFrames = 0;
					
					if(bBlink)
						bBlink = false;
					
					else
						bBlink = true;
				}
			}
			
			if(bBlink == false) {
				g.drawImage(Entity.ENEMY_GHOST_TYPE_1,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}

			if(bBlink == true) {
				g.drawImage(Entity.ENEMY_GHOST_TYPE_2,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}
		}
	}
	
	public void collisionWithPlayer() {
		
		if(!bVulnerable && Entity.isColliding(this, Game.player)) {
			
			//World.restartGanes("/level1.png");
		}
		
		else if(bVulnerable && Entity.isColliding(this, Game.player)) {
			
			bDead = true;
			Game.entities.remove(this);
			Game.enemies.remove(this);
		}
	}

}
