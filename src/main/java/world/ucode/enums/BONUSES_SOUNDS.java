package world.ucode.enums;

/**
 * Urls, volumes and cycleCounts of bonuses sounds
 */
public enum BONUSES_SOUNDS {
    ALLDIE("src/main/resources/sounds/enemyKill.wav", 1, 1),
    EXTRASPEED("src/main/resources/sounds/extraSpeed.mp3", 1, 1),
    MAKESLOWER("src/main/resources/sounds/enemySlower.mp3", 0.8, 1),
    BONUSFIRE("src/main/resources/sounds/fireBonus.mp3", 1, 1),
    BONUSJUMP("src/main/resources/sounds/jumpBonus.wav", 1, 1),
    BONUSJUMPSTART("src/main/resources/sounds/jumpStart.mp3", 1, 1);

    private final String urlSound;
    private final double volume;
    private final int cycleCount;

    private BONUSES_SOUNDS(String urlSound, double volume, int cycleCount) {
        this.urlSound = urlSound;
        this.volume = volume;
        this.cycleCount = cycleCount;
    }
    public String getUrl() {
        return urlSound;
    }
    public double getVolume() {
        return volume;
    }
    public int getCycleCount() {
        return cycleCount;
    }
}
