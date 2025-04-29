package gameentity;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import abstractentity.TexturedObject;
import gamecollision.ICollidable;

/**
 * Represents an obstacle that moves from right to left.
 */
public class Powerup extends TexturedObject implements ICollidable {
    private Rectangle bounds;

    public Powerup(float x, float y, float speed) {
        super("scroll.png", x, y, speed);  
        this.bounds = new Rectangle(x, y, getTexture().getWidth(), getTexture().getHeight());
    }
    
    // Static method to create an obstacle with randomized position
    public static Powerup createPowerup(float spawnableWidth, float spawnableHeight, float speed) {
        Random random = new Random();
        
        // Calculate spawnable area for y-position
        float entityHeight = new Powerup(0, 0, 0).getHeight();  // Use getHeight() to get the height dynamically
        float spawnableYHeight = spawnableHeight - entityHeight - 75;  //The area where the entity can spawn
        
        // Randomly determine the y-position within spawnable area
        float yPosition = random.nextFloat() * spawnableYHeight;  
        
        // Randomly setting x position within spawnable width
        float xPosition = spawnableWidth;

        // Create and return the new obstacle instance
        return new Powerup(xPosition, yPosition, speed);
    }

    @Override
    public void update() {  //Ensure movement is handled every frame
        moveLeft();
    }

    private void moveLeft() {
        x -= speed * Gdx.graphics.getDeltaTime();  //Moves left continuously
        updateBounds();
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    private void updateBounds() {
        bounds.setPosition(x, y);
    }
}
