package TicTacToe;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameSettings {
    private GameSettings() {}

    private static class LazyHolder {
        static final GameSettings INSTANCE = new GameSettings();
    }

    public static GameSettings getInstance() {
        return LazyHolder.INSTANCE;
    }

    private String player;
    private String figure;
    private String firstPlayer;
}
