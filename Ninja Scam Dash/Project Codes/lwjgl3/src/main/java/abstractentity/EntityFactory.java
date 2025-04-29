package abstractentity;

import java.util.Random;

import com.badlogic.gdx.Gdx;

import gameentity.Diamond;
import gameentity.Obstacles;
import gameentity.Powerup;

public class EntityFactory {

    private Random random;
 // Default values for width, height, and speed
    private float spawnableWidth;
    private float spawnableHeight;
    private float speed;
    private float diamondspeed;

    public EntityFactory() {
    	//Initialize default spawnable width and height from screen size
        this.spawnableWidth = Gdx.graphics.getWidth();
        this.spawnableHeight = Gdx.graphics.getHeight();
        this.speed = 250;  // Default speed
        this.diamondspeed= 350;//diamond should be faster
    }

    

    public Entity getEntity(String name){
        if (name == "Obstacle"){
            return Obstacles.createObstacle(spawnableWidth,spawnableHeight,speed);
        } else if (name=="Powerup") {
        	return Powerup.createPowerup(spawnableWidth,spawnableHeight,speed);
        } else if (name=="Diamond") {
        	return Diamond.createDiamond(spawnableWidth,spawnableHeight,diamondspeed);
        } else {
        	return null; 
        }		
      }

}
