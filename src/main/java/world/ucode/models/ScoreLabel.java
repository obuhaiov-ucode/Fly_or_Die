package world.ucode.models;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Score Label for Game Play Scene
 */
public class ScoreLabel extends Label {
    private final static String FONT_PATH = "src/main/resources/fonts/Homm2big.ttf";

    public ScoreLabel(String text) {
        setPrefWidth(600);
        setPrefHeight(200);
        setAlignment(Pos.TOP_RIGHT);
        setPadding(new Insets(10,10,10,10));
        setLabelFont(23);
        setText(text);
    }

    public void setLabelFont(int size) {
        try {
            setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), size));
        }
        catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", size));
        }
    }
}
