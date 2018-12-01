package TicTacToe;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UpdateLog {

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(550);
        window.setMaxWidth(550);
        window.setMinHeight(300);
        window.setMaxHeight(300);

        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Close the window");
        closeButton.setOnAction(e-> window.close());


        VBox layout = new VBox(20);
        layout.setPadding(new Insets( 10));
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.BOTTOM_CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
