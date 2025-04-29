package abstractentity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import gamecollision.Boundary;

/**
 * Base class for all game entities.
 */
public abstract class Entity {
    protected float x, y, speed;

    public Entity(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public abstract void update();

    public abstract void draw(SpriteBatch spriteBatch);

    public abstract void draw(ShapeRenderer shapeRenderer);

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	public void adjustPosition(Boundary boundary) {
		// TODO Auto-generated method stub
		
	}

	public void movement() {
		// TODO Auto-generated method stub
		
	}
}
