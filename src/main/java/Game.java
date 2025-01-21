import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Player> players = new ArrayList<>();


    public void register(Player player) {
        players.add(player);
    }

    /**
     * Метод для проведения "раунда" между двумя игроками по их именам.
     * @param playerName1 имя первого игрока
     * @param playerName2 имя второго игрока
     * @return 1, если победил первый; 2 — если второй; 0 — если ничья
     * @throws NotRegisteredException если хотя бы один из игроков не зарегистрирован
     */
    public int round(String playerName1, String playerName2) {
        Player player1 = findByName(playerName1);
        Player player2 = findByName(playerName2);

        if (player1 == null) {
            throw new NotRegisteredException("Игрок с именем " + playerName1 + " не зарегистрирован");
        }
        if (player2 == null) {
            throw new NotRegisteredException("Игрок с именем " + playerName2 + " не зарегистрирован");
        }

        if (player1.getStrength() > player2.getStrength()) {
            return 1;
        } else if (player1.getStrength() < player2.getStrength()) {
            return 2;
        } else {
            return 0;
        }
    }


     //Дополнительный вспомогательный метод: найти игрока по имени.
    private Player findByName(String name) {
        for (Player player : players) {
            if (player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }

    // Геттер для списка
    public List<Player> getPlayers() {
        return players;
    }
}