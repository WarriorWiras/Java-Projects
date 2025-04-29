package gameentity;

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

import movement.MovementManager;
import input_output.InputManager;
import collision.CollisionManager;
import gamecollision.PlayerObjectCollisionManager;
import scene.SceneManager;
import java.util.Random;
import java.util.Iterator;

public class UIManager {
    private SpriteBatch batch;
    private Texture heartTexture;
    private Texture diamondTexture;

    public UIManager(SpriteBatch batch, Texture heartTexture, Texture diamondTexture) {
        this.batch = batch;
        this.heartTexture = heartTexture;
        this.diamondTexture = diamondTexture;
    }

    public void drawHealthBar(Player player) {
        float heartSize = 32;
        float spacing = 10;
        float startX = 20;  // Hearts start from the left
        float startY = Gdx.graphics.getHeight() - heartSize - 10;

        for (int i = 0; i < player.getHealth(); i++) {
            batch.draw(heartTexture, startX + (i * (heartSize + spacing)), startY, heartSize, heartSize);
        }
    }

    public void drawDiamonds(Player player) {
        float diamondSize = 32;
        float spacing = 10;
        float startX = 400;  // Diamonds start from 400 pixels horizontally
        float startY = Gdx.graphics.getHeight() - diamondSize - 10;

        for (int i = 0; i < player.getDiamondsCollected(); i++) {
            batch.draw(diamondTexture, startX + (i * (diamondSize + spacing)), startY, diamondSize, diamondSize);
        }
    }
}
