package src.view;

import java.util.Stack;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class WindowMenuInterface extends Application {

    @Override
    public void start(Stage principalStage) throws Exception {

        Scene menuScene = menuScreen();

        principalStage.setHeight(600);
        principalStage.setWidth(800);
        principalStage.setScene(menuScene);
        principalStage.show();

    }

    public Scene menuScreen() {
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

        btnStart.setPrefWidth(100);
        btnSettings.setPrefWidth(100);
        btnCollection.setPrefWidth(100);
        btnInstructions.setPrefWidth(100);
        btnCredits.setPrefWidth(100);
        btnExit.setPrefWidth(100);

        label.setFont(Font.font("Arial", 30));

        Scene scene = new Scene(bPane);

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
