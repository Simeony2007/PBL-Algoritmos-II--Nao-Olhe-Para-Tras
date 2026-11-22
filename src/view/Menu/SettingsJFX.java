package src.view.Menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import src.controller.SettingsMenuJFX;

public class SettingsJFX {
    private static final String TEXT_STYLE =
            "-fx-text-fill: white;" +
                    "-fx-effect: dropshadow(gaussian, black, 4, 0.0, 1, 1);";

    public static Parent createView(SettingsMenuJFX settings, Runnable onBackAction, Runnable onSaveSettings) {
        VBox root = new VBox(30);
        root.setPadding(new Insets(30));

        Label title = new Label("Configurações");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;" + TEXT_STYLE);

        StackPane header = new StackPane(title);
        header.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(12);

        // BOTÃO: Velocidade de Texto
        Label lblTextSpeed = createBoxLabel("Velocidade de Texto");
        Button btnTextSpeed = CreateBoxButton(settings.getTextSpeedString());
        grid.addRow(0, lblTextSpeed, btnTextSpeed);

        btnTextSpeed.setOnAction(e -> {
            settings.changeTextSpeed();
            btnTextSpeed.setText(settings.getTextSpeedString());
        });

        // BOTÃO: Auto Save
        Label lblAutoSave = createBoxLabel("Auto Save");
        Button btnAutoSave = CreateBoxButton(settings.getAutoSaveString());
        grid.addRow(1, lblAutoSave, btnAutoSave);

        btnAutoSave.setOnAction(e -> {
            settings.toggleAutoSave();
            btnAutoSave.setText(settings.getAutoSaveString());
        });

        /*
         * // BOTÃO: Resolução da Tela
         * Label lblScreenResolution = createBoxLabel("Resolução da Tela");
         * Button btnScreenResolution = CreateBoxButton(settings.getResolutionString());
         * grid.addRow(2, lblScreenResolution, btnScreenResolution);
         */

        // BOTÃO: Volume + Slider + Porcentagem
        Label lblVolume = createBoxLabel("Volume");

        Slider sliderVolume = new Slider(0, 100, settings.getVolume());
        sliderVolume.setPrefWidth(170);

        Label lblPercent = new Label();
        lblPercent.setPrefWidth(48);
        lblPercent.setAlignment(Pos.CENTER_RIGHT);
        lblPercent.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;" + TEXT_STYLE);
        lblPercent.textProperty().bind(sliderVolume.valueProperty().asString("%.0f%%"));

        HBox volumeBox = new HBox(10, sliderVolume, lblPercent);
        volumeBox.setAlignment(Pos.CENTER);
        volumeBox.setPrefSize(250, 42);
        volumeBox.setPadding(new Insets(0, 12, 0, 12));
        volumeBox.setStyle(
                "-fx-border-color: #9E8309;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: rgba(20,20,28,0.9);");

        grid.addRow(3, lblVolume, volumeBox);

        Region verticalSpacer = new Region();
        VBox.setVgrow(verticalSpacer, Priority.ALWAYS);

        Button btnReturn = createButton("Retornar");
        btnReturn.setOnAction(e -> {
            if (onBackAction != null) {
                onBackAction.run();
            }
        });

        Button btnSave = createButton("Salvar");
        btnSave.setOnAction(e -> {
            settings.setVolume((int) sliderVolume.getValue());
            settings.saveSettings();
            onSaveSettings.run();
        });

        HBox barraInferior = new HBox(15, btnReturn, btnSave);
        barraInferior.setAlignment(Pos.CENTER_RIGHT);

        root.getChildren().addAll(header, grid, verticalSpacer, barraInferior);
        return root;
    }

    private static Label createBoxLabel(String texto) {
        Label label = new Label(texto);
        label.setPrefSize(220, 42);
        label.setPadding(new Insets(0, 0, 0, 12));
        label.setAlignment(Pos.CENTER_LEFT);
        label.setStyle(
                "-fx-font-size: 16px;" +
                        TEXT_STYLE +
                        "-fx-border-color: #9E8309;" +
                        "-fx-border-width: 2;" +
                        "-fx-background-color: rgba(20,20,28,0.9);");
        return label;
    }

    private static Button CreateBoxButton(String texto) {
        Button btn = new Button(texto);
        btn.setPrefSize(180, 42);
        btn.setStyle(TEXT_STYLE);
        return btn;
    }

    private static Button createButton(String texto) {
        Button btn = new Button(texto);
        btn.setPrefSize(130, 40);
        btn.setStyle(TEXT_STYLE);
        return btn;
    }
}