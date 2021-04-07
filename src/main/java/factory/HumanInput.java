package factory;

import lombok.AllArgsConstructor;
import models.Player;
import org.apache.commons.lang3.StringUtils;
import strategy.HumanMoveStrategy;

import java.util.ArrayList;
import java.util.Scanner;

@AllArgsConstructor
public class HumanInput {
    private final Scanner scanner;

    public Player getPlayer(int index) {
        System.out.println("Enter your name : ");
        String playerName = scanner.nextLine();
        playerName = getDefaultNameIfEmpty(playerName, index);

        System.out.println("Enter your preferred symbol : ");
        String preferredSymbol = scanner.nextLine();
        preferredSymbol = getDefaultSymbolIfInvalid(preferredSymbol, index);
        HumanMoveStrategy humanMoveStrategy = new HumanMoveStrategy(scanner);

        return new Player(
                index,
                playerName,
                preferredSymbol,
                new ArrayList<>(),
               3,
                humanMoveStrategy);
    }

    private String getDefaultNameIfEmpty(String givenName, int playerId) {
        if (StringUtils.isBlank(givenName)) {
            return String.format("Player%d", playerId);
        } else {
            return givenName;
        }
    }

    private String getDefaultSymbolIfInvalid(String givenSymbol, int playerId) {
        if (StringUtils.isEmpty(givenSymbol)
                || givenSymbol.length() > 1
                || (givenSymbol.equalsIgnoreCase("O") && playerId == 1)
                || (givenSymbol.equalsIgnoreCase("X") && playerId == 2)) {
            return (playerId == 1) ? "X" : "O";
        } else {
            return givenSymbol;
        }
    }
}
