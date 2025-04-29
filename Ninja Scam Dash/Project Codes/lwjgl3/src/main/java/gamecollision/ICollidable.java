package gamecollision;

import com.badlogic.gdx.math.Rectangle;

import abstractentity.Entity;

public interface ICollidable {
    //Method to get the bounds of the entity (used for collision detection)
    Rectangle getBounds();
}
