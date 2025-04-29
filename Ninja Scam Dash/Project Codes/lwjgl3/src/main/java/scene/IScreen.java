package scene;

/**
 * Defines required methods for game screens.
 */
public interface IScreen {
    void show();
    void render(float delta);
    void resize(int width, int height);
    void hide();
    void dispose();
}
