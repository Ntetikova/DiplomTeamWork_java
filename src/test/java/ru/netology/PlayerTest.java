package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

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
    public void shouldSumGenreIfTwoGamesWithoutInstall() {
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Голодные муравьи", "Аркады");


        Player player = new Player("Petya");
        player.play(game1, 3);

        player.play(game2, 8);

        int expected = 11;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }


  /*  @Test
    public void shouldGetUpdateGameHoursIfInstall() {
     Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.playedTime.get(game);
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
        player.play(game1, 8);

        Game expected = game2;
        Game actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindMostPlayerGameByGenreIfNoOnePlay() {
        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Головоломка");
        Game game2 = store.publishGame("Трон", "Стратегия");

        Player player1 = new Player("Petya");
        player1.installGame(game1);
        player1.play(game1, 3);

        Player player2 = new Player("Vladimir");
        player2.installGame(game2);
        player2.play(game1, 8);

        Game expected = null;
        Game actual = player2.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);
    }


}
