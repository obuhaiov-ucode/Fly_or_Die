package world.ucode.models;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import world.ucode.enums.BACKGROUND_IMAGES;

/**
 * Mini BackGround Button for dragonSubScene
 */
public class MiniBackGroundButton extends Button {

    public MiniBackGroundButton(BACKGROUND_IMAGES back) {
        setPrefHeight(50);
        setPrefWidth(80);
        String BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-image: url('";
        setStyle(BUTTON_STYLE + back.getUrlMiniBack() + "')");
        initializeButtonListeners();
    }

    private void initializeButtonListeners() {
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }
}