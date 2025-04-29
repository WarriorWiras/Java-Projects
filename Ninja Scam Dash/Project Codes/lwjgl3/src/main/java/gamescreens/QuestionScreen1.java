package gamescreens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

public class QuestionScreen1 implements IScreen {
    private Stage stage;
    private Skin skin;
    private Label questionLabel;
    private SceneManager sceneManager;
    private InputManager inputManager;
    private GameScreen gameScreen;
    private Player player;
    private Array<Question> questions;
    private int questionsAnswered = 0; //Track the number of answered questions
    
    private TextButton answerButton1;
    private TextButton answerButton2;

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

    public QuestionScreen1(SceneManager sceneManager, InputManager inputManager, GameScreen gameScreen, Player player) {
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

        questions = new Array<>();
        questions.add(new Question("What is a common sign of a phishing email?", 
                                   "An urgent request for personal information", 
                                   "An email from your boss about an upcoming meeting"));
        questions.add(new Question("Which of these is a safe way to verify a suspicious link?", 
                                   "Hover over the link to see the actual URL", 
                                   "Click the link to check if it looks legitimate"));
        questions.add(new Question("What should you do if you receive an unexpected email asking for your password?", 
                                   "Ignore it or report it as phishing", 
                                   "Enter your password to confirm your identity"));
        questions.add(new Question("What is a common trait of phishing websites?", 
                                   "They often have slight misspellings in the domain", 
                                   "They always have a padlock icon in the browser"));
        questions.add(new Question("Why is it risky to open email attachments from unknown senders?", 
                                   "They may contain malware or viruses", 
                                   "They always ask you to enter personal details"));
        questions.add(new Question("How can you tell if an email is from a fake sender?", 
                                   "Check the sender's email address carefully", 
                                   "Ignore the sender’s details and focus on the message content"));
        questions.add(new Question("What is an example of social engineering in phishing?", 
                                   "An email pretending to be from a trusted company", 
                                   "A newsletter from your favorite website"));
        questions.add(new Question("Why is it important to keep software up to date?", 
                                   "To protect against security vulnerabilities", 
                                   "Because updates slow down your computer"));
        questions.add(new Question("What should you do if a website asks for your credit card details unexpectedly?", 
                                   "Verify the request by contacting the company directly", 
                                   "Enter your details to check if the site is real"));
        questions.add(new Question("Which of these is a good way to protect yourself from phishing?", 
                                   "Use two-factor authentication (2FA)", 
                                   "Use the same password for all websites"));

        questions.shuffle();

        //Select the first question
        displayNextQuestion(table);
    }

    private void displayNextQuestion(Table table) {
        if (questions.size == 0) {
            System.out.println("All questions answered! Returning to GameScreen.");
            resumeGame();
            return;
        }

        //Select a random question
        int currentQuestionIndex = (int) (Math.random() * questions.size);
        Question currentQuestion = questions.get(currentQuestionIndex);

        
     //Create a question label with word wrapping
        questionLabel = new Label(currentQuestion.questionText, skin);
        questionLabel.setWrap(true);  //Enable text wrapping
        questionLabel.setWidth(Gdx.graphics.getWidth() * 0.8f);  //Limit width to 80% of screen width
        questionLabel.setAlignment(Align.left);  //Center the text
     //Wrap question inside a ScrollPane to prevent overflow
        ScrollPane questionScroll = new ScrollPane(questionLabel, skin);
        questionScroll.setScrollingDisabled(true, false);  //Disable horizontal scroll, allow vertical
        
        
        
        answerButton1 = new TextButton(currentQuestion.correctAnswer, skin);
        answerButton2 = new TextButton(currentQuestion.wrongAnswer, skin);

        //Remove question from the list after displaying it
        questions.removeIndex(currentQuestionIndex);
        questionsAnswered++;

        answerButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Correct answer! Increasing player health...");
                player.increaseHealth();
                resumeGame();
            }
        });

        answerButton2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("Wrong answer! No health gained.");
                resumeGame();
            }
        });



        // Update UI
        table.clear();
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
