package world.ucode.controllers;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import world.ucode.enums.BONUSES_IMAGES;
import world.ucode.enums.CREATURES_NAMES;
import world.ucode.enums.BACKGROUND_IMAGES;
import world.ucode.models.*;

import java.util.*;

/**
 * Game play scene manager
 * @author Alexey Buhaiov aka obuhaiov
 * @version 0.0.1
 */
public class GamePlayManager {
    /**
     * Fields with menu object, player name and game Stage, Pane and Scene
     */
    private GameMenuManager menuStage;
    private final String playerName;
    private final Stage gameStage;
    private final AnchorPane gamePane;
    private final Scene gameScene;

    /**
     * Fields with scene properties and background variables
     */
    private static final int gameWidth = 1280;
    private static final int gameHeight = 790;
    private AnchorPane anchorPane1;
    private AnchorPane anchorPane2;
    private final String backgroundImage;
    private final int backgroundImageWidth;

    /**
     * Fields with creature name, list with soundPlayers, random generator, game timer and score label
     */
    private final CREATURES_NAMES playerCreatureName;
    private GameSoundPlayer soundPlayer;
    private Random randomPositionGenerator;
    private AnimationTimer gameTimer;
    private ScoreLabel scoreLabel;

    /**
     * Fields with boolean states: pressed keys, available bonuses
     * {@value} isJumpStart true when player press jump
     * {@value} isJumpPlay true when player jumping
     * {@value} isFallDown true when player damaged and falling down
     * {@value} isEndGame true when player die and game over
     */
    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private boolean isUpKeyPressed;
    private boolean isDownKeyPressed;
    private boolean fireBonus = false;
    private boolean jumpBonus = false;
    private boolean isJumpStart = false;
    private boolean isJumpPlay = false;
    private boolean isFallDown = false;
    private boolean isEndGame = false;

    /**
     * Player`s parameters: game score
     * {@value} playerSpeed - speed of moveUp and moveDown player
     * {@value} playerAngle - Up or Down angle when player move
     * {@value} scoreJumpTo - when it equals score - jump finish
     * {@value} enemySpeed - all enemies speed
     * {@value} gameSpeed - speed of background and bonuses
     * {@value} playerLayoutY - player`s position Y
     * {@value} playerFallDownY - position Y where jump ends
     */
    private int score = 0;
    private int playerSpeed = 0;
    private int playerAngle = 0;
    private int scoreJumpTo = 0;
    private int enemySpeed = 8;
    private double gameSpeed = 8;
    private double playerLayoutY;
    private double playerFallDownY;

    /**
     * Stack Panes with animations:
     * {@value} player - player`s Stack Pane
     * {@value} enemyList - all enemy List
     * {@value} archangel and other - enemies
     */
    private AnimationStackPane player;
    List<AnimationStackPane> enemyList;
    private AnimationStackPane archangel;
    private AnimationStackPane behemoth;
    private AnimationStackPane champion;
    private AnimationStackPane pegasus;
    private AnimationStackPane thunderbird;
    private AnimationStackPane troglodyte;

    /**
     * Bonuses images:
     * {@value} bonusesList - all bonuses list
     * {@value} extraSpeedImage - bonus which increase playerSpeed
     * {@value} enemySlowerImage - bonus which decrease enemySpeed
     * {@value} dieEnemyImage - bonus starting enemies dying
     * {@value} bonusFireImage - makes bonusFire true
     * {@value} bonusJumpImage - makes bonusJump true
     * {@value} bonusFireMiniImage - icon visible when bonusFire true
     * {@value} bonusJumpMiniImage - icon visible when bonusJump true
     */
    List<ImageView> bonusesList;
    private ImageView extraSpeedImage;
    private ImageView enemySlowerImage;
    private ImageView dieEnemyImage;
    private ImageView bonusFireImage;
    private ImageView bonusJumpImage;
    private ImageView bonusFireMiniImage;
    private ImageView bonusJumpMiniImage;

