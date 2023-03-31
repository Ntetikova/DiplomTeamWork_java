package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class PlayerTest {

    GameStore store = new GameStore();

    Player player = new Player("Petya");

    Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
    Game game2 = store.publishGame("Голодные муравьи", "Аркады");
    Game game3 = store.publishGame("Трон", "Стратегия");
    Game game4 = store.publishGame("Трон", "Головоломка");
    Game game5 = store.publishGame("Пушка", "Стратегия");
    Game game6 = store.publishGame("Шахматы", "Головоломка");
    Game game7 = store.publishGame("Слова из слов", "Головоломка");
    Game game8 = store.publishGame("Поиск предметов", "Головоломка");

    @Test
    public void shouldSumGenreIfOneGame() {

        player.installGame(game1);
        player.play(game1, 3);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfTwoGame() {

        player.installGame(game1);
        player.play(game1, 8);

        player.installGame(game2);
        player.play(game2, 3);

        int expected = 11;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumIfNoOneGameInThisGenre() {

        player.installGame(game1);
        player.play(game1, 6);

        player.installGame(game3);
        player.play(game3, 8);

        player.installGame(game5);
        player.play(game5, 3);

        int expected = 0;
        int actual = player.sumGenre("Головоломка");
        assertEquals(expected, actual);
    }


    @Test
    public void shouldThrowExceptionIfNotInstall() {

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game1, 9);
        });
    }

    @Test
    public void shouldNotAddSameGame() {

        player.installGame(game1);
        player.play(game1, 2);

        player.installGame(game1);

        int expected = 3;
        int actual = player.play(game1, 1);
        assertEquals(expected, actual);

    }

    @Test
    public void shouldFindMostPlayerGameByGenre() {

        player.installGame(game4);
        player.play(game4, 3);

        player.installGame(game6);
        player.play(game6, 8);

        player.installGame(game7);
        player.play(game7, 5);

        player.installGame(game8);
        player.play(game8, 0);

        Game expected = game6;
        Game actual = player.mostPlayerByGenre("Головоломка");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMostPlayerGameByGenreIfNoOnePlay() {

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 6);

        player.installGame(game2);

        player.installGame(game3);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Стратегия");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMostPlayerGameByGenreIfNoOneGameAdd() {

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Стратегия");

        assertEquals(expected, actual);
    }

}
