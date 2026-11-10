package src.view;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;;

public class WindowMenuInterface extends Application {

    @Override
    public void start(Stage principalStage) throws Exception {
        Button btn1 = createNewButton("Iniciar");
        Button btn2 = createNewButton("Sair");

        btn1.setTextFill(Color.BLACK);
        btn2.setTextFill(Color.BLACK);

        btn1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 25));
        btn2.setFont(Font.font("Arial", 25));

        btn1.setPrefWidth(250);
        btn2.setPrefWidth(250);

        // 1. VBox empilha os botões verticalmente com 15px de espaço entre eles
        VBox menuBox = new VBox(15);
        menuBox.setAlignment(Pos.CENTER);
        menuBox.getChildren().addAll(btn1, btn2);

        BorderPane borderPane = new BorderPane();
        Scene menuScene = new Scene(borderPane);

        borderPane.setCenter(menuBox);

        principalStage.setHeight(600);
        principalStage.setWidth(800);
        principalStage.setScene(menuScene);
        principalStage.show();

    }

    public Button createNewButton(String text) {
        Button nButton = new Button(text);
        return nButton;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
