package com.HLF.entities;

import com.HLF.main.Game;

public class EnemySpawn {
	
	private int red_xSpawn = 306, red_ySpawn = 160;
	private int blue_xSpawn = 322, blue_ySpawn = 160;
	private int orange_xSpawn = 306, orange_ySpawn = 176;
	private int pink_xSpawn = 322, pink_ySpawn = 176;
	
	public void tick() {
		
		respawn();

	}

	public void respawn() {
		
		if(Game.nextLevel) {
			RedEnemy.rVulnerable = false;
			RedEnemy.curRedTime = 0;
			RedEnemy.rDead = false;
			
			PinkEnemy.pVulnerable = false;
			PinkEnemy.curPinkTime = 0;
			PinkEnemy.pDead = false;
			
			OrangeEnemy.oVulnerable = false;
			OrangeEnemy.curOrangeTime = 0;
			OrangeEnemy.oDead = false;
			
			BlueEnemy.bVulnerable = false;
			BlueEnemy.curBlueTime = 0;
			BlueEnemy.bDead = false;
			
			Game.nextLevel = false;
		}
		
		if(RedEnemy.rDead) {
			RedEnemy.curRedTime++;
			
			if(RedEnemy.curRedTime == RedEnemy.redTime) {
				RedEnemy.rVulnerable = false;
				RedEnemy.curRedTime = 0;
				RedEnemy r = new RedEnemy(red_xSpawn,red_ySpawn,16,16,1,Entity.ENEMY_RED_R);
				Game.entities.add(r);
				RedEnemy.rDead = false;
			}
		}
		
		if(PinkEnemy.pDead) {
			PinkEnemy.curPinkTime++;

			if(PinkEnemy.curPinkTime == PinkEnemy.pinkTime) {
				PinkEnemy.pVulnerable = false;
				PinkEnemy.curPinkTime = 0;
				PinkEnemy p = new PinkEnemy(pink_xSpawn,pink_ySpawn,16,16,1,Entity.ENEMY_PINK_R);
				Game.entities.add(p);
				PinkEnemy.pDead = false;
			}
		}
		
		if(OrangeEnemy.oDead) {
			OrangeEnemy.curOrangeTime++;
			
			if(OrangeEnemy.curOrangeTime == OrangeEnemy.orangeTime) {
				OrangeEnemy.oVulnerable = false;
				OrangeEnemy.curOrangeTime = 0;
				OrangeEnemy o = new OrangeEnemy(orange_xSpawn,orange_ySpawn,16,16,1,Entity.ENEMY_ORANGE_R);
				Game.entities.add(o);
				OrangeEnemy.oDead = false;
			}
		}
		
		if(BlueEnemy.bDead) {
			BlueEnemy.curBlueTime++;
			
			if(BlueEnemy.curBlueTime == BlueEnemy.blueTime) {
				BlueEnemy.bVulnerable = false;
				BlueEnemy.curBlueTime = 0;
				BlueEnemy b = new BlueEnemy(blue_xSpawn,blue_ySpawn,16,16,1,Entity.ENEMY_BLUE_R);
				Game.entities.add(b);
				BlueEnemy.bDead = false;
			}
		}
	}
	
	public boolean noEnemies() {
		if(Game.enemies.size() == 0) {
			return true;
			}
		
		return false;
	}
}
