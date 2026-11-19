package src.view;

import src.model.Observer;
import src.model.service.logic.Orfeu;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class GameInterfaceJFX implements Observer {

    @Override
    public void modelUpdated(Orfeu orfeu) {

    }

    public Scene gameScene() {

        BorderPane bpane = new BorderPane();
        StackPane stackPane = new StackPane();
        HBox hBox = new HBox();

        // TODO: Finalizar o modelo
        // Fui fazer a separação entre nome do npc e fala,
        // vai servir pra mostrar a imagem certa aqui - Simeony

        Scene scene = new Scene(bpane);

    }

}
