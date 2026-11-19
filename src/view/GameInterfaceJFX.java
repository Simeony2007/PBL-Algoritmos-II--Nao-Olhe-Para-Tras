package src.view;

import src.controller.ControllerFX;
import src.model.Observer;
import src.model.service.logic.Orfeu;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
    private VBox choicesVBox;

    @Override
    public void modelUpdated(Orfeu orfeu) {

    }

    public void setController(ControllerFX ctrl) {
        fxControl = ctrl;
    }

    public GameInterfaceJFX() {
        BorderPane bpane = new BorderPane();
        // StackPane stackPane = new StackPane();
        HBox hBox = new HBox();
        VBox vBox = new VBox();
        this.choicesVBox = new VBox();

        choicesVBox.setVisible(false);
        choicesVBox.setId("PainelDeEscolhas");
        choicesVBox.setAlignment(Pos.CENTER);
        choicesVBox.setSpacing(12);
        vBox.setPadding(new Insets(0, 10, 10, 10));

        nomePersonagem = new Label();
        falaPersonagem = new Label();

        nomePersonagem.setFont(new Font("Arial", 20));
        nomePersonagem.setTextFill(Color.BLACK);

        falaPersonagem.setFont(new Font("Arial", 20));
        falaPersonagem.setTextFill(Color.BLACK);
        falaPersonagem.setWrapText(true);

        nomePersonagem.setId("NomeDoPersonagem");
        falaPersonagem.setId("FalaDoPersonagem");

        vBox.getChildren().addAll(nomePersonagem, falaPersonagem);

        bpane.setCenter(choicesVBox);
        bpane.getChildren().add(hBox);
        bpane.setBottom(vBox);

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

            nomePersonagem.setText(nome);
            final int[] contadorLetras = { 0 };

            KeyFrame frame = new KeyFrame(Duration.millis(5/* TODO: Colocar velcidade compatível! */), evento -> {
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

    public void showEndChapter() {
        // TODO
    }
}
