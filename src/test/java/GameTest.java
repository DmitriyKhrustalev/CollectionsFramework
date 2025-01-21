import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;

    // Игроки для удобства использования в тестах
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    @BeforeEach
    public void setup() {
        game = new Game();

        // Создаём нескольких игроков с разной силой
        player1 = new Player(1, "Alice", 10);
        player2 = new Player(2, "Bob", 15);
        player3 = new Player(3, "Charlie", 10);
        player4 = new Player(4, "Den", 20);

        // Регистрируем некоторых игроков
        game.register(player1);
        game.register(player2);
        game.register(player3);
        // player4 не регистрирую специально
    }

    @Test
    public void shouldRegisterPlayersSuccessfully() {
        // Проверяем, что зарегистрированные игроки действительно в списке
        Assertions.assertTrue(game.getPlayers().contains(player1));
        Assertions.assertTrue(game.getPlayers().contains(player2));
        Assertions.assertTrue(game.getPlayers().contains(player3));
        // А не зарегистрированный там не содержится
        Assertions.assertFalse(game.getPlayers().contains(player4));
    }

    @Test
    public void shouldThrowExceptionIfFirstPlayerNotRegistered() {
        // Проверяем, что выпадет исключение, когда нет первого игрока
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Den", "Bob"); // "Den" (player4) не зарегистрирован
        });
    }

    @Test
    public void shouldThrowExceptionIfSecondPlayerNotRegistered() {
        // Проверяем, что выпадет исключение, когда нет второго игрока
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Bob", "Den");
        });
    }

    @Test
    public void shouldThrowExceptionIfBothPlayersNotRegistered() {
        // Проверяем случай, когда оба игрока не зарегистрированы
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Den", "SomeoneUnknown");
        });
    }

    @Test
    public void shouldReturn1IfFirstPlayerIsStronger() {
        game.register(new Player(5, "BobTheStrong", 15));

        int result = game.round("Bob", "Charlie");

        Assertions.assertEquals(1, result);
    }

    @Test
    public void shouldReturn2IfSecondPlayerIsStronger() {
        int result = game.round("Charlie", "Bob");
        Assertions.assertEquals(2, result);
    }

    @Test
    public void shouldReturn0IfStrengthEquals() {
        int result = game.round("Alice", "Charlie");
        Assertions.assertEquals(0, result);
    }
}
