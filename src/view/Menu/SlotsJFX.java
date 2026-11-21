package src.view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public final class SlotsJFX {

    private int slotIndex;

    public SlotsJFX() {
        slotIndex = 0;
    }

    public Parent createView(
            Map<Integer, String> savesInfo,
            Runnable onButtonClick,
            Runnable onReturn,
            Runnable onDelete) {

        BorderPane root = new BorderPane();
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

        SaveSlotManagerJFX saveManager = new SaveSlotManagerJFX(
                onButtonClick,
                onDelete,
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
                saveManager.showSaveManager(Integer.toString(slotNumber));
            });
            slotsBox.getChildren().add(button);
        }

        StackPane stackPane = new StackPane(slotsBox, saveManager.getView());
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

    public void updateSaveInformation() {
        VBox vbox = root.lookup("#SlotsBox");
    }

}
