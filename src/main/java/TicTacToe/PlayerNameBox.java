package TicTacToe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlayerNameBox {

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);
        window.setMaxWidth(350);
        window.setMinHeight(350);
        window.setMaxHeight(350);

        Label label = new Label();
        label.setText(message);

        final TextField player1 = new TextField();
        player1.setPromptText("Enter nickname for player 1");
        player1.getText();
        final TextField player2 = new TextField();
        player2.setPromptText("Enter nickname for player 2");
        player2.getText();

        Button startTheGame = new Button("Start the game");
        startTheGame.setOnAction(e-> window.close());
        startTheGame.setTranslateX(5);
        startTheGame.setTranslateY(85);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets( 10));
        layout.getChildren().addAll(label, player1, player2, startTheGame);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.show();
    }
}


