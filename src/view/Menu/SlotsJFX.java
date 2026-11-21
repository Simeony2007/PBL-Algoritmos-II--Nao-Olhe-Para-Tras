package src.view.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public final class SlotsJFX {

    private int slotIndex;
    private BorderPane saveManagerBPane;
    private BorderPane root;

    public SlotsJFX() {
        slotIndex = 0;
    }

    public Parent createView(
            Map<Integer, String> savesInfo,
            Runnable onButtonClick,
            Runnable onReturn,
            Runnable onDelete) {

        root = new BorderPane();
        VBox slotsBox = new VBox(10);
        slotsBox.setId("SlotsBox");
        slotsBox.setAlignment(Pos.CENTER);
        slotsBox.setPadding(new Insets(20));

        Label title = new Label("Slots");
        title.setFont(Font.font("Arial", 30));
        BorderPane.setAlignment(title, Pos.CENTER);
        root.setTop(title);
        saveManagerScreenCreator(onButtonClick);
        Button btnApagar = (Button) saveManagerBPane.lookup("#Apagar");

        List<Map.Entry<Integer, String>> slots = new ArrayList<>(savesInfo.entrySet());

        for (Map.Entry<Integer, String> slot : slots) {
            Button button = new Button(slot.getValue());
            button.setMaxWidth(Double.MAX_VALUE);

            // Numero real(1–8), não o índice(0–7).
            button.setOnAction(event -> {
                setSlot(slot.getKey());
                showSaveManager(slot.getKey().toString()); // TODO: COLOCAR A INFO DO SAVE E RETORNAR O CARREGAR!
                // onButtonClick.run();
            });

            btnApagar.setOnAction(e -> {
                System.out.println(slotIndex);
                onDelete.run();
            });

            slotsBox.getChildren().add(button);
        }

        StackPane stackPane = new StackPane(slotsBox);

        Button backButton = new Button("Voltar");
        backButton.setOnAction(event -> onReturn.run());
        backButton.setId("BackButton");

        stackPane.getChildren().add(saveManagerBPane);
        root.setCenter(stackPane);
        BorderPane.setAlignment(backButton, Pos.CENTER);
        BorderPane.setMargin(backButton, new Insets(0, 0, 20, 0));
        root.setBottom(backButton);

        return root;
    }

    private void saveManagerScreenCreator(Runnable loadButtonCustomFunction) {
        saveManagerBPane = new BorderPane();

        saveManagerBPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        saveManagerBPane.setPrefWidth(360);
        saveManagerBPane.setPadding(new Insets(15));

        saveManagerBPane.setBackground(new Background(new BackgroundFill(
                Color.rgb(20, 20, 28, 0.95),
                new CornerRadii(12),
                Insets.EMPTY)));
        saveManagerBPane.setBorder(new Border(new BorderStroke(
                Color.rgb(158, 131, 9),
                BorderStrokeStyle.SOLID,
                new CornerRadii(12),
                new BorderWidths(2))));

        Button fecharButton = new Button("X");
        BorderPane.setAlignment(fecharButton, Pos.TOP_RIGHT);

        Label tituloGeral = new Label("Gerenciamento de Save");
        tituloGeral.setFont(new Font("Arial", 20));
        tituloGeral.setTextFill(Color.WHITE);

        Label infoDoSave = new Label();
        infoDoSave.setFont(new Font("Arial", 15));
        infoDoSave.setTextFill(Color.LIGHTGRAY);
        infoDoSave.setWrapText(true);
        infoDoSave.setId("LabelSaveInfo");

        VBox vBoxText = new VBox(12, tituloGeral, infoDoSave);
        vBoxText.setAlignment(Pos.CENTER);
        BorderPane.setMargin(vBoxText, new Insets(15, 10, 20, 10));

        Button carregarButton = new Button("Carregar");
        Button apagarButton = new Button("Apagar");
        carregarButton.setPrefWidth(120);
        apagarButton.setPrefWidth(120);
        apagarButton.setId("Apagar");

        HBox hBoxButtons = new HBox(20, carregarButton, apagarButton);
        hBoxButtons.setAlignment(Pos.CENTER);

        saveManagerBPane.setTop(fecharButton);
        saveManagerBPane.setCenter(vBoxText);
        saveManagerBPane.setBottom(hBoxButtons);
        saveManagerBPane.setVisible(false);

        fecharButton.setOnAction(e -> onBackButton());
        carregarButton.setOnAction(e -> loadButtonCustomFunction.run());
    }

    public void showSaveManager(String saveInfo) {
        Label label = (Label) saveManagerBPane.lookup("#LabelSaveInfo");
        label.setText(saveInfo);
        saveManagerBPane.setVisible(true);
        VBox slotsBox = (VBox) root.lookup("#SlotsBox");
        slotsBox.setDisable(true);
        Button backButton = (Button) root.lookup("#BackButton");
        backButton.setDisable(true);
    }

    private void onBackButton() {
        Label label = (Label) saveManagerBPane.lookup("#LabelSaveInfo");
        label.setText("");
        saveManagerBPane.setVisible(false);
        VBox slotsBox = (VBox) root.lookup("#SlotsBox");
        slotsBox.setDisable(false);
        Button backButton = (Button) root.lookup("#BackButton");
        backButton.setDisable(false);
    }

    public void setSlot(int slotnumber) {
        slotIndex = slotnumber;
    }

    public int getSlot() {
        return slotIndex;
    }
}