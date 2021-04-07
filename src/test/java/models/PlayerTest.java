package models;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.HumanMoveStrategy;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerTest {
    private Player player;
    private HumanMoveStrategy humanMoveStrategy;

    @BeforeEach
    @SneakyThrows
    void init() {
        humanMoveStrategy = mock(HumanMoveStrategy.class);

        player = new Player(
                1,
                "Human",
                "X",
                new ArrayList<>(),
                3,
                humanMoveStrategy);

        Field instance = Board.class.getDeclaredField("instance");
            instance.setAccessible(true);
            instance.set(null, null);
    }

    @Test
    public void shouldUpdateCellAsVisitedForValidInput() {
       when(humanMoveStrategy.nextIndex(player)).thenReturn(5);
        Board board = Board.getInstance();

        player.play(board);

        assertEquals(1, player.getAlreadyVisitedIndexes().size());
        assertEquals(Integer.valueOf(5), player.getAlreadyVisitedIndexes().get(0));
    }
}