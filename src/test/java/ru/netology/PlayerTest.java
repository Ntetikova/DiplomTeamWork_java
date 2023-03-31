package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    GameStore store = new GameStore();

    @Test
    public void shouldSumGenreIfOneGame() {
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }
    @Test
    public void shouldSumGenreIfTwoGame() {
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Голодные муравьи", "Аркады");

        Player player = new Player("Petya");

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
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Голодные муравьи", "Аркады");
        Game game3 = store.publishGame("Трон", "Стратегия");

        Player player = new Player("Petya");

        player.installGame(game1);
        player.play(game1, 6);

        player.installGame(game2);
        player.play(game2, 8);

        player.installGame(game3);
        player.play(game3, 3);

        int expected = 0;
        int actual = player.sumGenre("Головоломка");
        assertEquals(expected, actual);
    }



    @Test
    public void shouldThrowExceptionIfNotInstall() {
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game1, 9);
        });
    }


 /*   @Test
    public  void shouldNotAddSameGame() {
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        player.installGame(game1);
        player.installGame(game2);

        int expected = 0;
        int actual = player.playedTime.get(0);
        assertEquals(expected, actual);
    }

  */

    @Test
    public void shouldFindMostPlayerGameByGenre() {
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Голодные муравьи", "Аркады");

        Player player = new Player("Petya");

        player.installGame(game1);
        player.play(game1, 3);

        player.installGame(game2);
        player.play(game2, 8);

        Game expected = game2;
        Game actual = player.mostPlayerByGenre("Аркады");

        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMostPlayerGameByGenreIfNoOnePlay() {
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Трон", "Стратегия");
        Game game3 = store.publishGame("Голодные муравьи", "Стратегия");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.play(game1, 6);

        player.installGame(game2);

        player.installGame(game3);

        Game expected = null;
        Game actual = player.mostPlayerByGenre("Стратегия");

        assertEquals(expected, actual);
    }


}