    /**
     * Constructor of game play manager:
     * @param back - choosen background image
     * @param choosenCreature - choosen creature
     * @param player - player name
     */
    public GamePlayManager(BACKGROUND_IMAGES back, CREATURES_NAMES choosenCreature, String player) {
        backgroundImage = back.getUrlGameBack();
        backgroundImageWidth = back.getWidth();
        playerCreatureName = choosenCreature;
        playerName = player;

        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, gameWidth, gameHeight);
        gameStage = new Stage();
        gameStage.setTitle("Fly or Die");
        gameStage.setScene(gameScene);
    }

    /**
     * Creating new game:
     * @param view - object of menu manager
     */
    public void createNewGame(GameMenuManager view) {
        randomPositionGenerator = new Random();
        menuStage = view;
        soundPlayer = view.getSoundPlayer();
        menuStage.getMenuStage().hide();

        createKeyListeners();
        createBackground();
        createBonuses();
        createEnemies();
        createPlayer();
        createScoreBar();
        createSoundPlayer();
        createGameLoop();

        gameStage.show();
    }

    /**
     * Creating events for pressed keys:
     */
    private void createKeyListeners() {
        gameScene.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.LEFT)) {
                    isLeftKeyPressed = true;
                }
                if (event.getCode().equals(KeyCode.RIGHT)) {
                    isRightKeyPressed = true;
                }
                if (event.getCode().equals(KeyCode.SPACE) || event.getCode().equals(KeyCode.UP)) {
                    isUpKeyPressed = true;
                }
                if (event.getCode().equals(KeyCode.CONTROL) || event.getCode().equals(KeyCode.DOWN)) {
                    isDownKeyPressed = true;
                }
            }
        });
        gameScene.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.SPACE) || event.getCode().equals(KeyCode.LEFT)) {
                    isLeftKeyPressed = false;
                }
                if (event.getCode().equals(KeyCode.RIGHT)) {
                    isRightKeyPressed = false;
                }
                if (event.getCode().equals(KeyCode.SPACE) || event.getCode().equals(KeyCode.UP)) {
                    isUpKeyPressed = false;
                }
                if (event.getCode().equals(KeyCode.CONTROL) || event.getCode().equals(KeyCode.DOWN)) {
                    isDownKeyPressed = false;
                }
            }
        });
    }

    /**
     * Creating game background - two panes that move endlessly and changing each other:
     */
    private void createBackground() {
        anchorPane1 = new AnchorPane();
        anchorPane2 = new AnchorPane();
        ImageView backgroundImage1 = new ImageView(backgroundImage);
        ImageView backgroundImage2 = new ImageView(backgroundImage);
        anchorPane1.getChildren().add(backgroundImage1);
        anchorPane2.getChildren().add(backgroundImage2);
        anchorPane2.setLayoutX(backgroundImageWidth);
        anchorPane1.setLayoutY(-80);
        anchorPane2.setLayoutY(-80);
        gamePane.getChildren().addAll(anchorPane1, anchorPane2);
    }

    /**
     * Creating bonuses images and their list:
     */
    private void createBonuses() {
        bonusesList = new ArrayList<>();
        extraSpeedImage = new ImageView(BONUSES_IMAGES.EXTRASPEED.getUrlImage());
        enemySlowerImage = new ImageView(BONUSES_IMAGES.MAKESLOWER.getUrlImage());
        dieEnemyImage = new ImageView(BONUSES_IMAGES.ALLDIE.getUrlImage());
        bonusFireImage = new ImageView(BONUSES_IMAGES.BONUSFIRE.getUrlImage());
        bonusJumpImage = new ImageView(BONUSES_IMAGES.BONUSJUMP.getUrlImage());

        bonusesAddAndSetPosition(extraSpeedImage);
        bonusesAddAndSetPosition(enemySlowerImage);
        bonusesAddAndSetPosition(dieEnemyImage);
        bonusesAddAndSetPosition(bonusFireImage);
        bonusesAddAndSetPosition(bonusJumpImage);
    }

    /**
     * Adds bonuses to list and game pane, set their position:
     * @param bonusImage - bonus image
     */
    private void bonusesAddAndSetPosition(ImageView bonusImage) {
        bonusesList.add(bonusImage);
        gamePane.getChildren().add(bonusImage);
        setNewBonusPosition(bonusImage);
    }

    /**
     * Sets new position for bonus image on game pane:
     * @param imageView - bonus image
     */
    private void setNewBonusPosition(ImageView imageView) {
        imageView.setLayoutX(randomPositionGenerator.nextInt(16000) + 1300);
        imageView.setLayoutY(randomPositionGenerator.nextInt(600) + 50);
    }

    /**
     * Create enemies Stack Panes with their list:
     */
    private void createEnemies() {
        enemyList = new ArrayList<>();
        archangel = new AnimationStackPane(CREATURES_NAMES.ARCHANGEL);
        behemoth = new AnimationStackPane(CREATURES_NAMES.BEHEMOTH);
        champion = new AnimationStackPane(CREATURES_NAMES.CHAMPION);
        pegasus = new AnimationStackPane(CREATURES_NAMES.PEGASUS);
        thunderbird = new AnimationStackPane(CREATURES_NAMES.THUNDERBIRD);
        troglodyte = new AnimationStackPane(CREATURES_NAMES.TROGLODYTE);

        enemyAddAndSetPosition(archangel);
        enemyAddAndSetPosition(behemoth);
        enemyAddAndSetPosition(champion);
        enemyAddAndSetPosition(pegasus);
        enemyAddAndSetPosition(thunderbird);
        enemyAddAndSetPosition(troglodyte);
    }

    /**
     * Adds enemies to list and game pane:
     * @param enemy - enemy stack pane with animations
     */
    private void enemyAddAndSetPosition(AnimationStackPane enemy) {
        enemyList.add(enemy);
        gamePane.getChildren().add(enemy);
        setNewEnemyPosition(enemy);
    }

    /**
     * Set new position for enemies on game pane:
     * @param enemy - enemy stack pane with animations
     */
    private void setNewEnemyPosition(AnimationStackPane enemy) {
        switch (enemy.getName()) {
            case ARCHANGEL:
                enemy.setLayoutX(randomPositionGenerator.nextInt(2000) + 1300);
                enemy.setLayoutY(randomPositionGenerator.nextInt(650) - 20);
                enemy.plzFly();
                break;
            case BEHEMOTH:
                enemy.setLayoutX(randomPositionGenerator.nextInt(1000) + 1300);
                enemy.setLayoutY(randomPositionGenerator.nextInt(100) + 450);
                enemy.plzFly();
                break;
            case CHAMPION:
                enemy.setLayoutX(randomPositionGenerator.nextInt(1500) + 1300);
                enemy.setLayoutY(randomPositionGenerator.nextInt(190) + 450);
                enemy.plzFly();
                break;
            case PEGASUS:
                enemy.setLayoutX(randomPositionGenerator.nextInt(1500) + 1300);
                enemy.setLayoutY(randomPositionGenerator.nextInt(450) - 30);
                enemy.plzFly();
                break;
            case THUNDERBIRD:
                enemy.setLayoutX(randomPositionGenerator.nextInt(2000) + 1300);
                enemy.setLayoutY(randomPositionGenerator.nextInt(310) - 60);
                enemy.plzFly();
                break;
            case TROGLODYTE:
                enemy.setLayoutX(randomPositionGenerator.nextInt(1000) + 1300);
                enemy.setLayoutY(randomPositionGenerator.nextInt(100) + 550);
                enemy.plzFly();
                break;
        }
    }

    /**
     * Create player`s Stack Pane with animations:
     */
    public void createPlayer() {
        player = new AnimationStackPane(playerCreatureName);

        player.setLayoutX(120);
        player.setLayoutY(550);
        gamePane.getChildren().add(player);
    }

    /**
     * Create score bar with mini icons on top right of game pane:
     */
    private void createScoreBar() {
        scoreLabel = new ScoreLabel(Integer.toString(score));
        scoreLabel.setLayoutX(675);
        scoreLabel.setLayoutY(0);
        gamePane.getChildren().add(scoreLabel);

        HBox bonusMiniIcons = new HBox();
        bonusMiniIcons.setSpacing(12);
        bonusMiniIcons.setLayoutX(1194);
        bonusMiniIcons.setLayoutY(44);
        bonusFireMiniImage = new ImageView(BONUSES_IMAGES.BONUSFIRE.getUrlImageMini());
        bonusJumpMiniImage = new ImageView(BONUSES_IMAGES.BONUSJUMP.getUrlImageMini());
        bonusFireMiniImage.setVisible(false);
        bonusJumpMiniImage.setVisible(false);
        bonusMiniIcons.getChildren().addAll(bonusJumpMiniImage, bonusFireMiniImage);
        gamePane.getChildren().add(bonusMiniIcons);
    }

    /**
     * Creating list of sound players which will play on game background randomly
     */
    private void createSoundPlayer() {
        soundPlayer.playGameTheme(randomPositionGenerator.nextInt(5) + 1);
    }

    /**
     * Creating and running game timer:
     */
    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateScoreAndGetPlayersLayoutY();
                checkUpAndDownPressedKey();
                checkLeftAndRightPressedKey();
                checkIfPlayerJump();
                checkIfPlayerFallDown();
                checkIfEndGame();
                checkIfElementsAreBehindPlayerAndRelocate();
                checkIfElementsCollide();
                moveGameElements();
                moveBackground();
            }
        };
        gameTimer.start();
    }

    /**
     * Updating score, enemySpeed and playerLayoutY:
     */
    private void updateScoreAndGetPlayersLayoutY() {
        if (isJumpPlay)
            score += 6;
        score++;
        if (score % 1000 == 0) {
            enemySpeed++;
            player.setRateFly(0.05);
        }
        scoreLabel.setText(Integer.toString(score));
        playerLayoutY = player.getLayoutY();
    }

    /**
     * Check if pressed Up or Down arrow key:
     */
    private void checkUpAndDownPressedKey() {
        if (isUpKeyPressed && !isDownKeyPressed && !isFallDown) {
            if (playerLayoutY > -30) {
                player.setLayoutY(playerLayoutY - 7 - playerSpeed);
                if (playerAngle > -30 && playerAngle < -20)
                    playerAngle -= 2;
                else if (playerAngle >= -20)
                    playerAngle -= 3;
                player.setRotate(playerAngle);
            }
        }
        if (!isUpKeyPressed && isDownKeyPressed && !isFallDown) {
            if (playerLayoutY < 600) {
                player.setLayoutY(playerLayoutY + 10 + playerSpeed);
                if (playerAngle < 40 && playerAngle > 20)
                    playerAngle += 3;
                else if (playerAngle <= 20 && playerAngle > 10)
                    playerAngle += 5;
                else if (playerAngle <= 10)
                    playerAngle += 10;
                player.setRotate(playerAngle);
            }
        }
        if ((!isUpKeyPressed && !isDownKeyPressed) || (isUpKeyPressed && isDownKeyPressed)) {
            if (playerAngle > 10)
                playerAngle -= 3;
            else if (playerAngle > 0)
                playerAngle -= 10;
            if (playerAngle < -10)
                playerAngle += 3;
            else if (playerAngle < 0)
                playerAngle += 10;
            player.setRotate(playerAngle);
        }
    }

    /**
     * Check if pressed Left or Right arrow key:
     */
    private void checkLeftAndRightPressedKey() {
        if (isLeftKeyPressed && !isFallDown && jumpBonus && !isJumpPlay) {
            soundPlayer.playJumpStart();
            jumpBonus = false;
            isJumpStart = true;
            bonusJumpMiniImage.setVisible(false);
            if (fireBonus)
                player.setEffect(new Bloom());
            else
                player.setEffect(null);
        }
        if (isRightKeyPressed && !isFallDown && fireBonus) {
            soundPlayer.playFire(player.getName());
            fireBonus = false;
            bonusFireMiniImage.setVisible(false);
            if (jumpBonus)
                player.setEffect(new BoxBlur());
            else
                player.setEffect(null);
            Animation playerFireAnimation = player.plzFire();
            playerFireAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    player.plzFly();
                }
            });
        }
    }

    /**
     * Check if animation Jump start or end:
     */
    private void checkIfPlayerJump() {
        if (isJumpStart && !isJumpPlay) {
            scoreJumpTo = score + 1000;
            isJumpStart = false;
            isJumpPlay = true;
            enemySpeed += 30;
            player.setRateFly(0.5);
            gameSpeed += 10;
            playerSpeed += 10;
        }
        if (isJumpPlay && scoreJumpTo <= score) {
            soundPlayer.playFire(player.getName());
            player.setRateFly(-0.5);
            isJumpPlay = false;
            enemySpeed -= 30;
            gameSpeed -= 10;
            playerSpeed -= 10;
            Animation playerFireAnimation = player.plzFire();
            playerFireAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    player.plzFly();
                }
            });
        }
    }

    /**
     * Check if player hurt and fall down:
     */
    private void checkIfPlayerFallDown() {
        if (isFallDown) {
            if (playerFallDownY - playerLayoutY > 400)
                player.setLayoutY(playerLayoutY + 30);
            else if (playerFallDownY - playerLayoutY > 200)
                player.setLayoutY(playerLayoutY + 20);
            else
                player.setLayoutY(playerLayoutY + 10);
            if (playerLayoutY >= playerFallDownY) {
                player.setLayoutY(playerFallDownY);
                isFallDown = false;
            }
        }
    }

    /**
     * Check if game is over:
     * If isEndGame true player`s die, all enemy fire,
     * pause, set new highscore and play game over scene
     */
    private void checkIfEndGame() {
        if (isEndGame) {
            Animation endAnimation = player.plzDie();

            gameTimer.stop();
            soundPlayer.stopGameTheme();
            for (AnimationStackPane enemy : enemyList)
                enemy.plzFireEnemy();
            soundPlayer.playEndGame();
            endAnimation.setOnFinished(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    menuStage.ifScoreBiggerThanHighscoreSetIt(score);
                    gameStage.close();

                    GameOverManager over = new GameOverManager(playerName, score);
                    over.playMediaAndShowMenu(menuStage);
                }
            });
        }
    }

    /**
     * If game elements behind player than relocate them in new random position:
     */
    private void checkIfElementsAreBehindPlayerAndRelocate() {
        for (AnimationStackPane enemy : enemyList) {
            if (enemy.getLayoutX() < -200)
                setNewEnemyPosition(enemy);
        }
        for (ImageView imageView : bonusesList) {
            if (imageView.getLayoutX() < -200)
                setNewBonusPosition(imageView);
        }
    }

    /**
     * Checking if game elements collide:
     */
    private void checkIfElementsCollide() {
        if (playerLayoutY > 650)
            isEndGame = true;
        if (!isJumpPlay) {
            for (AnimationStackPane enemy : enemyList)
                checkIfPlayerCollideWithEnemy(enemy);
        }
        for (ImageView bonus : bonusesList) {
            if (player.getRadiusDie() + 40 > calculateDistance
                    (player.getCentreX(), bonus.getLayoutX() + 20,
                            player.getCentreY(), bonus.getLayoutY() + 20))
                checkIfPlayerCollideWithBonuses(bonus);
        }
    }

    /**
     * If player collide with enemy and fire - enemy dying,
     * without fire player hurt and fall down,
     * if player position under 500 points - it may be end of game
     * @param enemy - enemy stack pane with animations
     */
    private void checkIfPlayerCollideWithEnemy(AnimationStackPane enemy) {
        if (player.getRadiusDie() + enemy.getRadiusDie() > calculateDistance
                (player.getCentreX(), enemy.getCentreX(), player.getCentreY(), enemy.getCentreY())) {
            if (player.getIsFire()) {
                enemy.plzDieEnemy();
                soundPlayer.playDie(enemy.getName());
            }
            else if (enemy.getIsDie()) {
                if (playerLayoutY > 600
                        || (playerLayoutY > 550 && enemy.getName() == CREATURES_NAMES.TROGLODYTE)
                        || (playerLayoutY > 500 && (enemy.getName() == CREATURES_NAMES.CHAMPION
                        || enemy.getName() == CREATURES_NAMES.ARCHANGEL
                        || enemy.getName() == CREATURES_NAMES.BEHEMOTH)))
                    isEndGame = true;
                else {
                    isFallDown = true;
                    playerFallDownY = player.getFallDownY(enemy.getName());
                }
            }
        }
        else if (player.getRadiusFire() + enemy.getRadiusFire()
                > calculateDistance(player.getCentreX(), enemy.getCentreX(),
                player.getCentreY(), enemy.getCentreY())) {
            if (enemy.getIsDie()) {
                enemy.plzFireEnemy();
                soundPlayer.playFire(enemy.getName());
            }
            if (player.getIsFire()) {
                enemy.plzDieEnemy();
                soundPlayer.playDie(enemy.getName());
            }
        }
    }

    /**
     * Check if player collide with bonuses:
     * @param bonus - bonus image
     */
    private void checkIfPlayerCollideWithBonuses(ImageView bonus) {
        if (bonus == extraSpeedImage) {
            soundPlayer.playExtraSpeed();
            playerSpeed++;
            setNewBonusPosition(bonus);
        }
        if (bonus == enemySlowerImage) {
            soundPlayer.playMakeSlower();
            if (enemySpeed > gameSpeed)
                enemySpeed--;
            setNewBonusPosition(bonus);
        }
        if (bonus == dieEnemyImage) {
            soundPlayer.playAllDie();
            for (AnimationStackPane enemy : enemyList) {
                Animation enemyDieAnimation = enemy.plzDie();
                enemyDieAnimation.setOnFinished(finish -> setNewEnemyPosition(enemy));
            }
            setNewBonusPosition(bonus);
        }
        if (bonus == bonusFireImage) {
            soundPlayer.playBonusFire();
            fireBonus = true;
            bonusFireMiniImage.setVisible(true);
            player.setEffect(new Bloom());
            setNewBonusPosition(bonus);
        }
        if (bonus == bonusJumpImage) {
            soundPlayer.playBonusJump();
            jumpBonus = true;
            bonusJumpMiniImage.setVisible(true);
            player.setEffect(new BoxBlur());
            setNewBonusPosition(bonus);
        }
    }

    /**
     * Calculate distance between player and enemies or bonuses:
     * @param x1 - centre X of players position
     * @param x2 - centre X of enemy or bonus position
     * @param y1 - centre Y of players position
     * @param y2 - centre Y of enemy or bonus position
     * @return distance between centres of animations
     */
    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }

    /**
     * Move enemies and bonuses in left side with different speed:
     */
    private void moveGameElements() {
        archangel.setLayoutX(archangel.getLayoutX() - 6 - enemySpeed);
        behemoth.setLayoutX(behemoth.getLayoutX() - 2 - enemySpeed);
        champion.setLayoutX(champion.getLayoutX() - 3 - enemySpeed);
        pegasus.setLayoutX(pegasus.getLayoutX() - 4 - enemySpeed);
        thunderbird.setLayoutX(thunderbird.getLayoutX() - 5 - enemySpeed);
        troglodyte.setLayoutX(troglodyte.getLayoutX() - 1 - enemySpeed);

        for (ImageView imageView : bonusesList)
            imageView.setLayoutX(imageView.getLayoutX() - gameSpeed);
    }

    /**
     * Move background or relocate if pane behind player:
     */
    private void moveBackground() {
        if (anchorPane1.getLayoutX() - gameSpeed > -backgroundImageWidth - 100)
            anchorPane1.setLayoutX(anchorPane1.getLayoutX() - gameSpeed);
        else
            anchorPane1.setLayoutX(anchorPane2.getLayoutX() + backgroundImageWidth);
        if (anchorPane2.getLayoutX() - gameSpeed > -backgroundImageWidth - 100)
            anchorPane2.setLayoutX(anchorPane2.getLayoutX() - gameSpeed);
        else
            anchorPane2.setLayoutX(anchorPane1.getLayoutX() + backgroundImageWidth);
    }
}
