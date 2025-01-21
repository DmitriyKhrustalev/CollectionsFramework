import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameTest {

    private Game game;

    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;

    @BeforeEach
    public void setup() {
        game = new Game();

        player1 = new Player(1, "Alice", 10);
        player2 = new Player(2, "Bob", 15);
        player3 = new Player(3, "Charlie", 10);
        player4 = new Player(4, "Den", 20);

        game.register(player1);
        game.register(player2);
        game.register(player3);
    }

    @Test
    public void shouldRegisterPlayersSuccessfully() {
        // Проверяем, что зарегистрированные игроки есть в Map
        Assertions.assertTrue(game.getPlayers().containsKey("Alice"));
        Assertions.assertTrue(game.getPlayers().containsKey("Bob"));
        Assertions.assertTrue(game.getPlayers().containsKey("Charlie"));
        // А "Den" (player4) не зарегистрирован
        Assertions.assertFalse(game.getPlayers().containsKey("Den"));
    }

    @Test
    public void shouldThrowExceptionIfFirstPlayerNotRegistered() {
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Den", "Bob");
        });
    }

    @Test
    public void shouldThrowExceptionIfSecondPlayerNotRegistered() {
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Bob", "Den");
        });
    }

    @Test
    public void shouldThrowExceptionIfBothPlayersNotRegistered() {
        Assertions.assertThrows(NotRegisteredException.class, () -> {
            game.round("Den", "SomeoneUnknown");
        });
    }

    @Test
    public void shouldReturn1IfFirstPlayerIsStronger() {
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
