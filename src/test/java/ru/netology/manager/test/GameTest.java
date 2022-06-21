package ru.netology.manager.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import ru.netology.domain.Player;
import ru.netology.exeption.NotRegisteredException;
import ru.netology.manager.Game;

import java.util.ArrayList;
import java.util.Collection;

public class GameTest {

    Player player1 = new Player(1, "Katya", 6);
    Player player2 = new Player(2, "Vasa", 8);
    Player player3 = new Player(3, "Kaile", 4);
    Player player4 = new Player(4, "David", 7);
    Player player5 = new Player(5, "Anton", 3);
    Player player6 = new Player(6, "Marina", 7);


    @Test
    public void roundPlayer2Win() {
        Game game = new Game();
        Collection<Player> players = new ArrayList<>();

        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.register(player5);
        game.register(player6);
        game.registerAll(players);
        game.findAll();
        game.findByName("David");
        game.round("David", "Vasa");

        int actual = game.round("David", "Vasa");
        int expected = 2;

        assertEquals(expected, actual);
    }

    @Test
    public void exceptionPlayer1() {
        Game game = new Game();

        game.findAll();
        game.findByName("David");

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("David", "Vasa");
        });
    }

    @Test
    public void exceptionPlayer2() {
        Game game = new Game();

        game.register(player1);
        game.findAll();

        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Katya", "Vasa");
        });
    }

    @Test
    public void roundPlayer1Win() {
        Game game = new Game();
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.register(player5);
        game.register(player6);
        game.findAll();
        game.findByName("Katya");

        int actual = game.round("Katya", "Anton");
        int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void roundPlayerDraw() {
        Game game = new Game();
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.register(player5);
        game.register(player6);
        game.findAll();
        game.findByName("Katya");

        int actual = game.round("Marina", "David");
        int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void searchStrengthFall() {
        Game game = new Game();
        game.register(player1);
        game.register(player2);
        game.register(player3);
        game.register(player4);
        game.register(player6);
        game.findAll();
        game.findByName("Katya");

        int actual = game.findByStrength("Anton");
        int expected = 0;
        assertEquals(expected, actual);
    }

}
