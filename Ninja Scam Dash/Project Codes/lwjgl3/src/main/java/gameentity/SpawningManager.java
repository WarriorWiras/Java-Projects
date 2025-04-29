package gameentity;

import java.util.Random;

import com.badlogic.gdx.Gdx;

import abstractentity.EntityFactory;
import abstractentity.EntityManager;

public class SpawningManager {
 private Random random;
 private float obstacleSpawnTimer = 0f;
 private float powerUpCooldown = 0f;
 private float diamondCooldown = 0f;

 private static final float SPAWN_INTERVAL = 1.5f;
 private static final float POWERUP_COOLDOWN_TIME = 5f;
 private static final float DIAMOND_COOLDOWN_TIME = 5f;
 private EntityManager entityManager;
 private EntityFactory entityFactory;

 public SpawningManager(EntityManager entityManager) {
     this.entityManager = entityManager;
     this.entityFactory= new EntityFactory();
     random = new Random();
 }

 public void update(float delta) {
     // Handle spawn timer for obstacles
     obstacleSpawnTimer += delta;
     if (obstacleSpawnTimer >= SPAWN_INTERVAL) {
    	 entityManager.addEntity(entityFactory.getEntity("Obstacle"));
         obstacleSpawnTimer = 0f;
     }

     // Handle cooldowns for power-ups and diamonds
     powerUpCooldown -= delta;
     diamondCooldown -= delta;

     if (powerUpCooldown <= 0 && random.nextFloat() < 0.05) {
    	 entityManager.addEntity(entityFactory.getEntity("Powerup"));
         powerUpCooldown = POWERUP_COOLDOWN_TIME;
     }

     if (diamondCooldown <= 0 && random.nextFloat() < 0.01) {
    	 entityManager.addEntity(entityFactory.getEntity("Diamond"));
         diamondCooldown = DIAMOND_COOLDOWN_TIME;
     }
 }
}
