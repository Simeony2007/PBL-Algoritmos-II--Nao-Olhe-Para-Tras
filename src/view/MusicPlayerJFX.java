package src.view;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public final class MusicPlayerJFX {

    private static final String SONG_DIRECTORY = "assets/songs/";

    private MediaPlayer player;
    private String currentTrack;
    private double volume = 0.5;

    public void playTrack(String filename) {
        if (filename.equals(currentTrack) && player != null) {
            return;
        }

        stop();
        File song = new File(SONG_DIRECTORY + filename);
        if (!song.isFile()) {
            return;
        }

        MediaPlayer nextPlayer = new MediaPlayer(new Media(song.toURI().toString()));
        nextPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        nextPlayer.setVolume(volume);
        nextPlayer.setOnError(() -> {
            if (nextPlayer.getError() != null) {
                System.err.println("Falha ao reproduzir " + song.getPath() + ": "
                        + nextPlayer.getError().getMessage());
            }
        });
        player = nextPlayer;
        currentTrack = filename;
        nextPlayer.play();
    }

    public void setVolume(int percentage) {
        volume = Math.max(0, Math.min(100, percentage)) / 100.0;
        if (player != null) {
            player.setVolume(volume);
        }
    }

    public void stop() {
        if (player != null) {
            player.stop();
            player.dispose();
            player = null;
        }
        currentTrack = null;
    }
}
