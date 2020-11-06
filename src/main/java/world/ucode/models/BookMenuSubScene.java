package world.ucode.models;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

/**
 * Book Subscene on Menu Scene
 */
public class BookMenuSubScene extends SubScene {

    private final static String BACKGROUND_IMAGE = "booksubscene649x420.png";

    /**
     * Constructor of BookMenuSubScene with setting size and background
     */
    public BookMenuSubScene() {
        super(new AnchorPane(), 649, 420);

        prefHeight(420);
        prefWidth(649);
        BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE, 649, 420, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        AnchorPane root2 = (AnchorPane) this.getRoot();
        root2.setBackground(new Background(image));

        setLayoutX(631);
        setLayoutY(1000);
    }

    /**
     * Show Subscene on Menu Scene
     * @return - transition for setOnFinished
     */
    public TranslateTransition showSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.4));
        transition.setNode(this);

        transition.setToY(-630);
        transition.play();
        return transition;
    }

    /**
     * Hide Subscene from Menu Scene
     * @return - transition for setOnFinished
     */
    public TranslateTransition hideSubScene() {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(0.4));
        transition.setNode(this);

        transition.setToY(0);
        transition.play();
        return transition;
    }

    /**
     * Getting this Pane:
     */
    public AnchorPane getPane() {
        return (AnchorPane) this.getRoot();
    }
}
