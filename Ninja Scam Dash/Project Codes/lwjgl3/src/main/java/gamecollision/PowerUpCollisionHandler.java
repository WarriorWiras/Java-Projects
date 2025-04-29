package gamecollision;

import com.mygdx.game.lwjgl3.GameScreen;

import abstractentity.Entity;
import abstractentity.EntityManager;
import gameentity.Player;
import gameentity.Powerup;
import gamescreens.QuestionScreen1;
import scene.SceneManager;

public class PowerUpCollisionHandler implements CollisionHandler {
    private final EntityManager entityManager;
    private final SceneManager sceneManager;

    public PowerUpCollisionHandler(EntityManager entityManager, SceneManager sceneManager) {
        this.entityManager = entityManager;
        this.sceneManager = sceneManager;
    }

    @Override
    public void handleCollision(Player player, Entity entity) {
        if (entity instanceof Powerup) {
            System.out.println("Player collided with a PowerUp!");
            entityManager.removeEntity(entity);  //Remove the PowerUp from the game
            triggerQuestionScreen(player);
        }
    }
    
    private void triggerQuestionScreen(Player player) {
        if (sceneManager.getCurrentScreen() instanceof GameScreen) {
            GameScreen gameScreen = (GameScreen) sceneManager.getCurrentScreen();
            sceneManager.setNextScreen(new QuestionScreen1(sceneManager, gameScreen.getInputManager(), gameScreen, player));
        } else {
            System.err.println("Error: Current screen is not a GameScreen instance.");
        }
    }
}
