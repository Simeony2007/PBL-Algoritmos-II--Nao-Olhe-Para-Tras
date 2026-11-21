package src.view.Menu;

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

final class SaveSlotManagerJFX {

    private final StackPane view = new StackPane();
    private final BorderPane managerPane = new BorderPane();
    private final Label saveInfoLabel = new Label();
    private final Runnable onLoad;
    private final Runnable onDelete;
    private final Runnable onOpen;
    private final Runnable onClose;
    private final ConfirmationScreenJFX confirmation;

    SaveSlotManagerJFX(
            Runnable onLoad,
            Runnable onDelete,
            Runnable onOpen,
            Runnable onClose) {
        this.onLoad = onLoad;
        this.onDelete = onDelete;
        this.onOpen = onOpen;
        this.onClose = onClose;

        confirmation = new ConfirmationScreenJFX(
                () -> {
                    managerPane.setDisable(false);
                    this.onDelete.run();
                },
                () -> managerPane.setDisable(false));

        createManagerPane();
        view.getChildren().addAll(managerPane, confirmation.getView());
        view.setVisible(false);
    }

    Parent getView() {
        return view;
    }

    void showSaveManager(String saveInfo) {
        // TODO: Exibir os dados completos do save, não apenas o número do slot.
        saveInfoLabel.setText(saveInfo);
        view.setVisible(true);
        onOpen.run();
    }

    private void createManagerPane() {
        managerPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        managerPane.setPrefWidth(360);
        managerPane.setPadding(new Insets(15));
        managerPane.setBackground(new Background(new BackgroundFill(
                Color.rgb(20, 20, 28, 0.95),
                new CornerRadii(12),
                Insets.EMPTY)));
        managerPane.setBorder(new Border(new BorderStroke(
                Color.rgb(158, 131, 9),
                BorderStrokeStyle.SOLID,
                new CornerRadii(12),
                new BorderWidths(2))));

        Button closeButton = new Button("X");
        BorderPane.setAlignment(closeButton, Pos.TOP_RIGHT);

        Label title = new Label("Gerenciamento de Save");
        title.setFont(new Font("Arial", 20));
        title.setTextFill(Color.WHITE);

        saveInfoLabel.setFont(new Font("Arial", 15));
        saveInfoLabel.setTextFill(Color.LIGHTGRAY);
        saveInfoLabel.setWrapText(true);
        saveInfoLabel.setId("LabelSaveInfo");

        VBox textBox = new VBox(12, title, saveInfoLabel);
        textBox.setAlignment(Pos.CENTER);
        BorderPane.setMargin(textBox, new Insets(15, 10, 20, 10));

        Button loadButton = new Button("Carregar");
        Button deleteButton = new Button("Apagar");
        loadButton.setPrefWidth(120);
        deleteButton.setPrefWidth(120);

        HBox buttons = new HBox(20, loadButton, deleteButton);
        buttons.setAlignment(Pos.CENTER);

        managerPane.setTop(closeButton);
        managerPane.setCenter(textBox);
        managerPane.setBottom(buttons);

        closeButton.setOnAction(event -> close());
        loadButton.setOnAction(event -> onLoad.run());
        deleteButton.setOnAction(event -> {
            managerPane.setDisable(true);
            confirmation.show();
        });
    }

    private void close() {
        saveInfoLabel.setText("");
        view.setVisible(false);
        onClose.run();
    }
}
