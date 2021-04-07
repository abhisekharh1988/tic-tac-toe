package models;

import exceptions.FatalException;
import factory.InputCollector;
import helpers.Printer;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import processor.AIProcessor;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class GameTest {
    private Printer mockPrinter;

    @BeforeEach
    @SneakyThrows
    void init() {
        mockPrinter = mock(Printer.class);
        Field instance = Board.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }

    @AfterEach
    void reset() {
        Mockito.reset(mockPrinter);
    }

    @Test
    @SneakyThrows
    public void shouldDeclareWinner() {
        String expectedResult = "X|X|O|X|O| |O| | ";
        ArgumentCaptor<String> printArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> resultArgumentCaptor = ArgumentCaptor.forClass(String.class);

        File fileInput = new File("src/test/resources/inputWhenPlayer2IsWinner.txt");
        String[] playerTypes = {"HUMAN", "HUMAN"};
        InputCollector inputCollector = new InputCollector(new Scanner(fileInput), new Random(), new AIProcessor());
        Game game = new Game(mockPrinter, inputCollector, playerTypes);

        game.start();

        verify(mockPrinter, times(7)).printBoard(printArgumentCaptor.capture());
        verify(mockPrinter, times(1)).announceResult(resultArgumentCaptor.capture());
        assertEquals(expectedResult, printArgumentCaptor.getAllValues().get(6));
        assertEquals("Player2 is winner", resultArgumentCaptor.getValue());
    }

    @Test
    @SneakyThrows
    public void shouldDeclareTieIfNoneOfThePlayerWinsTheGame() {
        String expectedResult = "X|X|O|O|O|X|X|O|X";
        ArgumentCaptor<String> printArgumentCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> resultArgumentCaptor = ArgumentCaptor.forClass(String.class);

        File fileInput = new File("src/test/resources/inputWhenMatchIsATie.txt");
        String[] playerTypes = {"HUMAN", "HUMAN"};
        InputCollector inputCollector = new InputCollector(new Scanner(fileInput), new Random(), new AIProcessor());
        Game game = new Game(mockPrinter, inputCollector, playerTypes);

        game.start();

        verify(mockPrinter, times(10)).printBoard(printArgumentCaptor.capture());
        verify(mockPrinter, times(1)).announceResult(resultArgumentCaptor.capture());
        assertEquals(expectedResult, printArgumentCaptor.getAllValues().get(9));
        assertEquals("The match is a tie", resultArgumentCaptor.getValue());
    }

    @Test
    @SneakyThrows
    public void shouldHandleException() {
        File fileInput = new File("src/test/resources/inputWhenExceptionOccurs.txt");
        String[] playerTypes = {"HUMAN", "HUMAN"};
        InputCollector inputCollector = new InputCollector(new Scanner(fileInput), new Random(), new AIProcessor());
        Game game = new Game(mockPrinter, inputCollector, playerTypes);

        FatalException thrown = assertThrows(FatalException.class, () -> game.start());

        assertEquals("Maximum Retry Limit Exceeded", thrown.getMessage());
    }

}