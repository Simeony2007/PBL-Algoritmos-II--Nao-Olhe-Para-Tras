package src.view;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.nio.file.Paths;

public class AudioManager {

    private final String CAMINHO = Paths.get(System.getProperty("user.home"), "Downloads", "test.mp3").toFile()
            .toString();
    // private final String CAMINHO = "assets/songs/bgSong.mp3";
    private MediaPlayer bgmPlayer;
    private float volume;

    public void playBgSong() {
        File resource = Paths.get(CAMINHO).toFile();
        if (!resource.exists()) {
            return;
        }

        Media media = new Media(resource.toURI().toString());
        bgmPlayer = new MediaPlayer(media);
        bgmPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop infinito
        bgmPlayer.setVolume(volume);
        bgmPlayer.setOnReady(() -> updateVolume());
        bgmPlayer.play();
    }

    public void stopSong() {
        if (bgmPlayer != null) {
            bgmPlayer.stop();
        }
    }

    public void setVolume(float value) {
        volume = value;
        updateVolume();
    }

    private void updateVolume() {
        if (bgmPlayer != null) {
            bgmPlayer.setVolume(volume * volume);
        }
    }
}