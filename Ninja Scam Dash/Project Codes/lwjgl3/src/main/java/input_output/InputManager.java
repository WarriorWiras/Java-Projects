package input_output;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles user input by mapping keys to actions and allowing custom keybindings.
 */
public class InputManager {
    private Map<String, Integer> keyBindings;

    public InputManager() {
        keyBindings = new HashMap<>();

        //Default key bindings (WASD)
        keyBindings.put("moveLeft", Input.Keys.A);
        keyBindings.put("moveRight", Input.Keys.D);
        keyBindings.put("moveUp", Input.Keys.W);
        keyBindings.put("moveDown", Input.Keys.S);
    }

    public int getKeyForAction(String action) {
        return keyBindings.getOrDefault(action, -1);
    }

    public boolean isKeyPressed(String action) {
        int key = getKeyForAction(action);
        return key != -1 && Gdx.input.isKeyPressed(key);
    }

    //Allows users to change key bindings dynamically
    public void updateKeyBinding(String action, int newKey) {
        keyBindings.put(action, newKey);
        System.out.println(action + " key set to: " + Input.Keys.toString(newKey));
    }
}
