package com.HLF.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.HLF.entities.Apple;
import com.HLF.entities.BlueEnemy;
import com.HLF.entities.Entity;
import com.HLF.entities.OrangeEnemy;
import com.HLF.entities.PinkEnemy;
import com.HLF.entities.Player;
import com.HLF.entities.RedEnemy;
import com.HLF.entities.VulnerableFruit;
import com.HLF.main.Game;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	
	
	public World(String path){
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int[] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(),pixels, 0, map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++){
				for(int yy = 0; yy < map.getHeight(); yy++){
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					if(pixelAtual == 0xFF000000){
						//Floor
						tiles[xx + (yy * WIDTH)] = new FloorTile(xx*16,yy*16,Tile.TILE_FLOOR);
					}else if(pixelAtual == 0xFFFFFFFF){
						//Wall type 1
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_TYPE_1);
					}else if(pixelAtual == 0xFFC0C0C0) {
						//Wall type 2
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_TYPE_2);
					}else if(pixelAtual == 0xFFA0A0A0) {
						//Wall type 3
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_TYPE_3);
					}else if(pixelAtual == 0xFF808080) {
						//Wall type 4
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_TYPE_4);
					}else if(pixelAtual == 0xFF303030) {
						//Wall type 5
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_TYPE_5);
					}else if(pixelAtual == 0xFF606060) {
						//Wall type 6
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_TYPE_6);
					}else if(pixelAtual == 0xFF404040) {
						//Wall type 7
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_TYPE_7);
					}else if(pixelAtual == 0xFF7F3F3F) {
						//Wall type 8
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_TYPE_8);
					}else if(pixelAtual == 0xFF7F593F) {
						//Wall type 9
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_TYPE_9);
					}else if(pixelAtual == 0xFF7F743F) {
						//Wall type 10
						tiles[xx + (yy * WIDTH)] = new WallTile(xx*16,yy*16,Tile.TILE_WALL_TYPE_10);
					}else if(pixelAtual == 0xFF0026FF) {
						//Player
						Game.player.setX(xx*16);
						Game.player.setY(yy*16);
					}else if(pixelAtual == 0xFFFF0000) {
						//Enemy type 1
						RedEnemy e = new RedEnemy(xx*16,yy*16,16,16,1,Entity.ENEMY_RED_R);
						Game.entities.add(e);
					}else if(pixelAtual == 0xFF00FFFF) {
						//Enemy type 2
						BlueEnemy e = new BlueEnemy(xx*16,yy*16,16,16,1,Entity.ENEMY_BLUE_R);
						Game.entities.add(e);
					}else if(pixelAtual == 0xFFFF6A00) {
						//Enemy type 3
						OrangeEnemy e = new OrangeEnemy(xx*16,yy*16,16,16,1,Entity.ENEMY_ORANGE_R);
						Game.entities.add(e);
					}else if(pixelAtual == 0xFFFDC2D4) {
						//Enemy type 4
						PinkEnemy e = new PinkEnemy(xx*16,yy*16,16,16,1,Entity.ENEMY_PINK_R);
						Game.entities.add(e);
					}else if(pixelAtual == 0xFFFFD800) {
						// Apple
						Apple apple = new Apple(xx*16,yy*16,16,16,0,Entity.Apple_Sprite);
						Game.entities.add(apple);
						Game.fruits++;
				
					}else if(pixelAtual == 0xFFFFB594) {
						// VulnerableFruit
						VulnerableFruit fruit = new VulnerableFruit(xx*16,yy*16,16,16,0,Entity.VulnerableFruit_Sprite);
						Game.entities.add(fruit);
						Game.fruits++;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xnext,int ynext){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	
	public static void restartGame(){
		Game.player = new Player(0,0,16,16,2,Game.spritesheet.getSprite(32,0,16,16));
		Game.entities.clear();		
		Game.entities.add(Game.player);
		Game.fruits = 0;
		Game.score = 0;
		Game.world = new World("/level1.png");
		return;
	}
	
	public void render(Graphics g){
		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
			}
		}
	}
	
}
