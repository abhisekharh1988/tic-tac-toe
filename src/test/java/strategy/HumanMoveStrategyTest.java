package strategy;

import exceptions.FatalException;
import lombok.SneakyThrows;
import models.Board;
import models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class HumanMoveStrategyTest {
    private Player player;

    @BeforeEach
    @SneakyThrows
    void init() {
        player = new Player(1,
                "Test_Player",
                "X",
                List.of(),
                3,
                mock(MoveStrategy.class));
        Field instance = Board.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @Test
    public void shouldReturnIndexIfInputIsValid() {
        String input = String.format("5%n");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        HumanMoveStrategy humanMoveStrategy = new HumanMoveStrategy(scanner);

        assertEquals(5, humanMoveStrategy.nextIndex(player));
    }

    @Test
    public void shouldThrowExceptionIfMaxRetriesAreElapsed() {
        Board board = Board.getInstance();
        board.placeSymbol(5, "X");

        String input = String.format(" %na%n5%n");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        HumanMoveStrategy humanMoveStrategy = new HumanMoveStrategy(scanner);

        FatalException thrown = assertThrows(FatalException.class, () -> humanMoveStrategy.nextIndex(player));

        assertEquals("Maximum Retry Limit Exceeded", thrown.getMessage());
    }

}