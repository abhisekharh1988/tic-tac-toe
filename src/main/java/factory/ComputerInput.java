package factory;

import lombok.AllArgsConstructor;
import models.Player;
import processor.AIProcessor;
import strategy.ComputerMoveStrategy;
import java.util.ArrayList;
import java.util.Random;

@AllArgsConstructor
public class ComputerInput {

    private final Random random;
    private final AIProcessor aiProcessor;

    public Player getPlayer(int index) {
        String playerName = String.format("Computer-%s", index);
        String preferredSymbol= (index == 1) ? "X" : "O";
        ComputerMoveStrategy computerMoveStrategy = new ComputerMoveStrategy(random, aiProcessor);

        return new Player(
                index,
                playerName,
                preferredSymbol,
                new ArrayList<>(),
                3,
                computerMoveStrategy);
    }
}
