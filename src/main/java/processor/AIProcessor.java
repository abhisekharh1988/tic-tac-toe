package processor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AIProcessor {
    public int findIndexToWin(List<Integer> indexesNotVisited,
                              List<Integer> indexesVisitedByThisPlayer,
                              List<Integer> indexesVisitedByThatPlayer,
                              List<List<Integer>> winningConditions) {
        int indexToWinInTheCurrentTurn = getIndex(indexesNotVisited,
                indexesVisitedByThisPlayer,
                winningConditions);

        if (indexToWinInTheCurrentTurn > 0) {
            return indexToWinInTheCurrentTurn;
        }
        int indexToStopOtherPlayerToWin = getIndex(indexesNotVisited,
                indexesVisitedByThatPlayer, winningConditions);

        if (indexToStopOtherPlayerToWin > 0) {
            return indexToStopOtherPlayerToWin;
        }
        return 0;
    }

    public List<Integer> findIndexesToWin(List<Integer> indexesNotVisited,
                                          List<Integer> indexesVisitedByThisPlayer,
                                          List<List<Integer>> winningConditions) {
        List<Integer> optimizedIndexes = indexesNotVisited.stream()
                .filter(index -> {
                    List<Integer> indexesToVerify = new ArrayList<>(indexesVisitedByThisPlayer);
                    indexesToVerify.add(index);
                    return winningConditions.stream()
                            .anyMatch(winingCondition ->
                                    checkIfMinimumTwpElementsAreCommon(indexesToVerify, winingCondition)); })
                .collect(Collectors.toList());

        return optimizedIndexes.isEmpty() ? indexesNotVisited : optimizedIndexes;
    }

    private Integer getIndex(List<Integer> neverVisitedIndexes,
                             List<Integer> alreadyVisitedIndexes,
                             List<List<Integer>> winningConditions) {
        return neverVisitedIndexes.stream()
                .filter(index -> {
                    List<Integer> indexesToVerify = new ArrayList<>(alreadyVisitedIndexes);
                    indexesToVerify.add(index);
                    return winningConditions.stream()
                            .anyMatch(indexesToVerify::containsAll);
                })
                .findFirst()
                .orElse(0);
    }

    private boolean checkIfMinimumTwpElementsAreCommon(List<Integer> thisList, List<Integer> thatList) {
        return thisList.stream().filter(thatList::contains).count() >= 2;
    }
}
