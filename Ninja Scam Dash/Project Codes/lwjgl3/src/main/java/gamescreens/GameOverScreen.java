package gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.lwjgl3.MenuScreen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import scene.IScreen;
import scene.SceneManager;
import input_output.AudioManager;
import input_output.InputManager;

/**
 * Game Over Screen when player loses all lives.
 */
public class GameOverScreen implements IScreen {
    private SceneManager sceneManager;
    private InputManager inputManager;
    private Stage stage;
    private Skin skin;

    public GameOverScreen(SceneManager sceneManager, InputManager inputManager) {
        this.sceneManager = sceneManager;
        this.inputManager = inputManager;
        this.stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json")); 

        Table table = new Table();
        table.setFillParent(true);
        table.center();
        stage.addActor(table);

        Label gameOverLabel = new Label("Game Over!", skin);
        gameOverLabel.setFontScale(2.5f);  //Make text bigger

        TextButton menuButton = new TextButton("Back to Menu", skin);

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Returning to Main Menu...");
                AudioManager.getInstance().stopSound("gamebg.mp3");
                sceneManager.setNextScreen(new MenuScreen(sceneManager, inputManager));
            }
        });

        table.add(gameOverLabel).padBottom(30).row();
        table.add(menuButton).size(200, 50).padTop(20);
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
