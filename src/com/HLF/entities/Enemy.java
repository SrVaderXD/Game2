package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.HLF.main.Game;
import com.HLF.world.AStar;
import com.HLF.world.Camera;
import com.HLF.world.Vector2i;

public class Enemy extends Entity{
	
	public static boolean vulnerable = false;
	public static int vulFrames = 0;
	public static int blinkFrames = 0;
	public static boolean animation = false, blink = false;

	public int time = 360; // respawn time
	public int curTime = 0;
	
	public static int dir = 1;
	
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
			
			vulFrames++;
			
			if(vulFrames == 120)
				animation = true;
			
			if(vulFrames == 360) {
				vulnerable = false;
				blink = false;
			}
		}
		
		collisionWithPlayer();
		System.out.println("REDTIME:"+RedEnemy.curRedTime);
		System.out.println("PINKTIME:"+PinkEnemy.curPinkTime);
		System.out.println("ORANGETIME:"+OrangeEnemy.curOrangeTime);
		System.out.println("BLUETIME:"+BlueEnemy.curBlueTime);
		//System.out.println("VERMELHO MORTO:"+RedEnemy.rDead);
		//System.out.println("ROSA MORTO:"+PinkEnemy.pDead);
		//System.out.println("LARANJA MORTO:"+OrangeEnemy.oDead);
		//System.out.println("AZUL MORTO:"+BlueEnemy.bDead);
	}
	
	public void render(Graphics g) {
							
		if(vulnerable == true) {
			
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
	
	private int xEscape(int xplayer, int xenemy) {
		int Dx, dx;
		
		Dx = (xplayer - xenemy);
		
		if(Dx > 0)
			dx = 1;
		
		else
			dx = -1;
		
		return (xenemy + (dx*Dx));
	}
	
	private int yEscape(int yplayer, int yenemy) {
		
		int Dy, dy;
		
		Dy = (yplayer - yenemy);
		
		if(Dy > 0)
			dy = 1;
		
		else
			dy = -1;
		
		return (yenemy + (dy*Dy));
	}
	
	public void collisionWithPlayer() {
		
	}
}
