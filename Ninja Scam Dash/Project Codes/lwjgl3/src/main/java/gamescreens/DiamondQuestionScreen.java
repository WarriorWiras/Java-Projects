package gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.lwjgl3.GameScreen;

import gameentity.Player;
import input_output.AudioManager;
import input_output.InputManager;
import scene.IScreen;
import scene.SceneManager;

import java.util.Collections;

public class DiamondQuestionScreen implements IScreen {
    private Stage stage;
    private Skin skin;
    private Label questionLabel;
    private SceneManager sceneManager;
    private InputManager inputManager;
    private GameScreen gameScreen;
    private Player player;
    
    private Array<Question> questions;

    public class Question {
        String questionText;
        String correctAnswer;
        String wrongAnswer;

        public Question(String questionText, String correctAnswer, String wrongAnswer) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
            this.wrongAnswer = wrongAnswer;
        }
    }

    public DiamondQuestionScreen(SceneManager sceneManager, InputManager inputManager, GameScreen gameScreen, Player player) {
        this.sceneManager = sceneManager;
        this.inputManager = inputManager;
        this.gameScreen = gameScreen;
        this.player = player;
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        //more difficult questions
        questions = new Array<>();
        questions.add(new Question(
            "You receive an email from your bank asking you to urgently confirm your account details via a link. What should you do?",
            "Visit the bank's official website and contact customer support.",
            "Click the link in the email and enter your details immediately."
        ));
        questions.add(new Question(
            "A message from a known friend's hacked account asks you to download a file urgently. What should you do?",
            "Confirm with the friend through another contact method before opening it.",
            "Download the file since it came from a known account."
        ));
        questions.add(new Question(
            "A website looks exactly like your email provider but has a slightly different URL. What should you do?",
            "Close the website and manually enter the official URL.",
            "Log in to check if your account is still active."
        ));
        questions.add(new Question(
            "You get a pop-up while browsing that says your device is infected and you must install security software. What should you do?",
            "Ignore the pop-up and run a security scan with your own antivirus software.",
            "Click the pop-up and install the software immediately."
        ));
        questions.add(new Question(
            "A colleague sends you a link to a shared work document, but the email looks slightly unusual. What is the best action?",
            "Verify with the colleague via a separate communication channel before clicking the link.",
            "Click the link immediately to access the document."
        ));

        //Shuffle questions for random
        questions.shuffle();

        //select the first question
        Question currentQuestion = questions.get(0);
        
     //Create a question label with word wrapping
        questionLabel = new Label(currentQuestion.questionText, skin);
        questionLabel.setWrap(true);  //Enable text wrapping
        questionLabel.setWidth(Gdx.graphics.getWidth() * 0.8f);  //Limit width to 80% of screen width
        questionLabel.setAlignment(Align.left);  //Center the text
     //Wrap question inside a ScrollPane to prevent overflow
        ScrollPane questionScroll = new ScrollPane(questionLabel, skin);
        questionScroll.setScrollingDisabled(true, false);  //Disable horizontal scroll, allow vertical
        
        
        TextButton answerButton1 = new TextButton(currentQuestion.correctAnswer, skin);
        TextButton answerButton2 = new TextButton(currentQuestion.wrongAnswer, skin);


        answerButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Correct answer! Awarding a Diamond...");
                player.collectDiamond();  
                resumeGame();
            }
        });

        answerButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Wrong answer! No Diamond gained.");
                player.takeDamage();
                resumeGame();
            }
        });
        

     //Add elements to the table with constraints
        table.add(questionScroll).width(Gdx.graphics.getWidth() * 0.8f).height(Gdx.graphics.getHeight() * 0.3f).padBottom(20).row();
        table.add(answerButton1).padBottom(20).row();
        table.add(answerButton2).padBottom(20).row();
    }
    
    private void resumeGame() {
        Gdx.app.postRunnable(() -> {
            gameScreen.resumeGame();
            sceneManager.setNextScreen(gameScreen);
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    @Override
    public void show() {
    	if (!AudioManager.getInstance().isPlaying("gamebg.mp3")) {
            AudioManager.getInstance().playSoundOnLoop("gamebg.mp3");
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void hide() {}
}
