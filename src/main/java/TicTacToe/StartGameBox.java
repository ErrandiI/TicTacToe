package TicTacToe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StartGameBox {

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
        Label label2 = new Label();
        label2.setText("Will you play against other player or against computer?");
        Label label3 = new Label();
        label3.setText("Which figure will you select?");
        Label label4 = new Label();
        label4.setText("Who will start?");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
        ChoiceBox<String> choiceBox3 = new ChoiceBox<>();
        ///agains who
        choiceBox.getItems().add("Player");
        choiceBox.getItems().add("Computer");
        ///which figure will use P1
        choiceBox2.getItems().add("X");
        choiceBox2.getItems().add("0");
        ///who will start
        choiceBox3.getItems().add("X");
        choiceBox3.getItems().add("0");

        Button next = new Button("Next");
        next.setOnAction(e-> {
            getChoice(choiceBox);
            getChoice2(choiceBox2);
            getChoice3(choiceBox3);
            window.close();
            PlayerNameBox.display("Name settings", "Please set nicknames for Player 1 and Player 2");
        });
        next.setTranslateY(25);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets( 10));
        layout.getChildren().addAll(label, label2, choiceBox, label3, choiceBox2, label4, choiceBox3, next);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    public static void getChoice(ChoiceBox<String> choiceBox){
        final String player = choiceBox.getValue();
        System.out.println(player);
    }
    public static void getChoice2(ChoiceBox<String> choiceBox2){
        String whichFigureiIsP1 = choiceBox2.getValue();
        System.out.println(whichFigureiIsP1);
    }
    public static void getChoice3(ChoiceBox<String> choiceBox3){
        String whichFigureStart = choiceBox3.getValue();
        System.out.println(whichFigureStart);
    }

}


