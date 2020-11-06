package world.ucode.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.media.Media;
import javafx.stage.Stage;

import java.io.File;

/**
 * Game over scene manager
 * @author Alexey Buhaiov aka obuhaiov
 * @version 0.0.1
 */
public class GameOverManager {
    /**
     * Stage, MediaPlayer and MediaView of game over manager:
     */
    private final Stage overStage;
    private final Scene overScene;
    private final MediaPlayer mediaPlayer;
    private final MediaView mediaView;

    /**
     * Constructor of GameOverManager, create mediaView, it`s width and height
     * depends(bind) on width and height of overScene:
     * @param playerName - player name
     * @param score - score
     */
    public GameOverManager(String playerName, int score) {
        AnchorPane overPane = new AnchorPane();
        overScene = new Scene(overPane, 960, 720);
        overStage = new Stage();

        Media mediaFile = new Media(new File("src/main/resources/movies/videoplayback.mp4").toURI().toString());
        mediaPlayer = new MediaPlayer(mediaFile);
        mediaView = new MediaView(mediaPlayer);
        overPane.getChildren().add(mediaView);

        DoubleProperty width = mediaView.fitWidthProperty();
        DoubleProperty height = mediaView.fitHeightProperty();
        width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "height"));

        mediaPlayer.setAutoPlay(true);
        mediaView.setPreserveRatio(true);
        overStage.setTitle(playerName + " you die! Score: " + score);
        overStage.setScene(overScene);
    }

    /**
     * Show overStage and play video, on finish close overStage and show menuStage:
     * @param menuStage - Stage of game menu manager
     */
    public void playMediaAndShowMenu(GameMenuManager menuStage) {
        overStage.show();
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                overStage.close();
                menuStage.playNewGameMusic();
                menuStage.getMenuStage().show();
            }
        });
        mediaView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.stop();
                overStage.close();
                menuStage.playNewGameMusic();
                menuStage.getMenuStage().show();
            }
        });
        overScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.LEFT) || event.getCode().equals(KeyCode.RIGHT)
                    || event.getCode().equals(KeyCode.SPACE) || event.getCode().equals(KeyCode.UP)
                    || event.getCode().equals(KeyCode.CONTROL) || event.getCode().equals(KeyCode.DOWN)) {
                    mediaPlayer.stop();
                    overStage.close();
                    menuStage.playNewGameMusic();
                    menuStage.getMenuStage().show();
                }
            }
        });
    }
}
