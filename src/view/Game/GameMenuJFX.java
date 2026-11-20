package src.view.Game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameMenuJFX {

    private final VBox view = new VBox();

    public GameMenuJFX(Runnable onReturnToMainMenu) {
        Button statusButton = new Button("Status");
        Button settingsButton = new Button("Configurações");
        Button saveButton = new Button("Salvar");
        Button returnButton = new Button("Retornar ao menu");
        returnButton.setOnAction(event -> onReturnToMainMenu.run());

        double buttonWidth = 200;
        for (Button button : new Button[] {
                statusButton, settingsButton, saveButton, returnButton
        }) {
            button.setPrefWidth(buttonWidth);
            button.setMaxWidth(Double.MAX_VALUE);
        }

        view.getChildren().addAll(statusButton, settingsButton, saveButton, returnButton);
        view.setSpacing(20);
        view.setAlignment(Pos.CENTER);
        view.setPadding(new Insets(30));
        view.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        view.setBackground(new Background(new BackgroundFill(
                Color.rgb(20, 20, 28, 0.92),
                new CornerRadii(12),
                Insets.EMPTY)));
        view.setBorder(new Border(new BorderStroke(
                Color.rgb(158, 131, 9),
                BorderStrokeStyle.SOLID,
                new CornerRadii(12),
                new BorderWidths(2))));
        view.setVisible(false);
    }

    public VBox getView() {
        return view;
    }

    public boolean isVisible() {
        return view.isVisible();
    }

    public void setVisible(boolean visible) {
        view.setVisible(visible);
    }
}
