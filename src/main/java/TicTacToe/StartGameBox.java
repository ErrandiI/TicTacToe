package TicTacToe;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StartGameBox extends Stage {
    private final VBox layout;
    private final ObservableList<Node> components;
    private final ChoiceBox<String> playerCB;
    private final ChoiceBox<String> figureCB;
    private final ChoiceBox<String> firstPlayerCB;


    public StartGameBox(String title, String message) {
        initBox(title);

        layout = createWindowLayout();
        components = layout.getChildren();
        components.add(new Label(message));

        playerCB = createPlayerSelector();
        figureCB = createFigureSelector();
        firstPlayerCB = createFirstPlayerSelector();

        Button next = new Button("Next");
        next.setOnAction(this::doNext);
        next.setTranslateY(25);
        components.add(next);

        Scene scene = new Scene(layout);
        setScene(scene);
    }


    private void doNext(ActionEvent actionEvent) {
        GameSettings settings = GameSettings.getInstance();
        settings.setPlayer(getPlayer());
        settings.setFigure(getFigure());
        settings.setFirstPlayer(getFirstPlayer());
        System.out.println(settings.getPlayer());
        System.out.println(settings.getFigure());
        System.out.println(settings.getFirstPlayer());
        close();
        PlayerNameBox.display("Name settings", "Please set nicknames for Player 1 and Player 2");
    }

    private void initBox(String title) {
        initModality(Modality.APPLICATION_MODAL);
        setTitle(title);
        setMinWidth(350);
        setMaxWidth(350);
        setMinHeight(350);
        setMaxHeight(350);
    }

    VBox createWindowLayout() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10));
        layout.setAlignment(Pos.CENTER);

        return layout;
    }

    ChoiceBox<String> createPlayerSelector() {
        ChoiceBox<String> playerCB = new ChoiceBox<>();
        playerCB.getItems().addAll("Player", "Computer");

        VBox playerVB = new VBox();
        playerVB.getChildren().addAll(
                new Label("Will you play against other player or against computer?"),
                playerCB
        );

        components.add(playerVB);
        return playerCB;
    }

    ChoiceBox<String> createFigureSelector() {
        ChoiceBox<String> figureCB = new ChoiceBox<>();
        figureCB.getItems().addAll("X", "O");

        VBox figureVB = new VBox();
        figureVB.getChildren().addAll(
                new Label("Which figure will you select?"),
                figureCB
        );

        components.add(figureVB);
        return figureCB;
    }

    ChoiceBox<String> createFirstPlayerSelector() {
        ChoiceBox<String> firstPlayerCB = new ChoiceBox<>();
        firstPlayerCB.getItems().addAll("X", "O");

        VBox firstPlayerVB = new VBox();
        firstPlayerVB.getChildren().addAll(
                new Label("Who will start?"),
                firstPlayerCB
        );

        components.add(firstPlayerVB);
        return firstPlayerCB;
    }

    public String getPlayer() {
        return playerCB.getValue();
    }

    public String getFigure() {
        return figureCB.getValue();
    }

    public String getFirstPlayer() {
        return firstPlayerCB.getValue();
    }
}
