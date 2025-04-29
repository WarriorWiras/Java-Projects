package com.mygdx.game.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import scene.IScreen;
import scene.SceneManager;
import input_output.AudioManager;
import input_output.InputManager;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;

/**
 * Settings screen for customizing key bindings.
 */
public class SettingsScreen implements IScreen {
    private SceneManager sceneManager;
    private InputManager inputManager;
    //private final AudioManager audioManager;
    private Stage stage;
    private Skin skin;
    private String waitingForKey = null;  //Stores which key is being changed

    public SettingsScreen(SceneManager sceneManager, InputManager inputManager) {
        this.sceneManager = sceneManager;
        this.inputManager = inputManager;
        //this.audioManager = sceneManager.getAudioManager();
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        table.center();  //Align table to center
        stage.addActor(table);

        Label titleLabel = new Label("Settings", skin);
        titleLabel.setFontScale(1.2f);  // ✅ Make title text slightly bigger

        TextButton leftButton = new TextButton("Move Left: " + Input.Keys.toString(inputManager.getKeyForAction("moveLeft")), skin);
        TextButton rightButton = new TextButton("Move Right: " + Input.Keys.toString(inputManager.getKeyForAction("moveRight")), skin);
        TextButton upButton = new TextButton("Move Up: " + Input.Keys.toString(inputManager.getKeyForAction("moveUp")), skin);
        TextButton downButton = new TextButton("Move Down: " + Input.Keys.toString(inputManager.getKeyForAction("moveDown")), skin);
        TextButton backButton = new TextButton("Back", skin);
        

        //Ensure proper spacing between buttons
        float buttonWidth = 250f;
        float buttonHeight = 60f;
        float buttonPadding = 15f;  // Adjusts spacing between buttons

        table.add(titleLabel).padBottom(30).center().row();
        table.add(leftButton).size(buttonWidth, buttonHeight).padBottom(buttonPadding).row();
        table.add(rightButton).size(buttonWidth, buttonHeight).padBottom(buttonPadding).row();
        table.add(upButton).size(buttonWidth, buttonHeight).padBottom(buttonPadding).row();
        table.add(downButton).size(buttonWidth, buttonHeight).padBottom(buttonPadding).row();
        table.add(backButton).size(buttonWidth, buttonHeight).padTop(20).row();  // Extra spacing above Back button
        
        
        
        // Click listeners for key rebinding
        leftButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                waitingForKey = "moveLeft";
                System.out.println("Press a new key for Move Left...");
            }
        });

        rightButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                waitingForKey = "moveRight";
                System.out.println("Press a new key for Move Right...");
            }
        });

        upButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                waitingForKey = "moveUp";
                System.out.println("Press a new key for Move Up...");
            }
        });

        downButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                waitingForKey = "moveDown";
                System.out.println("Press a new key for Move Down...");
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sceneManager.setNextScreen(new MenuScreen(sceneManager, inputManager));
                AudioManager.getInstance().playUISound("other-ui.mp3");
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        if (!AudioManager.getInstance().isPlaying("bgmusic.mp3")) {
            AudioManager.getInstance().playSoundOnLoop("bgmusic.mp3");
        }
    }

    @Override
    public void render(float delta) {
        if (waitingForKey != null) {
            for (int key = 0; key < 256; key++) {
                if (Gdx.input.isKeyJustPressed(key)) {
                    inputManager.updateKeyBinding(waitingForKey, key);
                    System.out.println(waitingForKey + " key set to: " + Input.Keys.toString(key));
                    waitingForKey = null;
                    sceneManager.setNextScreen(new SettingsScreen(sceneManager, inputManager));  // ✅ Refresh screen to update key labels
                    return;
                }
            }
        }

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
