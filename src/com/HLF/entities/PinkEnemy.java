package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.HLF.main.Game;
import com.HLF.world.AStar;
import com.HLF.world.Camera;
import com.HLF.world.Vector2i;

public class PinkEnemy extends Enemy{
	
	public static int pDir = 1;
	
	public static boolean pVulnerable = false;
	public int pVulFrames = 0, pBlinkFrames = 0;
	public boolean pAnimation = false, pBlink = false;
	
	public static boolean pDead = false;
	public static int pinkTime = 300; // pink respawn time
	public static int curPinkTime = 0;

	public PinkEnemy(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		depth = 1;
		
		if(pVulnerable == false) {
	
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
				
			pAnimation = false;	
			pVulFrames = 0;
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
			
			pVulFrames++;
			
			if(pVulFrames == 120)
				pAnimation = true;
			
			if(pVulFrames == 360) {
				pVulnerable = false;
				pBlink = false;
			}
		}
		
		collisionWithPlayer();
	}
	
	public void render(Graphics g) {
		
		if(!pVulnerable) {
			
			if(pDir == 1) {
				g.drawImage(Entity.ENEMY_PINK_R,this.getX(),this.getY(),null);
			}
			
			else if(pDir == 2) {
				g.drawImage(Entity.ENEMY_PINK_L,this.getX(),this.getY(),null);
			}
			
			else if(pDir == 3) {
				g.drawImage(Entity.ENEMY_PINK_D,this.getX(),this.getY(),null);
			}
			
			else if(pDir == 4) {
				g.drawImage(Entity.ENEMY_PINK_U,this.getX(),this.getY(),null);
			}
		}
		
		if(pVulnerable) {
			
			if(pAnimation) {
				pBlinkFrames++;
				
				if(pBlinkFrames == 30) {
					pBlinkFrames = 0;
					
					if(pBlink)
						pBlink = false;
					
					else
						pBlink = true;
				}
			}
			
			if(pBlink == false) {
				g.drawImage(Entity.ENEMY_GHOST_TYPE_1,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}

			if(pBlink == true) {
				g.drawImage(Entity.ENEMY_GHOST_TYPE_2,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}
		}
	}
	
	public void collisionWithPlayer() {
		
		if(!pVulnerable && Entity.isColliding(this, Game.player)) {
			
			//World.restartGanes("/level1.png");
		}
		
		else if(pVulnerable && Entity.isColliding(this, Game.player)) {
			
			pDead = true;
			Game.entities.remove(this);
			Game.enemies.remove(this);
		}
	}

}
