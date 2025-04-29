package gamecollision;

import com.mygdx.game.lwjgl3.GameScreen;

import abstractentity.Entity;
import abstractentity.EntityManager;
import gameentity.Diamond;
import gameentity.Player;
import gamescreens.DiamondQuestionScreen;
import scene.SceneManager;

public class DiamondCollisionHandler implements CollisionHandler {
    private final EntityManager entityManager;
    private final SceneManager sceneManager;

    public DiamondCollisionHandler(EntityManager entityManager, SceneManager sceneManager) {
        this.entityManager = entityManager;
        this.sceneManager = sceneManager;
    }
    
    @Override
    public void handleCollision(Player player, Entity entity) {
        if (entity instanceof Diamond) {
            System.out.println("Player collided with a Diamond!");
            entityManager.removeEntity(entity);
            triggerHardQuestionScreen(player);
        }
        }
    
    private void triggerHardQuestionScreen(Player player) {
        if (sceneManager.getCurrentScreen() instanceof GameScreen) {
            GameScreen gameScreen = (GameScreen) sceneManager.getCurrentScreen();
            sceneManager.setNextScreen(new DiamondQuestionScreen(sceneManager, gameScreen.getInputManager(), gameScreen, player));
        } else {
            System.err.println("Error: Current screen is not a GameScreen instance.");
        }
    }
}
    
    