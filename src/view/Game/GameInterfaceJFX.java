package src.view.Game;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import src.controller.ControllerFX;
import src.controller.SettingsMenuJFX;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import src.view.Menu.ConfirmationScreenJFX;
import src.view.Menu.SettingsJFX;
import src.view.AudioManager;

public class GameInterfaceJFX {

    private final BorderPane root = new BorderPane();
    private final StackPane sceneRoot = new StackPane();
    private final StackPane returnConfirmationOverlay = new StackPane();
    private final Scene scene;
    private final StackPane miniMenuOverlay = new StackPane();
    private final DialogueJFX dialogue;
    private final ChoicesJFX choices;
    private final NotificationOverlayJFX notifications;
    private final StatusOverlayJFX statusOverlay;
    private final StackPane gameLayer;
    private final CharacterCreationJFX characterCreation;
    private final GameControlsJFX controls;
    private final ChapterTitleOverlayJFX chapterTitle;
    private InGameSaveMenuJFX inGameSaveMenu;
    private final EndGameJFX endGame;
    private final ConfirmationScreenJFX returnConfirmation;
    private SettingsMenuJFX settings;
    private AudioManager audio;
    private ControllerFX controller;
    private Runnable onReturnToMainMenu;
    private boolean gameScreenVisible = true;

