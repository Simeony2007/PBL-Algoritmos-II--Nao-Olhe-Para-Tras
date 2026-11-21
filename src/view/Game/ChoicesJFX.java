package src.view.Game;

import java.util.ArrayList;
import java.util.function.IntConsumer;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ChoicesJFX {

    private final VBox view = new VBox();

    public ChoicesJFX() {
        view.setVisible(false);
        view.setId("PainelDeEscolhas");
        view.setAlignment(Pos.CENTER);
        view.setSpacing(12);
    }

    public VBox getView() {
        return view;
    }

    public boolean isVisible() {
        return view.isVisible();
    }

    public void setDisabled(boolean disabled) {
        view.setDisable(disabled);
    }

    public void hide() {
        view.setVisible(false);
    }

    public void reset() {
        hide();
        setDisabled(false);
        view.getChildren().clear();
    }

    public void showChoicesOptions(ArrayList<String> choices, IntConsumer onChoice) {
        showChoicesOptions(choices, null, onChoice);
    }

    public void showCharacterCreationOptions(ArrayList<String> choices, IntConsumer onChoice) {
        Label title = new Label("Criação de personagem");
        title.getStyleClass().add("choice-title");
        Label prompt = new Label("O que motiva os passos de Orfeu em direção ao abismo?");
        prompt.getStyleClass().add("choice-prompt");
        showChoicesOptions(choices, new Label[] { title, prompt }, onChoice);
        view.getStyleClass().add("choice-panel");
    }

    private void showChoicesOptions(
            ArrayList<String> choices,
            Label[] header,
            IntConsumer onChoice) {
        view.getChildren().clear();
        view.getStyleClass().remove("choice-panel");
        view.setMaxWidth(760);
        view.setMaxHeight(VBox.USE_PREF_SIZE);

        if (header != null) {
            for (Label label : header) {
                view.getChildren().add(label);
            }
        }

        for (int index = 0; index < choices.size(); index++) {
            int choiceIndex = index;
            Button button = new Button(choices.get(index));
            button.getStyleClass().add("choice-button");
            button.setWrapText(true);
            button.setPrefWidth(720);
            button.setMinHeight(64);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setOnAction(event -> {
                event.consume();
                hide();
                onChoice.accept(choiceIndex);
            });
            view.getChildren().add(button);
        }

        view.setVisible(true);
    }
}
