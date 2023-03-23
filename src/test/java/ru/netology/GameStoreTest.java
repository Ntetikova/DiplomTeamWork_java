package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class GameStoreTest {

    @Test
    public void shouldAddGame() {

        GameStore store = new GameStore();
        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        assertTrue(store.containsGame(game));
    }

    // другие ваши тесты
    @Test
    public void shouldAddMoreGames() {

        GameStore store = new GameStore();
        Game game1 = store.publishGame("Нетология Баттл", "Головоломка");
        Game game2 = store.publishGame("Трон", "Стратегия");
        Game game3 = store.publishGame("Потеряный остров", "Приключения");
        Game game4 = store.publishGame("Райд", "Экшн");
        Game game5 = store.publishGame("Симс", "Симулятор");

        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));
        assertTrue(store.containsGame(game3));
        assertTrue(store.containsGame(game4));
        assertTrue(store.containsGame(game5));
    }

    @Test
    public void shouldNotAddSameGames() {

        GameStore store = new GameStore();
        Game game1 = store.publishGame("Потеряный остров", "Приключения");
        Game game2 = store.publishGame("Нетология Баттл", "Головоломка");
        Game game3 = store.publishGame("Нетология Баттл", "Головоломка");

        assertTrue(store.containsGame(game1));
        assertTrue(store.containsGame(game2));
        assertFalse(store.containsGame(game3));

    }

    @Test
    public void shouldSumPlayedTime() {

        GameStore store = new GameStore();
        store.addPlayTime("Gamer_1", 4);
        store.addPlayTime("Gamer_1", 6);


        int expected = 10;
        int actual = store.getSumPlayedTime();

        assertEquals(expected, actual);

    }

    @Test
    public void shouldFindPlayerWithMorePlayTime() {

        GameStore store = new GameStore();
        store.addPlayTime("Gamer_1", 6);
        store.addPlayTime("Gamer_2", 7);
        store.addPlayTime("Gamer_3", 5);

        String expected = "Gamer_2";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);

    }

    @Test
    public void testGetMostPlayerInOneHour() {

        GameStore store = new GameStore();
        store.addPlayTime("Gamer_1", 1);
        store.addPlayTime("Gamer_2", 0);

        String expected = "Gamer_1";
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);

    }

    @Test
    public void testGetMostPlayerWithoutGamePlayers() {

        GameStore store = new GameStore();
        String expected = null;
        String actual = store.getMostPlayer();

        assertEquals(expected, actual);

    }
}
