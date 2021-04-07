package models;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {
    @Getter
    private List<Cell> cells;
    private static Board instance;

    private Board() {
        initialize();
    }

    public static Board getInstance() {
        if (Objects.isNull(instance)) {
            instance = new Board();
        }
        return instance;
    }

    private void initialize() {
        List<Cell> cells = new ArrayList<>();

        for (short index = 1; index <= 9; index++) {
            cells.add(new Cell(index, " ", false));
        }
        this.cells = Collections.unmodifiableList(cells);
    }

    public String getCurrentStatus() {
        return cells.stream()
                .map(Cell::getValue)
                .collect(Collectors.joining("|"));
    }

    public void placeSymbol(int cellIndex, String symbol) {
        Cell cellToBeUpdated = cells.get(cellIndex - 1);
        cellToBeUpdated.setValue(symbol);
        cellToBeUpdated.setVisited(true);
    }
}
