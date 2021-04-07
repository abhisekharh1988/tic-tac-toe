package helpers;

import factory.InputCollector;
import models.Player;
import org.junit.jupiter.api.Test;
import processor.AIProcessor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputCollectorTest {

    @Test
    public void shouldGetPlayerFromFactory() {
        String input = String.format("Player1%n%n");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        InputCollector inputCollector = new InputCollector(scanner, new Random(), new AIProcessor());

        Player human = inputCollector.getPlayer("HUMAN", 1);
        Player computer = inputCollector.getPlayer("COMPUTER", 2);

        assertEquals("Player1", human.getName());
        assertEquals("Computer-2", computer.getName());
    }
}