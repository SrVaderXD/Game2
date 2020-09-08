package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.HLF.main.Game;
import com.HLF.world.AStar;
import com.HLF.world.Camera;
import com.HLF.world.Vector2i;



public class Enemy extends Entity{
	
	public static boolean vulnerable = false;
	public int vulFrames = 0;
	private int ghostFrames = 0, blinkFrames = 0;
	private boolean animation = false, blink = false;
	

	public Enemy(int x, int y, int width, int height,int speed, BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
	}

	public void tick(){
		depth = 1;
		
		if(vulnerable == false) {
	
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
				
			animation = false;	
			vulFrames = 0;
		}
		
		else { //TODO ghosts run from player
			vulFrames++;
			
			if(vulFrames == 120)
				animation = true;
			
			if(vulFrames == 360) {
				vulnerable = false;
				blink = false;
			}
			
		}
		
		System.out.println("vulnerable:"+vulnerable + " " + vulFrames+"\n");
		System.out.println("animation:"+animation +"\n");
		System.out.println("blink:"+blink + " " + blinkFrames+"\n");

	}
	
	public void render(Graphics g) {
		
		if(vulnerable == false)
			super.render(g);
		
		else if(vulnerable == true) {
			
			if(animation) {
				blinkFrames++;
				
				if(blinkFrames == 30) {
					blinkFrames = 0;
					
					if(blink)
						blink = false;
					
					else
						blink = true;
				}
			}
			
			if(blink == false) {
				g.drawImage(Entity.ENEMY_GHOST_TYPE_1,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}

			if(blink == true) {
				g.drawImage(Entity.ENEMY_GHOST_TYPE_2,this.getX() - Camera.x,this.getY() - Camera.y,null);
			}
		}
	}
}
