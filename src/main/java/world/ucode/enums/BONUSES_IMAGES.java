package world.ucode.enums;
/**
 * Image Url for bonuses and mini icon of it
 */
public enum BONUSES_IMAGES {
    ALLDIE("swordOfDestraction58x64.png", "swordOfDestraction58x64.png"),
    EXTRASPEED("speedBoots58x64.png", "swordOfDestraction58x64.png"),
    MAKESLOWER("clockSlow58x64.png", "swordOfDestraction58x64.png"),
    BONUSFIRE("bonusFire58x64.png", "bonusFire29x32.png"),
    BONUSJUMP("bonusJump58x64.png", "bonusJump29x32.png");

    private final String urlImage;
    private final String urlImageMini;

    private BONUSES_IMAGES(String urlImage, String urlImageMini) {
        this.urlImage = urlImage;
        this.urlImageMini = urlImageMini;
    }
    public String getUrlImage() {
        return urlImage;
    }
    public String getUrlImageMini() {
        return urlImageMini;
    }
}
