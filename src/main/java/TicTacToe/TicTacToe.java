package TicTacToe;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe extends Application {

    private Label statusMsg = new Label("Click File -> Start a New Game");
    private Cell [][] board = new Cell[3][3];
    private boolean withComputer = true;
    private List<WinCondition> checkList = new ArrayList<>();
    private boolean playable = true;
    private boolean turnX = true;
    public boolean startable = false;
    Stage window;
    BorderPane layout;

    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;
        window.setTitle("Tic Tac Toe");

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        //File menu
        Menu fileMenu = new Menu("_File");
        Menu helpMenu = new Menu("_Help");
        Menu updateLogMenu = new Menu ("_Update log");

        //Menu File items
        MenuItem newGame = new MenuItem("Start a new game...");
        newGame.setOnAction(e -> startNewGame());
        fileMenu.getItems().add(newGame);
        fileMenu.getItems().add(new SeparatorMenuItem());
        MenuItem resetSettings = new MenuItem("Reset settings");
        resetSettings.setOnAction(e  -> {
            reset();
            startNewGame();
        });
        fileMenu.getItems().add(resetSettings);
        fileMenu.getItems().add(new SeparatorMenuItem());
        MenuItem closeGame = new MenuItem("Exit...");
        closeGame.setOnAction(e -> closeProgram());
        fileMenu.getItems().add(closeGame);

        //Menu Help items
        MenuItem howToPlay = new MenuItem("How to play...");
        howToPlay.setOnAction(e -> AlertBox2.display("How to play", "The board is made of a 3 x 3 grid of squares. Though traditionally, the first player goes with \"X\", but you can \ndecide whether you wants to go with \"X\"s or \"O\"s. These symbols will be placed on the table, in the attempt to \nhave three of them in a row. If you're going first, then the best move you can make is to move into the center. \nThis will maximize your chances of winning. The first player to draw three of his or her symbols in a row, \noptimal strategy, then there's a good chance that no one will win because you will have blocked all of each \nother's opportunities to create a row of three. "));
        helpMenu.getItems().add(howToPlay);
        helpMenu.getItems().add(new SeparatorMenuItem());
        MenuItem about = new MenuItem("About...");
        about.setOnAction(e -> AlertBox.display("About", "This is my first project in Java. It is pretty cool, isn't it?"));
        helpMenu.getItems().add(about);

        //Update Log menu
        MenuItem updateLog = new MenuItem("What's new...");
        updateLog.setOnAction(e -> UpdateLog.display("Latest application updates", " 14.11.2018 - Build 0.2 \n\n * Removed empty lines. \n * Added one boolean to application functionality, which does not allow to cheat in game. \n * Added an update log. \n\n12.11.2018 - Build 0.1 \n\n *  Game was created."));
        updateLogMenu.getItems().add(updateLog);


        //Main menu bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu, updateLogMenu);

        layout = new BorderPane();
        layout.setTop(menuBar);

        GridPane pane = new GridPane();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Cell();
                pane.add(board[i][j], j, i);
            }
        }

        for(int i = 0; i <3; i++) {
            checkList.add(new WinCondition(board[i][0], board[i][1], board[i][2]));
        }

        for(int j = 0; j <3; j++) {
            checkList.add(new WinCondition(board[0][j], board[1][j], board[2][j]));
        }

        checkList.add(new WinCondition(board[0][0], board[1][1], board[2][2]));
        checkList.add(new WinCondition(board[0][2], board[1][1], board[2][0]));

        layout.setCenter(pane);
        layout.setBottom(statusMsg);

        Scene scene = new Scene(layout, 616,685);

        window.setScene(scene);
        window.setMinWidth(616);
        window.setMaxWidth(616);
        window.setMinHeight(685);
        window.setMaxHeight(685);
        window.show();
    }

    public class Cell extends StackPane {
        Text text = new Text();

        public Cell() {

            Rectangle border = new Rectangle(200, 200);
            border.setFill(null);
            border.setStroke(Color.BLACK);
            text.setFont(Font.font(96));
            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(e -> {
                if (!startable) {
                    startNewGame();
                } else {
                    if (!playable)
                        return;

                    if (withComputer && !turnX) {
                        AI.computerMove(turnX,board);
                        turnX=!turnX;
                        gameStatus();
                        if (!playable) {
                            statusMsg.setText("O won!");
                        } else if (isBoardFull()) {
                            statusMsg.setText("It's a draw!");
                        } else {
                            statusMsg.setText("Player X turn.");
                        }
                    } else {
                        statusMsg.setText("This move is forbidden");
                        if (e.getButton() == MouseButton.PRIMARY) {
                            if (!turnX)
                                return;
                            if (checkValue())
                                return;
                            drawX();
                            turnX = false;
                            gameStatus();

                            if (!playable) {
                                statusMsg.setText("X won!");
                            } else if (isBoardFull()) {
                                statusMsg.setText("It's a draw!");
                            } else {
                                statusMsg.setText("Player O turn.");
                            }

                        } else if (e.getButton() == MouseButton.SECONDARY) {
                            if (turnX)
                                return;
                            if (checkValue())
                                return;
                            drawO();
                            turnX = true;
                            gameStatus();

                            if (!playable) {
                                statusMsg.setText("0 won!");
                            } else if (isBoardFull()) {
                                statusMsg.setText("It's a draw!");
                            } else {
                                statusMsg.setText("Player X turn.");
                            }
                        }
                    }
                }
            });
        }

        public void drawX() {
            text.setText("X");
        }

        public void drawO() {
            text.setText("O");
        }

        public String getValue(){
            return text.getText();
        }

        public void removeValue() {
            text.setText("");
        }

        private boolean checkValue() {
            if (!text.getText().equals("")) {
                return true;
            }
            return false;
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j].getValue().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void gameStatus() {
        for (WinCondition winCondition : checkList){
            if (winCondition.isGameFinished()) {
                playable = false;
                break;
            }
        }
    }

    private class WinCondition {

        private Cell[] cells;

        public WinCondition(Cell... cells) {
            this.cells = cells;
        }

        public boolean isGameFinished() {
            if (cells[0].getValue().isEmpty())
                return false;

            return  cells[0].getValue().equals(cells[1].getValue())
                    && cells[0].getValue().equals(cells[2].getValue());
        }
    }
    private void startNewGame() {
        if (!startable)
            StartGameBox.display("Start settings", "Before you will start you need to select a few settings");
        startable = true;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j].removeValue();
                statusMsg.setText("Player X turn");
                turnX = true;
                playable = true;
            }
        }
    }

    private void reset() {
        startable = false;
    }
    public static void main(String[] args) {
        launch(args);
    }
    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Confirmation tab", "Do you really want to close the game?");
        if (answer)
            window.close();
    }
//    private void playerReader() {
//        StartGameBox.getChoice();
//    }
}