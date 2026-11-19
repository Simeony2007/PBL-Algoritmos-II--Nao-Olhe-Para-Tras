package src.view;

import src.model.Observer;
import src.model.service.logic.Orfeu;

import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;
import javafx.animation.SequentialTransition;

public class GameInterfaceJFX implements Observer {

    private Scene scene;

    @Override
    public void modelUpdated(Orfeu orfeu) {

    }

    public GameInterfaceJFX() {
        BorderPane bpane = new BorderPane();
        // StackPane stackPane = new StackPane();
        HBox hBox = new HBox();
        VBox vBox = new VBox();

        Label nomePersonagem = new Label();
        Label falaPersonagem = new Label();
        nomePersonagem.setId("#NomeDoPersonagem");
        falaPersonagem.setId("#FalaDoPersonagem");

        vBox.getChildren().addAll(nomePersonagem, falaPersonagem);

        bpane.getChildren().add(hBox);
        bpane.getChildren().add(vBox);

        scene = new Scene(bpane);
    }

    public Scene gameScene() {
        return scene;
    }

    public void showDialogues(ArrayList<Map.Entry<String, String>> dialogos, int speed) {
        Label nomePersonagem = (Label) scene.lookup("#NomeDoPersonagem");
        Label falaPersonagem = (Label) scene.lookup("#FalaDoPersonagem");

        SequentialTransition sequencia = new SequentialTransition();

        for (Map.Entry<String, String> dialogue : dialogos) {
            String nome = dialogue.getKey();
            String fala = dialogue.getValue();

            nomePersonagem.setText(nome);
            final int[] contadorLetras = { 0 };

            KeyFrame frame = new KeyFrame(Duration.millis(speed), evento -> {
                contadorLetras[0]++;
                falaPersonagem.setText(fala.substring(0, contadorLetras[0]));
            });

            Timeline timelineFala = new Timeline(frame);
            timelineFala.setCycleCount(fala.length());

            sequencia.getChildren().addAll(timelineFala, new PauseTransition(Duration.seconds(1)));
        }

        sequencia.play();
    }

    public void showEndGame() {
        // TODO
    }

    public void showChoicesOptions(ArrayList<String> escolhas) {
        // TODO
    }

    public void showEndChapter() {
        // TODO
    }
}
