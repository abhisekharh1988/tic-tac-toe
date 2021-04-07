package providers;

import factory.InputCollector;
import models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import strategy.MoveStrategy;

import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PlayerProviderTest {
    private InputCollector mockInputCollector;
    private PlayerProvider playerProvider;

    @BeforeEach
    void init() {
        mockInputCollector = mock(InputCollector.class);
        playerProvider = new PlayerProvider(mockInputCollector);
    }

    @Test
    public void shouldReturnTwoPlayersWithExpectedValues() {
        when(mockInputCollector.getPlayer("HUMAN", 1)).thenReturn(
                new Player(1,
                        "Player1",
                        "A",
                        List.of(),
                        1,
                        mock(MoveStrategy.class)));
        when(mockInputCollector.getPlayer("HUMAN", 2)).thenReturn(
                new Player(1,
                        "Player2",
                        "Z",
                        List.of(),
                        1,
                        mock(MoveStrategy.class)));

        Queue<Player> actualPlayers = playerProvider.getPlayers(new String[]{"HUMAN", "HUMAN"});

        assertEquals(2, actualPlayers.size());

        Player player1 = actualPlayers.remove();
        Player player2 = actualPlayers.remove();

        assertEquals("Player1", player1.getName());
        assertEquals("A", player1.getPreferredSymbol());
        assertEquals(0, player1.getAlreadyVisitedIndexes().size());
        assertEquals("Player2", player2.getName());
        assertEquals("Z", player2.getPreferredSymbol());
        assertEquals(0, player2.getAlreadyVisitedIndexes().size());
    }
}