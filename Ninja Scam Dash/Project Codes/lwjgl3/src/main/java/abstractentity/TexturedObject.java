package abstractentity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import gamecollision.Boundary;


//Represents a game entity that uses a texture for rendering.

public class TexturedObject extends Entity{
    private Texture tex; 
    private Rectangle bounds;  

    public TexturedObject(String texFile, float x, float y, float speed) {
        super(x, y, speed); 
        tex = new Texture(texFile);
        bounds = new Rectangle(x, y, tex.getWidth(), tex.getHeight());
    }

    @Override
    public Rectangle getBounds() { 
        bounds.setPosition(getX(), getY());
        return bounds;
    }

    public Texture getTexture() {
        return tex;
    }

    public void setTexture(Texture tex) {
        this.tex = tex;
    }
    
    public float getWidth() {
        return tex.getWidth();
    }

    public float getHeight() {
        return tex.getHeight();
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(tex, x, y);
    }

    @Override
    public void update() {
        bounds.setPosition(getX(), getY());
    }

    @Override
    public void movement() {
        //Movement will be handled by subclasses
    	
    }

    @Override
    public void draw(ShapeRenderer shapeRenderer) {
        //Not used since we use SpriteBatch
    }

	@Override
	public void adjustPosition(Boundary boundary) {
		// TODO Auto-generated method stub
		
	}
}
