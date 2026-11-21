package src.view.Game;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableNumberValue;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public final class SceneDirectorJFX {

    private static final String CAST_FILE = "assets/story/scene_cast.json";
    private static final String SPRITE_DIRECTORY = "assets/sprites/character/";
    private static final double SPEAKING_OPACITY = 1.0;
    private static final double IDLE_OPACITY = 0.55;

    private final Map<String, List<String>> castByScene = loadSceneCasts();
    private final Map<String, ImageView> visibleCharacters = new HashMap<>();
    private final HBox view = new HBox(18);
    private final DoubleProperty characterHeight = new SimpleDoubleProperty(350);

    public SceneDirectorJFX() {
        view.setAlignment(Pos.BOTTOM_CENTER);
        view.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);
        view.setMouseTransparent(true);
    }

    public HBox getView() {
        return view;
    }

    public void bindCharacterHeightTo(ObservableNumberValue screenHeight) {
        characterHeight.bind(Bindings.multiply(screenHeight, 0.75));
    }

    public void setScene(int chapter, String sceneId) {
        List<String> configuredCast = castByScene.get(sceneKey(chapter, sceneId));
        if (configuredCast == null) {
            throw new IllegalArgumentException(
                    "Não há elenco configurado para o capítulo " + chapter + ", cena \"" + sceneId + "\".");
        }

        Set<String> cast = new LinkedHashSet<>();
        cast.add("Orfeu");
        cast.addAll(configuredCast);
        visibleCharacters.clear();
        view.getChildren().clear();

        for (String character : cast) {
            ImageView sprite = loadSprite(character);
            visibleCharacters.put(character, sprite);
            view.getChildren().add(sprite);
        }

        setSpeakingCharacter(null);
    }

    public void setSpeakingCharacter(String characterName) {
        for (Map.Entry<String, ImageView> character : visibleCharacters.entrySet()) {
            double opacity = character.getKey().equals(characterName)
                    ? SPEAKING_OPACITY
                    : IDLE_OPACITY;
            character.getValue().setOpacity(opacity);
        }
    }

    private ImageView loadSprite(String characterName) {
        File spriteFile = new File(SPRITE_DIRECTORY + characterName + ".png");
        if (!spriteFile.isFile()) {
            throw new IllegalStateException("Imagem do personagem não encontrada: " + spriteFile.getPath());
        }

        Image image = new Image(spriteFile.toURI().toString());
        if (image.isError()) {
            throw new IllegalStateException("Não foi possível carregar a imagem: " + spriteFile.getPath());
        }

        ImageView sprite = new ImageView(image);
        sprite.setPreserveRatio(true);
        sprite.fitHeightProperty().bind(characterHeight);
        if ("Orfeu".equals(characterName)) {
            sprite.setScaleX(-1);
        }
        return sprite;
    }

    private Map<String, List<String>> loadSceneCasts() {
        File castFile = new File(CAST_FILE);
        if (!castFile.isFile()) {
            throw new IllegalStateException("Arquivo de elenco das cenas não encontrado: " + castFile.getPath());
        }

        try (Reader reader = new InputStreamReader(
                new FileInputStream(castFile),
                StandardCharsets.UTF_8)) {
            SceneCastData data = new Gson().fromJson(reader, SceneCastData.class);
            if (data == null || data.scenes == null) {
                throw new IllegalStateException("O arquivo de elenco não contém a lista de cenas.");
            }

            Map<String, List<String>> result = new HashMap<>();
            for (SceneCast scene : data.scenes) {
                if (scene == null || scene.chapter < 1 || scene.sceneId == null || scene.characters == null) {
                    throw new IllegalStateException("Entrada inválida no elenco das cenas.");
                }
                String key = sceneKey(scene.chapter, scene.sceneId);
                if (result.containsKey(key)) {
                    throw new IllegalStateException("Entrada inválida ou duplicada no elenco: " + key);
                }
                result.put(key, new ArrayList<>(scene.characters));
            }
            return result;
        } catch (IOException e) {
            throw new IllegalStateException("Falha ao ler o elenco das cenas.", e);
        }
    }

    private static String sceneKey(int chapter, String sceneId) {
        return chapter + ":" + sceneId;
    }

    private static final class SceneCastData {
        private List<SceneCast> scenes;
    }

    private static final class SceneCast {
        private int chapter;
        private String sceneId;
        private List<String> characters;
    }
}
