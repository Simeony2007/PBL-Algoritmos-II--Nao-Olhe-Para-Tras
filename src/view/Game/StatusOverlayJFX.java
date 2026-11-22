package src.view.Game;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import src.model.service.logic.Npc;
import src.model.service.logic.Orfeu;

public class StatusOverlayJFX {

    private static final String CHARACTER_SPRITES_PATH = "assets/sprites/character/";

    private final VBox view = new VBox(6);
    private final Map<String, Label> statusLabels = new HashMap<>();

    public StatusOverlayJFX() {
        view.setAlignment(Pos.TOP_LEFT);
        view.setPadding(new Insets(10));
        view.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        view.setMouseTransparent(true);

        view.getChildren().addAll(
                createCard("Caronte", "Afinidade: —", "Caronte.png"),
                createCard("Eurídice", "Afinidade: —", "Eurídice.png"),
                createCard("Sísifo", "Afinidade: —", "Sísifo.png"),
                createCard("Orfeu", "Óbolos: —\nAmor: —\nRaiva: —\nTristeza: —", "Orfeu.png"));
    }

    public VBox getView() {
        return view;
    }

    public StackPane createCard(String characterName, String status, String spriteFileName) {
        StackPane card = new StackPane();
        card.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

        // 1. Sprite suavizado e alinhado na base
        ImageView spriteView = createSpriteView(spriteFileName);
        spriteView.setSmooth(true);
        spriteView.setCache(true);
        spriteView.setFitHeight(64);
        spriteView.setPreserveRatio(true);
        cortarTransparenciaInferior(spriteView);

        // Calcula o centro horizontal exato do sprite
        double spriteWidth = 64.0;
        if (spriteView.getViewport() != null && spriteView.getViewport().getHeight() > 0) {
            double ratio = spriteView.getViewport().getWidth() / spriteView.getViewport().getHeight();
            spriteWidth = 64.0 * ratio;
        }
        double metadeSprite = spriteWidth / 2.0;

        // 2. Cartão cinza: compacto para NPCs e mais largo/adaptável para o Orfeu
        BorderPane cardBackground = new BorderPane();
        cardBackground.setMinWidth(characterName.equalsIgnoreCase("Orfeu") ? 185 : 135);
        cardBackground.setPrefWidth(Region.USE_PREF_SIZE);
        cardBackground.setMinHeight(48);
        cardBackground.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        cardBackground.setPadding(new Insets(6, 10, 6, metadeSprite + 6));
        cardBackground.setBackground(new Background(new BackgroundFill(
                Color.web("#9E8309"),
                new CornerRadii(2),
                Insets.EMPTY)));

        Label nameLabel = new Label(characterName);
        nameLabel.setFont(new Font("Arial", 13));
        nameLabel.setTextFill(Color.BLACK);
        nameLabel.setMinWidth(Region.USE_PREF_SIZE);

        Label statusLabel = new Label(status);
        statusLabel.setFont(new Font("Arial", 11.5));
        statusLabel.setTextFill(Color.BLACK);
        statusLabel.setMinSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        statusLabels.put(characterName, statusLabel);

        VBox text = new VBox(5, nameLabel, statusLabel);
        text.setAlignment(Pos.CENTER_LEFT);
        text.setMinWidth(Region.USE_PREF_SIZE);
        cardBackground.setCenter(text);

        // 3. Alinha a borda esquerda do cartão no centro horizontal do sprite e ambos
        // na base
        StackPane.setAlignment(cardBackground, Pos.BOTTOM_LEFT);
        StackPane.setMargin(cardBackground, new Insets(0, 0, 0, metadeSprite));

        StackPane.setAlignment(spriteView, Pos.BOTTOM_LEFT);

        card.getChildren().addAll(cardBackground, spriteView);
        return card;
    }

    private void cortarTransparenciaInferior(ImageView imageView) {
        Image img = imageView.getImage();
        if (img == null || img.getPixelReader() == null)
            return;

        int width = (int) img.getWidth();
        int height = (int) img.getHeight();
        var reader = img.getPixelReader();

        int ultimaLinhaVisivel = height - 1;
        for (int y = height - 1; y >= 0; y--) {
            boolean temPixel = false;
            for (int x = 0; x < width; x++) {
                if ((reader.getArgb(x, y) >>> 24) > 10) {
                    temPixel = true;
                    break;
                }
            }
            if (temPixel) {
                ultimaLinhaVisivel = y;
                break;
            }
        }

        imageView.setViewport(new javafx.geometry.Rectangle2D(0, 0, width, ultimaLinhaVisivel + 1));
    }

    public void updateStatus(Orfeu orfeu, List<Npc> npcs) {
        Label orfeuStatus = statusLabels.get("Orfeu");
        orfeuStatus.setText(String.format(
                "Óbolos: %d\nAmor: %d | Raiva: %d | Tristeza: %d",
                orfeu.getObol(),
                orfeu.getLove(),
                orfeu.getAngry(),
                orfeu.getSadness()));

        for (Npc npc : npcs) {
            String characterName = displayNameFor(npc.getName());
            Label status = statusLabels.get(characterName);
            if (status != null) {
                status.setText("Afinidade: " + npc.getAffinity());
            }
        }
    }

    private ImageView createSpriteView(String spriteFileName) {
        File spriteFile = new File(CHARACTER_SPRITES_PATH + spriteFileName);
        ImageView spriteView = new ImageView();
        if (spriteFile.isFile()) {
            Image image = new Image(spriteFile.toURI().toString());
            if (!image.isError()) {
                spriteView.setImage(image);
            }
        }
        spriteView.setFitHeight(100);
        spriteView.setFitWidth(72);
        spriteView.setPreserveRatio(true);
        return spriteView;
    }

    private String displayNameFor(String modelName) {
        switch (modelName) {
            case "Charon":
                return "Caronte";
            case "Eurydice":
                return "Eurídice";
            case "Sisyphus":
                return "Sísifo";
            default:
                return modelName;
        }
    }
}