    public GameInterfaceJFX(SettingsMenuJFX settingsMenu, AudioManager audioManager) {
        dialogue = new DialogueJFX();
        choices = new ChoicesJFX();
        notifications = new NotificationOverlayJFX();
        statusOverlay = new StatusOverlayJFX();
        characterCreation = new CharacterCreationJFX();
        settings = settingsMenu;
        audio = audioManager;
        endGame = new EndGameJFX(this::returnToMainMenu);
        controls = new GameControlsJFX(this::returnTOMainMenu, this::saveGame, this::openSettings);
        chapterTitle = new ChapterTitleOverlayJFX();
        returnConfirmation = new ConfirmationScreenJFX(
                this::returnToMainMenu,
                this::cancelReturnToMainMenu);
        gameLayer = new StackPane(
                dialogue.getCharacterLayer(),
                choices.getView(),
                notifications.getView(),
                statusOverlay.getView(),
                chapterTitle.getView());
        StackPane.setAlignment(notifications.getView(), Pos.TOP_LEFT);
        StackPane.setMargin(notifications.getView(), new Insets(15));
        StackPane.setAlignment(statusOverlay.getView(), Pos.CENTER_LEFT);
        StackPane.setMargin(statusOverlay.getView(), new Insets(10));

        root.setOnMouseClicked(event -> {
            if (gameScreenVisible && !choices.isVisible()) {
                nextDialogue();
            }
        });

        returnConfirmationOverlay.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        returnConfirmationOverlay.setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 0, 0, 0.65),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        returnConfirmationOverlay.getChildren().add(returnConfirmation.getView());
        returnConfirmationOverlay.setVisible(false);
        returnConfirmationOverlay.setOnMouseClicked(event -> event.consume());
        miniMenuOverlay.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        miniMenuOverlay.setBackground(new Background(new BackgroundFill(
                Color.rgb(0, 0, 0, 0.65),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        miniMenuOverlay.getChildren().add(controls.getMiniMenuView());
        miniMenuOverlay.setVisible(false);
        controls.getMiniMenuView().visibleProperty().addListener((observable, oldValue, visible) -> {
            miniMenuOverlay.setVisible(visible);
            root.setDisable(visible);
            if (visible) {
                dialogue.pause();
            } else {
                dialogue.resume();
            }
        });
        sceneRoot.getChildren().addAll(
                root,
                miniMenuOverlay,
                returnConfirmationOverlay,
                characterCreation.getView());

        showGameScreen();

        scene = new Scene(sceneRoot);
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

    public void openSettings() {
        controls.hideMiniMenu();
        dialogue.pause();
        root.setCenter(SettingsJFX.createView(
                settings,
                () -> {
                    showGameScreen();
                    dialogue.resume();
                },
                () -> {
                    updateSettings();
                    showGameScreen();
                    dialogue.resume();
                }));
    }

    private void updateSettings() {
        dialogue.setTextSpeed(settings.getTextSpeed());
        if (controller != null) {
            controller.updateTextSpeed(settings.getTextSpeed());
        }
        if (audio != null) {
            audio.setVolume((float) settings.getVolume() / 100);
        }
    }

    public void showDialogues(ArrayList<Map.Entry<String, String>> dialogues, int speed) {
        showGameScreen();
        choices.hide();
        choices.setDisabled(false);
        dialogue.showDialogues(dialogues, speed, this::onDialogueQueueFinished);
    }

    public void nextDialogue() {
        dialogue.advance();
    }

    private void onDialogueQueueFinished() {
        if (controller != null) {
            controller.endedDialogues();
        }
    }

    public void returnTOMainMenu() {
        if (returnConfirmationOverlay.isVisible()) {
            return;
        }
        dialogue.pause();
        root.setDisable(true);
        returnConfirmation.show();
        returnConfirmationOverlay.setVisible(true);
    }

    private void cancelReturnToMainMenu() {
        returnConfirmationOverlay.setVisible(false);
        root.setDisable(false);
        dialogue.resume();
    }

    public void showNotification(String message) {
        notifications.showNotification(message);
    }

    public void updateStatus(Orfeu orfeu, List<Npc> npcs) {
        statusOverlay.updateStatus(orfeu, npcs);
    }

    public void showChoicesOptions(ArrayList<String> choices) {
        this.choices.showChoicesOptions(choices, number -> {
            if (controller != null) {
                controller.choicedOption(number);
            }
        });
    }

    public void showCharacterCreationOptions(ArrayList<String> options) {
        root.setDisable(true);
        characterCreation.show(options, choiceIndex -> {
            root.setDisable(false);
            if (controller != null) {
                controller.choicedOption(choiceIndex);
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
        HBox controlsView = controls.getControlsView();
        controlsView.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        StackPane.setAlignment(controlsView, Pos.TOP_RIGHT);
        StackPane.setMargin(controlsView, new Insets(10));

        // 1. Camada de personagens fixada na base real da janela
        HBox characters = dialogue.getCharacterLayer();
        characters.setMaxHeight(Region.USE_PREF_SIZE);
        StackPane.setAlignment(characters, Pos.BOTTOM_CENTER);

        // 2. Caixa de diálogo sobreposta na base (na frente dos personagens)
        StackPane dialogueBox = dialogue.getDialogueBox();
        dialogueBox.setMaxHeight(Region.USE_PREF_SIZE);
        StackPane.setAlignment(dialogueBox, Pos.BOTTOM_CENTER);
        StackPane.setMargin(dialogueBox, new Insets(0, 15, 15, 15));

        // 3. Empilha tudo na ordem: personagens -> overlays do jogo -> caixa de diálogo
        // -> botões
        StackPane base = new StackPane();
        base.getChildren().addAll(characters, gameLayer, dialogueBox, controlsView);

        root.setCenter(base);
    }

    public void returnToMainMenu() {
        reset();
        onReturnToMainMenu.run();
    }

    public void saveGame() {
        if (inGameSaveMenu == null) {
            inGameSaveMenu = new InGameSaveMenuJFX(controller, notifications);
        }
        if (!gameLayer.getChildren().contains(inGameSaveMenu.getView())) {
            gameLayer.getChildren().add(inGameSaveMenu.getView());
        }
        inGameSaveMenu.show();
    }

    public void reset() {
        returnConfirmationOverlay.setVisible(false);
        characterCreation.hide();
        controls.hideMiniMenu();
        root.setDisable(false);
        dialogue.reset();
        choices.reset();
        notifications.reset();
        endGame.reset();
        chapterTitle.reset();
        if (inGameSaveMenu != null) {
            inGameSaveMenu.hide();
        }
        gameScreenVisible = false;
        root.setTop(null);
        root.setCenter(null);
        root.setBottom(null);
        root.setStyle("");
    }

    public boolean showStartNewChapter(String nomeDoCapitulo) {
        chapterTitle.show(nomeDoCapitulo);
        return true;
    }
}
