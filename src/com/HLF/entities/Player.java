package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.HLF.main.Game;
import com.HLF.world.Camera;
import com.HLF.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	
	public static int dir = 1;
	
	public BufferedImage CIRCLE_SPRITE;
	
	private BufferedImage rightPlayer[]; // Direction players sprite
	private BufferedImage leftPlayer[];
	private BufferedImage upPlayer[];
	private BufferedImage downPlayer[];
	
	private int frames = 0, maxFrames = 5, index = 0, maxIndex = 2; // variables to animate pacman
	private boolean moved = false;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);

		rightPlayer = new BufferedImage[3];
		leftPlayer = new BufferedImage[3];
		upPlayer = new BufferedImage[3];
		downPlayer = new BufferedImage[3];
		
		
		for(int i = 0; i < 3; i++) { // right
			rightPlayer[i] = Game.spritesheet.getSprite(96 + (i*16), 0, 16, 16);
		}
		
		for(int i = 0; i < 3; i++) { // left
			leftPlayer[i] = Game.spritesheet.getSprite(96 + (i*16), 16, 16, 16);
		}
		
		for(int i = 0; i < 3; i++) { // up
			upPlayer[i] = Game.spritesheet.getSprite(96 + (i*16), 32, 16, 16);
		}
		
		for(int i = 0; i < 3; i++) { // down
			downPlayer[i] = Game.spritesheet.getSprite(96 + (i*16), 48, 16, 16);
		}
	}
	
	public void tick(){
		depth = 1;
		
		//System.out.println("xatual: "+x+" "+"yatual: "+y);
		//System.out.println("xdepois: "+x+" "+"ydepois: "+y);
		
		moved = false;
		
		if(right && World.isFree((int)(x+speed),this.getY())) {
			x+=speed;
			dir = 1;
			moved = true;
		}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			x-=speed;
			dir = 2;
			moved = true;
		}
		if(up && World.isFree(this.getX(),(int)(y-speed))){
			y-=speed;
			dir = 3;
			moved = true;
		}
		else if(down && World.isFree(this.getX(),(int)(y+speed))){
			y+=speed;
			dir = 4;
			moved = true;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) 
					index = 0;
			}
		}
		//death();
		checkCollisionWithItems();
		checkNextStage();
	}
	
	private void checkNextStage() {
		//TODO
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
			
			if(current instanceof Banana) {
				if(isColliding(this, current)) {
					Game.score++;
					Game.entities.remove(i);
					return;
				}
			}
				
			if(current instanceof Strawberry) {
				if(isColliding(this, current)) {
					Game.score++;
					Game.entities.remove(i);
					return;
				}
			}
				
			if(current instanceof Kiwi) {
				if(isColliding(this, current)) {
					Game.score++;
					Game.entities.remove(i);
					return;
				}
			}
			
			if(current instanceof Cookie) {
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
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		else if(dir == 2){
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		else if(dir == 3){
			g.drawImage(upPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		else if(dir == 4){
			g.drawImage(downPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}
}
