package world.ucode.models;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

/**
 * MediaView with MediaPlayer for playing all sounds in game
 */
public class SoundPlayer extends MediaView {
    private final MediaPlayer mediaPlayer;
    private boolean isPlaying;

    public SoundPlayer(String soundUrl, double volume, int cycleCount) {
        Media mediaFile = new Media(new File(soundUrl).toURI().toString());
        mediaPlayer = new MediaPlayer(mediaFile);

        mediaPlayer.setVolume(volume);
        mediaPlayer.setCycleCount(cycleCount);
        this.setMediaPlayer(mediaPlayer);
    }

    public void play() {
        if (!isPlaying) {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
            mediaPlayer.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    isPlaying = false;
                }
            });
            isPlaying = true;
        }
    }

    public void setOnEndofSound(SoundPlayer nextPlayer) {
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                if (isPlaying)
                    nextPlayer.play();
            }
        });
    }

    public void stop() {
        mediaPlayer.stop();
        isPlaying = false;
    }

    public boolean getIsPlaying() {
        return isPlaying;
    }
}
