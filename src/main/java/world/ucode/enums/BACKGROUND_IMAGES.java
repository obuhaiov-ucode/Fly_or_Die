package world.ucode.enums;

/**
 * Image Urls for MiniBackgroundButton and game background with it`s width
 */
public enum BACKGROUND_IMAGES {
    GREEN("miniBackGroundGreen80x50.png", "gameBackGroundGreen1600x1112.png", 1582),
    ROCK("miniBackGroundRock80x50.png", "gameBackGroundRock1392x1112.png", 1374),
    BROWN("miniBackGroundBrown80x50.png", "gameBackGroundBrown1600x1112.png", 1582),
    WINTER("miniBackGroundWinter80x50.png", "gameBackGroundWinter1368x1112.png", 1350);

    private final String urlMiniBack;
    private final String urlGameBack;

    private final int width;

    private BACKGROUND_IMAGES(String urlMiniBack, String urlGameBack, int width) {
        this.urlMiniBack = urlMiniBack;
        this.urlGameBack = urlGameBack;
        this.width = width;
    }

    public String getUrlMiniBack() {
        return this.urlMiniBack;
    }
    public String getUrlGameBack() {
        return this.urlGameBack;
    }

    public int getWidth() {
        return this.width;
    }
}
