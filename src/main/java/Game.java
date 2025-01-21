import java.util.HashMap;
import java.util.Map;

public class Game {
    private Map<String, Player> players = new HashMap<>();


    public void register(Player player) {
        // При добавлении мы кладём в мапу пару: (имя -> Player)
        players.put(player.getName(), player);
    }

    /**
     * Метод для проведения раунда между двумя игроками по их именам.
     * @param playerName1 имя первого игрока
     * @param playerName2 имя второго игрока
     * @return 1, если победил первый; 2 — если второй; 0 — если ничья
     * @throws NotRegisteredException если хотя бы один из игроков не зарегистрирован
     */
    public int round(String playerName1, String playerName2) {
        Player player1 = players.get(playerName1);
        Player player2 = players.get(playerName2);

        // Если по ключу в мапе вернулся null, значит игрок не найден
        if (player1 == null) {
            throw new NotRegisteredException("Игрок с именем " + playerName1 + " не зарегистрирован");
        }
        if (player2 == null) {
            throw new NotRegisteredException("Игрок с именем " + playerName2 + " не зарегистрирован");
        }

        // Сравниваем силу
        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        } else if (player1.getStrength() < player2.getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }


    public Map<String, Player> getPlayers() {
        return players;
    }
}