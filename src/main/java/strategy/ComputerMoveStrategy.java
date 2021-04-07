package strategy;

import lombok.AllArgsConstructor;
import models.Board;
import models.Cell;
import models.Player;
import org.apache.commons.lang3.StringUtils;
import processor.AIProcessor;
import providers.CachedDataProvider;

import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
public class ComputerMoveStrategy implements MoveStrategy {

    private final Random random;
    private final AIProcessor aiProcessor;

    @Override
    public int nextIndex(Player player) {
        System.out.println(String.format("%s is going to play...", player.getName()));

        List<Integer> neverVisitedIndexes = getNeverVisitedIndexes();
        List<Integer> indexesVisitedByOtherPlayer = getIndexesVisitedByOtherPlayer(player);
        List<List<Integer>> winningConditions = CachedDataProvider.getInstance().getWinningConditions();
        Integer indexToWinInTheCurrentTurn = aiProcessor.findIndexToWin(
                neverVisitedIndexes,
                player.getAlreadyVisitedIndexes(),
                indexesVisitedByOtherPlayer,
                winningConditions);

        if (indexToWinInTheCurrentTurn > 0) {
            return indexToWinInTheCurrentTurn;
        }

        List<Integer> optimizedIndexes = aiProcessor.findIndexesToWin(neverVisitedIndexes,
                player.getAlreadyVisitedIndexes(), winningConditions);

        int randomIndexOfNeverVisitedIndexes = random
                .ints(0, optimizedIndexes.size())
                .findFirst()
                .getAsInt();

        return optimizedIndexes.get(randomIndexOfNeverVisitedIndexes);
    }


    private List<Integer> getIndexesVisitedByOtherPlayer(Player player) {
        Predicate<Cell> notContainBlank = cell -> !StringUtils.equals(" ", cell.getValue());
        Predicate<Cell> notContainCurrentPlayerSymbol = cell ->
                !StringUtils.equals(player.getPreferredSymbol(), cell.getValue());

        return Board.getInstance()
                .getCells()
                .stream()
                .filter(notContainBlank.and(notContainCurrentPlayerSymbol))
                .map(Cell::getIndex)
                .collect(Collectors.toList());
    }

    private List<Integer> getNeverVisitedIndexes() {
        Predicate<Cell> isCellAlreadyVisited = Cell::isVisited;

        return Board.getInstance()
                    .getCells()
                    .stream()
                    .filter(isCellAlreadyVisited.negate())
                    .map(Cell::getIndex)
                    .collect(Collectors.toList());
    }
}
