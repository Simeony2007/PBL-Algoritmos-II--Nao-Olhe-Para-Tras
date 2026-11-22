package src.view.Menu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import java.io.File;
import src.controller.ControllerFX;
import src.controller.SettingsMenuJFX;
import src.controller.SlotsMenuJFX;
import src.view.AudioManager;
import src.view.Game.GameInterfaceJFX;
import src.view.Game.NotificationOverlayJFX;

public class MenuInterfaceJFX extends Application {
    private static final int BUTTON_WIDTH = 200;
    private final ControllerFX controllerFx = new ControllerFX();
    private final NotificationOverlayJFX slotsNotifications = new NotificationOverlayJFX();
    private final SlotsMenuJFX slotsController = new SlotsMenuJFX(controllerFx, slotsNotifications);
    private final SlotsJFX slotsView = new SlotsJFX(slotsNotifications);
    private final SettingsMenuJFX settings = new SettingsMenuJFX();
    private final AudioManager audio = new AudioManager();
    private final GameInterfaceJFX ginterface = new GameInterfaceJFX(settings, audio);

    private Stage principalStage;
    private Scene menuScene;

    public MenuInterfaceJFX() {
        controllerFx.setOnSaveDeleted(
                () -> slotsView.updateSaveInformation(slotsController.getAllSaves()));
    }

    @Override
    public void start(Stage principalStage) throws Exception {
        this.principalStage = principalStage;
        this.menuScene = new Scene(withMenuBackground(menuScreen()));
        String buttonStylesheet = new File("assets/styles/game.css").toURI().toString();
        menuScene.getStylesheets().add(buttonStylesheet);
        ginterface.gameScene().getStylesheets().add(buttonStylesheet);
        ginterface.setOnReturnToMainMenu(this::returnToMainMenu);
        int vol = settings.getVolume();
        audio.setVolume((float) vol / 100);
        audio.playBgSong();

        principalStage.setHeight(600);
        principalStage.setWidth(800);
        principalStage.setScene(menuScene);
        principalStage.show();

    }

    private Parent menuScreen() {
        BorderPane root = new BorderPane();
        VBox vBox = new VBox();
        StackPane stackPane = new StackPane();

        Button btnStart = new Button("Iniciar");
        Button btnSettings = new Button("Configurações");
        Button btnCollection = new Button("Coleção");
        Button btnInstructions = new Button("Instruções");
        Button btnCredits = new Button("Créditos");
        Button btnExit = new Button("Fechar jogo");

        Label title = new Label("Não Olhe Para Trás");
        title.setStyle("-fx-font-size: 50px; -fx-font-weight: bold;");

        root.setTop(stackPane);
        root.setCenter(vBox);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(btnStart, btnSettings, btnCollection, btnInstructions, btnCredits, btnExit);
        vBox.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(title);
        title.setPadding(new Insets(50));

        btnStart.setPrefWidth(BUTTON_WIDTH);
        btnSettings.setPrefWidth(BUTTON_WIDTH);
        btnCollection.setPrefWidth(BUTTON_WIDTH);
        btnInstructions.setPrefWidth(BUTTON_WIDTH);
        btnCredits.setPrefWidth(BUTTON_WIDTH);
        btnExit.setPrefWidth(BUTTON_WIDTH);

        btnStart.setOnAction(e -> showMenuPage(slotsScreen()));
        btnSettings.setOnAction(e -> showMenuPage(settingsScreen()));
        btnCollection.setOnAction(e -> showMenuPage(collectionScreen()));
        btnInstructions.setOnAction(e -> showMenuPage(instructionsScreen()));
        btnCredits.setOnAction(e -> showMenuPage(creditsScreen()));
        StackPane menuWithConfirmation = new StackPane();
        StackPane exitConfirmationOverlay = new StackPane();
        exitConfirmationOverlay.setBackground(new Background(new BackgroundFill(
                javafx.scene.paint.Color.rgb(0, 0, 0, 0.65),
                CornerRadii.EMPTY,
                Insets.EMPTY)));
        ConfirmationScreenJFX exitConfirmation = new ConfirmationScreenJFX(
                () -> {
                    exitConfirmationOverlay.setVisible(false);
                    principalStage.close();
                },
                () -> {
                    exitConfirmationOverlay.setVisible(false);
                    root.setDisable(false);
                },
                "Deseja fechar o jogo?");
        exitConfirmationOverlay.setVisible(false);
        exitConfirmationOverlay.setOnMouseClicked(event -> event.consume());
        exitConfirmationOverlay.getChildren().add(exitConfirmation.getView());
        menuWithConfirmation.getChildren().addAll(root, exitConfirmationOverlay);

        btnExit.setOnAction(e -> {
            root.setDisable(true);
            exitConfirmation.show();
            exitConfirmationOverlay.setVisible(true);
        });

        title.setFont(Font.font("Arial", 30));

        return menuWithConfirmation;
    }

