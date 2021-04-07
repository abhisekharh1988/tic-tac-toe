package factory;

import lombok.AllArgsConstructor;
import models.Player;
import processor.AIProcessor;

import java.util.Random;
import java.util.Scanner;

import static helpers.Constants.HUMAN;

@AllArgsConstructor
public class InputCollector {
    private final Scanner scanner;
    private final Random random;
    private final AIProcessor aiProcessor;

    public Player getPlayer(String type, int index) {
        if (HUMAN.equals(type)) {
            return new HumanInput(scanner).getPlayer(index);
        } else {
            return new ComputerInput(random, aiProcessor).getPlayer(index);
        }
    }
}
