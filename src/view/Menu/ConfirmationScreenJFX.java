package src.view.Menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

final class ConfirmationScreenJFX {

    private final VBox view = new VBox(20);

    ConfirmationScreenJFX(Runnable onConfirm, Runnable onCancel) {
        view.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        view.setPrefWidth(260);
        view.setPadding(new Insets(20));
        view.setAlignment(Pos.CENTER);
        view.setBackground(new Background(new BackgroundFill(
                Color.rgb(20, 20, 28, 0.96),
                new CornerRadii(12),
                Insets.EMPTY)));
        view.setBorder(new Border(new BorderStroke(
                Color.rgb(158, 131, 9),
                BorderStrokeStyle.SOLID,
                new CornerRadii(12),
                new BorderWidths(2))));

        Label question = new Label("Tem certeza?");
        question.setFont(new Font("Arial", 18));
        question.setTextFill(Color.WHITE);

        Button yesButton = new Button("Sim");
        Button noButton = new Button("Não");
        yesButton.setPrefWidth(90);
        noButton.setPrefWidth(90);

        HBox buttons = new HBox(15, yesButton, noButton);
        buttons.setAlignment(Pos.CENTER);
        view.getChildren().addAll(question, buttons);
        view.setVisible(false);

        noButton.setOnAction(event -> {
            hide();
            onCancel.run();
        });
        yesButton.setOnAction(event -> {
            hide();
            onConfirm.run();
        });
    }

    Parent getView() {
        return view;
    }

    void show() {
        view.setVisible(true);
    }

    private void hide() {
        view.setVisible(false);
    }
}
