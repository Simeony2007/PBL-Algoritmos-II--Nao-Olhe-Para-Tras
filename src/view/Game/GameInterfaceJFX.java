package src.view.Game;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import src.controller.ControllerFX;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class GameInterfaceJFX {

    private final BorderPane root = new BorderPane();
    private final Scene scene;
    private final DialogueJFX dialogue;
    private final ChoicesJFX choices;
    private final NotificationOverlayJFX notifications;
    private final StackPane gameLayer;
    private final Button menuButton;
    private final GameMenuJFX gameMenu;
    private final EndGameJFX endGame;
    private ControllerFX controller;
    private Runnable onReturnToMainMenu;
    private boolean gameScreenVisible = true;

    public GameInterfaceJFX() {
        dialogue = new DialogueJFX();
        choices = new ChoicesJFX();
        notifications = new NotificationOverlayJFX();
        gameMenu = new GameMenuJFX(this::returnToMainMenu, this::returnToMainMenu, this::returnToMainMenu,
                this::returnToMainMenu); // TODO: Fazer as funções para os botões do miniMenu
        endGame = new EndGameJFX(this::returnToMainMenu);

        gameLayer = new StackPane(
                dialogue.getCharacterLayer(),
                choices.getView(),
                notifications.getView(),
                gameMenu.getView());
        StackPane.setAlignment(notifications.getView(), Pos.TOP_LEFT);
        StackPane.setMargin(notifications.getView(), new Insets(15));

        menuButton = new Button("☰");
        BorderPane.setAlignment(menuButton, Pos.TOP_RIGHT);
        BorderPane.setMargin(menuButton, new Insets(15, 15, 0, 0));
        menuButton.setOnAction(event -> {
            event.consume();
            openedMenu();
        });

        root.setOnMouseClicked(event -> {
            if (gameScreenVisible && !gameMenu.isVisible() && !choices.isVisible()) {
                nextDialogue();
            }
        });

        showGameScreen();

        scene = new Scene(root);
    }

    public void setController(ControllerFX controller) {
        this.controller = controller;
    }

    public void setOnReturnToMainMenu(Runnable onReturnToMainMenu) {
        this.onReturnToMainMenu = onReturnToMainMenu;
    }

    public Scene gameScene() {
        return scene;
    }

    public void showDialogues(ArrayList<Map.Entry<String, String>> dialogues, int speed) {
        showGameScreen();
        choices.hide();
        choices.setDisabled(false);
        gameMenu.setVisible(false);
        dialogue.showDialogues(dialogues, this::onDialogueQueueFinished);
    }

    public void nextDialogue() {
        dialogue.advance();
    }

    private void onDialogueQueueFinished() {
        if (controller != null) {
            controller.endedDialogues();
        }
    }

    public void openedMenu() {
        boolean opening = !gameMenu.isVisible();
        gameMenu.setVisible(opening);
        choices.setDisabled(opening);
        if (opening) {
            dialogue.pause();
        } else {
            dialogue.resume();
        }
    }

    public void showNotification(String message) {
        notifications.showNotification(message);
    }

    public void showChoicesOptions(ArrayList<String> choices) {
        this.choices.showChoicesOptions(choices, number -> {
            if (controller != null) {
                controller.choicedOption(number);
            }
        });
    }

    public void setChapterBackground(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            return;
        }

        File background = new File("assets/sprites/background/" + filename);
        if (!background.isFile()) {
            return;
        }

        String uri = background.toURI().toString();
        root.setStyle(
                "-fx-background-image: url('" + uri + "'); " +
                        "-fx-background-size: cover; " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: no-repeat;");
    }

    public void showEndGame(String title) {
        gameScreenVisible = false;
        endGame.show(title);
        root.setTop(null);
        root.setCenter(endGame.getView());
        root.setBottom(null);
    }

    private void showGameScreen() {
        gameScreenVisible = true;
        root.setTop(menuButton);
        root.setCenter(gameLayer);
        root.setBottom(dialogue.getDialogueBox());
        BorderPane.setMargin(dialogue.getDialogueBox(), new Insets(0, 15, 15, 15));
    }

    public void returnToMainMenu() {
        if (onReturnToMainMenu == null) {
            throw new IllegalStateException("A tela principal não configurou a ação de retorno ao menu.");
        }
        reset();
        onReturnToMainMenu.run();
    }

    public void reset() {
        dialogue.reset();
        choices.reset();
        notifications.reset();
        gameMenu.setVisible(false);
        endGame.reset();
        gameScreenVisible = false;
        root.setTop(null);
        root.setCenter(null);
        root.setBottom(null);
        root.setStyle("");
    }

    public void showEndChapter() {
        // TODO
    }
}
