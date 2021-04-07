package providers;

import factory.InputCollector;
import lombok.AllArgsConstructor;
import models.Player;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@AllArgsConstructor
public class PlayerProvider {
    private final InputCollector inputCollector;

    public Queue<Player> getPlayers(String[] playerTypes) {

        return IntStream.range(0, playerTypes.length)
                .mapToObj(i -> inputCollector.getPlayer(playerTypes[i], i+1))
                .collect(Collectors.toCollection(LinkedList::new));
    }
}
