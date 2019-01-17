/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * the sound effect when there is movement in a tetris.
 * @author Ruitao Yu
 * @version November 30 2017
 */
public class SoundEffect {

    /** the clip of the BGM. */
    private Clip myBGMClip;
    
    /** the clip of the BGM. */
    private AudioInputStream myBGMSteam;
    
    /** is mute or not. */
    private boolean myMute;
    
    /**
     * constructor.
     */
    public SoundEffect() {
        // set the default mute to false
        myMute = false;
    }
    /**
     * method that plays a clip depends on the action of a tetris.
     * @param theFile the directory of the sound file.
     */
    private void playClip(final File theFile) {
        // try if we can get the file
        try {
            // set up the require object to play music
            final AudioInputStream stream;
            final AudioFormat format;
            final DataLine.Info info;
            final Clip clip;

            // get the file and get the format of that file
            // make the format match the clip then play the music
            stream = AudioSystem.getAudioInputStream(theFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
            
        } catch (final UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final LineUnavailableException e) {
            e.printStackTrace();
        }
        
    }

    /**
     * 
     */
    public void playBGM() {
        // if is not mute, play the BGM
        if (!myMute) {
            try {        // try if we can get the file
                
                // set up the require object to play music
                final AudioFormat format;
                final DataLine.Info info;

                // get the file and get the format of that file
                // make the format match the clip then play the music
                myBGMSteam = AudioSystem.getAudioInputStream(new File("sounds/bolero.wav"));
                format = myBGMSteam.getFormat();
                info = new DataLine.Info(Clip.class, format);
                myBGMClip = (Clip) AudioSystem.getLine(info);
                myBGMClip.open(myBGMSteam);
                myBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (final UnsupportedAudioFileException e) {
                e.printStackTrace();
            } catch (final IOException e) {
                e.printStackTrace();
            } catch (final LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * stop the BGM.
     */
    public void stopBGM() {
        myBGMClip.close();       
    }
    
    /**
    * start the BGM.
    */
    public void startBGM() {
        if (!myMute) { // if not mute, start the BGM
            try {
                try {
                    myBGMClip.open(myBGMSteam);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
                myBGMClip.loop(Clip.LOOP_CONTINUOUSLY);
            } catch (final LineUnavailableException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * play the drop sounds effect.
     */
    public void playDrop() {
        if (!myMute) { // if not mute, play the file
            playClip(new File("sounds/drop.wav"));
        }
    }
    
    /**
     * play the drop sounds effect.
     */
    public void playRotate() {
        if (!myMute) { // if not mute, play the file
            playClip(new File("sounds/rotate.wav"));
        }
    }
    
    /**
     * play the drop sounds effect.
     */
    public void playMove() {
        if (!myMute) { // if not mute, play the file
            playClip(new File("sounds/move.wav"));
        }
    }

    /**
     * switch the mute the opposite value.
     */
    public void mute() {
        // change the value to a opposite value
        myMute = !myMute;
        if (myMute) { // if it is mute, stop the BGM
            stopBGM();
        } else { // if not, play the BGM
            playBGM();
        }
    }

}
