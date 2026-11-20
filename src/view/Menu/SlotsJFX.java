package src.view.Menu;

import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

public class SlotsJFX {
    public static Parent createView(Runnable onReturnAction) {
            return new VBox();
    }


    private Parent slotsScreen(ArrayList<String> textOfButtons) {
        BorderPane bPane = new BorderPane();
        VBox vBox = new VBox();
        StackPane stackPane = new StackPane();
        bPane.setLeft(vBox);

        Label title = new Label("Slots");
        title.setFont(Font.font("Arial", 30));
        bPane.setTop(stackPane);
        stackPane.getChildren().add(title);
        title.setPadding(new Insets(50));

        ArrayList<Button> buttonsList = new ArrayList<>();

        for (String str : textOfButtons) {
            Button nSlot = new Button(str);
            // nSlot.setMaxWidth(BUTTON_WIDTH);
            //nSlot.setMinWidth(BUTTON_WIDTH);
            buttonsList.add(nSlot);
        }
        vBox.getChildren().addAll(buttonsList);
        vBox.setPadding(new Insets(0, 0, 0, 30));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        for (Button btn : buttonsList) {
            //btn.setOnAction(e -> controllerFx.loader(buttonsList.indexOf(btn))); // DONE(aparentemente) - Simeony
        }
        return bPane;
    }
}
