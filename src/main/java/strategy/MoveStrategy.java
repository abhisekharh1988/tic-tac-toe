package strategy;

import models.Player;

public interface MoveStrategy {
    int nextIndex(Player player);
}
