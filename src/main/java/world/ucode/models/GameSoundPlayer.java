package world.ucode.models;

import world.ucode.enums.BONUSES_SOUNDS;
import world.ucode.enums.CREATURES_NAMES;
import world.ucode.enums.DIE_SOUNDS;
import world.ucode.enums.FIRE_SOUNDS;

/**
 * Class for playing all sounds in game
 */
public class GameSoundPlayer {
    /**
     * SoundPlayers with music for main menu
     */
    private final SoundPlayer mainThemeSoundPlayer;
    private final SoundPlayer newGameThemeSoundPlayer;

    /**
     * SoundPlayers with music for play scene
     */
    private final SoundPlayer playBack1;
    private final SoundPlayer playBack2;
    private final SoundPlayer playBack3;
    private final SoundPlayer playBack4;
    private final SoundPlayer playBack5;
    private final SoundPlayer endGame;

    /**
     * SoundPlayers with music of bonus effects
     */
    private SoundPlayer allDiePlayer;
    private SoundPlayer extraSpeedPlayer;
    private SoundPlayer makeSlowerPlayer;
    private SoundPlayer bonusFirePlayer;
    private SoundPlayer bonusJumpPlayer;
    private SoundPlayer jumpStartPlayer;

    /**
     * SoundPlayers with music of firing creature
     */
    private SoundPlayer darkFirePlayer;
    private SoundPlayer fenixFirePlayer;
    private SoundPlayer boneFirePlayer;
    private SoundPlayer ghostFirePlayer;
    private SoundPlayer archFirePlayer;
    private SoundPlayer begemFirePlayer;
    private SoundPlayer champFirePlayer;
    private SoundPlayer pegasFirePlayer;
    private SoundPlayer thunderFirePlayer;
    private SoundPlayer troglFirePlayer;

    /**
     * SoundPlayers with music of dying creature
     */
    private SoundPlayer darkDiePlayer;
    private SoundPlayer fenixDiePlayer;
    private SoundPlayer boneDiePlayer;
    private SoundPlayer ghostDiePlayer;
    private SoundPlayer archDiePlayer;
    private SoundPlayer begemDiePlayer;
    private SoundPlayer champDiePlayer;
    private SoundPlayer pegasDiePlayer;
    private SoundPlayer thunderDiePlayer;
    private SoundPlayer troglDiePlayer;

    /**
     * Constructor with creating all SoundPlayers
     */
    public GameSoundPlayer() {
        mainThemeSoundPlayer = new SoundPlayer("src/main/resources/sounds/menu.mp3",  0.7, -1);
        newGameThemeSoundPlayer = new SoundPlayer("src/main/resources/sounds/newMenu.mp3",  1, 1);

        playBack1 = new SoundPlayer("src/main/resources/sounds/play1.mp3",  1, -1);
        playBack2 = new SoundPlayer("src/main/resources/sounds/play2.mp3",  1, -1);
        playBack3 = new SoundPlayer("src/main/resources/sounds/play3.mp3",  1, -1);
        playBack4 = new SoundPlayer("src/main/resources/sounds/play4.mp3",  1, -1);
        playBack5 = new SoundPlayer("src/main/resources/sounds/play5.mp3",  1, -1);
        endGame = new SoundPlayer("src/main/resources/sounds/endGame.mp3",  1, 1);

        for (BONUSES_SOUNDS sound : BONUSES_SOUNDS.values()) {
            switch (sound) {
                case ALLDIE:
                    allDiePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case EXTRASPEED:
                    extraSpeedPlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case MAKESLOWER:
                    makeSlowerPlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case BONUSFIRE:
                    bonusFirePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case BONUSJUMP:
                    bonusJumpPlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case BONUSJUMPSTART:
                    jumpStartPlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
            }
        }
        for (FIRE_SOUNDS sound : FIRE_SOUNDS.values()) {
            switch (sound) {
                case DARK:
                    darkFirePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case FENIX:
                    fenixFirePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case BONE:
                    boneFirePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case GHOST:
                    ghostFirePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case ARCHANGEL:
                    archFirePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case BEHEMOTH:
                    begemFirePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case CHAMPION:
                    champFirePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case PEGASUS:
                    pegasFirePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case THUNDERBIRD:
                    thunderFirePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case TROGLODYTE:
                    troglFirePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
            }
        }
        for (DIE_SOUNDS sound : DIE_SOUNDS.values()) {
            switch (sound) {
                case DARK:
                    darkDiePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case FENIX:
                    fenixDiePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case BONE:
                    boneDiePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case GHOST:
                    ghostDiePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case ARCHANGEL:
                    archDiePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case BEHEMOTH:
                    begemDiePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case CHAMPION:
                    champDiePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case PEGASUS:
                    pegasDiePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case THUNDERBIRD:
                    thunderDiePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
                case TROGLODYTE:
                    troglDiePlayer = new SoundPlayer(sound.getUrl(), sound.getVolume(), sound.getCycleCount());
                    break;
            }
        }
    }

