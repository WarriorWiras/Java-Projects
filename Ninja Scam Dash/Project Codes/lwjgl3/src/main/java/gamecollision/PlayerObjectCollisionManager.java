package gamecollision;

import com.badlogic.gdx.Gdx;

import abstractentity.Entity;
import abstractentity.EntityManager;
import collision.CollisionManager;
import gameentity.Diamond;
import gameentity.Obstacles;
import gameentity.Player;
import gameentity.Powerup;

import java.util.Map;
import java.util.HashMap;

import input_output.AudioManager;
import scene.SceneManager;

public class PlayerObjectCollisionManager extends CollisionManager {
    private final SceneManager sceneManager;
    private final EntityManager entityManager;
    private final Map<Class<? extends Entity>, CollisionHandler> collisionHandlers;


    public PlayerObjectCollisionManager(EntityManager entityManager, SceneManager sceneManager) {
        super(entityManager);
        this.entityManager = entityManager;
        this.sceneManager = sceneManager;
        this.collisionHandlers = new HashMap<>();
        registerCollisionHandlers();
    }

    //Register the different collision handlers
    private void registerCollisionHandlers() {
        collisionHandlers.put(Diamond.class, new DiamondCollisionHandler(entityManager, sceneManager));
        collisionHandlers.put(Powerup.class, new PowerUpCollisionHandler(entityManager, sceneManager));
        collisionHandlers.put(Obstacles.class, new ObstacleCollisionHandler(entityManager, sceneManager));
    }

    @Override
    protected void handleCollision(Entity entity1, Entity entity2) {
        Player player = null;
        Entity otherEntity = null;

        if (entity1 instanceof Player) {
            player = (Player) entity1;
            otherEntity = entity2;
        } else if (entity2 instanceof Player) {
            player = (Player) entity2;
            otherEntity = entity1;
        }

        if (player == null) {
            return;  //If no player involved, return early
        }

        //Look up the handler based on the entity class
        CollisionHandler handler = collisionHandlers.get(otherEntity.getClass());
        if (handler != null) {
            handler.handleCollision(player, otherEntity);
        } else {
            System.err.println("No handler found for entity: " + otherEntity.getClass().getSimpleName());
        }
    }
}

