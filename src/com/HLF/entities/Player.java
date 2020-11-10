package com.HLF.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.HLF.main.Game;
import com.HLF.world.Camera;
import com.HLF.world.World;

public class Player extends Entity{
	
	public boolean right,up,left,down;
	
	public static int dir = 1;
	
	public static boolean death = false;
	
	public BufferedImage CIRCLE_SPRITE;
	
	private BufferedImage rightPlayer[]; // Direction players sprite
	private BufferedImage leftPlayer[];
	private BufferedImage upPlayer[];
	private BufferedImage downPlayer[];
	private BufferedImage deathAnimation[];
	
	private int walkFrames = 0,walkMaxFrames = 5,walkIndex = 0,walkMaxIndex = 2; // variables to animate pacman walk
	private int deathFrames = 0,deathMaxFrames = 5,deathIndex = 0,deathMaxIndex = 7; // variables to animate pacman death
	private boolean moved = false;
	
	public static int life = 3;

	public Player(int x, int y, int width, int height,double speed,BufferedImage sprite) {
		super(x, y, width, height,speed,sprite);

		rightPlayer = new BufferedImage[3];
		leftPlayer = new BufferedImage[3];
		upPlayer = new BufferedImage[3];
		downPlayer = new BufferedImage[3];
		deathAnimation = new BufferedImage[8];
		
		
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
		
		for(int i = 0; i < 8; i++) { // death animation
			deathAnimation[i] = Game.spritesheet.getSprite(32 + (i*16), 128, 16, 16);
		}
	}
	
	public void tick(){
		depth = 1;
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
			walkFrames++;
			if(walkFrames == walkMaxFrames) {
				walkFrames = 0;
				walkIndex++;
				if(walkIndex > walkMaxIndex) 
					walkIndex = 0;
			}
		}
			
		if(death) {
			deathFrames++;
			if(deathFrames == deathMaxFrames) {
				deathFrames = 0;
				deathIndex++;
				if(deathIndex > deathMaxIndex) 
					deathIndex = 0;
			}
		}
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
					Game.fruits++;
					Game.entities.remove(i);
					Game.score += 75;
					return;
				}
			}
			
			if(current instanceof Banana) {
				if(isColliding(this, current)) {
					Game.fruits++;
					Game.entities.remove(i);
					Game.score += 100;
					return;
				}
			}
				
			if(current instanceof Strawberry) {
				if(isColliding(this, current)) {
					Game.fruits++;
					Game.entities.remove(i);
					Game.score += 125;
					return;
				}
			}
				
			if(current instanceof Kiwi) {
				if(isColliding(this, current)) {
					Game.fruits++;
					Game.entities.remove(i);
					Game.score += 200;
					death = true;
					return;
				}
			}
			
			if(current instanceof Cookie) {
				if(isColliding(this, current)) {
					BlueEnemy.bVulnerable = true;
					OrangeEnemy.oVulnerable = true;
					PinkEnemy.pVulnerable = true;
					RedEnemy.rVulnerable = true;
					Game.entities.remove(i);
					return;
				}
			}
			
			if(current instanceof Dot) {
				if(isColliding(this, current)) {
					Game.entities.remove(i);
					Game.score += 5;
					return;
				}
			}
		}
		
	}
	
	public void render(Graphics g) {
		
		if(!death) {
		
			if(dir == 1) {
				g.drawImage(rightPlayer[walkIndex], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			
			else if(dir == 2){
				g.drawImage(leftPlayer[walkIndex], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			
			else if(dir == 3){
				g.drawImage(upPlayer[walkIndex], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
			
			else if(dir == 4){
				g.drawImage(downPlayer[walkIndex], this.getX() - Camera.x, this.getY() - Camera.y, null);
			}
		}
		
		else {
			g.drawImage(deathAnimation[deathIndex], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
	}
}
