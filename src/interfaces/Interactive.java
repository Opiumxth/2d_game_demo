package interfaces;

import entity.Player;

public interface Interactive {
    void interact(Player player);
    boolean isInteractable();
}