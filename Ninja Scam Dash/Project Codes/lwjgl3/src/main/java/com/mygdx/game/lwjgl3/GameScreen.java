package com.mygdx.game.lwjgl3;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import abstractentity.Entity;
import abstractentity.EntityFactory;
import abstractentity.EntityManager;
import movement.MovementManager;
import input_output.AudioManager;
import input_output.InputManager;
import collision.CollisionManager;
import gamecollision.PlayerObjectCollisionManager;
import gameentity.Diamond;
import gameentity.Obstacles;
import gameentity.Player;
import gameentity.Powerup;
import gameentity.SpawningManager;
import gameentity.UIManager;
import gamescreens.DiamondQuestionScreen;
import gamescreens.GameOverScreen;
import gamescreens.WinScreen;
import scene.IScreen;
import scene.SceneManager;
import java.util.Random;

import java.util.Iterator;

/**
 * Handles game logic and rendering.
 */
public class GameScreen implements IScreen {
    private SpriteBatch batch;
    private Texture heartTexture;
    private Texture diamondTexture;
    private Texture menuIconTexture;
    private ImageButton menuButton;
    private Stage stage;
    private Player player;
    private EntityManager entityManager;
    private MovementManager movementManager;
    private InputManager inputManager;
    private CollisionManager collisionManager;
    private SceneManager sceneManager;
    private final AudioManager audioManager;
    private Random random;
    private EntityFactory entityFactory; //add factory to spawn entity
    private SpawningManager spawnManager; //enables spawning of entity
    private UIManager uiManager; //renders UI
    private Texture backgroundTexture;

    
    
    private boolean showQuestionScreen = false;
    private boolean isPaused = false;  //Pause flag



    public GameScreen(SceneManager sceneManager, InputManager inputManager) {
        this.sceneManager = sceneManager;
        this.inputManager = inputManager;
        this.audioManager = sceneManager.getAudioManager();
        entityManager = new EntityManager();
        batch = new SpriteBatch();
        stage = new Stage();
        backgroundTexture = new Texture(Gdx.files.internal("bg.jpg"));
        heartTexture = new Texture(Gdx.files.internal("heart.png"));
        diamondTexture = new Texture(Gdx.files.internal("diamond.png"));
        menuIconTexture = new Texture(Gdx.files.internal("menuIcon.png"));
        random = new Random();

        player = new Player(50, Gdx.graphics.getHeight() / 2 - 25, 200);
        entityManager.addEntity(player);
        movementManager = new MovementManager(player, inputManager);
        collisionManager = new PlayerObjectCollisionManager(entityManager, sceneManager);
        
        
      //new additions
        entityFactory = new EntityFactory();
        spawnManager = new SpawningManager(entityManager);
        uiManager = new UIManager(batch, heartTexture, diamondTexture);

    }



    @Override
    public void show() {
    	Gdx.input.setInputProcessor(stage); 
   	 // Create menu button
       TextureRegionDrawable menuDrawable = new TextureRegionDrawable(menuIconTexture);
       menuButton = new ImageButton(menuDrawable);
       
       menuButton.setSize(50, 50);
       menuButton.setPosition(Gdx.graphics.getWidth() - menuButton.getWidth() - 10, 
                              Gdx.graphics.getHeight() - menuButton.getHeight() - 10);
       
     
       menuButton.addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
	               pauseGame();
           }
       });
       
       stage.addActor(menuButton);

       if (!AudioManager.getInstance().isPlaying("gamebg.mp3")) {
           AudioManager.getInstance().playSoundOnLoop("gamebg.mp3");
       }
    }
    

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        if (isPaused) {
        	sceneManager.setNextScreen(new PauseScreen(sceneManager,inputManager,this)); //Stop updates while paused
        }

        spawnManager.update(delta); //call to spawn objects
        stage.act(Gdx.graphics.getDeltaTime());
        
        movementManager.checkAndHandleMovement();
        entityManager.update();
        collisionManager.checkCollisions();
        
        if (showQuestionScreen) {
            showQuestionScreen();  // Transition to QuestionScreen
            showQuestionScreen = false;
        }
        
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); // Draw background
        batch.end();
        
        stage.draw();
        
        batch.begin();
        entityManager.draw(batch, null);
        entityManager.draw(batch, null);
        
        uiManager.drawHealthBar(player);  //Render hearts in left-to-right order
        uiManager.drawDiamonds(player);
        batch.end();
        
       

        //Transition to Game Over screen if health reaches zero
        if (player.getHealth() <= 0) {
            System.out.println("Game Over! Transitioning to Game Over Screen...");
            sceneManager.setNextScreen(new GameOverScreen(sceneManager, inputManager));
        }
        
     //Transition to Game Over screen if health reaches zero
        if (player.getDiamondsCollected() == 3) {
            System.out.println("Game Over! Transitioning to Game Over Screen...");
            sceneManager.setNextScreen(new WinScreen(sceneManager));
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {

    }

    public void setShowQuestionScreen(boolean showQuestionScreen) {
        this.showQuestionScreen = showQuestionScreen;
    }

    private void showQuestionScreen() {
        sceneManager.setNextScreen(new DiamondQuestionScreen(sceneManager, inputManager, this, player));  //Pass the current gameScreen
    }
    
    public void pauseGame() {
        System.out.println("Game paused.");
        isPaused = true;
    }

    public void resumeGame() {
        System.out.println("Game resumed.");
        isPaused = false;
    }

	public InputManager getInputManager() {
		// TODO Auto-generated method stub
		return null;
	}

}
