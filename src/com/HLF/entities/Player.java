package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.HLF.main.Game;
import com.HLF.world.Camera;
import com.HLF.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	
	public static int dir = 1;
	
	public BufferedImage sprite_left;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);
		sprite_left = Game.spritesheet.getSprite(48, 0, 16, 16);
	}
	
	public void tick(){
		depth = 1;
		
		System.out.println("xatual: "+x+" "+"yatual: "+y);
		
		if(right && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;
			dir = 1;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
			dir = 2;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
			//dir = 3;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
			//dir = 4;
		}
		
		checkCollisionWithItems();
		checkVictory();
		System.out.println("xdepois: "+x+" "+"ydepois: "+y);
	}

	private void checkVictory() {
		
		if(Game.score == Game.fruits) {
			World.restartGame();
		}
		
	}

	public void checkCollisionWithItems() { // this will check collision with every item
		
		for(int i = 0; i < Game.entities.size(); i++) {
			
			Entity current = Game.entities.get(i);
			
			if(current instanceof Apple) {
				if(isColliding(this, current)) {
					Game.score++;
					Game.entities.remove(i);
					return;
				}
			}
			
			if(current instanceof VulnerableFruit) {
				if(Enemy.vulnerable == false) {
					if(isColliding(this, current)) {
						Enemy.vulnerable = true;
						Game.entities.remove(i);
						return;
					}
				}
			}
		}
		
	}

	public void render(Graphics g) {
		
		if(dir == 1) {
			super.render(g);
		}
		
		else if(dir == 2){
			g.drawImage(sprite_left,this.getX() - Camera.x,this.getY() - Camera.y,null);
		}
	}


}
