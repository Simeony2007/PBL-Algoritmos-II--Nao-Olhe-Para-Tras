package src.view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import src.view.Game.NotificationOverlayJFX;

public final class SlotsJFX {

    private SaveSlotManagerJFX saveManager;
    private StackPane saveMenuRoot;
    private VBox saveMenuSlots;
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
                },
                () -> {
                    slotsBox.setDisable(true);
                    backButton.setDisable(true);
                },
                () -> {
                    slotsBox.setDisable(false);
                    backButton.setDisable(false);
                });

        createVBoxWitshSaves(savesInfo, slotsBox, false);

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

    public StackPane simpleSaveMenuMounter(Map<Integer, String> savesInfo,
            IntConsumer onSave,
            IntConsumer onDelete,
            IntPredicate saveExists) {
        if (saveManager == null) {
            saveManager = new SaveSlotManagerJFX(
                    () -> {
                    },
                    () -> {
                        onDelete.accept(slotIndex);
                    },
                    () -> {
                    },
                    () -> {
                    },
                    () -> {
                        onSave.accept(slotIndex);
                    });
        }

        if (saveMenuRoot == null) {
            saveMenuSlots = new VBox(10);
            saveMenuSlots.setAlignment(Pos.CENTER);
            saveMenuSlots.setPadding(new Insets(20));
            saveMenuSlots.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
            saveMenuSlots.setBackground(new Background(new BackgroundFill(
                    Color.rgb(20, 20, 28, 0.95),
                    new CornerRadii(12),
                    Insets.EMPTY)));

            saveMenuRoot = new StackPane(saveMenuSlots, saveManager.getView());
            saveMenuRoot.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            saveMenuRoot.setBackground(new Background(new BackgroundFill(
                    Color.rgb(0, 0, 0, 0.65),
                    CornerRadii.EMPTY,
                    Insets.EMPTY)));
            saveMenuRoot.setOnMouseClicked(event -> {
            });

        }

        updateSimpleSaveInformation(savesInfo, saveExists);
        saveManager.getView().setVisible(false);
        saveMenuRoot.setVisible(false);
        return saveMenuRoot;
    }

    public void updateSimpleSaveInformation(Map<Integer, String> savesInfo, IntPredicate saveExists) {
        if (saveMenuSlots == null) {
            return;
        }

        saveMenuSlots.getChildren().clear();
        Label label = new Label("Selecione o slot:");
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial", 20));
        saveMenuSlots.getChildren().add(label);
        createVBoxWitshSaves(savesInfo, saveMenuSlots, true, saveExists);
        Button closeButton = new Button("Cancelar");
        closeButton.setOnAction(event -> {
            saveManager.getView().setVisible(false);
            saveMenuRoot.setVisible(false);
        });
        saveMenuSlots.getChildren().add(closeButton);
    }

    public void updateSaveInformation(Map<Integer, String> savesInfo) {
        VBox newVBox = (VBox) root.lookup("#SlotsBox");
        newVBox.getChildren().clear();
        createVBoxWitshSaves(savesInfo, newVBox, false);
    }

    private void createVBoxWitshSaves(Map<Integer, String> savesInfo, VBox vbox, boolean withoutAutoSave) {
        createVBoxWitshSaves(savesInfo, vbox, withoutAutoSave, slotNumber -> false);
    }

    private void createVBoxWitshSaves(
            Map<Integer, String> savesInfo,
            VBox vbox,
            boolean withoutAutoSave,
            IntPredicate saveExists) {
        List<Map.Entry<Integer, String>> slots = new ArrayList<>(savesInfo.entrySet());
        for (Map.Entry<Integer, String> slot : slots) {
            int slotNumber = slot.getKey();
            if (withoutAutoSave) {
                if (slotNumber < 4) {
                    Button button = new Button(slot.getValue());
                    button.setMaxWidth(Double.MAX_VALUE);
                    button.setOnAction(event -> {
                        setSlot(slotNumber);
                        saveManager.showSaveManagerForSave(slot.getValue(), saveExists.test(slotNumber));
                    });
                    vbox.getChildren().add(button);
                }
            } else {

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
}
