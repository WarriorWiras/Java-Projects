package gamecollision;

import abstractentity.Entity;
import gameentity.Player;

public interface CollisionHandler {
    void handleCollision(Player player, Entity entity);
}
