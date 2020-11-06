package world.ucode;

import javafx.application.Application;
import javafx.stage.Stage;

import world.ucode.controllers.GameMenuManager;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        GameMenuManager manager = new GameMenuManager();
        primaryStage = manager.getMenuStage();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
