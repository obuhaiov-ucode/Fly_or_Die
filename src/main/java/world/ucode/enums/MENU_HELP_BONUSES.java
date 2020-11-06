package world.ucode.enums;

/**
 * Menu HelpSubScene Images Urls with text description
 */
public enum MENU_HELP_BONUSES {
    EXTRASPEED("speedBoots58x64.png", "You will\nmove faster"),
    MAKESLOWER("clockSlow58x64.png", "Slowing\nenemy speed"),
    ALLDIE("swordOfDestraction58x64.png", "All enemy\ndying"),
    FIRE("bonusFire58x64.png", "Fire reloading"),
    JUMP("bonusJump58x64.png", "Jump reloading");

    private final String urlImage;
    private final String text;

    private MENU_HELP_BONUSES(String urlImage, String text) {
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
