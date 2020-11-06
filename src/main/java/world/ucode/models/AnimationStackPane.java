package world.ucode.models;

import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.util.Duration;
import world.ucode.enums.CREATURES_NAMES;

/**
 * Stack Pane with fly, die, fire creature SpriteAnimation, parameters and game methods
 */
public class AnimationStackPane extends StackPane {
    /**
     * Radius of fire and die range actions
     */
    private final static double PLAYERS_RADIUS = 20;
    private final static double ARCHANGEL_RADIUS_DIE = 60;
    private final static double ARCHANGEL_RADIUS_FIRE = 200;
    private final static double BEHEMOTH_RADIUS_DIE = 80;
    private final static double BEHEMOTH_RADIUS_FIRE = 200;
    private final static double CHAMPION_RADIUS_DIE = 80;
    private final static double CHAMPION_RADIUS_FIRE = 200;
    private final static double PEGASUS_RADIUS_DIE = 80;
    private final static double PEGASUS_RADIUS_FIRE = 200;
    private final static double THUNDERBIRD_RADIUS_DIE = 80;
    private final static double THUNDERBIRD_RADIUS_FIRE = 200;
    private final static double TROGLODYTE_RADIUS_DIE = 80;
    private final static double TROGLODYTE_RADIUS_FIRE = 200;

    /**
     * This creature
     */
    private final CREATURES_NAMES creature;

    /**
     * Groups of Images like cartoons for SpriteAnimation
     */
    private Group flyGroup;
    private Group dieGroup;
    private Group fireGroup;

    /**
     * Animations of this creature
     */
    Animation animationFly;
    Animation animationDie;
    Animation animationFire;

    /**
     * {@value} isChoosen - true if player clicked on it
     * {@value} isFire - true if fire animation playing
     * {@value} isDie - true if die animation played once
     * {@value} rateFly - rate(speed) of fly animation
     */
    private boolean isChoosen;
    private boolean isFire;
    private boolean isDie;
    private double rateFly = 1;

    /**
     * Constructor of AnimationStackPane with creating animations and effects
     * @param currentCreature - this creature
     */
    public AnimationStackPane(CREATURES_NAMES currentCreature) {
        this.creature = currentCreature;
        createFlyAnimation(currentCreature);
        createDieAnimation(currentCreature);
        createFireAnimation(currentCreature);
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }

    /**
     * Create Fly SpriteAnimation, setVisible true
     * @param currentCreature - this creature
     */
    private void createFlyAnimation(CREATURES_NAMES currentCreature) {
        int COLUMNS_FLY = currentCreature.getFly().getCount();
        int WIDTH_FLY = currentCreature.getFly().getWidth();
        int HEIGHT_FLY = currentCreature.getFly().getHight();
        int OFFSET_FLY_X = currentCreature.getFly().getOffsetX();
        int OFFSET_FLY_Y = currentCreature.getFly().getOffsetY();
        int DURATION_FLY = currentCreature.getFly().getDuration();

        ImageView imageViewFly = new ImageView(currentCreature.getFly().getUrl());
        imageViewFly.setViewport(new Rectangle2D(0, 0, WIDTH_FLY, HEIGHT_FLY));
        animationFly = new SpriteAnimation(
                imageViewFly,
                Duration.millis(DURATION_FLY),
                COLUMNS_FLY, COLUMNS_FLY,
                OFFSET_FLY_X, OFFSET_FLY_Y,
                WIDTH_FLY, HEIGHT_FLY
        );
        animationFly.setCycleCount(Animation.INDEFINITE);
        flyGroup = new Group(imageViewFly);

        getChildren().add(flyGroup);
        animationFly.play();
    }

