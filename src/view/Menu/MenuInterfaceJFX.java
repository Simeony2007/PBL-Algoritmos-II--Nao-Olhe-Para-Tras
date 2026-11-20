package src.view.Menu;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.Parent;
import src.controller.ControllerFX;
import src.controller.SlotsMenuJFX;
import src.view.GameInterfaceJFX;

public class MenuInterfaceJFX extends Application {
    private static final int BUTTON_WIDTH = 100;
    private ControllerFX controllerFx = new ControllerFX();
    private GameInterfaceJFX ginterface = new GameInterfaceJFX();
    private Stage principalStage;
    private Scene menuScene;

    @Override
    public void start(Stage principalStage) throws Exception {
        this.principalStage = principalStage;
        this.menuScene = new Scene(menuScreen());

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

        btnStart.setOnAction(e -> menuScene.setRoot(slotsScreen()));
        btnSettings.setOnAction(e -> menuScene.setRoot(settingsScreen()));
        btnCollection.setOnAction(e -> menuScene.setRoot(collectionScreen()));
        btnInstructions.setOnAction(e -> menuScene.setRoot(instructionsScreen()));
        btnCredits.setOnAction(e -> menuScene.setRoot(creditsScreen()));
        btnExit.setOnAction(e -> principalStage.close());

        title.setFont(Font.font("Arial", 30));

        return root;
    }

    private Parent slotsScreen() {
        return SlotsJFX.createView(
                controllerFx,
                () -> menuScene.setRoot(menuScreen()),
                () -> startGame());
    }

    private Parent collectionScreen() {
        return CollectionsJFX.createView(() -> menuScene.setRoot(menuScreen()));
    }

    private Parent settingsScreen() {
        return SettingsJFX.createView(() -> menuScene.setRoot(menuScreen()));
    }

    private void startGame() {
        principalStage.setScene(ginterface.gameScene());
        ginterface.setController(controllerFx);
        controllerFx.setView(ginterface);
        controllerFx.startCurrentScene();
    }

    private Parent creditsScreen() {
        BorderPane bPane = new BorderPane();
        VBox vBox = new VBox();
        StackPane stackPane = new StackPane();

        Button btnBack = new Button("Voltar");

        Label label = new Label("Créditos");
        Label labelCredits = new Label("Desenvolvido por:\n Pedro e Simeony\n\n"
                + "Universo e História inspirados na mitologia grega, em especial o conto de Orfeu e Eurídice\n\n" +
                "Artes:\n Retiradas do jogo Hades e Hades 2\n\n" + "Músicas:\n Retiradas do jogo Hades e Hades 2");

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

        btnBack.setOnAction(e -> menuScene.setRoot(menuScreen()));

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

        btnBack.setOnAction(e -> menuScene.setRoot(menuScreen()));

        label.setFont(Font.font("Arial", 30));

        return bPane;
    }

    public static void main(String[] args) {
        launch(args);
    }

}