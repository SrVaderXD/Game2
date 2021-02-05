package com.HLF.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Menu {
	
	public String[] options = {"Start Game", "How to play", "Exit"};
	
	public int currentOption = 0, maxOption = (options.length)-1;

	public boolean up, down, enter;
	public boolean pause = false;
	public static boolean tutorial = false;
	
	public void tick() {
		
		if(up) {
			up = false;
			currentOption--;
			if(currentOption < 0)
				currentOption = maxOption;
		}
		
		if(down) {
			down = false;
			currentOption++;
			if(currentOption > maxOption)
				currentOption = 0;
		}
		
		if(enter) {
			//Sound.backGround.loop();
			enter = false;
			
			if(options[currentOption] == "Start Game" || options[currentOption] == "Resume") {
				Game.GameState = "Normal";
				pause = false;
				tutorial = false;
			}
			
			else if(options[currentOption] == "How to play") {
				tutorial = true;
				Game.GameState = "Tutorial";
			}
			
			else if(options[currentOption] == "Exit") {
				System.exit(1);
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(new Color(0,0,0,200));
		g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
			
		g.setColor(Color.yellow);
		g.setFont(new Font("arial", Font.BOLD, 56));
		g.drawString("Pacman", Game.WIDTH / 2 + 60, Game.HEIGHT / 2 - 30);
			
		//Menu options
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 36));
			
			
		if(!pause)
			g.drawString("Start Game", Game.WIDTH / 2 + 80, Game.HEIGHT / 2 + 70);
			
		else
			g.drawString("Resume", Game.WIDTH / 2 + 100, Game.HEIGHT / 2 + 70);
			
		g.drawString("How to play", Game.WIDTH / 2 + 80, Game.HEIGHT / 2 + 190);
			
		g.drawString("Exit", Game.WIDTH / 2 + 140, Game.HEIGHT / 2 + 310);
		
		
		if(options[currentOption] == "Start Game") {
			g.drawString(">", Game.WIDTH / 2 + 50, Game.HEIGHT / 2 + 70);
		}
			
		else if(options[currentOption] == "How to play") {
			g.drawString(">", Game.WIDTH / 2 + 50, Game.HEIGHT / 2 + 190);
		}
			
		else if(options[currentOption] == "Exit") {
			g.drawString(">", Game.WIDTH / 2 + 110, Game.HEIGHT / 2 + 310);
		}
	}
}
