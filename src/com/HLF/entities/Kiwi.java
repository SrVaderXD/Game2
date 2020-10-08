package com.HLF.entities;

import java.awt.image.BufferedImage;

public class Kiwi extends Entity{

	public Kiwi(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		depth = 0;
	}

}
