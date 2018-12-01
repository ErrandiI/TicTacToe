package TicTacToe;

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

    public String getPlayer() {
        return player;
    }

//    public String getFigure() {
//        return figure;
//    }
//
//    public String getFirstPlayer() {
//        return firstPlayer;
//    }

    public void setPlayer(String player) {
        this.player = player;
    }

//    public void setFigure(String figure) {
//        this.figure = figure;
//    }
//
//    public void setFirstPlayer(String firstPlayer) {
//        this.firstPlayer = firstPlayer;
//    }
}
