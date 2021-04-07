package strategy;

import exceptions.FatalException;
import lombok.AllArgsConstructor;
import models.Board;
import models.Cell;
import models.Player;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;
import java.util.function.Predicate;

@AllArgsConstructor
public class HumanMoveStrategy implements MoveStrategy {
    private final Scanner scanner;

    @Override
    public int nextIndex(Player player) {
        System.out.println(String.format("%s, Enter your move - ", player.getName()));
        String inputReceived = scanner.nextLine();

        if (!isValid(inputReceived)) {
            player.setChancesRemainingDueToInvalidInput(player.getChancesRemainingDueToInvalidInput() - 1);
            fallBack(player);
        }

        return Integer.parseInt(inputReceived);
    }

    private void fallBack(Player player) {
        if (player.getChancesRemainingDueToInvalidInput() > 0) {
            nextIndex(player);
        } else {
            throw new FatalException("Maximum Retry Limit Exceeded");
        }
    }

    private boolean isValid(String input) {
        if (StringUtils.isEmpty(input)) {
            return false;
        } else if (!NumberUtils.isDigits(input)) {
            return false;
        } else {
            int inputIndex = Integer.parseInt(input);
            Predicate<Cell> isIndexWithinRange = cell -> cell.getIndex() == inputIndex;
            Predicate<Cell> isCellNeverVisited = cell -> !cell.isVisited();

            return Board.getInstance()
                    .getCells()
                    .stream()
                    .anyMatch(isIndexWithinRange.and(isCellNeverVisited));
        }
    }
}
