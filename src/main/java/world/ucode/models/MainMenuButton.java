package world.ucode.models;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Main Menu Button for Menu Scene
 */
public class MainMenuButton extends Button {

    public MainMenuButton(String text) {
        setText(text);
        setButtonFont();
        setPrefHeight(60);
        setPrefWidth(180);
        String BUTTON_STYLE = "-fx-background-color: transparent; -fx-background-image: url('scrollbutton180x60.png')";
        setStyle(BUTTON_STYLE);
        initializeButtonListeners();
    }

    private void setButtonFont() {
        try {
            String FONT_PATH = "src/main/resources/fonts/Homm2big.ttf";
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
        }
        catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
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
