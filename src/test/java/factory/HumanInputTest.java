package factory;

import models.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HumanInputTest {

    @ParameterizedTest
    @ValueSource(strings = {" \nAA\n", "\nO\n", "\n\n"})
    public void shouldReturnDefaultNameAndSymbolIfInputIsInvalid(String input) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        HumanInput humanInput = new HumanInput(scanner);

        Player player = humanInput.getPlayer(1);

        assertEquals("Player1", player.getName());
        assertEquals("X", player.getPreferredSymbol());
    }

    @Test
    public void shouldReturnGivenNameAndSymbolIfInputIsValid() {
        String input = String.format("Jack%n*%n");
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);

        HumanInput humanInput = new HumanInput(scanner);

        Player player = humanInput.getPlayer(1);

        assertEquals("Jack", player.getName());
        assertEquals("*", player.getPreferredSymbol());
    }


}