    /**
     * Create Die SpriteAnimation, setVisible false
     * @param currentCreature - this creature
     */
    private void createDieAnimation(CREATURES_NAMES currentCreature) {
        int COLUMNS_DIE = currentCreature.getDie().getCount();
        int WIDTH_DIE = currentCreature.getDie().getWidth();
        int HEIGHT_DIE = currentCreature.getDie().getHight();
        int OFFSET_DIE_X = currentCreature.getDie().getOffsetX();
        int OFFSET_DIE_Y = currentCreature.getDie().getOffsetY();
        int DURATION_DIE = currentCreature.getDie().getDuration();

        ImageView imageViewDie = new ImageView(currentCreature.getDie().getUrl());
        imageViewDie.setViewport(new Rectangle2D(0, 0, WIDTH_DIE, HEIGHT_DIE));
        animationDie = new SpriteAnimation(
                imageViewDie,
                Duration.millis(DURATION_DIE),
                COLUMNS_DIE, COLUMNS_DIE,
                OFFSET_DIE_X, OFFSET_DIE_Y,
                WIDTH_DIE, HEIGHT_DIE
        );
        animationDie.setCycleCount(1);
        dieGroup = new Group(imageViewDie);

        getChildren().add(dieGroup);
        dieGroup.setVisible(false);
    }

    /**
     * Create Fire SpriteAnimation, setVisible false
     * @param currentCreature - this creature
     */
    private void createFireAnimation(CREATURES_NAMES currentCreature) {
        int COLUMNS_FIRE = currentCreature.getFire().getCount();
        int WIDTH_FIRE = currentCreature.getFire().getWidth();
        int HEIGHT_FIRE = currentCreature.getFire().getHight();
        int OFFSET_FIRE_X = currentCreature.getFire().getOffsetX();
        int OFFSET_FIRE_Y = currentCreature.getFire().getOffsetY();
        int DURATION_FIRE = currentCreature.getFire().getDuration();

        ImageView imageViewFire = new ImageView(currentCreature.getFire().getUrl());
        imageViewFire.setViewport(new Rectangle2D(0, 0, WIDTH_FIRE, HEIGHT_FIRE));
        animationFire = new SpriteAnimation(
                imageViewFire,
                Duration.millis(DURATION_FIRE),
                COLUMNS_FIRE, COLUMNS_FIRE,
                OFFSET_FIRE_X, OFFSET_FIRE_Y,
                WIDTH_FIRE, HEIGHT_FIRE
        );
        animationFire.setCycleCount(1);
        fireGroup = new Group(imageViewFire);

        getChildren().add(fireGroup);
        fireGroup.setVisible(false);
    }

    /**
     * Play Die SpriteAnimation
     * @return - Animation for setOnFinished
     */
    public Animation plzDie() {
        isFire = false;
        isDie = true;
        flyGroup.setVisible(false);
        fireGroup.setVisible(false);
        dieGroup.setVisible(true);
        animationDie.play();
        animationDie.setOnFinished(finish -> isChoosen = true);
        return animationDie;
    }

    /**
     * Play Die SpriteAnimation for enemies
     */
    public void plzDieEnemy() {
        isFire = false;
        isDie = true;
        flyGroup.setVisible(false);
        fireGroup.setVisible(false);
        dieGroup.setVisible(true);
        animationDie.play();
    }

    /**
     * Play Fire SpriteAnimation
     * @return - Animation for setOnFinished
     */
    public Animation plzFire() {
        isFire = true;
        dieGroup.setVisible(false);
        flyGroup.setVisible(false);
        fireGroup.setVisible(true);
        animationFire.play();
        return animationFire;
    }

    /**
     * Play Fire SpriteAnimation for enemies
     */
    public void plzFireEnemy() {
        isFire = true;
        dieGroup.setVisible(false);
        flyGroup.setVisible(false);
        fireGroup.setVisible(true);
        animationFire.play();
    }

    /**
     * Play Fly SpriteAnimation
     */
    public void plzFly() {
        flyGroup.setVisible(true);
        dieGroup.setVisible(false);
        fireGroup.setVisible(false);
        isChoosen = false;
        isFire = false;
        isDie = false;
    }

