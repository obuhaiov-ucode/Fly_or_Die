package world.ucode.controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import world.ucode.enums.MENU_HELP_BONUSES;
import world.ucode.enums.MENU_HELP_CONTROLS;
import world.ucode.enums.CREATURES_NAMES;
import world.ucode.enums.BACKGROUND_IMAGES;
import world.ucode.models.*;

import java.io.*;
import java.util.*;

/**
 * Game menu manager scene
 * @author Alexey Buhaiov aka obuhaiov
 * @version 0.0.1
 */
public class GameMenuManager {
    /**
     * All parameters of menu manager:
     */
    private static final int width = 1280;
    private static final int height = 790;
    private final AnchorPane menuPane;
    private final Stage menuStage;
    private AnchorPane namePane;
    private TextField nameField;
    private String playerName;
    private VBox namesScoreVBox;
    private VBox scoresScoreVBox;
    private int score = 0;
    /**
     * Lists of menu buttons, mini background buttons:
     * {@value} highscores - list of Strings like 123:defaultName\n
     * {@value} highscoresMap - Treemap of Pairs like <123, defaultName> in descending order
     */
    List<MainMenuButton> menuButtons;
    List<MiniBackGroundButton> backGroundList;
    List<String> highscores;
    TreeMap<Integer, String> highscoresMap;
    /**
     * BookMenuSubScenes with their list:
     * {@value} sceneToHide - sub scene visible now on menuScene
     */
    List<BookMenuSubScene> bookSubSceneList;
    private BookMenuSubScene dragonChooserScene;
    private BookMenuSubScene scoreSubScene;
    private BookMenuSubScene helpSubScene;
    private BookMenuSubScene sceneToHide = null;
    /**
     * List of dragons to choose:
     * {@value} choosenDragon - choosen animation
     * {@value} textStyle - Heroes of MM style text
     * {@value} menuSoundPlayer - main menu music player
     * {@value} newGameSoundPlayer - play after end of game
     */
    List<AnimationStackPane> dragonsList;
    private AnimationStackPane choosenDragon;
    private final static String textStyle = "src/main/resources/fonts/Homm2big.ttf";
    private final GameSoundPlayer soundPlayer;

    /**
     * Constructor of game menu manager scene, create all visual
     */
    public GameMenuManager() {
        menuPane = new AnchorPane();
        Scene menuScene = new Scene(menuPane, width, height);
        menuStage = new Stage();
        menuStage.setScene(menuScene);

        dragonsList = new ArrayList<>();
        menuButtons = new ArrayList<>();
        backGroundList = new ArrayList<>();
        bookSubSceneList = new ArrayList<>();

        menuStage.setTitle("Fly or Die");
        soundPlayer = new GameSoundPlayer();
        soundPlayer.playMainTheme();
        createMenuButtons();
        createBackground();
        createSubScenes();
    }

    /**
     * Getter of menuStage:
     * @return menuStage - Stage of game menu manager
     */
    public Stage getMenuStage() {
        return menuStage;
    }

    /**
     * Creating of MainMenuButtons:
     */
    private void createMenuButtons() {
        createStartButton();
        createScoresButton();
        createHelpButton();
        createExitButton();
    }

    /**
     * Adds MainMenuButton on pane and list, sets position:
     * @param button - LayoutY depends on size of menuButtons list
     */
    private void addMenuButton(MainMenuButton button) {
        button.setLayoutX(110);
        button.setLayoutY(440 + menuButtons.size() * 75);
        menuButtons.add(button);
        menuPane.getChildren().add(button);
    }

