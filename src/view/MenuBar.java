/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * This is the menu bar of the Tetris project's GUI.
 * @author Ruitao Yu
 * @version December 5 2017
 */
public class MenuBar extends JMenuBar {
    
    /** the text description of the second color menu item. */
    public static final String WIN_ICON_DIR = "images/tetris_dribbble.png";
    
    /** the number of square in a row. */
    public static final int DIVISOR_FOR_HEIGHT = 3;
    
    /** the serial number. */ 
    private static final long serialVersionUID = -674997019846339723L;

    /** the UW Tacoma icon. */
    public static final Icon ICON = new ImageIcon(WIN_ICON_DIR);
    
    /** my sound effects. */
    private final SoundEffect mySoundEffect;

    /** the GUI of this project. */
    private final TetrisGUI myGUI;

    /** the timer of the first player. */
    private final Timer myPlayerOneTimer;

    /** the timer of the second player. */
    private final Timer myPlayerTwoTimer;
    
    /** window size names. */
    private final String[] myWinName = {"Small (250 X 500)", "Normal (350 X 700)"
                    , "Large (450 X 900)"};
    
    /** the list of the window size buttons. */
    private final List<JMenuItem> myWinButtons = new ArrayList<JMenuItem>();
    
    /** the end button. */
    private JMenuItem myEnd;
    
    /** the start button. */
    private JMenuItem myStart;
    
    /** the single player button. */
    private JMenuItem myOnePlayer;
    
    /** the two player button. */
    private JMenuItem myTwoPlayer;

    /** the control menu item. */
    private JMenu myControl;
    
    /**
     * the constructor.
     * @param theGUI the GUI of this project
     * @param theTimerOne the timer of the first player
     * @param theTimerTwo the timer of the second player
     * @param theSound the sound effect object.
     */
    public MenuBar(final TetrisGUI theGUI
                   , final Timer theTimerOne
                   , final Timer theTimerTwo
                   , final SoundEffect theSound) {
        super();
        myGUI = theGUI;
        myPlayerOneTimer = theTimerOne;
        myPlayerTwoTimer = theTimerTwo;
        mySoundEffect = theSound;
        myGUI.setUpOnePlayMap();
        myGUI.twoPlayerMode(false);
        setUpMenu();
    }
    
    /**
     * This method set up a menu bar.
     */
    private void setUpMenu() {
        // Set up the game menu
        final JMenu game = new JMenu("Game");
        myStart = new JMenuItem("Start new Game");
        myEnd = new JMenuItem("End Game");
        myOnePlayer = new JMenuItem("Single Player mode");
        myTwoPlayer = new JMenuItem("Two players mode");
        final JMenuItem exit = new JMenuItem("Exit");
        
        // set up the mnemonics
        game.setMnemonic(KeyEvent.VK_G);
        myStart.setMnemonic(KeyEvent.VK_S);
        myEnd.setMnemonic(KeyEvent.VK_E);
        myOnePlayer.setMnemonic(KeyEvent.VK_I);
        myTwoPlayer.setMnemonic(KeyEvent.VK_T);
        exit.setMnemonic(KeyEvent.VK_X);
        
        // add all the action listener to menu items.
        myStart.addActionListener(event -> {
            mySoundEffect.playBGM();
            myGUI.startGame();
            activeMenuControlButtons(true);
        });
        myEnd.addActionListener(event -> {
            myGUI.checkHighest();
            myGUI.endGame(null);
            myGUI.setScoreZero();
            myGUI.activeKey(false);
            
        });
        myOnePlayer.addActionListener(event -> {
            if (myPlayerTwoTimer.isRunning()) {
                JOptionPane.showMessageDialog(myGUI
                                              , "Please end the game before switch mode.");
                return;
            }
            myGUI.setUpOnePlayMap();
            myGUI.setMyIsTwoPlayer(false);
            myGUI.twoPlayerMode(false);
            myGUI.pack();
            activePlayerButtons(false);
            myControl.setEnabled(true);
        });
        myTwoPlayer.addActionListener(event -> {
            if (myPlayerOneTimer.isRunning()) {
                JOptionPane.showMessageDialog(myGUI
                                              , "Please end the game before switch mode!");
                return;
            }
            myGUI.setUpTwoPlayMap();
            myGUI.setMyIsTwoPlayer(true);
            myGUI.twoPlayerMode(true);
            myGUI.pack();
            activePlayerButtons(true);
            myControl.setEnabled(false);
        });
        exit.addActionListener(event -> {
            myGUI.dispatchEvent(new WindowEvent(myGUI, WindowEvent.WINDOW_CLOSING));
        });
        
        // add the menu items to the menu
        game.add(myStart);
        game.add(myEnd);
        game.add(myOnePlayer);
        game.add(myTwoPlayer);
        game.add(exit);      
        add(game);
        
        // set up the next menu
        setUpWindowMenu();
    }
    
    /**
     * set up the window menu.
     */
    private void setUpWindowMenu() {
        final JMenu view = new JMenu("View");
        view.setMnemonic(KeyEvent.VK_V);
        
        // create 3 widow size menu item
        setUpWinSize(myWinName[0], view, KeyEvent.VK_S);
        setUpWinSize(myWinName[1], view, KeyEvent.VK_N);
        setUpWinSize(myWinName[2], view, KeyEvent.VK_L);
        add(view);
        setUpControlMenu();
    }
    
