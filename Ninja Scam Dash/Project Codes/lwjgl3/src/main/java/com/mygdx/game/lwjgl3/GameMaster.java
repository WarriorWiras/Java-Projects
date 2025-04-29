package com.mygdx.game.lwjgl3;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import scene.SceneManager;
import input_output.InputManager;

/**
 * Main game class, manages rendering and game loop.
 */
public class GameMaster extends Game {
    private SpriteBatch batch;
    private SceneManager sceneManager;
    private InputManager inputManager;

    @Override
    public void create() {
        batch = new SpriteBatch();
        sceneManager = new SceneManager();
        inputManager = new InputManager();
        sceneManager.setInitialScreen(new MenuScreen(sceneManager, inputManager));
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        sceneManager.updateAndTransitScene(delta);  // ✅ FIXED: Ensures screen transitions
        sceneManager.updateCurrent(delta);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
