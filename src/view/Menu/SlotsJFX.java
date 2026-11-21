package src.view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import src.view.Game.NotificationOverlayJFX;

public final class SlotsJFX {

    private SaveSlotManagerJFX saveManager;
    private BorderPane root;
    private int slotIndex;
    private final NotificationOverlayJFX notifications;

    public SlotsJFX(NotificationOverlayJFX notifications) {
        slotIndex = 0;
        this.notifications = notifications;
    }

    public Parent createView(
            Map<Integer, String> savesInfo,
            Runnable onButtonClick,
            Runnable onReturn,
            Runnable onDelete) {

        root = new BorderPane();
        root.setId("Root");
        VBox slotsBox = new VBox(10);
        slotsBox.setId("SlotsBox");
        slotsBox.setAlignment(Pos.CENTER);
        slotsBox.setPadding(new Insets(20));

        Label title = new Label("Slots");
        title.setFont(Font.font("Arial", 30));
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setTop(title);

        Button backButton = new Button("Voltar");
        backButton.setOnAction(event -> onReturn.run());
        backButton.setId("BackButton");

        saveManager = new SaveSlotManagerJFX(
                onButtonClick,
                () -> {
                    onDelete.run();
                    saveManager.showSaveManager(slotIndex + " - Vazio");
                },
                () -> {
                    slotsBox.setDisable(true);
                    backButton.setDisable(true);
                },
                () -> {
                    slotsBox.setDisable(false);
                    backButton.setDisable(false);
                });

        List<Map.Entry<Integer, String>> slots = new ArrayList<>(savesInfo.entrySet());
        for (Map.Entry<Integer, String> slot : slots) {
            int slotNumber = slot.getKey();
            Button button = new Button(slot.getValue());
            button.setMaxWidth(Double.MAX_VALUE);
            button.setOnAction(event -> {
                setSlot(slotNumber);
                saveManager.showSaveManager(slot.getValue());
            });
            slotsBox.getChildren().add(button);
        }

        StackPane stackPane = new StackPane(slotsBox, saveManager.getView(), notifications.getView());
        StackPane.setAlignment(notifications.getView(), Pos.TOP_LEFT);
        StackPane.setMargin(notifications.getView(), new Insets(15));
        root.setCenter(stackPane);

        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(0, 0, 20, 0));
        root.setBottom(backButton);

        return root;
    }

    public void setSlot(int slotnumber) {
        slotIndex = slotnumber;
    }

    public int getSlot() {
        return slotIndex;
    }

    private void onDelete(Runnable onDeleteFunction) {
        onDeleteFunction.run();
        saveManager.showSaveManager("Vazio");
    }

    public void updateSaveInformation(Map<Integer, String> savesInfo) {
        VBox vbox = (VBox) root.lookup("#SlotsBox");
        List<Map.Entry<Integer, String>> slots = new ArrayList<>(savesInfo.entrySet());
        vbox.getChildren().clear();
        for (Map.Entry<Integer, String> slot : slots) {
            int slotNumber = slot.getKey();
            Button button = new Button(slot.getValue());
            button.setMaxWidth(Double.MAX_VALUE);
            button.setOnAction(event -> {
                setSlot(slotNumber);
                saveManager.showSaveManager(slot.getValue());
            });
            vbox.getChildren().add(button);
        }
    }

}
