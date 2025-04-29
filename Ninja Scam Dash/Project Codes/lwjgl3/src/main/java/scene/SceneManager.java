package scene;

import com.badlogic.gdx.Gdx;

import input_output.AudioManager;

/**
 * Manages screen transitions and active game scenes.
 */
public class SceneManager {
    private IScreen currentScreen;
    private IScreen nextScreen;
    private boolean transitioning;
    private final AudioManager audioManager;

    public SceneManager() {
        currentScreen = null;
        nextScreen = null;
        transitioning = false;
        this.audioManager = AudioManager.getInstance();
    }

    public void setInitialScreen(IScreen screen) {
        if (currentScreen != null) {
            currentScreen.dispose();
        }
        currentScreen = screen;
        currentScreen.show();
    }

    public void setNextScreen(IScreen screen) {
        System.out.println("Transitioning to new screen...");  // ✅ Debugging output
        transitioning = true;
        this.nextScreen = screen;
    }

    public void updateAndTransitScene(float delta) {
        if (transitioning && nextScreen != null) {
            if (currentScreen != null) {
                currentScreen.hide();
                currentScreen.dispose();
            }
            currentScreen = nextScreen;
            nextScreen = null;
            transitioning = false;
            currentScreen.show();
            System.out.println("Screen changed successfully!");  // ✅ Debugging output
        }
    }

    public void updateCurrent(float delta) {
        if (currentScreen != null) {
            currentScreen.render(delta);
        }
    }

    public IScreen getCurrentScreen() {
        return currentScreen;
    }

    public AudioManager getAudioManager() {
        return audioManager;
    }
}
