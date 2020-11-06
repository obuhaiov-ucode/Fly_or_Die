package world.ucode.enums;

/**
 * Die Image Urls for AnimationStackPane and theirs parameters for SpriteAnimation
 */
public enum DIE_IMAGES {
    DARK("dragDie4000x200x250x10.png",  225, 200, 10, -52, 0, 1000),
    BONE("boneDie1500x180x175x8.png", 175, 180, 8, 30, 0, 800),
    FENIX("fenixDie3000x300x300x7.png",  300, 300, 7, -10, 30, 700),
    GHOST("ghostDie2500x200x200x8.png", 200, 200, 8, 0, 0, 800),
    BONE_INVERSED("boneDie1500x180x175x8inv.png", 175, 180, 8, 80, 0, 800),
    GHOST_INVERSED("ghostDie1622x190x200x8inv.png", 200, 190, 8, 30, 0, 800),
    ARCHANGEL("archDie2000x200x200x7.png", 200, 200, 7, 0, 0, 700),
    BEHEMOTH("behemothDie2000x200x200x7.png", 200, 200, 7, -20, 10, 700),
    CHAMPION("champDie2000x200x200x8.png", 200, 200, 7, 0, 0, 700),
    PEGASUS("pegasDie2000x200x200x9.png", 200, 200, 9, -30, 0, 900),
    THUNDERBIRD("thunderDie3000x300x300x7.png", 300, 300, 7, -25, 10, 700),
    TROGLODYTE("trogDie2000x200x200x8.png", 200, 200, 8, -60, 12, 500);

    private final String urlImage;
    private final int width;
    private final int hight;
    private final int count;
    private final int offsetX;
    private final int offsetY;
    private final int duration;

    private DIE_IMAGES(String urlImage, int width, int hight, int count, int offsetX, int offsetY, int duration) {
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
