package com.HLF.entities;

import com.HLF.main.Game;

public class EnemySpawn {
	
	public void tick() {
		
		respawn();
	}

	public void respawn() {
		
		if(noEnemies()) {
			
			Enemy.vulFrames++;
			
			if(Enemy.vulFrames == 120)
				Enemy.animation = true;
			
			if(Enemy.vulFrames == 360) {
				Enemy.vulnerable = false;
				Enemy.blink = false;
			}
		}
		
		if(RedEnemy.rDead) {
			RedEnemy.curRedTime++;
			
			if(RedEnemy.curRedTime == RedEnemy.redTime) {
				RedEnemy.curRedTime = 0;
				RedEnemy r = new RedEnemy(16,16,16,16,1,Entity.ENEMY_RED_R);
				Game.entities.add(r);
				RedEnemy.rDead = false;
			}
		}
		
		if(PinkEnemy.pDead) {
			PinkEnemy.curPinkTime++;

			if(PinkEnemy.curPinkTime == PinkEnemy.pinkTime) {
				PinkEnemy.curPinkTime = 0;
				PinkEnemy p = new PinkEnemy(16,16,16,16,1,Entity.ENEMY_PINK_R);
				Game.entities.add(p);
				PinkEnemy.pDead = false;
			}
		}
		
		if(OrangeEnemy.oDead) {
			OrangeEnemy.curOrangeTime++;
			
			if(OrangeEnemy.curOrangeTime == OrangeEnemy.orangeTime) {
				OrangeEnemy.curOrangeTime = 0;
				OrangeEnemy o = new OrangeEnemy(16,16,16,16,1,Entity.ENEMY_ORANGE_R);
				Game.entities.add(o);
				OrangeEnemy.oDead = false;
			}
		}
		
		if(BlueEnemy.bDead) {
			BlueEnemy.curBlueTime++;
			
			if(BlueEnemy.curBlueTime == BlueEnemy.blueTime) {
				BlueEnemy.curBlueTime = 0;
				BlueEnemy b = new BlueEnemy(16,16,16,16,1,Entity.ENEMY_BLUE_R);
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
