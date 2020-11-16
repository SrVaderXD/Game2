package com.HLF.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import com.HLF.main.Game;

public class UI {
	
	public static BufferedImage LIFE_ICON = Game.spritesheet.getSprite(0,16,16,16);


	public void render(Graphics g) {
		score(g);
		fruits(g);
		life(g);
	}
	
	private void score(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,18));
		g.drawString("Score: "+Game.score, 300, 20);
	}
	
	private void fruits(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,18));
		g.drawString("Fruits: "+Game.fruits+"/"+Game.totalFruits, 30, 20);
	}
	
	public void life(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,18));
		g.drawString("x"+Game.life, 650, 20);
		g.drawImage(LIFE_ICON,620,0,32,32, null);
	}
}