    /**
     * Getting FallDownY for player when it damaged
     * @param enemy - enemy who fire
     * @return fallDownY - level Y to Falling Down
     */
    public double getFallDownY(CREATURES_NAMES enemy) {
        switch (enemy) {
            case ARCHANGEL:
                return 600;
            case BEHEMOTH:
                return 600;
            case CHAMPION:
                return this.getLayoutY() + 100;
            case PEGASUS:
                return this.getLayoutY() + 200;
            case THUNDERBIRD:
                return this.getLayoutY() + 300;
            case TROGLODYTE:
                return this.getLayoutY() + 50;
            default:
                return this.getLayoutY();
        }
    }

    /**
     * Getting creature name:
     */
    public CREATURES_NAMES getName() {
        return creature;
    }

    /**
     * Getting boolean isFire, true if Firing:
     */
    public boolean getIsFire() {
        return isFire;
    }

    /**
     * Getting boolean isDie, true if Alive:
     */
    public boolean getIsDie(){
        return !isDie;
    }

    /**
     * Getting Centre position X this creature:
     */
    public double getCentreX() {
        switch (creature) {
            case ARCHANGEL:
                return this.getLayoutX() + 100;
            case BEHEMOTH:
                return this.getLayoutX() + 100;
            case CHAMPION:
                return this.getLayoutX() + 100;
            case PEGASUS:
                return this.getLayoutX() + 100;
            case THUNDERBIRD:
                return this.getLayoutX() + 150;
            case TROGLODYTE:
                return this.getLayoutX() + 100;
            default:
                return this.getLayoutX() + 100;
        }
    }

    /**
     * Getting Centre position Y this creature:
     */
    public double getCentreY() {
        switch (creature) {
            case ARCHANGEL:
                return this.getLayoutY() + 100;
            case BEHEMOTH:
                return this.getLayoutY() + 100;
            case CHAMPION:
                return this.getLayoutY() + 100;
            case PEGASUS:
                return this.getLayoutY() + 100;
            case THUNDERBIRD:
                return this.getLayoutY() + 150;
            case TROGLODYTE:
                return this.getLayoutY() + 100;
            default:
                return this.getLayoutY() + 100;
        }
    }

    /**
     * Getting Radius Die this creature:
     */
    public double getRadiusDie() {
        switch (creature) {
            case ARCHANGEL:
                return ARCHANGEL_RADIUS_DIE;
            case BEHEMOTH:
                return BEHEMOTH_RADIUS_DIE;
            case CHAMPION:
                return CHAMPION_RADIUS_DIE;
            case PEGASUS:
                return PEGASUS_RADIUS_DIE;
            case THUNDERBIRD:
                return THUNDERBIRD_RADIUS_DIE;
            case TROGLODYTE:
                return TROGLODYTE_RADIUS_DIE;
            default:
                return PLAYERS_RADIUS;
        }
    }

    /**
     * Getting Radius Fire this creature:
     */
    public double getRadiusFire() {
        switch (creature) {
            case ARCHANGEL:
                return ARCHANGEL_RADIUS_FIRE;
            case BEHEMOTH:
                return BEHEMOTH_RADIUS_FIRE;
            case CHAMPION:
                return CHAMPION_RADIUS_FIRE;
            case PEGASUS:
                return PEGASUS_RADIUS_FIRE;
            case THUNDERBIRD:
                return THUNDERBIRD_RADIUS_FIRE;
            case TROGLODYTE:
                return TROGLODYTE_RADIUS_FIRE;
            default:
                return PLAYERS_RADIUS;
        }
    }

    /**
     * Setting Rate Fly this creature:
     */
    public void setRateFly(double rate) {
        if (rateFly < 2) {
            rateFly += rate;
            animationFly.setRate(rateFly);
        }
    }
}