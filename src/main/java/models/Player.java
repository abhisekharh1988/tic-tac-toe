package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import strategy.MoveStrategy;

import java.util.List;

@AllArgsConstructor
public class Player {
    private final int id;
    @Getter
    private final String name;
    @Getter
    private final String preferredSymbol;
    @Getter
    private final List<Integer> alreadyVisitedIndexes;
    @Getter
    @Setter
    private int chancesRemainingDueToInvalidInput;
    private final MoveStrategy moveStrategy;

    public void play(Board board) {
        int inputIndex = moveStrategy.nextIndex(this);
        board.placeSymbol(inputIndex, this.preferredSymbol);
        alreadyVisitedIndexes.add(inputIndex);
    }
}
