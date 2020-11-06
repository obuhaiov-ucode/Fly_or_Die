package world.ucode.enums;

/**
 * Menu HelpSubScene Controls Images Urls with their text description
 */
public enum MENU_HELP_CONTROLS {
    UP("arrowUp60x60.png", "ArrowUp\nfor move up"),
    DOWN("arrowDown60x60.png", "ArrowDown\nfor move down"),
    RIGHT("arrowRight60x60.png", "Right for fire\nif you ready"),
    LEFT("arrowLeft60x60.png", "Left for jump\nif you ready");

    private final String urlImage;
    private final String text;

    private MENU_HELP_CONTROLS(String urlImage, String text) {
        this.urlImage = urlImage;
        this.text = text;
    }

    public String getUrlImage() {
        return urlImage;
    }
    public String getText() {
        return text;
    }
 }
