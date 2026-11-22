package src.view.Game;

import java.util.ArrayList;
import java.util.function.IntConsumer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

final class CharacterCreationJFX {
    private static final String TEXT_STYLE =
            "-fx-text-fill: white;" +
                    "-fx-effect: dropshadow(gaussian, black, 4, 0.0, 1, 1);";

    private final StackPane view = new StackPane();

    CharacterCreationJFX() {
        view.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        view.setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 0, 0, 0.78),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        view.setOnMouseClicked(event -> event.consume());
        view.setVisible(false);
    }

    StackPane getView() {
        return view;
    }

    void show(ArrayList<String> options, IntConsumer onChoice) {
        Label title = new Label("Criação do personagem");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;" + TEXT_STYLE);

        Label description = new Label("Escolha a motivação inicial de Orfeu.");
        description.setStyle("-fx-font-size: 16px;" + TEXT_STYLE);

        VBox optionList = new VBox(12);
        optionList.setAlignment(Pos.CENTER);
        for (int index = 0; index < options.size(); index++) {
            int choiceIndex = index;
            Button option = new Button(options.get(index));
            option.setWrapText(true);
            option.setAlignment(Pos.CENTER);
            option.setPrefWidth(700);
            option.setMinHeight(Region.USE_PREF_SIZE);
            option.setStyle(
                    "-fx-font-size: 15px;" +
                            "-fx-padding: 12px;" +
                            "-fx-background-color: rgba(32,32,40,0.98);" +
                            "-fx-border-color: #9E8309;" +
                            "-fx-border-width: 1;" +
                            TEXT_STYLE);
            option.setOnAction(event -> {
                event.consume();
                view.setVisible(false);
                onChoice.accept(choiceIndex);
            });
            optionList.getChildren().add(option);
        }

        VBox dialog = new VBox(18, title, description, optionList);
        dialog.setAlignment(Pos.CENTER);
        dialog.setPadding(new Insets(28));
        dialog.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        dialog.setPrefWidth(760);
        dialog.setBackground(new Background(new BackgroundFill(
                Color.rgb(20, 20, 28, 0.98),
                new CornerRadii(12),
                Insets.EMPTY)));
        dialog.setBorder(new Border(new BorderStroke(
                Color.rgb(158, 131, 9),
                BorderStrokeStyle.SOLID,
                new CornerRadii(12),
                new BorderWidths(2))));

        view.getChildren().setAll(dialog);
        view.setVisible(true);
    }

    void hide() {
        view.setVisible(false);
    }
}