    private Parent slotsScreen() {
        return slotsView.createView(
                slotsController.getAllSaves(),
                () -> startGameFromSlot(slotsView.getSlot()),
                () -> showMenuPage(menuScreen()),
                () -> onDelete(slotsView.getSlot()));
    }

    private Parent withMenuBackground(Parent content) {
        Image backgroundImage = new Image(new File("assets/sprites/background/MainMenu.jpg").toURI().toString());
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, false, true));

        StackPane page = new StackPane(content);
        page.getStyleClass().add("menu-page");
        page.setBackground(new Background(background));
        return page;
    }

    private void showMenuPage(Parent page) {
        menuScene.setRoot(withMenuBackground(page));
    }

    private void onDelete(int index) {
        slotsController.deleteSave(index);

    }

    private void startGameFromSlot(int slot) {
        slotsController.loadSave(slot);

        ginterface.setController(controllerFx);
        controllerFx.setView(ginterface);

        principalStage.setScene(ginterface.gameScene());
        controllerFx.startCurrentScene();
    }

    private void returnToMainMenu() {
        showMenuPage(menuScreen());
        principalStage.setScene(menuScene);
    }

    private Parent collectionScreen() {
        return CollectionsJFX.createView(() -> showMenuPage(menuScreen()));
    }

    private Parent settingsScreen() {
        return SettingsJFX.createView(settings,
                () -> showMenuPage(menuScreen()),
                () -> {
                    int vol = settings.getVolume();
                    audio.setVolume((float) vol / 100);
                });
    }

    private Parent creditsScreen() {
        BorderPane bPane = new BorderPane();
        VBox vBox = new VBox();
        StackPane stackPane = new StackPane();

        Button btnBack = new Button("Voltar");

        Label label = new Label("Créditos");
        Label labelCredits = new Label("Desenvolvido por:\n Pedro e Simeony\n\n"
                + "Universo e História inspirados na mitologia grega, em especial o conto de Orfeu e Eurídice\n\n" +
                "Artes:\n Retiradas do jogo Hades e Hades 2, além de geração por IA. \n\n"
                + "Músicas:\n Geradas por IA.");

        bPane.setTop(stackPane);
        bPane.setCenter(vBox);
        vBox.setSpacing(10);
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        vBox.getChildren().addAll(labelCredits, spacer, btnBack);
        vBox.setPadding(new Insets(0, 0, 30, 0));
        vBox.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(label);
        label.setPadding(new Insets(50));

        btnBack.setPrefWidth(BUTTON_WIDTH);

        btnBack.setOnAction(e -> showMenuPage(menuScreen()));

        label.setFont(Font.font("Arial", 30));

        return bPane;
    }

    private Parent instructionsScreen() {
        BorderPane bPane = new BorderPane();
        VBox vBox = new VBox();
        StackPane stackPane = new StackPane();

        Button btnBack = new Button("Voltar");

        Label label = new Label("Instruções");
        Label labelInstructions = new Label("Objetivo:\n- Atravessar o Submundo e recuperar sua Amada.\n\n" +
                "Como Jogar:\n- Selecione uma opção clicando no botão correspondente.\n\n" +
                "Mecânicas:\n- Orfeu possui 3 atributos, Amor, Raiva e Tristeza, eles aumentam ou diminuem conforme suas escolhas\n"
                +
                "- Orfeu também irá coletar óbolos (moedas) para atravessar o Submundo.\n" +
                "- Você também fará relacionamento com alguns personagens.\n" +
                "- Tanto o relacionameto, quanto os atributos terão consequências futuras.\n\n" +
                "Para salvar manualmente, digite \"salvar\" no momento onde uma escolha for exigida.");
        bPane.setTop(stackPane);
        bPane.setCenter(vBox);
        vBox.setSpacing(10);
        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        vBox.getChildren().addAll(labelInstructions, spacer, btnBack);
        vBox.setPadding(new Insets(0, 0, 30, 0));
        vBox.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(label);
        label.setPadding(new Insets(50));

        btnBack.setPrefWidth(BUTTON_WIDTH);

        btnBack.setOnAction(e -> showMenuPage(menuScreen()));

        label.setFont(Font.font("Arial", 30));

        return bPane;
    }

    public static void main(String[] args) {
        launch(args);
    }

}