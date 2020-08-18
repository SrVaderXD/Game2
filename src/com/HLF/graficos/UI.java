package com.HLF.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.HLF.main.Game;

public class UI {

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,18));
		g.drawString("Score: "+Game.score+"/"+Game.fruits, 10, 20);
	}
}
