package world.ucode.enums;

/**
 * Urls, volumes and cycleCounts of firing creatures sounds
 */
public enum FIRE_SOUNDS {
    DARK("src/main/resources/sounds/dragFire.wav",  1, 1),
    BONE("src/main/resources/sounds/boneFire.mp3", 1, 1),
    FENIX("src/main/resources/sounds/fenixFire.mp3",  1, 1),
    GHOST("src/main/resources/sounds/ghostFire.mp3", 1, 1),
    BONE_INVERSED("src/main/resources/sounds/boneFire.mp3", 1, 1),
    GHOST_INVERSED("src/main/resources/sounds/ghostFire.mp3", 1, 1),
    ARCHANGEL("src/main/resources/sounds/archFire.mp3", 0.7, 1),
    BEHEMOTH("src/main/resources/sounds/begemothFire.wav", 0.7, 1),
    CHAMPION("src/main/resources/sounds/champFire.mp3", 0.7, 1),
    PEGASUS("src/main/resources/sounds/pegasFire.mp3", 0.7, 1),
    THUNDERBIRD("src/main/resources/sounds/thunderFire.mp3", 0.7, 1),
    TROGLODYTE("src/main/resources/sounds/troglFire.mp3", 0.7, 1);

    private final String urlSound;
    private final double volume;
    private final int cycleCount;

    private FIRE_SOUNDS(String urlSound, double volume, int cycleCount) {
        this.urlSound = urlSound;
        this.volume = volume;
        this.cycleCount = cycleCount;
    }

    public String getUrl() {
        return this.urlSound;
    }

    public double getVolume() {
        return this.volume;
    }
    public int getCycleCount() {
        return this.cycleCount;
    }
}
