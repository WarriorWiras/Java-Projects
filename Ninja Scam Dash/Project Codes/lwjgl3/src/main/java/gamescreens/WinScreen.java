package gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import gameentity.Player;
import input_output.InputManager;
import scene.IScreen;
import scene.SceneManager;

public class WinScreen implements IScreen {
    private Stage stage;
    private Skin skin;
    private SceneManager sceneManager;
    private InputManager inputManager;
    private SpriteBatch batch;
    private BitmapFont winFont;

    public WinScreen(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        this.batch = new SpriteBatch();
        this.stage = new Stage(new ScreenViewport());
        
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json")); // Load UI skin

        //Load a custom font for "YOU WON!"
        winFont = new BitmapFont(Gdx.files.internal("losewin.fnt")); 
        winFont.getData().setScale(2.0f);
        
        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //"YOU WON!" label with custom font
        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = winFont;
        labelStyle.fontColor = Color.YELLOW; // Set text color to yellow
        
        Label winLabel = new Label("YOU WON!", labelStyle);
        winLabel.setAlignment(Align.center);

        TextButton quitButton = new TextButton("Quit", skin);

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Quitting the game...");
                Gdx.app.exit();
            }
        });

        // Adding widgets to the table
        table.add(winLabel).padBottom(60).row();
        table.add(quitButton).width(200).height(60).padBottom(20).row();
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        // Set background color to green
        Gdx.gl.glClearColor(0, 0.5f, 0, 1); 
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
        winFont.dispose();
    }

}
