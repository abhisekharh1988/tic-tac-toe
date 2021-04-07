package processor;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AIProcessorTest {

    @Test
    public void shouldReturnIndexThatWillHelpToWin() {
        List<Integer> indexesNotVisitedByComputer = List.of(2, 5, 7);
        List<Integer> indexesVisitedByComputer = List.of(1, 3);
        List<Integer> indexesVisitedByHuman = List.of(8, 6, 4);
        List<List<Integer>> winningConditions = List.of(List.of(1, 2, 3));

        int actual = new AIProcessor().findIndexToWin(indexesNotVisitedByComputer,
                indexesVisitedByComputer, indexesVisitedByHuman, winningConditions);

        assertEquals(2, actual);
    }

    @Test
    public void shouldReturnIndexThatWillStopOtherPlayerToWin() {
        List<Integer> indexesNotVisitedByThisPlayer = List.of(2, 4, 5, 7);
        List<Integer> indexesVisitedByThisPlayer = List.of(6, 8);
        List<Integer> indexesVisitedByThatPlayer = List.of(1, 3);
        List<List<Integer>> winningConditions = List.of(List.of(1, 2, 3));

        int actual = new AIProcessor().findIndexToWin(indexesNotVisitedByThisPlayer,
                indexesVisitedByThisPlayer, indexesVisitedByThatPlayer, winningConditions);

        assertEquals(2, actual);
    }

    @Test
    public void shouldReturnZeroIfNoIndexFound() {
        List<Integer> indexesNotVisitedByThisPlayer = List.of(4, 5, 7);
        List<Integer> indexesVisitedByThisPlayer = List.of(3, 6, 8);
        List<Integer> indexesVisitedByThatPlayer = List.of(1, 2);
        List<List<Integer>> winningConditions = List.of(List.of(1, 2, 3));

        int actual = new AIProcessor().findIndexToWin(indexesNotVisitedByThisPlayer,
                indexesVisitedByThisPlayer, indexesVisitedByThatPlayer, winningConditions);

        assertEquals(0, actual);
    }

    @Test
    public void shouldReturnPossibleIndexesThoseWillHelpToWin() {
        List<Integer> indexesNotVisitedByThisPlayer = List.of(2, 3, 5, 6, 8);
        List<Integer> indexesVisitedByThisPlayer = List.of(1, 4);
        List<List<Integer>> winningConditions = List.of(List.of(1, 2, 3), List.of(4, 5, 6));

        List<Integer> actual = new AIProcessor().findIndexesToWin(indexesNotVisitedByThisPlayer,
                indexesVisitedByThisPlayer, winningConditions);

        assertEquals(List.of(2, 3, 5, 6), actual);
    }

}