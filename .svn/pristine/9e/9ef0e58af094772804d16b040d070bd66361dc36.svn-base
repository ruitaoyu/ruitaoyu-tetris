/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Ruitao Yu
 * @version November 18 2017
 *
 */
public final class TetrisMain {
    
    /**
     * Private constructor, to prevent instantiation of this class.
     */
    private TetrisMain() {
        throw new IllegalStateException();
    }

    /**
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        
        // set the look and feel of the window
        try {

            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        // disable the default bold of the metal look
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
        // run the mission
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TetrisGUI();
            }
        });
    }

}
