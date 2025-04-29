package gamecollision;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import java.util.ArrayList;
import java.util.List;


public class Boundary {
    private float screenLeft, screenRight, screenTop, screenBottom;

    public Boundary(float screenLeft, float screenRight, float screenTop, float screenBottom) {
        this.screenLeft = screenLeft;
        this.screenRight = screenRight;
        this.screenTop = screenTop;
        this.screenBottom = screenBottom;
    }

    //method to check if an entity is out of bounds
    public boolean checkifOOB(float x, float y, float width, float height) {
        if (x < screenLeft || x + width > screenRight || y < screenBottom || y + height > screenTop) {
            return true; //  out of bounds
        }
        return false; // in bounds
    }


    //getters for the screen boundaries
    public float getScreenLeft() {
        return screenLeft;
    }

    public float getScreenRight() {
        return screenRight;
    }

    public float getScreenTop() {
        return screenTop;
    }

    public float getScreenBottom() {
        return screenBottom;
    }
}
