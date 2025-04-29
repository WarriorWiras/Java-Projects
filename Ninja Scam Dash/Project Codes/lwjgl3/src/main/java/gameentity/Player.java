package gameentity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import abstractentity.TexturedObject;
import gamecollision.ICollidable;

/**
 * Represents the Player entity in the game.
 */
public class Player extends TexturedObject implements ICollidable {
    private int health;
    private static final int MAX_HEALTH = 5;  //Maximum health limit
    private Rectangle bounds;
    private int diamondsCollected = 0;

    public Player(float x, float y, float speed) {
        super("player.png", x, y, speed);
        this.health = 5;
        this.bounds = new Rectangle(x, y, getTexture().getWidth(), getTexture().getHeight());
    }

    //FIXED: Added movement methods */
    public void moveLeft() {
        x -= speed * Gdx.graphics.getDeltaTime();
        if (x < 0) x = 0;
        updateBounds();
    }

    public void moveRight() {
        x += speed * Gdx.graphics.getDeltaTime();
        if (x + getTexture().getWidth() > Gdx.graphics.getWidth()) {
            x = Gdx.graphics.getWidth() - getTexture().getWidth();
        }
        updateBounds();
    }

    public void moveUp() {
        y += speed * Gdx.graphics.getDeltaTime();
        if (y + getTexture().getHeight() > Gdx.graphics.getHeight()) {
            y = Gdx.graphics.getHeight() - getTexture().getHeight();
        }
        updateBounds();
    }

    public void moveDown() {
        y -= speed * Gdx.graphics.getDeltaTime();
        if (y < 0) y = 0;
        updateBounds();
    }

    public void takeDamage() {
        if (health > 0) {
            health--;
            System.out.println("Player hit! Health: " + health);
        }
    }

    public void increaseHealth() {  
        if (health < MAX_HEALTH) {  //Prevent exceeding max health
            health++;
            System.out.println("Power-up collected! Health increased: " + health);
        }
    }
    
    public void collectDiamond() {  //Method to collect diamonds
        diamondsCollected++;
        System.out.println("Diamond collected! Total: " + diamondsCollected);
    }

    public int getHealth() {
        return health;
    }
    
    public int getDiamondsCollected() {  //Getter for tracking diamonds
        return diamondsCollected;
    }

    @Override
    public Rectangle getBounds() {
        return bounds;
    }

    private void updateBounds() {
        bounds.setPosition(x, y);
    }
}
