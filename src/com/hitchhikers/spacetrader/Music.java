package com.hitchhikers.spacetrader;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

/**
 * @author Roi Atalla
 */
public class Music {
    private static ContinuousAudioDataStream backgroundMusic;
    private static ContinuousAudioDataStream intenseMusic;
    private static ContinuousAudioDataStream soothingMusic;
    
    private static enum MusicOptions {
        BACKGROUND, INTENSE, SOOTHING
    }
    
    private static MusicOptions currentMusic;

    public static void init() {
        try {
            AudioStream audioStream = new AudioStream(Utils.getResourceAsStream("sounds/background_music.wav"));
            AudioData musicData = audioStream.getData();
            backgroundMusic = new ContinuousAudioDataStream(musicData);

            audioStream = new AudioStream(Utils.getResourceAsStream("sounds/action_music.wav"));
            musicData = audioStream.getData();
            intenseMusic = new ContinuousAudioDataStream(musicData);

            audioStream = new AudioStream(Utils.getResourceAsStream("sounds/tavern_music.wav"));
            musicData = audioStream.getData();
            soothingMusic = new ContinuousAudioDataStream(musicData);
        } catch(Exception exc) {
            exc.printStackTrace();
        }
    }

    public static void playBackground() {
        AudioPlayer.player.stop(intenseMusic);
        AudioPlayer.player.stop(soothingMusic);
        
        if(currentMusic != MusicOptions.BACKGROUND) {
            backgroundMusic.reset();
            AudioPlayer.player.start(backgroundMusic);
            currentMusic = MusicOptions.BACKGROUND;
        }
    }

    public static void playIntense() {
        AudioPlayer.player.stop(backgroundMusic);
        AudioPlayer.player.stop(soothingMusic);
        
        if(currentMusic != MusicOptions.INTENSE) {
            intenseMusic.reset();
            AudioPlayer.player.start(intenseMusic);
            currentMusic = MusicOptions.INTENSE;
        }
    }
    
    public static void playSoothing() {
        AudioPlayer.player.stop(backgroundMusic);
        AudioPlayer.player.stop(intenseMusic);

        if(currentMusic != MusicOptions.SOOTHING) {
            soothingMusic.reset();
            AudioPlayer.player.start(soothingMusic);
            currentMusic = MusicOptions.SOOTHING;
        }
    }

    public static void stopAll() {
        AudioPlayer.player.stop(backgroundMusic);
        AudioPlayer.player.stop(intenseMusic);
        AudioPlayer.player.stop(soothingMusic);
    }
}
