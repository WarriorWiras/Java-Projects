package com.mygdx.game.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.graphics.GL20;

import scene.IScreen;
import scene.SceneManager;
import input_output.AudioManager;
import input_output.InputManager;

/**
 * Main menu screen where players can start the game, go to settings, or exit.
 */
public class MenuScreen implements IScreen {
    private SceneManager sceneManager;
    private InputManager inputManager;
    private Stage stage;
    private Skin skin;

    public MenuScreen(SceneManager sceneManager, InputManager inputManager) {
        this.sceneManager = sceneManager;
        this.inputManager = inputManager;
        this.stage = new Stage();  
        Gdx.input.setInputProcessor(stage); 
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label titleLabel = new Label("Game Menu", skin);
        TextButton startButton = new TextButton("Start Game", skin);
        TextButton settingsButton = new TextButton("Settings", skin);  // ✅ NEW: Settings Button
        TextButton exitButton = new TextButton("Exit", skin);
     // Add the "Get 3 Diamonds to Win" message
        Label winConditionLabel = new Label("Get 3 diamonds to win!", skin);

        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sceneManager.setNextScreen(new GameScreen(sceneManager, inputManager));
                AudioManager.getInstance().playUISound("other-ui.mp3");
                AudioManager.getInstance().stopSound("bgmusic.mp3");
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sceneManager.setNextScreen(new SettingsScreen(sceneManager, inputManager));
                AudioManager.getInstance().playUISound("other-ui.mp3");
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        table.add(titleLabel).padBottom(20).row();
        table.add(winConditionLabel).padBottom(20).row();  // Add the "Get 3 diamonds" label
        table.add(startButton).size(200, 50).padBottom(10).row();
        table.add(settingsButton).size(200, 50).padBottom(10).row();  //new Settings Button
        table.add(exitButton).size(200, 50);
    }

    @Override
    public void show() {  //FIXED: Implemented missing show() method
        Gdx.input.setInputProcessor(stage);
        if (!AudioManager.getInstance().isPlaying("bgmusic.mp3")) {
            AudioManager.getInstance().playSoundOnLoop("bgmusic.mp3");
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void hide() {  
        Gdx.input.setInputProcessor(null);
        
        }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
