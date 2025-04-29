package movement;

import gameentity.Player;
import input_output.InputManager;

/**
 * Manages player movement based on input.
 */
public class MovementManager {
    private Player player;
    private InputManager inputManager;

    public MovementManager(Player player, InputManager inputManager) {
        this.player = player;
        this.inputManager = inputManager;
    }

    public void checkAndHandleMovement() {
        if (inputManager.isKeyPressed("moveLeft")) {
            player.moveLeft();
        }
        if (inputManager.isKeyPressed("moveRight")) {
            player.moveRight();
        }
        if (inputManager.isKeyPressed("moveUp")) {
            player.moveUp();
        }
        if (inputManager.isKeyPressed("moveDown")) {
            player.moveDown();
        }
    }
}
