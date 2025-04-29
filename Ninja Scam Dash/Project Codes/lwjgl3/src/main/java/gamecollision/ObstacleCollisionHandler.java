package gamecollision;

import scene.SceneManager;
import abstractentity.Entity;
import abstractentity.EntityManager;
import gameentity.Obstacles;
import gameentity.Player;
import input_output.AudioManager;

public class ObstacleCollisionHandler implements CollisionHandler {
    private final EntityManager entityManager;
    private final SceneManager sceneManager;
    private final AudioManager audioManager;

    public ObstacleCollisionHandler(EntityManager entityManager, SceneManager sceneManager) {
        this.entityManager = entityManager;
        this.sceneManager = sceneManager;
        this.audioManager = sceneManager.getAudioManager();
    }

    @Override
    public void handleCollision(Player player, Entity entity) {
        if (entity instanceof Obstacles) {
            System.out.println("Player collided with an Obstacle!");
            //Handle Obstacle-specific behavior like reducing health
            audioManager.playUISound("hit.mp3");
            player.takeDamage(); 
            entityManager.removeEntity(entity);  //Remove the Obstacle from the game
        }
    }
}
