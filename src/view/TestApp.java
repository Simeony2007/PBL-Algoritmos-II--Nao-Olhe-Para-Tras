package src.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class TestApp extends Application {

    @Override
    public void start(Stage inicialStage) throws Exception {
        // ==========================================
        // 1. CARREGAMENTO E CONFIGURAÇÃO DA IMAGEM
        // ==========================================
        File test = new File("test.jpg");
        InputStream is = new FileInputStream(test);

        Image image = new Image(is);
        ImageView iView = new ImageView(image);
        iView.setPreserveRatio(true);

        // BorderPane principal com a imagem centralizada
        BorderPane baseBorderPane = new BorderPane(iView);

        iView.fitWidthProperty().bind(baseBorderPane.widthProperty().subtract(150));
        iView.fitHeightProperty().bind(baseBorderPane.heightProperty().subtract(150));

        // ==========================================
        // 2. CRIAÇÃO DO PAINEL DO SUL (DIÁLOGO HUD)
        // ==========================================
        Pane painelSul = new Pane();

        // Define que a região Sul ocupará 25% da altura total da janela
        painelSul.prefHeightProperty().bind(baseBorderPane.heightProperty().multiply(0.25));

        // Polígono do fundo do balão de fala
        Polygon caixaDialogo = new Polygon();
        caixaDialogo.setFill(Color.web("#111111"));
        caixaDialogo.setStroke(Color.web("#FF0033"));
        caixaDialogo.strokeWidthProperty().bind(baseBorderPane.widthProperty().divide(150));

        // Recalcula o polígono considerando as dimensões do próprio painelSul
        Runnable atualizarCaixaDialogo = () -> {
            double w = painelSul.getWidth();
            double h = painelSul.getHeight();
            if (w == 0 || h == 0)
                return;

            double marginX = w * 0.05;
            double boxWidth = w - (2 * marginX);
            double boxHeight = h * 0.85;

            caixaDialogo.getPoints().setAll(new Double[] {
                    marginX, 10.0,
                    marginX + boxWidth, 0.0,
                    marginX + boxWidth - 25, boxHeight,
                    marginX + 15, boxHeight - 5
            });
        };

        painelSul.widthProperty().addListener((obs, o, n) -> atualizarCaixaDialogo.run());
        painelSul.heightProperty().addListener((obs, o, n) -> atualizarCaixaDialogo.run());

        // Label de texto do diálogo
        Label textLabel = new Label("Texto");
        textLabel.setTextFill(Color.WHITE);
        textLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 18));

        textLabel.setWrapText(true);

        // Posicionamento do Label relativo ao painelSul
        textLabel.prefWidthProperty().bind(painelSul.widthProperty().multiply(0.80));
        textLabel.layoutXProperty().bind(painelSul.widthProperty().multiply(0.09));
        textLabel.layoutYProperty().bind(painelSul.heightProperty().multiply(0.20));

        // Adiciona a caixa e o texto dentro do sub-painel
        painelSul.getChildren().add(caixaDialogo);
        painelSul.getChildren().add(textLabel);

        // VINCULA O SUB-PAINEL NA REGIÃO SUL DO BORDERPANE
        baseBorderPane.setBottom(painelSul);

        // ==========================================
        // 3. SVG FLUTUANTE (CANTO SUPERIOR DIREITO)
        // ==========================================
        SVGPath shapeUsandoSVG = new SVGPath();
        shapeUsandoSVG.setContent(
                "m64.75 58.48c0 0 76.07-6.59 83.95-6.59 2.39 0 5.18 0.31 7.91 0.74" +
                        "-2.78 8.14 12.14 2.6 12.14 2.6 0 0 0.92 1.96-1.5 3-2.42 1.04 2.58 1.66 2.58 1.66 " +
                        "0 0-1 4.63-3.75 4.51-2.75-0.13 0.25 2.37 0.25 2.37 0 0-4.5 0.38-4.62 1.5-0.13 1.13 " +
                        "1.75 3.73 1.75 3.73 0 0-53.03-0.77-61.53 0-8.51 0.77-21.01 1.15-27.02 1.77-6 0.63-13-0.37-13-0.37z");

        shapeUsandoSVG.setFill(Color.BLACK);
        shapeUsandoSVG.setStroke(Color.RED);
        shapeUsandoSVG.strokeWidthProperty().bind(baseBorderPane.widthProperty().divide(300));
        shapeUsandoSVG.layoutXProperty().bind(baseBorderPane.widthProperty().subtract(220));
        shapeUsandoSVG.layoutYProperty().bind(baseBorderPane.heightProperty().multiply(0.05));
        shapeUsandoSVG.scaleXProperty().bind(baseBorderPane.widthProperty().divide(350));
        shapeUsandoSVG.scaleYProperty().bind(baseBorderPane.widthProperty().divide(350));

        // Adiciona o SVG como uma camada livre sobre o BorderPane
        baseBorderPane.getChildren().add(shapeUsandoSVG);

        // ==========================================
        // 4. CENA E PALCO
        // ==========================================
        Scene inicialScene = new Scene(baseBorderPane, 600, 400);
        inicialStage.setScene(inicialScene);

        getInput(inicialScene, inicialStage);

        inicialStage.setTitle("JavaFX Persona 5 Dialogue - BorderPane South");
        inicialStage.show();
    }

    public void getInput(Scene scene, Stage stage) {
        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.F11) {
                stage.setFullScreen(!stage.isFullScreen());
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}