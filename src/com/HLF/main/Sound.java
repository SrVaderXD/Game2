package com.HLF.main;

import java.applet.Applet;
import java.applet.AudioClip;

@SuppressWarnings("deprecation")
public class Sound {

	private AudioClip clip;
	
	public static final Sound jojoRef = new Sound("/jojo_reference.wav");
	public static final Sound begin = new Sound("/pacman_beginning.wav");
	public static final Sound extraLife = new Sound("/pacman_extrapac.wav");
	public static final Sound death = new Sound("/pacman_death.wav");
	public static final Sound eatFruit = new Sound("/pacman_eatfruit.wav");
	public static final Sound eatGhost = new Sound("/pacman_eatghost.wav");
	
	private Sound(String name) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		}catch(Throwable e) {}
	}
	
	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();
		}catch(Throwable e) {}
	}
	
	public void loop() {
		try {
			new Thread() {
				public void run() {
					clip.loop();
				}
			}.start();
		}catch(Throwable e) {}
	}
}
