package src.view.Game;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

final class ChapterTitleOverlayJFX {

    private final StackPane view = new StackPane();
    private final VBox banner = new VBox(8);
    private final Label chapterNumber = new Label();
    private final Label chapterTitle = new Label();
    private PauseTransition timer;

    public ChapterTitleOverlayJFX() {
        view.setId("EndChapterOverlay");
        view.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        view.setBackground(Background.EMPTY);
        view.setOnMouseClicked(event -> {
            event.consume();
            hide();
        });

        banner.setId("EndChapterRoot");
        banner.setAlignment(Pos.CENTER);
        banner.setMaxWidth(Double.MAX_VALUE);
        banner.setMaxHeight(VBox.USE_PREF_SIZE);
        banner.setPadding(new Insets(20));
        banner.setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 0, 0, 0.65),
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        DropShadow textShadow = new DropShadow();
        textShadow.setColor(Color.BLACK);
        textShadow.setRadius(6.0);
        textShadow.setOffsetX(2.0);
        textShadow.setOffsetY(2.0);
        textShadow.setSpread(0.35);

        chapterNumber.setId("NumeroDoCapitulo");
        chapterNumber.setFont(new Font("Arial", 22));
        chapterNumber.setTextFill(Color.rgb(212, 175, 55));
        chapterNumber.setEffect(textShadow);

        chapterTitle.setId("TituloCapitulo");
        chapterTitle.setFont(new Font("Arial", 32));
        chapterTitle.setTextFill(Color.WHITE);
        chapterTitle.setEffect(textShadow);

        banner.getChildren().addAll(chapterNumber, chapterTitle);
        StackPane.setAlignment(banner, Pos.TOP_CENTER);
        view.getChildren().add(banner);
        view.setVisible(false);
    }

    public StackPane getView() {
        return view;
    }

    public void show(String title) {
        if (timer != null) {
            timer.stop();
        }

        chapterTitle.setText(title);
        view.setVisible(true);
        timer = new PauseTransition(Duration.seconds(3));
        timer.setOnFinished(event -> hide());
        timer.play();
    }

    public void reset() {
        if (timer != null) {
            timer.stop();
        }
        view.setVisible(false);
    }

    private void hide() {
        if (timer != null) {
            timer.stop();
        }
        view.setVisible(false);
    }
}
