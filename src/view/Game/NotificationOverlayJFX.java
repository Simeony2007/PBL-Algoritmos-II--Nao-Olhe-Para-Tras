package src.view.Game;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
import javafx.scene.text.Font;
import javafx.util.Duration;

public class NotificationOverlayJFX {

    private final VBox view = new VBox(8);

    public NotificationOverlayJFX() {
        view.setAlignment(Pos.TOP_LEFT);
        view.setMouseTransparent(true);
        view.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        view.setVisible(false);
    }

    public VBox getView() {
        return view;
    }

    public void showNotification(String message) {
        Label notification = new Label(message);
        notification.setFont(new Font("Arial", 16));
        notification.setTextFill(Color.WHITE);
        notification.setPadding(new Insets(8, 15, 8, 15));
        notification.setBackground(new Background(new BackgroundFill(
                Color.rgb(20, 20, 28, 0.90),
                new CornerRadii(8),
                Insets.EMPTY)));
        notification.setBorder(new Border(new BorderStroke(
                Color.rgb(158, 131, 9),
                BorderStrokeStyle.SOLID,
                new CornerRadii(8),
                new BorderWidths(1.5))));

        view.getChildren().add(notification);
        view.setVisible(true);

        PauseTransition timer = new PauseTransition(Duration.seconds(2.5));
        timer.setOnFinished(event -> {
            view.getChildren().remove(notification);
            if (view.getChildren().isEmpty()) {
                view.setVisible(false);
            }
        });
        timer.play();
    }
}
