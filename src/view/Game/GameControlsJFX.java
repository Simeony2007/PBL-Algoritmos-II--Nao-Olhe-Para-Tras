package src.view.Game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

final class GameControlsJFX {

    private final HBox view = new HBox();

    public GameControlsJFX(Runnable onReturnToMenu, Runnable onSave) {
        Button menuButton = new Button("Retornar ao menu");
        Button saveButton = new Button("Salvar");

        menuButton.setOnAction(event -> {
            event.consume();
            onReturnToMenu.run();
        });
        saveButton.setOnAction(event -> onSave.run());

        view.setAlignment(Pos.TOP_LEFT);
        view.getChildren().addAll(saveButton, menuButton);
    }

    public HBox getView() {
        return view;
    }
}
