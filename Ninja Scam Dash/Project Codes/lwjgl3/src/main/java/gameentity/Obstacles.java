package gameentity;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

import abstractentity.TexturedObject;
import gamecollision.ICollidable;

/**
 * Represents an obstacle that moves from right to left.
 */
public class Obstacles extends TexturedObject implements ICollidable {
    private Rectangle bounds;
    private Random random;

    public Obstacles(float x, float y, float speed) {
        super("spike.png", x, y, speed);  //Uses "spike.png" as obstacle
        this.bounds = new Rectangle(x, y, getTexture().getWidth(), getTexture().getHeight());
    }
    
 //Static method to create an obstacle with randomized position
    public static Obstacles createObstacle(float spawnableWidth, float spawnableHeight, float speed) {
        Random random = new Random();
        
        // Calculate spawnable area for y-position
        float entityHeight = new Obstacles(0, 0, 0).getHeight();  // Use getHeight() to get the height dynamically from entity
        float spawnableYHeight = spawnableHeight - entityHeight - 75;  //The area where the entity can spawn
        
        // Randomly determine the y-position within spawnable area
        float yPosition = random.nextFloat() * spawnableYHeight;  
        
        // You can define any logic to randomize the x-position as well, such as:
        // Randomly setting x position within spawnable width
        float xPosition = spawnableWidth;

        // Create and return the new obstacle instance
        return new Obstacles(xPosition, yPosition, speed);
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
