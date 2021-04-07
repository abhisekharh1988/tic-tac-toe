package providers;

import lombok.Getter;

import java.util.List;

public class CachedDataProvider {
    private static final CachedDataProvider instance = new CachedDataProvider();
    @Getter
    private final List<List<Integer>> winningConditions;

    private CachedDataProvider() {
        this.winningConditions = generateWinningConditions();
    }

    public static CachedDataProvider getInstance() {
        return instance;
    }

    private List<List<Integer>> generateWinningConditions() {
        List<Integer> firstRow = List.of(1, 2, 3);
        List<Integer> secondRow = List.of(4, 5, 6);
        List<Integer> thirdRow = List.of(7, 8, 9);
        List<Integer> firstColumn = List.of(1, 4, 7);
        List<Integer> secondColumn = List.of(2, 5, 8);
        List<Integer> thirdColumn = List.of(3, 6, 9);
        List<Integer> firstCross = List.of(1, 5, 9);
        List<Integer> secondCross = List.of(3, 5, 7);
        return List.of(
                firstRow,
                secondRow,
                thirdRow,
                firstColumn,
                secondColumn,
                thirdColumn,
                firstCross,
                secondCross
        );
    }

}
