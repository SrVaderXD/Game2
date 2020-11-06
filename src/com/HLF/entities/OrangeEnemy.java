package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.HLF.main.Game;
import com.HLF.world.AStar;
import com.HLF.world.Camera;
import com.HLF.world.Vector2i;

public class OrangeEnemy extends Enemy{
	
	public static int oDir = 1;
	
	public static boolean oVulnerable = false;
	public int oVulFrames = 0, oBlinkFrames = 0;
	public boolean oAnimation = false, oBlink = false;
	
	public static boolean oDead = false;
	public static int orangeTime = 300; // orange respawn time
	public static int curOrangeTime = 0;

	public OrangeEnemy(int x, int y, int width, int height, int speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		depth = 1;
		
		if(oVulnerable == false) {
	
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
				
			oAnimation = false;	
			oVulFrames = 0;
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
			
			oVulFrames++;
			
			if(oVulFrames == 120)
				oAnimation = true;
			
			if(oVulFrames == 360) {
				oVulnerable = false;
				oBlink = false;
			}
		}
		
		collisionWithPlayer();
	}
	
	public void render(Graphics g) {
		
		if(!oVulnerable) {
			
			if(oDir == 1) {
				g.drawImage(Entity.ENEMY_ORANGE_R,this.getX(),this.getY(),null);
			}
			
			else if(oDir == 2) {
				g.drawImage(Entity.ENEMY_ORANGE_L,this.getX(),this.getY(),null);
			}
			
			else if(oDir == 3) {
				g.drawImage(Entity.ENEMY_ORANGE_D,this.getX(),this.getY(),null);
			}
			
			else if(oDir == 4) {
				g.drawImage(Entity.ENEMY_ORANGE_U,this.getX(),this.getY(),null);
			}
		}
		
		if(oVulnerable) {
			
			if(oAnimation) {
				oBlinkFrames++;
				
				if(oBlinkFrames == 30) {
					oBlinkFrames = 0;
					
					if(oBlink)
						oBlink = false;
					
					else
						oBlink = true;
				}
			}
			
			if(oBlink == false) {
				g.drawImage(Entity.ENEMY_GHOST_TYPE_1,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}

			if(oBlink == true) {
				g.drawImage(Entity.ENEMY_GHOST_TYPE_2,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}
		}
	}
	
	public void collisionWithPlayer() {
		
		if(!oVulnerable && Entity.isColliding(this, Game.player)) {
			
			//World.restartGanes("/level1.png");
		}
		
		else if(oVulnerable && Entity.isColliding(this, Game.player)) {
			
			oDead = true;
			Game.entities.remove(this);
			Game.enemies.remove(this);
		}
	}
}
