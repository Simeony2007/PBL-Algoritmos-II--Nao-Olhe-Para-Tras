package src.view.Game;

import java.util.ArrayList;
import java.util.function.IntConsumer;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
        view.getChildren().clear();

        for (int index = 0; index < choices.size(); index++) {
            int choiceIndex = index;
            Button button = new Button(choices.get(index));
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
