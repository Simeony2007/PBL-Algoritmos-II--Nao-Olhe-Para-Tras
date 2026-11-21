package src.view.Game;

import java.util.ArrayList;
import java.util.Map;
import java.util.function.Consumer;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class DialogueJFX {

    private static final double NAME_HEIGHT = 38;
    private static final double DIALOGUE_HEIGHT = 122;
    private static final double NAME_TAB_WIDTH = 240;
    private static final double DEFAULT_TYPEWRITER_INTERVAL_MILLIS = 15;

    private final Label characterName = new Label();
    private final Label dialogueText = new Label();
    private final StackPane dialogueBox;
    private ArrayList<Map.Entry<String, String>> dialogues;
    private Timeline typewriter;
    private Runnable onFinished = () -> {
    };
    private int dialogueIndex;
    private String currentText = "";
    private boolean completionSent;
    private double typewriterIntervalMillis = DEFAULT_TYPEWRITER_INTERVAL_MILLIS;
    private Consumer<String> onSpeakerChanged = ignored -> {
    };

    public DialogueJFX() {
        characterName.setFont(new Font("Arial", 20));
        characterName.setTextFill(Color.WHITE);
        characterName.setAlignment(Pos.CENTER_LEFT);
        characterName.setId("NomeDoPersonagem");
        characterName.setMinHeight(NAME_HEIGHT);
        characterName.setPrefHeight(NAME_HEIGHT);
        characterName.setMaxHeight(NAME_HEIGHT);
        characterName.setPadding(new Insets(0, 15, 0, 15));

        dialogueText.setFont(new Font("Arial", 20));
        dialogueText.setTextFill(Color.WHITE);
        dialogueText.setAlignment(Pos.TOP_LEFT);
        dialogueText.setWrapText(true);
        dialogueText.setId("FalaDoPersonagem");
        dialogueText.setMinHeight(DIALOGUE_HEIGHT);
        dialogueText.setPrefHeight(DIALOGUE_HEIGHT);
        dialogueText.setMaxHeight(DIALOGUE_HEIGHT);
        dialogueText.setMaxWidth(Double.MAX_VALUE);
        dialogueText.setPadding(new Insets(12, 20, 12, 20));

        VBox content = new VBox(characterName, dialogueText);
        content.setAlignment(Pos.TOP_LEFT);
        content.setMinHeight(NAME_HEIGHT + DIALOGUE_HEIGHT);
        content.setPrefHeight(NAME_HEIGHT + DIALOGUE_HEIGHT);
        content.setMaxHeight(NAME_HEIGHT + DIALOGUE_HEIGHT);
        content.setMaxWidth(Double.MAX_VALUE);
        content.setPadding(Insets.EMPTY);
        content.setSpacing(0);

        Polygon background = new Polygon();
        background.setFill(Color.rgb(20, 20, 28, 0.88));
        background.setStroke(Color.rgb(158, 131, 9));
        background.setStrokeWidth(2.0);
        background.setManaged(false);

        Runnable updateBackground = () -> updateBackground(background, content.getWidth());
        content.widthProperty().addListener((observable, oldValue, newValue) -> updateBackground.run());
        characterName.textProperty().addListener((observable, oldValue, newValue) -> updateBackground.run());

        dialogueBox = new StackPane(background, content);
        dialogueBox.setAlignment(Pos.TOP_LEFT);
        dialogueBox.setMaxWidth(Double.MAX_VALUE);
        double dialogueBoxHeight = NAME_HEIGHT + DIALOGUE_HEIGHT;
        dialogueBox.setMinHeight(dialogueBoxHeight);
        dialogueBox.setPrefHeight(dialogueBoxHeight);
        dialogueBox.setMaxHeight(dialogueBoxHeight);
    }

    public void setOnSpeakerChanged(Consumer<String> onSpeakerChanged) {
        this.onSpeakerChanged = onSpeakerChanged;
    }

    public StackPane getDialogueBox() {
        return dialogueBox;
    }

    public void showDialogues(
            ArrayList<Map.Entry<String, String>> dialogues,
            Runnable onFinished) {
        stopTypewriter();
        this.dialogues = dialogues;
        this.onFinished = onFinished;
        dialogueIndex = 0;
        completionSent = false;
        advance();
    }

    public void setTextSpeed(int textSpeed) {
        switch (textSpeed) {
            case 2500:
                typewriterIntervalMillis = 60;
                break;
            case 1000:
                typewriterIntervalMillis = 35;
                break;
            case 100:
                typewriterIntervalMillis = DEFAULT_TYPEWRITER_INTERVAL_MILLIS;
                break;
            case 0:
                typewriterIntervalMillis = 0;
                break;
            default:
                typewriterIntervalMillis = DEFAULT_TYPEWRITER_INTERVAL_MILLIS;
                break;
        }
    }

    public void reset() {
        stopTypewriter();
        dialogues = null;
        onFinished = () -> {
        };
        dialogueIndex = 0;
        currentText = "";
        completionSent = false;
        characterName.setText("");
        dialogueText.setText("");
        onSpeakerChanged.accept(null);
    }

    public void advance() {
        if (dialogues == null) {
            return;
        }

        if (typewriter != null && typewriter.getStatus() == Animation.Status.RUNNING) {
            typewriter.stop();
            dialogueText.setText(currentText);
            return;
        }

        if (dialogueIndex >= dialogues.size()) {
            if (!completionSent) {
                completionSent = true;
                onSpeakerChanged.accept(null);
                onFinished.run();
            }
            return;
        }

        Map.Entry<String, String> dialogue = dialogues.get(dialogueIndex++);
        String name = dialogue.getKey();
        currentText = dialogue.getValue();

        characterName.setText(name);
        onSpeakerChanged.accept(name);

        if (typewriterIntervalMillis == 0) {
            dialogueText.setText(currentText);
            return;
        }

        dialogueText.setText("");
        int[] visibleCharacters = { 0 };
        typewriter = new Timeline(new KeyFrame(
                Duration.millis(typewriterIntervalMillis),
                event -> {
                    if (visibleCharacters[0] < currentText.length()) {
                        dialogueText.setText(currentText.substring(0, ++visibleCharacters[0]));
                    }
                }));
        typewriter.setCycleCount(Math.max(1, currentText.length()));
        typewriter.play();
    }

    public void pause() {
        if (typewriter != null && typewriter.getStatus() == Animation.Status.RUNNING) {
            typewriter.pause();
        }
    }

    public void resume() {
        if (typewriter != null && typewriter.getStatus() == Animation.Status.PAUSED) {
            typewriter.play();
        }
    }

    private void stopTypewriter() {
        if (typewriter != null) {
            typewriter.stop();
            typewriter = null;
        }
    }

    private void updateBackground(Polygon background, double width) {
        double totalHeight = NAME_HEIGHT + DIALOGUE_HEIGHT;
        boolean hasName = !characterName.getText().trim().isEmpty();

        if (hasName) {
            background.getPoints().setAll(
                    0.0, 0.0,
                    NAME_TAB_WIDTH, 0.0,
                    NAME_TAB_WIDTH + 20, NAME_HEIGHT,
                    width, NAME_HEIGHT,
                    width, totalHeight,
                    0.0, totalHeight);
        } else {
            background.getPoints().setAll(
                    0.0, NAME_HEIGHT,
                    width, NAME_HEIGHT,
                    width, totalHeight,
                    0.0, totalHeight);
        }
    }
}
