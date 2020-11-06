package world.ucode.enums;

/**
 * Fly Image Urls for AnimationStackPane and theirs parameters for SpriteAnimation
 */
public enum FLY_IMAGES {
    DARK("dragFly1200x200x200x5.png", 260, 200, 4, 240, 0, 650),
    BONE("boneFly1500x200x200x7.png", 200, 200, 7, 0, 0, 700),
    FENIX("fenixFly2000x200x200x7.png", 200, 200, 7, -15, 30, 700),
    GHOST("ghostFly2500x200x250x7.png", 250, 200, 7, 0, 0, 700),
    BONE_INVERSED("boneFly1500x200x200x7inv.png", 200, 200, 7, 110, 0, 700),
    GHOST_INVERSED("ghostFly1700x200x250x7inv.png", 250, 200, 7, -20, 0, 700),
    ARCHANGEL("archFly2000x200x200x9.png", 200, 200, 8, 195, 0, 800),
    BEHEMOTH("behemothFly2500x200x200x11.png", 200, 200, 11, 0, 0, 1100),
    CHAMPION("champFly3200x200x200x16.png", 200, 200, 16, 0, 0, 1600),
    PEGASUS("pegasFly2000x200x200x6.png", 200, 200, 6, -30, 0, 600),
    THUNDERBIRD("thunderFly2000x300x300x5.png", 300, 300, 5, -25, 0, 500),
    TROGLODYTE("trogFly2000x200x200x7.png", 200, 200, 7, -50, 0, 700);

    private final String urlImage;
    private final int width;
    private final int hight;
    private final int count;
    private final int offsetX;
    private final int offsetY;
    private final int duration;

    private FLY_IMAGES(String urlImage, int width, int hight, int count, int offsetX, int offsetY, int duration) {
        this.urlImage = urlImage;
        this.width = width;
        this.hight = hight;
        this.count = count;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.duration = duration;
    }

    public String getUrl() {
        return this.urlImage;
    }

    public int getWidth() {
        return this.width;
    }
    public int getHight() {
        return this.hight;
    }
    public int getCount() {
        return this.count;
    }
    public int getOffsetX() {
        return this.offsetX;
    }
    public int getOffsetY() {
        return this.offsetY;
    }
    public int getDuration() {
        return this.duration;
    }
}
