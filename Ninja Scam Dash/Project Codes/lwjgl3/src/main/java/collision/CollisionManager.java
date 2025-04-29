package collision;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import abstractentity.Entity;
import abstractentity.EntityManager;
import gamecollision.ICollidable;
import gameentity.Obstacles;
import gameentity.Player;

public abstract class CollisionManager {
    protected EntityManager entityManager;

    public CollisionManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    
    //Abstract method to handle specific collision behavior for managers that extend this class
    protected abstract void handleCollision(Entity entity1, Entity entity2);

    //Method to check for all collisions
    public void checkCollisions() {
        List<Entity> entities = new ArrayList<>(entityManager.getEntities());

        //Check all pairs of entities for collisions
        for (int i = 0; i < entities.size(); i++) {
            Entity entity1 = entities.get(i);

            for (int j = i + 1; j < entities.size(); j++) {
                Entity entity2 = entities.get(j);

                if (entity1 instanceof ICollidable && entity2 instanceof ICollidable) {
                    ICollidable collidableEntity1 = (ICollidable) entity1;
                    ICollidable collidableEntity2 = (ICollidable) entity2;

                    //Check if the entities overlap (collision occurs)
                    if (collidableEntity1.getBounds().overlaps(collidableEntity2.getBounds())) {
                        //Call the abstract method to handle collision logic
                        handleCollision(entity1, entity2);
                    }
                }
            }
        }
    }
}

    

