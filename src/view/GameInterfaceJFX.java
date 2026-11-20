package src.view;

import src.controller.ControllerFX;
import src.model.Observer;
import src.model.service.logic.Orfeu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.util.Duration;
import javafx.animation.SequentialTransition;

public class GameInterfaceJFX implements Observer {

    private Scene scene;
    private ControllerFX fxControl; // Acho que dá pra usar como Static - Simeony
    private Label nomePersonagem;
    private Label falaPersonagem;
    private HBox hBox;
    private VBox choicesVBox;
    private BorderPane bpane;

    @Override
    public void modelUpdated(Orfeu orfeu) {
        // TODO: Finalizar implementação do Observer - Simeony
    }

    public void setController(ControllerFX ctrl) {
        fxControl = ctrl;
    }

    public GameInterfaceJFX() {
        StackPane stackPane = new StackPane();
        VBox vBox = new VBox();
        bpane = new BorderPane();
        hBox = new HBox();
        this.choicesVBox = new VBox();

        choicesVBox.setVisible(false);
        choicesVBox.setId("PainelDeEscolhas");
        choicesVBox.setAlignment(Pos.CENTER);
        choicesVBox.setSpacing(12);

        nomePersonagem = new Label();
        falaPersonagem = new Label();

        nomePersonagem.setFont(new Font("Arial", 20));
        nomePersonagem.setTextFill(Color.WHITE);
        nomePersonagem.setAlignment(Pos.CENTER_LEFT);
        nomePersonagem.setId("NomeDoPersonagem");

        falaPersonagem.setFont(new Font("Arial", 20));
        falaPersonagem.setTextFill(Color.WHITE);
        falaPersonagem.setWrapText(true);
        falaPersonagem.setAlignment(Pos.TOP_LEFT);
        falaPersonagem.setId("FalaDoPersonagem");

        double alturaNome = 38;
        double alturaFala = 122;
        double larguraAbaNome = 240;

        nomePersonagem.setMinHeight(alturaNome);
        nomePersonagem.setPrefHeight(alturaNome);
        nomePersonagem.setMaxHeight(alturaNome);
        nomePersonagem.setPadding(new Insets(0, 15, 0, 15));

        falaPersonagem.setMinHeight(alturaFala);
        falaPersonagem.setPrefHeight(alturaFala);
        falaPersonagem.setMaxHeight(alturaFala);
        falaPersonagem.setMaxWidth(Double.MAX_VALUE);
        falaPersonagem.setPadding(new Insets(12, 20, 12, 20));

        Polygon poligonoCaixa = new Polygon();
        poligonoCaixa.setFill(Color.rgb(20, 20, 28, 0.88));
        poligonoCaixa.setStroke(Color.rgb(158, 131, 9));
        poligonoCaixa.setStrokeWidth(2.0);
        poligonoCaixa.setManaged(false);

        Runnable atualizarPoligono = () -> {
            double w = vBox.getWidth();
            double hTotal = alturaNome + alturaFala;
            String nomeAtual = nomePersonagem.getText();
            boolean temNome = nomeAtual != null && !nomeAtual.trim().isEmpty();

            if (temNome) {
                poligonoCaixa.getPoints().setAll(
                        0.0, 0.0,
                        larguraAbaNome, 0.0,
                        larguraAbaNome + 20, alturaNome,
                        w, alturaNome,
                        w, hTotal,
                        0.0, hTotal);
            } else {
                poligonoCaixa.getPoints().setAll(
                        0.0, alturaNome,
                        w, alturaNome,
                        w, hTotal,
                        0.0, hTotal);
            }
        };

        vBox.widthProperty().addListener((obs, oldVal, newVal) -> atualizarPoligono.run());
        nomePersonagem.textProperty().addListener((obs, oldVal, newVal) -> atualizarPoligono.run());

        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setMinHeight(alturaNome + alturaFala);
        vBox.setPrefHeight(alturaNome + alturaFala);
        vBox.setMaxHeight(alturaNome + alturaFala);
        vBox.setPadding(Insets.EMPTY);
        vBox.setSpacing(0);

        hBox.setAlignment(Pos.BOTTOM_CENTER);

        StackPane containerDialogo = new StackPane(poligonoCaixa, vBox);
        containerDialogo.setAlignment(Pos.TOP_LEFT);
        BorderPane.setMargin(containerDialogo, new Insets(0, 15, 15, 15));

        vBox.getChildren().addAll(nomePersonagem, falaPersonagem);

        stackPane.getChildren().addAll(hBox, choicesVBox);
        bpane.setCenter(stackPane);
        bpane.setBottom(containerDialogo);

        scene = new Scene(bpane);
    }

    public Scene gameScene() {
        return scene;
    }

    public void showDialogues(ArrayList<Map.Entry<String, String>> dialogos, int speed) {
        SequentialTransition sequencia = new SequentialTransition();

        for (Map.Entry<String, String> dialogue : dialogos) {
            String nome = dialogue.getKey();
            String fala = dialogue.getValue();

            ImageView spriteView = null;
            if (nome != null && !nome.isEmpty()) {
                File arquivoSprite = new File("assets/sprites/character/" + nome + ".png");
                if (arquivoSprite.exists()) {
                    try (InputStream is = new FileInputStream(arquivoSprite)) {
                        Image image = new Image(is);
                        spriteView = new ImageView(image);
                        spriteView.setPreserveRatio(true);
                        spriteView.setFitHeight(350); // Ajuste a altura do sprite conforme sua tela
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            final ImageView imagemFinal = spriteView;

            final int[] contadorLetras = { 0 };

            KeyFrame frame = new KeyFrame(Duration.millis(1/* TODO: Colocar velcidade compatível! */), evento -> {
                if (contadorLetras[0] == 0) {
                    nomePersonagem.setText(nome);
                    hBox.getChildren().clear();
                    if (imagemFinal != null) {
                        hBox.getChildren().add(imagemFinal);
                    }
                }
                contadorLetras[0]++;
                falaPersonagem.setText(fala.substring(0, contadorLetras[0]));
            });

            Timeline timelineFala = new Timeline(frame);
            timelineFala.setCycleCount(fala.length());

            sequencia.getChildren().addAll(timelineFala, new PauseTransition(Duration.seconds(0.5)));
        }
        sequencia.setOnFinished(e -> fxControl.endedDialogues());
        sequencia.play();
    }

    public void showEndGame() {
        // TODO
    }

    public void showChoicesOptions(ArrayList<String> escolhas) {
        ArrayList<Button> choicesList = new ArrayList<>();

        choicesVBox.getChildren().clear();

        for (String str : escolhas) {
            Button nButton = new Button(str);
            choicesList.add(nButton);
        }

        choicesVBox.getChildren().addAll(choicesList);
        choicesVBox.setVisible(true);

        for (Button cButton : choicesList) {
            cButton.setOnAction(e -> {
                choicesVBox.setVisible(false);
                fxControl.choicedOption(choicesList.indexOf(cButton));
            });
        }

    }

    public void setChapterBackground(String nomeArquivoFundo) {
        File arquivoFundo = new File("assets/sprites/background/" + nomeArquivoFundo);
        if (arquivoFundo.exists()) {
            // Converte o caminho do Windows (C:\...) para formato URI válido no CSS do
            // JavaFX (file:/C:/...)
            String uri = arquivoFundo.toURI().toString();
            bpane.setStyle(
                    "-fx-background-image: url('" + uri + "'); " +
                            "-fx-background-size: cover; " +
                            "-fx-background-position: center center; " +
                            "-fx-background-repeat: no-repeat;");
        }
    }

    public void showEndChapter() {
        // TODO
    }
}
