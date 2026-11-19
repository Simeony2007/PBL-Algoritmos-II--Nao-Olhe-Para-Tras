package src.view;

import java.util.ArrayList;
import java.util.Stack;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.*;
import javafx.stage.Stage;
import src.model.repository.SaveManager;
import javafx.scene.layout.*;

public class MenuInterfaceJFX extends Application {
    int buttonWidth = 100;
    Stage principalStage;
    Scene menuScene;

    @Override
    public void start(Stage principalStage) throws Exception {

        this.principalStage = principalStage;
        this.menuScene = menuScreen(principalStage);

        principalStage.setHeight(600);
        principalStage.setWidth(800);
        principalStage.setScene(menuScene);
        principalStage.show();

    }

    private Scene menuScreen(Stage principalStage) {
        BorderPane bPane = new BorderPane();
        VBox vBox = new VBox();
        StackPane stackPane = new StackPane();

        Button btnStart = new Button("Iniciar");
        Button btnSettings = new Button("Configurações");
        Button btnCollection = new Button("Coleção");
        Button btnInstructions = new Button("Instruções");
        Button btnCredits = new Button("Créditos");
        Button btnExit = new Button("Fechar jogo");

        Label label = new Label("Título");

        bPane.setTop(stackPane);
        bPane.setCenter(vBox);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(btnStart, btnSettings, btnCollection, btnInstructions, btnCredits, btnExit);
        vBox.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(label);
        label.setPadding(new Insets(50));

        btnStart.setPrefWidth(buttonWidth);
        btnSettings.setPrefWidth(buttonWidth);
        btnCollection.setPrefWidth(buttonWidth);
        btnInstructions.setPrefWidth(buttonWidth);
        btnCredits.setPrefWidth(buttonWidth);
        btnExit.setPrefWidth(buttonWidth);

        // TESTE!
        ArrayList<String> textos = new ArrayList<>();
        textos.add("teste");
        textos.add("teste1");
        textos.add("teste2");
        textos.add("teste2");
        textos.add("teste2");
        textos.add("teste2");
        textos.add("teste2");
        textos.add("teste2");

        // Apenas para teste atualmente! Isso deve vir da real lista de Metadados dos
        // botões. - Simeony
        btnStart.setOnAction(e -> principalStage.setScene(slotsScreen(textos)));
        /*
         * 
         * btnSettings.setOnAction(e -> principalStage.setScene(menuSlots));
         * btnCollection.setOnAction(e -> principalStage.setScene(menuSlots));
         */
        btnInstructions.setOnAction(e -> principalStage.setScene(instructionsScreen()));
        btnCredits.setOnAction(e -> principalStage.setScene(creditsScreen()));
        btnExit.setOnAction(e -> principalStage.close());

        label.setFont(Font.font("Arial", 30));

        Scene scene = new Scene(bPane);

        return scene;
    }

    private Scene slotsScreen(ArrayList<String> textOfButtons) {
        BorderPane bPane = new BorderPane();
        VBox vBox = new VBox();
        StackPane stackPane = new StackPane();
        bPane.setLeft(vBox);

        Label title = new Label("Slots");
        title.setFont(Font.font("Arial", 30));
        bPane.setTop(stackPane);
        stackPane.getChildren().add(title);
        title.setPadding(new Insets(50));

        ArrayList<Button> buttonsList = new ArrayList<>();

        for (String str : textOfButtons) {
            Button nSlot = new Button(str);
            // nSlot.setMaxWidth(buttonWidth);
            nSlot.setMinWidth(buttonWidth);
            buttonsList.add(nSlot);
        }
        vBox.getChildren().addAll(buttonsList);
        vBox.setPadding(new Insets(0, 0, 0, 30));
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        for (Button btn : buttonsList) {
            btn.setOnAction(e -> controllerFx.loader(buttonsList.indexOf(btn))); // TODO: Método de carregar save no
                                                                                 // ControllerFX - Simeony
        }
        Scene scene = new Scene(bPane);
        return scene;
    }

    private Scene creditsScreen() {
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

        btnBack.setPrefWidth(buttonWidth);

        btnBack.setOnAction(e -> principalStage.setScene(menuScene));

        label.setFont(Font.font("Arial", 30));

        Scene scene = new Scene(bPane);

        return scene;
    }

    private Scene instructionsScreen() {
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

        btnBack.setPrefWidth(buttonWidth);

        btnBack.setOnAction(e -> principalStage.setScene(menuScene));

        label.setFont(Font.font("Arial", 30));

        Scene scene = new Scene(bPane);

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
