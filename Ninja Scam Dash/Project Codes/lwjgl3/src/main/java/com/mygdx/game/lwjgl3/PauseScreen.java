package com.mygdx.game.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import scene.IScreen;
import scene.SceneManager;
import input_output.InputManager;

/**
 * Game Over Screen when player loses all lives.
 */
public class PauseScreen implements IScreen {
    private SceneManager sceneManager;
    private InputManager inputManager;
    private GameScreen gameScreen;
    private Stage stage;
    private Skin skin;

    public PauseScreen(SceneManager sceneManager, InputManager inputManager, GameScreen gameScreen) {
        this.sceneManager = sceneManager;
        this.inputManager = inputManager;
        this.gameScreen = gameScreen; 
        this.stage = new Stage();

        skin = new Skin(Gdx.files.internal("uiskin.json"));  // ✅ Ensure "uiskin.json" is in `core/assets/`

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        Label gamePauseLabel = new Label("Game Paused!", skin);
        gamePauseLabel.setFontScale(2.5f);  //Make text bigger

        TextButton resumeButton = new TextButton("Resume Game", skin);

        resumeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
            	  Gdx.app.postRunnable(() -> {
	                System.out.println("Returning to Game...");
	                //sceneManager.setNextScreen(new GameScreen(sceneManager, inputManager));
	                gameScreen.resumeGame();
	                sceneManager.setNextScreen(gameScreen);
                  });
            }
        });

        table.add(gamePauseLabel).padBottom(30).row();
        table.add(resumeButton).size(200, 50).padTop(20);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
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
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
