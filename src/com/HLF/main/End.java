package com.HLF.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class End {
	
	public static BufferedImage LIFE_ICON = Game.spritesheet.getSprite(0,16,16,16);
	
	public String[] options = {"Play again", "Exit"};
	
	public int currentOption = 0, maxOption = (options.length)-1;

	public boolean up, down, enter;
	
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
			enter = false;
			
			if(options[currentOption] == "Play again") {
				Game.restart = true;
			}
			
			else if(options[currentOption] == "Exit") {
				System.exit(1);
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics g2 = (Graphics)g;
		g2.setColor(Color.black);
		g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
			
		g.setColor(Color.red);
		g.setFont(new Font("arial", Font.BOLD, 40));
		g.drawString("The end", Game.WIDTH/2 + 105, Game.HEIGHT/2 - 20);
		g.drawString("Thank you for playing", Game.WIDTH/2 - 25, Game.HEIGHT/2+ 50);
			
		//Menu options
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 36));
		
		g.drawString("Play again", Game.WIDTH/2 + 102, Game.HEIGHT/2 + 120);
		g.drawString("Exit", Game.WIDTH/2 + 140, Game.HEIGHT/2 + 240);
		
		if(options[currentOption] == "Play again") {
			g.drawImage(LIFE_ICON,Game.WIDTH/2 + 60, Game.HEIGHT/2 + 93,32,32,null);
		}
			
		else if(options[currentOption] == "Exit") {
			g.drawImage(LIFE_ICON,Game.WIDTH/2 + 100, Game.HEIGHT/2 + 213,32,32,null);
		}
	}
}