    /**
     * Creating of startButton, it binded with dragonSubScene and namePane:
     */
    private void createStartButton() {
        MainMenuButton startButton = new MainMenuButton("PLAY");
        addMenuButton(startButton);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TranslateTransition transition = moveSubScene(dragonChooserScene);
                if (sceneToHide != dragonChooserScene)
                    namePane.setVisible(false);
                else
                    transition.setOnFinished(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            namePane.setVisible(true);
                        }
                    });
            }
        });
    }

    /**
     * Creating of scoresButton, it binded with scoreSubScene:
     */
    private void createScoresButton() {
        MainMenuButton scoresButton = new MainMenuButton("SCORES");
        addMenuButton(scoresButton);

        scoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                moveSubScene(scoreSubScene);
                namePane.setVisible(false);
            }
        });
    }

    /**
     * Creating of helpButton, it binded with helpSubScene:
     */
    private void createHelpButton() {
        MainMenuButton helpButton = new MainMenuButton("HELP");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                moveSubScene(helpSubScene);
                namePane.setVisible(false);
            }
        });
    }

    /**
     * Creating of exitButton, on action:
     * - creating new highscore.dat file based on highscoreMap and close game
     */
    private void createExitButton() {
        MainMenuButton exitButton = new MainMenuButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                createNewHighscoreFile();
                menuStage.close();
            }
        });
    }

    /**
     * Create background:
     */
    private void createBackground() {
        Image backgroundImage = new Image("menuBackground.jpg", 1280, 790, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

        menuPane.setBackground(new Background(background));
    }

    /**
     * If sceneToHide showed now and choosen now it hides, if null showed - choosen BookSubScene depends on MainMenuButtons
     * @param subScene - choosen subscene
     * @return transition for setOnFinished dragonChooserSubScene - visible namePane
     */
    private TranslateTransition moveSubScene(BookMenuSubScene subScene) {
        TranslateTransition transition;

        if (subScene == sceneToHide) {
            transition = subScene.hideSubScene();
            sceneToHide = null;
        }
        else if (sceneToHide == null) {
            transition = subScene.showSubScene();
            sceneToHide = subScene;
        }
        else {
            for (BookMenuSubScene book : bookSubSceneList) {
                if (sceneToHide == book)
                    book.hideSubScene();
            }
            transition = subScene.showSubScene();
            sceneToHide = subScene;
        }
        return transition;
    }

    /**
     * Creating BookMenuSubScenes with their parts and adds to pane and list:
     */
    private void createSubScenes() {
        dragonChooserScene = new BookMenuSubScene();
        createPlayersNameTextField();
        createDragonsToChoose();
        createMiniBackGroundButtons();
        menuPane.getChildren().add(dragonChooserScene);
        bookSubSceneList.add(dragonChooserScene);

        scoreSubScene = new BookMenuSubScene();
        highscores = new ArrayList<>();
        namesScoreVBox = new VBox();
        scoresScoreVBox = new VBox();
        highscoresMap = new TreeMap<>(Collections.reverseOrder());
        getHighScore();
        menuPane.getChildren().add(scoreSubScene);
        bookSubSceneList.add(scoreSubScene);

        helpSubScene = new BookMenuSubScene();
        createHelpImages();
        menuPane.getChildren().add(helpSubScene);
        bookSubSceneList.add(helpSubScene);
    }

    /**
     * Create namePane with nameField for playerName and prompt text on dragonChooserScene:
     */
    private void createPlayersNameTextField() {
        namePane = new AnchorPane();
        nameField = new TextField("Choose name, dragon && map");

        setFontTextField(nameField, 33);
        nameField.setLayoutX(686);
        nameField.setLayoutY(315);
        nameField.setPrefColumnCount(14);
        nameField.setStyle("-fx-text-fill: #000000; -fx-text-alignment: center;");
        namePane.setVisible(false);

        Image backgroundImage = new Image("nameField544x61.png", 544, 61, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        nameField.setBackground(new Background(background));
        namePane.getChildren().add(nameField);
        menuPane.getChildren().add(namePane);

        nameField.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nameField.setEffect(new DropShadow());
            }
        });
        nameField.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nameField.setEffect(null);
            }
        });
        nameField.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                nameField.clear();
            }
        });
    }

    /**
     * Create dragons animation Stack Panes for player choose on dragonChooserSubScene,
     * adds it on boxes and list, setOnClicked choosenDragon die and choose:
     */
    private void createDragonsToChoose() {
        VBox box1 = new VBox();
        VBox box2 = new VBox();

        AnimationStackPane dark = new AnimationStackPane(CREATURES_NAMES.DARK);
        AnimationStackPane fenix = new AnimationStackPane(CREATURES_NAMES.FENIX);
        AnimationStackPane bone_inv = new AnimationStackPane(CREATURES_NAMES.BONE_INVERSED);
        AnimationStackPane ghost_inv = new AnimationStackPane(CREATURES_NAMES.GHOST_INVERSED);
        dragonsList.add(dark);
        dragonsList.add(fenix);
        dragonsList.add(bone_inv);
        dragonsList.add(ghost_inv);
        box1.getChildren().add(fenix);
        box1.getChildren().add(dark);
        box2.getChildren().add(bone_inv);
        box2.getChildren().add(ghost_inv);

        choosenDragon = dragonsList.get(dragonsList.size() - 3);
        box1.setSpacing(-130);
        box1.setLayoutX(55);
        box1.setLayoutY(-20);
        box2.setSpacing(-55);
        box2.setLayoutX(340);
        box2.setLayoutY(-5);

        for (AnimationStackPane dragPane : dragonsList) {
            dragPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (AnimationStackPane drag : dragonsList) {
                        if (drag != dragPane)
                            drag.plzFly();
                    }
                    dragPane.plzDie();
                    choosenDragon = dragPane;
                    soundPlayer.playDie(dragPane.getName());
                }
            });
        }
        dragonChooserScene.getPane().getChildren().add(box1);
        dragonChooserScene.getPane().getChildren().add(box2);
    }

    /**
     * Create MiniBackGroundButtons, adds it in box, pane and list:
     * setOnClicked - start New Game
     */
    private void createMiniBackGroundButtons() {
        HBox box = new HBox();

        for (BACKGROUND_IMAGES back : BACKGROUND_IMAGES.values()) {
            MiniBackGroundButton button = new MiniBackGroundButton(back);

            backGroundList.add(button);
            box.getChildren().add(button);
            button.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    soundPlayer.stopMenuSound();
                    newGame(back);
                }
            });
        }
        box.setSpacing(43);
        box.setLayoutX(100);
        box.setLayoutY(340);
        dragonChooserScene.getPane().getChildren().add(box);
    }

    /**
     * Starting New Game, getting playerName, by default - defaultName
     * @param back - chosen game background
     */
    private void newGame(BACKGROUND_IMAGES back) {
        playerName = nameField.getText();

        if (playerName.equals("") || playerName.equals("Choose name, dragon && map"))
            playerName = "defaultName";

        if (choosenDragon.getName() == CREATURES_NAMES.BONE_INVERSED) {
            GamePlayManager gameManager = new GamePlayManager(back, CREATURES_NAMES.BONE, playerName);
            gameManager.createNewGame(this);
        }
        else if (choosenDragon.getName() == CREATURES_NAMES.GHOST_INVERSED) {
            GamePlayManager gameManager = new GamePlayManager(back, CREATURES_NAMES.GHOST, playerName);
            gameManager.createNewGame(this);
        }
        else {
            GamePlayManager gameManager = new GamePlayManager(back, choosenDragon.getName(), playerName);
            gameManager.createNewGame(this);
        }
    }

    /**
     * Check if playerName exist in highscoresMap and score bigger than highscore:
     * @param score - score of last game
     */
    public void ifScoreBiggerThanHighscoreSetIt(int score) {
        this.score = score;
        boolean needed = true;

        if (highscoresMap.containsValue(playerName)) {
            for (Map.Entry map : highscoresMap.entrySet()) {
                int highscore = (int) map.getKey();
                String name = String.valueOf(map.getValue());

                if (name.equals(playerName) && (score <= highscore))
                    needed = false;
            }
            if (!needed)
                return;
        }
        setNewHighScore();
    }

    /**
     * Append write new highscore in highscores.dat file like 123:defaultName\n:
     */
    private void setNewHighScore() {
        String line = score + ":" + playerName + '\n';

        try {
            FileWriter writeFile = new FileWriter("src/main/resources/highscores/highscore.dat", true);

            writeFile.write(line);
            writeFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        getHighScore();
    }

    /**
     * Getting highscores from highscores.dat file like 123:defaultName\n:
     */
    private void getHighScore() {
        String line;

        highscores.clear();
        try {
            FileReader readFile = new FileReader("src/main/resources/highscores/highscore.dat");
            BufferedReader reader = new BufferedReader(readFile);
            while (true) {
                try {
                    if ((line = reader.readLine()) != null)
                        highscores.add(line);
                    else
                        break;
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            readFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        clearAndCreateHighscores();
    }

    /**
     * Clear old highscores and create new:
     */
    private void clearAndCreateHighscores() {
        namesScoreVBox.getChildren().clear();
        scoresScoreVBox.getChildren().clear();
        scoreSubScene.getPane().getChildren().clear();
        highscoresMap.clear();

        createHighscores();
    }

    /**
     * Create highscoresMap columns on scoresSubScene:
     */
    private void createHighscores() {
        int place = 1;

        for (String line : highscores) {
            int score = Integer.parseInt(line.split(":")[0]);
            String text = line.split(":")[1];

            highscoresMap.put(score, text);
        }
        for (Map.Entry map : highscoresMap.entrySet()) {
            int highscore = (int) map.getKey();
            String name = String.valueOf(map.getValue());

            if (name.length() > 16)
                name = name.substring(0, 16);
            Text playerName = new Text(place++ + ": " + name);
            Text playerScore = new Text(Integer.toString(highscore));

            setFontText(playerName, 18);
            setFontText(playerScore, 18);
            namesScoreVBox.getChildren().add(playerName);
            scoresScoreVBox.getChildren().add(playerScore);
            if (place == 16)
                break;
        }
        namesScoreVBox.setSpacing(5);
        namesScoreVBox.setLayoutX(95);
        namesScoreVBox.setLayoutY(16);
        scoresScoreVBox.setSpacing(5);
        scoresScoreVBox.setLayoutX(440);
        scoresScoreVBox.setLayoutY(16);
        scoreSubScene.getPane().getChildren().addAll(namesScoreVBox, scoresScoreVBox);
    }

    /**
     * Create line for write it in highscore.dat file on end game:
     */
    private void createNewHighscoreFile() {
        String line = "";
        int place = 1;

        for (Map.Entry map : highscoresMap.entrySet()) {
            String score = String.valueOf(map.getKey());
            String name = score + ":" + map.getValue() + '\n';

            line += name;
            place++;
            if (place == 16)
                break;
        }
        setHighScore(line);
    }

    /**
     * Create new highscore.dat file:
     * @param allLines - all highscores String for write it in highscore.dat file
     */
    private void setHighScore(String allLines) {
        try {
            FileWriter writeFile = new FileWriter("src/main/resources/highscores/highscore.dat");
            writeFile.write(allLines);
            writeFile.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create label, images and texts for helpSubScene:
     */
    private void createHelpImages() {
        AnchorPane labelHelpPane = new AnchorPane();
        Text textHelp = new Text("  BONUSES\n          &&\nCONTROLS");
        textHelp.setLayoutY(32);
        textHelp.setLayoutX(380);
        setFontText(textHelp, 21);
        labelHelpPane.getChildren().add(textHelp);
        helpSubScene.getPane().getChildren().add(labelHelpPane);

        VBox controls = new VBox();
        VBox bonuses = new VBox();
        for (MENU_HELP_CONTROLS control : MENU_HELP_CONTROLS.values()) {
            HBox hBox = new HBox();
            ImageView imageView = new ImageView(control.getUrlImage());
            Text text = new Text(control.getText());

            text.setWrappingWidth(160);
            setFontText(text, 21);
            hBox.setSpacing(10);
            hBox.setPadding(new Insets(10,6,10,10));
            hBox.getChildren().addAll(imageView, text);
            controls.getChildren().add(hBox);
        }
        for (MENU_HELP_BONUSES bonus : MENU_HELP_BONUSES.values()) {
            HBox hBox = new HBox();
            ImageView imageView = new ImageView(bonus.getUrlImage());
            Text text = new Text(bonus.getText());

            text.setWrappingWidth(140);
            setFontText(text, 21);
            hBox.setSpacing(10);
            hBox.setPadding(new Insets(6,6,6,6));
            hBox.getChildren().addAll(imageView, text);
            bonuses.getChildren().add(hBox);
        }
        controls.setLayoutX(325);
        bonuses.setLayoutX(90);
        controls.setLayoutY(85);
        bonuses.setLayoutY(16);
        helpSubScene.getPane().getChildren().addAll(controls, bonuses);
    }

    /**
     * Set textStyle for Text:
     * @param text - Text object
     * @param size - text size
     */
    private void setFontText(Text text, int size) {
        try {
            text.setFont(Font.loadFont(new FileInputStream(new File(textStyle)), size));
        }
        catch (FileNotFoundException e) {
            text.setFont(Font.font("Verdana", size));
        }
    }

    /**
     * Set textStyle for TextField:
     * @param text - TextField object
     * @param size - text size
     */
    private void setFontTextField(TextField text, double size) {
        try {
            text.setFont(Font.loadFont(new FileInputStream(new File(textStyle)), size));
        }
        catch (FileNotFoundException e) {
            text.setFont(Font.font("Verdana", size));
        }
    }

    /**
     * Play new game music after the end game and set on finished - menuSound
     */
    public void playNewGameMusic() {
        soundPlayer.playNewGameTheme();
    }

    /**
     * Getter for GamePlayManager
     */
    public GameSoundPlayer getSoundPlayer() {
        return soundPlayer;
    }
}