    public void playMainTheme() {
        mainThemeSoundPlayer.play();
    }

    public void playNewGameTheme() {
        newGameThemeSoundPlayer.play();
        newGameThemeSoundPlayer.setOnEndofSound(mainThemeSoundPlayer);
    }

    public void stopMenuSound() {
        mainThemeSoundPlayer.stop();
        newGameThemeSoundPlayer.stop();
    }

    public void playGameTheme(int random) {
        switch (random) {
            case 1:
                playBack1.play();
                break;
            case 2:
                playBack2.play();
                break;
            case 3:
                playBack3.play();
                break;
            case 4:
                playBack4.play();
                break;
            case 5:
                playBack5.play();
                break;
        }
    }

    public void stopGameTheme() {
        if (playBack1.getIsPlaying())
            playBack1.stop();
        if (playBack2.getIsPlaying())
            playBack2.stop();
        if (playBack3.getIsPlaying())
            playBack3.stop();
        if (playBack4.getIsPlaying())
            playBack4.stop();
        if (playBack5.getIsPlaying())
            playBack5.stop();
    }

    public void playEndGame() {
        endGame.play();
    }

    public void playAllDie() {
        allDiePlayer.play();
    }

    public void playExtraSpeed() {
        extraSpeedPlayer.play();
    }

    public void playMakeSlower() {
        makeSlowerPlayer.play();
    }

    public void playBonusFire() {
        bonusFirePlayer.play();
    }

    public void playBonusJump() {
        bonusJumpPlayer.play();
    }

    public void playJumpStart() {
        jumpStartPlayer.play();
    }

    public void playFire(CREATURES_NAMES creature) {
        switch (creature) {
            case DARK:
                darkFirePlayer.play();
                break;
            case BONE:
            case BONE_INVERSED:
                boneFirePlayer.play();
                break;
            case FENIX:
                fenixFirePlayer.play();
                break;
            case GHOST:
            case GHOST_INVERSED:
                ghostFirePlayer.play();
                break;
            case ARCHANGEL:
                archFirePlayer.play();
                break;
            case BEHEMOTH:
                begemFirePlayer.play();
                break;
            case CHAMPION:
                champFirePlayer.play();
                break;
            case PEGASUS:
                pegasFirePlayer.play();
                break;
            case THUNDERBIRD:
                thunderFirePlayer.play();
                break;
            case TROGLODYTE:
                troglFirePlayer.play();
                break;
        }
    }

    public void playDie(CREATURES_NAMES creature) {
        switch (creature) {
            case DARK:
                darkDiePlayer.play();
                break;
            case BONE:
            case BONE_INVERSED:
                boneDiePlayer.play();
                break;
            case FENIX:
                fenixDiePlayer.play();
                break;
            case GHOST:
            case GHOST_INVERSED:
                ghostDiePlayer.play();
                break;
            case ARCHANGEL:
                archDiePlayer.play();
                break;
            case BEHEMOTH:
                begemDiePlayer.play();
                break;
            case CHAMPION:
                champDiePlayer.play();
                break;
            case PEGASUS:
                pegasDiePlayer.play();
                break;
            case THUNDERBIRD:
                thunderDiePlayer.play();
                break;
            case TROGLODYTE:
                troglDiePlayer.play();
                break;
        }
    }
}
