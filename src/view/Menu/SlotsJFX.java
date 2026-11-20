package src.view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.IntConsumer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public final class SlotsJFX {

    private SlotsJFX() {
    }

    public static Parent createView(
            Map<Integer, String> savesInfo,
            IntConsumer onSelectSlot,
            Runnable onReturn) {

        BorderPane root = new BorderPane();
        VBox slotsBox = new VBox(10);
        slotsBox.setAlignment(Pos.CENTER);
        slotsBox.setPadding(new Insets(20));

        Label title = new Label("Slots");
        title.setFont(Font.font("Arial", 30));
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setTop(title);

        List<Map.Entry<Integer, String>> slots = new ArrayList<>(savesInfo.entrySet());
        slots.sort(Map.Entry.comparingByKey());

        for (Map.Entry<Integer, String> slot : slots) {
            Button button = new Button(slot.getValue());
            button.setMaxWidth(Double.MAX_VALUE);

            // Numero real(1–8), não o índice(0–7).
            button.setOnAction(event -> onSelectSlot.accept(slot.getKey()));
            slotsBox.getChildren().add(button);
        }

        root.setCenter(slotsBox);

        Button backButton = new Button("Voltar");
        backButton.setOnAction(event -> onReturn.run());
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(0, 0, 20, 0));
        root.setBottom(backButton);

        return root;
    }
}