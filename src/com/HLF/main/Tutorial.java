package com.HLF.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Tutorial {

	public String[] options = {"Back"};
	
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
			
			if(options[currentOption] == "Back") {
				Menu.tutorial = false;
				Game.GameState = "Menu";
			}
			
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(new Color(0,0,0,200));
		g2.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("arial", Font.BOLD, 26));

		//option
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 36));
		g.drawString("Back", Game.WIDTH/2 + 400, Game.HEIGHT/2 + 300);
		
		if(options[currentOption] == "Back") {
			g.drawString(">", Game.WIDTH/2 + 370, Game.HEIGHT/2 + 300);

		
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 22));
		g.drawString("> ARRROWS - MOVE", Game.WIDTH /2 - 160, Game.HEIGHT/2 - 60);
		g.drawString("> ENTER - SELECT OPTIONS", Game.WIDTH/2 - 160, Game.HEIGHT/2);
		g.drawString("> ESC - PAUSE", Game.WIDTH/2 - 160, Game.HEIGHT/2 + 60);
		g.drawString("> COLLECT ALL FRUITS TO ADVANCE TO THE NEXT LEVEL", Game.WIDTH/2 - 160, Game.HEIGHT/2 + 120);
		g.drawString("> EAT COOKIES TO LET THE PHANTOMS VULNERABLE", Game.WIDTH/2 - 160, Game.HEIGHT/2 + 180);
		g.drawString("> BECAREFUL WITH THE GATES", Game.WIDTH/2 - 160, Game.HEIGHT/2 + 240);
		g.drawString("> HAVE FUN =)", Game.WIDTH/2 - 160, Game.HEIGHT/2 + 300);
		}
	}
}