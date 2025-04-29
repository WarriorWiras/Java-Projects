package abstractentity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages all entities in the game.
 */
public class EntityManager {
    private List<Entity> entityList = new ArrayList<>();

    public void addEntity(Entity entity) {
        entityList.add(entity);
    }

    public void removeEntity(Entity entity) {
        entityList.remove(entity);
    }

    public List<Entity> getEntities() {
        return entityList;
    }

    public void draw(SpriteBatch spriteBatch, ShapeRenderer shapeRenderer) {
        for (Entity entity : entityList) {
            entity.draw(spriteBatch);
        }
    }

    public void update() {
        for (Entity entity : entityList) {
            entity.update();
        }
    }
}
