package com.HLF.entities;

import com.HLF.main.Game;

public class EnemySpawn {
	
	public void tick() {
		
		respawn();

	}

	public void respawn() {
		
		if(RedEnemy.rDead) {
			RedEnemy.curRedTime++;
			
			if(RedEnemy.curRedTime == RedEnemy.redTime) {
				RedEnemy.rVulnerable = false;
				RedEnemy.curRedTime = 0;
				RedEnemy r = new RedEnemy(306,160,16,16,1,Entity.ENEMY_RED_R);
				Game.entities.add(r);
				RedEnemy.rDead = false;
			}
		}
		
		if(PinkEnemy.pDead) {
			PinkEnemy.curPinkTime++;

			if(PinkEnemy.curPinkTime == PinkEnemy.pinkTime) {
				PinkEnemy.pVulnerable = false;
				PinkEnemy.curPinkTime = 0;
				PinkEnemy p = new PinkEnemy(322,176,16,16,1,Entity.ENEMY_PINK_R);
				Game.entities.add(p);
				PinkEnemy.pDead = false;
			}
		}
		
		if(OrangeEnemy.oDead) {
			OrangeEnemy.curOrangeTime++;
			
			if(OrangeEnemy.curOrangeTime == OrangeEnemy.orangeTime) {
				OrangeEnemy.oVulnerable = false;
				OrangeEnemy.curOrangeTime = 0;
				OrangeEnemy o = new OrangeEnemy(306,176,16,16,1,Entity.ENEMY_ORANGE_R);
				Game.entities.add(o);
				OrangeEnemy.oDead = false;
			}
		}
		
		if(BlueEnemy.bDead) {
			BlueEnemy.curBlueTime++;
			
			if(BlueEnemy.curBlueTime == BlueEnemy.blueTime) {
				BlueEnemy.bVulnerable = false;
				BlueEnemy.curBlueTime = 0;
				BlueEnemy b = new BlueEnemy(322,160,16,16,1,Entity.ENEMY_BLUE_R);
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
