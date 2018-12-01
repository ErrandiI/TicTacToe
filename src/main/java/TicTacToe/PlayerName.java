package TicTacToe;

public class PlayerName {
    private String name;

    public PlayerName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
