package world.ucode.enums;

/**
 * Fire Image Urls for AnimationStackPane and theirs parameters for SpriteAnimation
 */
public enum FIRE_IMAGES {
    DARK("dragFire6000x250x250x19.png", 250, 250, 19, 0, -40, 1900),
    BONE("boneFire1500x180x180x7.png", 180, 180, 7, 20, 0, 700),
    FENIX("fenixFire3000x300x300x7.png", 295, 300, 7, -12, 40, 700),
    GHOST("ghostFire2500x200x250x9.png", 250, 200, 9, -8, -15, 900),
    BONE_INVERSED("boneFly1500x200x200x7.png", 200, 200, 7, 0, 0, 700),
    GHOST_INVERSED("ghostFire2500x200x250x9.png", 250, 200, 9, 0, 0, 900),
    ARCHANGEL("archFire2000x200x200x8.png", 200, 200, 8, 0, 0, 800),
    BEHEMOTH("behemothFire2000x200x200x10.png", 200, 200, 10, -30, 0, 1000),
    CHAMPION("champFire2000x200x200x6.png", 200, 200, 6, -20, 0, 600),
    PEGASUS("pegasFire2000x200x200x6.png", 200, 200, 6, -22, 2, 600),
    THUNDERBIRD("thunderFire3000x300x300x7.png", 300, 300, 7, -25, 0, 900),
    TROGLODYTE("trogFire2000x200x200x5.png", 200, 200, 6, -50, -10, 600);

    private final String urlImage;
    private final int width;
    private final int hight;
    private final int count;
    private final int offsetX;
    private final int offsetY;
    private final int duration;

    private FIRE_IMAGES(String urlImage, int width, int hight, int count, int offsetX, int offsetY, int duration) {
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
