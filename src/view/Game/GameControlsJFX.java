package src.view.Game;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

final class GameControlsJFX {

    private final HBox view = new HBox();
    private final BorderPane miniMenu = new BorderPane();

    public GameControlsJFX(Runnable onReturnToMenu, Runnable onSave, Runnable onSettings, Runnable switchVisible) {
        Button menuButton = new Button("Menu");
        Button saveButton = new Button("Salvar");
        Button statusButton = new Button("Status");

        BorderPane mountedMiniMenu = miniMenuMounter(onReturnToMenu, onSettings);
        mountedMiniMenu.setVisible(false);
        menuButton.setOnAction(event -> {
            showMiniMenu();
        });
        saveButton.setOnAction(event -> onSave.run());

        statusButton.setOnAction(event -> switchVisible.run());

        view.setAlignment(Pos.TOP_RIGHT);
        view.setSpacing(5);
        view.getChildren().addAll(statusButton, saveButton, menuButton);
    }

    public HBox getControlsView() {
        return view;
    }

    public BorderPane getMiniMenuView() {
        return miniMenu;
    }

    public void hideMiniMenu() {
        miniMenu.setVisible(false);
    }

    private BorderPane miniMenuMounter(Runnable onReturnMenu, Runnable onSettings) {
        Label title = new Label("Menu");

        Button settButton = new Button("Configurações");
        settButton.setOnAction(event -> onSettings.run());

        Button returnButton = new Button("Retornar ao menu");
        returnButton.setOnAction(event -> onReturnMenu.run());

        Button closeButton = new Button("Fechar");
        closeButton.setOnAction(event -> miniMenu.setVisible(false));

        VBox content = new VBox(10, title, settButton, returnButton, closeButton);
        content.setAlignment(Pos.CENTER);
        content.setPadding(new javafx.geometry.Insets(15));
        content.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        miniMenu.setCenter(content);
        miniMenu.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        miniMenu.setStyle("-fx-background-color: rgba(20,20,28,0.95);");
        return miniMenu;
    }

    private void showMiniMenu() {
        miniMenu.setVisible(!miniMenu.isVisible());
    }
}
