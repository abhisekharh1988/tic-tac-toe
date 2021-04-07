import exceptions.FatalException;
import factory.InputCollector;
import helpers.Printer;
import models.Game;
import processor.AIProcessor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeApplication {

    public static void main(String[] args) {
        Properties prop = new Properties();
        try (InputStream input = new FileInputStream("src/main/resources/config.properties")) {
            prop.load(input);
            System.out.println("Player Type - " + prop.getProperty("player.types"));
        } catch (IOException ex) {
            new FatalException("Unable To Load Config");
        }
        String[] playerTypes = prop.getProperty("player.types").split(",");
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        AIProcessor aiProcessor = new AIProcessor();
        InputCollector inputCollector = new InputCollector(scanner, random, aiProcessor);
        Printer printer = new Printer();
        Game game = new Game(printer, inputCollector, playerTypes);
        game.start();
    }
}
