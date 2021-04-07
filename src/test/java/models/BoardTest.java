package models;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    @BeforeEach
    void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field instance = Board.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void shouldCreateOnlyOneInstanceOfBoard() {
        Board board1 = Board.getInstance();
        Board board2 = Board.getInstance();

        assertEquals(board1, board2);
    }

    @Test
    public void shouldReturnCurrentStatusOfBoard() {
        String expectedStatus = " | | | | | | | | ";
        Board board = Board.getInstance();

        String actualStatus = board.getCurrentStatus();

        assertEquals(expectedStatus, actualStatus);

        expectedStatus = "O|X|O|X|O|X|O|X|O";

        board.getCells().forEach(cell -> {
            if (cell.getIndex() % 2 == 0) {
                cell.setValue("X");
            } else {
                cell.setValue("O");
            }
        });

        actualStatus = board.getCurrentStatus();

        assertEquals(expectedStatus, actualStatus);
    }

    @Test
    public void shouldNotAllowAddingNewCellOnceBoardIsInitialized() {
        Board board = Board.getInstance();
        assertThrows(UnsupportedOperationException.class,
                () -> board.getCells().add(new Cell(10, "", false)));
    }

    @Test
    public void shouldUpdateCellValueAndMakeTheCellAsVisited() {
        Board board = Board.getInstance();

        board.placeSymbol(3, "X");

        assertEquals("X", board.getCells().get(2).getValue());
        assertTrue(board.getCells().get(2).isVisited());
    }
}