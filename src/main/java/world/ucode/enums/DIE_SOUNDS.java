package world.ucode.enums;

/**
 * Urls, volumes and cycleCounts of dying creatures sounds
 */
public enum DIE_SOUNDS {
    DARK("src/main/resources/sounds/dragDie.mp3",  1, 1),
    BONE("src/main/resources/sounds/boneDie.mp3", 1, 1),
    FENIX("src/main/resources/sounds/fenixDie.mp3",  1, 1),
    GHOST("src/main/resources/sounds/ghostDie.mp3", 1, 1),
    BONE_INVERSED("src/main/resources/sounds/boneDie.mp3", 1, 1),
    GHOST_INVERSED("src/main/resources/sounds/ghostDie.mp3", 1, 1),
    ARCHANGEL("src/main/resources/sounds/archDie.mp3", 0.7, 1),
    BEHEMOTH("src/main/resources/sounds/begemothDie.mp3", 0.7, 1),
    CHAMPION("src/main/resources/sounds/champDie.mp3", 0.7, 1),
    PEGASUS("src/main/resources/sounds/pegasDie.mp3", 0.7, 1),
    THUNDERBIRD("src/main/resources/sounds/thunderDie.mp3", 0.7, 1),
    TROGLODYTE("src/main/resources/sounds/troglDie.mp3", 0.7, 1);

    private final String urlSound;
    private final double volume;
    private final int cycleCount;

    private DIE_SOUNDS(String urlSound, double volume, int cycleCount) {
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
