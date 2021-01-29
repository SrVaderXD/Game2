package com.HLF.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;

import com.HLF.entities.Enemy;
import com.HLF.entities.EnemySpawn;
import com.HLF.entities.Entity;
import com.HLF.entities.Player;
import com.HLF.graficos.Spritesheet;
import com.HLF.graficos.UI;
import com.HLF.world.World;

public class Game extends Canvas implements Runnable,KeyListener{

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 352;
	public static final int HEIGHT = 240;
	public static final int SCALE = 2;
	public static int life = 3;
	
	private int framesRestart = 0, framesGameOver = 0;
	
	private int currentLevel = 1, maxLevel = 5;
	
	public static boolean nextLevel = false, GameOver;
	
	private BufferedImage image;
	
	public static List<Entity> entities;
	public static List<Enemy> enemies;
	public static Spritesheet spritesheet;
	public static World world;
	public static Player player;
	
	public static EnemySpawn eSpawn;
	
	public UI ui;
	
	public Menu menu;
	public End end;
	public static String GameState = "Menu";
	
	public static int fruits = 0, totalFruits = 0;
	public static int score = 0;
	
	public static boolean restart = false;
	
	public Game(){
		addKeyListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		//Initializing objects
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(0,0,16,16,2,spritesheet.getSprite(32,0,16,16));
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		world = new World("/level"+currentLevel+".png");
		eSpawn = new EnemySpawn();
		ui = new UI();
		menu = new Menu();
		end = new End();
		entities.add(player);		
	}
	
	public void initFrame(){
		frame = new JFrame("PacMan");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		Game game = new Game();
		game.start();
	}
	
	public void tick(){
		
		if(GameState == "Normal") {
		
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
			
			eSpawn.tick();
			
			if(Player.death) {
			
				framesRestart++;
				
				if(framesRestart == 64) {
					framesRestart = 0;
					Player.death = false;
					life --;
					String level = "level"+currentLevel+".png";
					score = 0;
					fruits = 0;
					World.restartGame(level);
				}
			}
			
			if(life == 0) {
				currentLevel = 1;
				life = 3;
				String level = "level"+currentLevel+".png";
				score = 0;
				fruits = 0;
				World.restartGame(level);
			}
			
			nextLevel();
			
			if(currentLevel == maxLevel) {
				GameState = "TheEnd";
				currentLevel = 1;
			}
		}
		
		else if(GameState == "End") {
			entities.clear();
			framesGameOver++;
			if(framesGameOver == 30) {
				framesGameOver = 0;
				if(GameOver)
					GameOver = false;
				else
					GameOver = true;
			}
		
			if(restart) {
				restart = false;
				GameState = "Normal";
				String map = "map"+currentLevel+".png";
				World.restartGame(map);
			}
		}
		
		else if(GameState == "Menu") {
			menu.tick();
		}
		
		else if(GameState == "TheEnd") {
			entities.clear();
			end.tick();
			
			if(restart) {
				restart = false;
				GameState = "Normal";
				String map = "map"+currentLevel+".png";
				World.restartGame(map);
			}
		}
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0,WIDTH,HEIGHT);
		
		/*Game Render*/
		world.render(g);
		Collections.sort(entities,Entity.nodeSorter);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
		ui.render(g);
		
		if(GameState == "End") {
			Graphics2D g2 = (Graphics2D)g;
			g2.setColor(new Color(0,0,0,100));
			g2.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
			g.setFont(new Font("arial", Font.BOLD, 72));
			g.setColor(Color.white);
			g.drawString("GAME OVER", WIDTH/2 + 8, HEIGHT/2 + 130);
			g.setFont(new Font("arial", Font.BOLD, 36));
			g.setColor(Color.white);
			if(GameOver)
				g.drawString("Press 'R' to restart", WIDTH/2 + 72, HEIGHT/2 + 170);
		}
		
		else if(GameState == "Menu") {
			menu.render(g);
		}
		
		else if(GameState == "TheEnd") {
			entities.clear();
			end.render(g);
		}
		
		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println("FPS: "+ frames);
				frames = 0;
				timer+=1000;
			}
		}
		
		stop();
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.right = true;
			
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			player.up = true;
			if(GameState == "Menu")
				menu.up = true;
			
			if(GameState == "TheEnd")
				end.up = true;
			
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
			
			if(GameState == "Menu")
				menu.down = true;
			
			if(GameState == "TheEnd")
				end.down = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_R) {
				restart = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(GameState == "Menu")
				menu.enter = true;
			
			if(GameState == "TheEnd")
				end.enter = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if(GameState == "Normal" || GameState == "StageClear") {
				GameState = "Menu";
				menu.pause = true;
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			player.right = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT){
			player.left = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP){
			player.up = false;
			
			if(GameState == "Menu")
				menu.up = false;
			
			if(GameState == "TheEnd")
				end.up = false;
			
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
			
			if(GameState == "Menu")
				menu.down = false;
			
			if(GameState == "TheEnd")
				end.down = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_R) {
			restart = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(GameState == "Menu")
				menu.enter = false;
			
			if(GameState == "TheEnd")
				end.enter = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if(GameState == "Normal" || GameState == "StageClear") {
				GameState = "Menu";
				menu.pause = false;
			}
		}
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	public void nextLevel() {
		if(fruits == totalFruits) {
			
			nextLevel = true;
			currentLevel++;
	
			String level = "level"+currentLevel+".png";
			World.restartGame(level);		
				
		}
	}
}
