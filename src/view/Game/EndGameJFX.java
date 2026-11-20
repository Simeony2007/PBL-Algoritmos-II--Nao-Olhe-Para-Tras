package src.view.Game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class EndGameJFX {

    private final BorderPane view = new BorderPane();
    private final Label endingName = new Label();

    public EndGameJFX(Runnable onReturnToMainMenu) {
        Label title = new Label("Fim de Jogo");
        Button returnButton = new Button("Retornar ao menu");

        title.setFont(new Font("Arial", 40));
        endingName.setFont(new Font("Arial", 20));

        VBox content = new VBox(title, endingName);
        content.setAlignment(Pos.CENTER);
        view.setPadding(new Insets(0, 0, 50, 0));
        view.setCenter(content);
        view.setBottom(returnButton);
        BorderPane.setAlignment(returnButton, Pos.BOTTOM_CENTER);

        returnButton.setOnAction(event -> onReturnToMainMenu.run());
    }

    public BorderPane getView() {
        return view;
    }

    public void show(String title) {
        endingName.setText(title);
    }

    public void reset() {
        endingName.setText("");
    }
}
