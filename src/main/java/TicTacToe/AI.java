package TicTacToe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AI {

    public int calcBoardScore(boolean turnX, TicTacToe.Cell[][] board) {
        int score = 0;
        String s = board[0][0].getValue()+board[0][1].getValue()+board[0][2].getValue();
        if (!s.contains(turnX ? "0" : "X")) score+=10;
        s = board[1][0].getValue()+board[1][1].getValue()+board[1][2].getValue();
        if (!s.contains(turnX ? "0" : "X")) score+=10;
        s = board[2][0].getValue()+board[2][1].getValue()+board[2][2].getValue();
        if (!s.contains(turnX ? "0" : "X")) score+=10;

        s = board[0][0].getValue()+board[1][0].getValue()+board[2][0].getValue();
        if (!s.contains(turnX ? "0" : "X")) score+=10;
        s = board[0][1].getValue()+board[1][1].getValue()+board[2][1].getValue();
        if (!s.contains(turnX ? "0" : "X")) score+=10;
        s = board[0][2].getValue()+board[1][2].getValue()+board[2][2].getValue();
        if (!s.contains(turnX ? "0" : "X")) score+=10;

        s = board[0][0].getValue()+board[1][1].getValue()+board[2][2].getValue();
        if (!s.contains(turnX ? "0" : "X")) score+=10;
        s = board[0][1].getValue()+board[1][1].getValue()+board[2][0].getValue();
        if (!s.contains(turnX ? "0" : "X")) score+=10;

        return score;
    }

    public static void computerMove(boolean turnX, TicTacToe.Cell[][] board) {
        List<TicTacToe.Cell> emptyCells = new ArrayList<>();
        for (int k = 0; k < 3; k++) {
            for (int n = 0; n < 3; n++) {
                if (board[k][n].getValue().equals("")){
                    emptyCells.add(board[k][n]);
                }
            }
        }
        Random random = new Random();
        int rnd = random.nextInt(emptyCells.size());
        if (turnX) {
            emptyCells.get(rnd).drawX();
        } else {
            emptyCells.get(rnd).drawO();
        }
    }
}


