package strategy;

import models.Board;
import models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processor.AIProcessor;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class ComputerMoveStrategyTest {
    private AIProcessor  mockAiProcessor;
    private Random mockRandom;
    private ComputerMoveStrategy computerMoveStrategy;
    private Player player;

    @BeforeEach
    void init() {
        mockAiProcessor = mock(AIProcessor.class);
        mockRandom = mock(Random.class);
        computerMoveStrategy = new ComputerMoveStrategy(mockRandom, mockAiProcessor);
        player = new Player(1, "Player1", "X", List.of(), 2, computerMoveStrategy);
    }

    @Test
    public void shouldReturnIndexProvidedByAIProcessor() {
        when(mockAiProcessor.findIndexToWin(anyList(), anyList(), anyList(), anyList())).thenReturn(2);

        int actual = computerMoveStrategy.nextIndex(player);

        assertEquals(2, actual);

        verify(mockAiProcessor, never()).findIndexesToWin(anyList(), anyList(), anyList());
        verify(mockRandom, never()).ints(anyInt(), anyInt());
    }

    @Test
    public void shouldReturnRandomIndex() {
        when(mockAiProcessor.findIndexToWin(anyList(), anyList(), anyList(), anyList())).thenReturn(0);
        when(mockAiProcessor.findIndexesToWin(anyList(), anyList(), anyList())).thenReturn(List.of(1, 2, 3));
        when(mockRandom.ints(0, 3)).thenReturn(IntStream.of(2));

        int actual = computerMoveStrategy.nextIndex(player);

        assertEquals(3, actual);

        verify(mockAiProcessor, times(1)).findIndexesToWin(anyList(), anyList(), anyList());
        verify(mockRandom, times(1)).ints(anyInt(), anyInt());
    }

}