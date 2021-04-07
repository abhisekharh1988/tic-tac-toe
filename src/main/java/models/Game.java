package models;

import exceptions.FatalException;
import providers.CachedDataProvider;
import providers.PlayerProvider;
import factory.InputCollector;
import helpers.Printer;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Queue;

public class Game {
    private final Board board;
    private final Queue<Player> players;
    private final Printer printer;

    public Game(Printer printer, InputCollector inputCollector, String[] playerTypes) {
        this.board = Board.getInstance();
        this.players = new PlayerProvider(inputCollector)
                .getPlayers(playerTypes);
        this.printer = printer;
    }

    public void start() {
        printer.printBoard(board.getCurrentStatus());
        int numberOfTurnsRemaining = 9;
        boolean isWinnerFound = false;

        try {
            while (numberOfTurnsRemaining > 0) {
                Player currentPlayer = players.remove();
                currentPlayer.play(board);

                printer.printBoard(board.getCurrentStatus());
                players.add(currentPlayer);

                String winner = checkWinner();
                if (StringUtils.isNotEmpty(winner)) {
                    printer.announceResult(String.format("%s is winner", winner));
                    isWinnerFound = true;
                    break;
                }

                --numberOfTurnsRemaining;
            }

            if (!isWinnerFound) {
                printer.announceResult("The match is a tie");
            }
        } catch (FatalException fatalException) {
            System.out.println(String.format("Program is stopped due to : %s", fatalException.getMessage()));
            throw fatalException;
        }
    }

    private String checkWinner() {
        List<List<Integer>> winningConditions = CachedDataProvider.getInstance().getWinningConditions();

        return players.stream()
                .filter(player -> winningConditions.stream()
                        .anyMatch(winingCondition -> player.getAlreadyVisitedIndexes().containsAll(winingCondition)))
                .map(Player::getName)
                .findFirst()
                .orElse("");
    }
}
