package src.view.Menu;

import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import src.controller.CollectionsMenuJFX;
import src.model.service.CollectionItem;

public class CollectionsJFX {

    public static Parent createView(Runnable onReturnAction) {
        VBox root = new VBox(15);
        root.setPadding(new Insets(25));

        Label title = new Label("Galeria");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Button btnEndings = CreateButtonsMenu("Finais");
        Button btnAdvancements = CreateButtonsMenu("Conquistas");
        Button btnReturn = CreateButtonsMenu("Voltar");


        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox barButtons = new HBox(15, btnEndings, btnAdvancements, spacer, btnReturn);
        barButtons.setAlignment(Pos.CENTER_LEFT);

        VBox listEndings = getEndingsList();

        VBox listAdvancements = getAchievementsList();

        StackPane contentContainer = new StackPane(listEndings, listAdvancements);
        listEndings.managedProperty().bind(listEndings.visibleProperty());
        listAdvancements.managedProperty().bind(listAdvancements.visibleProperty());
        listAdvancements.setVisible(false);

        btnEndings.setOnAction(e -> {
            listEndings.setVisible(true);
            listAdvancements.setVisible(false);
        });

        btnAdvancements.setOnAction(e -> {
            listEndings.setVisible(false);
            listAdvancements.setVisible(true);
        });

        // Triggers whatever navigation logic your main class passed in
        btnReturn.setOnAction(e -> onReturnAction.run());

        ScrollPane scrollPane = new ScrollPane(contentContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        root.getChildren().addAll(title, barButtons, scrollPane);
        return root;
    }

    private static VBox getEndingsList() {
        VBox endingList = new VBox(8);
        endingList.setPadding(new Insets(10));
        List<CollectionItem> endingsItem = new CollectionsMenuJFX().getEndingsList();
        for (CollectionItem item : endingsItem) {
            String description = item.unlocked() ? item.description() : "Você ainda não desbloqueou este final.";
            endingList.getChildren().add(createCard(item.title(), description));
        }

        return endingList;
    }

    private static VBox getAchievementsList() {
        VBox achievementList = new VBox(8);
        achievementList.setPadding(new Insets(10));
        List<CollectionItem> achievementsItem = new CollectionsMenuJFX().getAchievementsList();
        for (CollectionItem item : achievementsItem) {
            String description = item.unlocked() ? item.description() : "Você ainda não desbloqueou esta conquista.";
            achievementList.getChildren().add(createCard(item.title(), description));
        }

        return achievementList;
    }

    private static VBox createCard(String tituloTexto, String descricaoTexto) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(12));
        card.setStyle(
                "-fx-border-color: #9E8309; -fx-border-width: 2; " +
                        "-fx-background-color: rgba(20,20,28,0.9);");
        Label lblTitulo = new Label(tituloTexto);
        lblTitulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label lblDesc = new Label(descricaoTexto);
        card.getChildren().addAll(lblTitulo, lblDesc);
        return card;
    }

    private static Button CreateButtonsMenu(String texto) {
        Button btn = new Button(texto);
        btn.setPrefSize(120, 35);
        return btn;
    }

    public static void main(String[] args) {
		main(args);
	}
}