    /**
     * set up the control menu item.
     */
    private void setUpControlMenu() {
        // create a menu and 5 menu item
        myControl = new JMenu("Control");
        myControl.setMnemonic(KeyEvent.VK_C);
        myGUI.modifyButtons(myControl, "Set rotate as...", KeyEvent.VK_T);
        myGUI.modifyButtons(myControl, "Set down as...", KeyEvent.VK_D);
        myGUI.modifyButtons(myControl, "Set left as...", KeyEvent.VK_L);
        myGUI.modifyButtons(myControl, "Set right as...", KeyEvent.VK_R);
        myGUI.modifyButtons(myControl, "set drop as...", KeyEvent.VK_O);
        add(myControl);
        setUpAboutMenu();
    }
    
    
    /**
     * set up the about menu.
     */
    private void setUpAboutMenu() {
        // create a help menu and an about menu item 
        // attach a listener to the about item
        // when clicked, show the author information of this project
        final JMenu help = new JMenu("Help");
        final JMenuItem about = new JMenuItem("About");
        final JMenuItem scoring = new JMenuItem("Scoring");
        help.setMnemonic(KeyEvent.VK_H);
        about.setMnemonic(KeyEvent.VK_A);
        scoring.setMnemonic(KeyEvent.VK_S);
        
        about.addActionListener(event -> {
            JOptionPane.showMessageDialog(myGUI
                                          , "Ruitao Yu" + '\n' 
                                                          + "Autumn 2017" + '\n' 
                                                          + "TCSS 305 Assignment 6 Tetris"
                                                          , "About."
                                                          , JOptionPane.INFORMATION_MESSAGE
                                                          , ICON);
        });
        
        scoring.addActionListener(event -> {
            JOptionPane.showMessageDialog(myGUI
                                          , "Clear one line get (40 * level) in score."
                                           + '\n' 
                                           + "Clear two lines get (100 * level) in score."
                                           + '\n' 
                                           + "Clear three lines get (300 * level) in score."
                                           + '\n'
                                           + "Clear four lines get (1200 * level) in score."
                                           + '\n' 
                                           , "Scoring Tip"
                                           , JOptionPane.INFORMATION_MESSAGE
                                           , ICON);
        });
        
        help.add(scoring);
        help.add(about);
        add(help);
    }
    
    /**
     * set the control buttons in the menu to be the given value.
     * @param theIsEnable active or not
     */
    public void activeMenuControlButtons(final boolean theIsEnable) {
        // the start and end can not have the same value
        myStart.setEnabled(!theIsEnable);
        myEnd.setEnabled(theIsEnable);
    }
    
    /**
     * set the player mode to the current selection..
     * @param theIsEnable active or not
     */
    public void activePlayerButtons(final boolean theIsEnable) {
        // the start and end can not have the same value
        myOnePlayer.setEnabled(theIsEnable);
        myTwoPlayer.setEnabled(!theIsEnable);
    }  
    
    /**
     * This method set up a menu item in the window menu.
     * @param theSize the text of the menu item
     * @param theView the menu
     * @param theKey the key value
     */
    public void setUpWinSize(final String theSize, final JMenu theView, final int theKey) {
        // create a menu item
        final JMenuItem item = new JMenuItem(theSize);
        item.setMnemonic(theKey);
        
        // attach it to a action listener
        item.addActionListener(event -> {
            // get the width and height from the string
            final String size = ((JMenuItem) event.getSource()).getText();
            final int width = Integer.valueOf(theSize.substring(size.indexOf('(')
                                                          + 1, size.indexOf('(') + 4));
            final int height = Integer.valueOf(theSize.substring(size.indexOf('X')
                                                           + 2, size.indexOf(')')));
            // enable all item except this one
            disableWin(size);
            
            myGUI.setMyWidth(width);
            myGUI.setMyHeight(height);
            // update the new width and height in each panel
            // pack the window
            myGUI.getMyGamePanel().setWinSize(width, height);
            myGUI.getMyNextPanel().setWinSize(height);
            myGUI.getMyScorePanel().setWinSize(width, height);
            myGUI.getMyExtraScorePanel().setWinSize(width, height);
            myGUI.getMyPlayerTwoGamePanel().setWinSize(width, height);
            myGUI.getMyPlayerTwoNextPanel().setWinSize(height);
            myGUI.getMyPlayerTwoScorePanel().setWinSize(width, height);
            myGUI.getMyExtraPlayerTwoScorePanel().setWinSize(width, height);
            myGUI.getMyPlayerOnePanel().setPreferredSize(new Dimension(myGUI.getMyWidth() 
                                                            + myGUI.getMyHeight() 
                                                            / DIVISOR_FOR_HEIGHT
                                                            , myGUI.getMyHeight()));
            myGUI.getMyPlayerTwoPanel().setPreferredSize(new Dimension(myGUI.getMyWidth() 
                                                            + myGUI.getMyHeight() 
                                                            / DIVISOR_FOR_HEIGHT
                                                            , myGUI.getMyHeight()));

            myGUI.pack();
        });
        
        if (item.getText().equals(myWinName[1])) {
            item.setEnabled(false);
        }
        // add the item to the view menu
        myWinButtons.add(item);
        theView.add(item);
    }
    
    /**
     * disable specific button.
     * @param theSizeName the text in the button
     */
    private void disableWin(final String theSizeName) {
        // the current using window size button is disable
        for (int i = 0; i < myWinButtons.size(); i++) {
            if (myWinButtons.get(i).getText().equals(theSizeName)) {
                myWinButtons.get(i).setEnabled(false);
            } else {
                myWinButtons.get(i).setEnabled(true);
            }
        }
    }
}
