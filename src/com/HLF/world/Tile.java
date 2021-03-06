package com.HLF.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.HLF.main.Game;

public class Tile {
	
	public static BufferedImage FLOOR_TILE = Game.spritesheet.getSprite(0,0,16,16);
	public static BufferedImage WALL_TILE_TYPE_1 = Game.spritesheet.getSprite(16,0,16,16);
	public static BufferedImage WALL_TILE_TYPE_2 = Game.spritesheet.getSprite(16,16,16,16);
	public static BufferedImage WALL_TILE_TYPE_3 = Game.spritesheet.getSprite(16,32,16,16);
	public static BufferedImage WALL_TILE_TYPE_4 = Game.spritesheet.getSprite(16,48,16,16);
	public static BufferedImage WALL_TILE_TYPE_5 = Game.spritesheet.getSprite(16,64,16,16);
	public static BufferedImage WALL_TILE_TYPE_6 = Game.spritesheet.getSprite(16,80,16,16);
	public static BufferedImage WALL_TILE_TYPE_7 = Game.spritesheet.getSprite(16,96,16,16);
	public static BufferedImage WALL_TILE_TYPE_8 = Game.spritesheet.getSprite(16,112,16,16);
	public static BufferedImage WALL_TILE_TYPE_9 = Game.spritesheet.getSprite(0,128,16,16);
	public static BufferedImage WALL_TILE_TYPE_10 = Game.spritesheet.getSprite(16,144,16,16);
	public static BufferedImage WALL_TILE_TYPE_11 = Game.spritesheet.getSprite(0,32,16,16);
	public static BufferedImage WALL_TILE_TYPE_12 = Game.spritesheet.getSprite(0,48,16,16);
	public static BufferedImage WALL_TILE_TYPE_13 = Game.spritesheet.getSprite(0,64,16,16);
	public static BufferedImage WALL_TILE_TYPE_14 = Game.spritesheet.getSprite(0,80,16,16);
	public static BufferedImage GATE_TILE_TYPE_1 = Game.spritesheet.getSprite(0,96,16,16);
	public static BufferedImage GATE_TILE_TYPE_2 = Game.spritesheet.getSprite(0,112,16,16);


	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
