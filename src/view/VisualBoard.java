/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.Block;


/**
 * The game board of the Tetris game.
 * @author Ruitao Yu
 * @version November 18 2017
 */
public class VisualBoard extends JPanel implements Observer {
    
    /** the number of square in a row. */
    public static final int NUM_OF_SAUARE = 10;
    
    /** the number of square in a row. */
    public static final int NUM_OF_ROW = 20;
    
    /** the gap makes the gem looks good. */
    public static final int SPECIAL_EFFECT_GAP = 7;
    
    /** the number of points. */
    public static final int NUM_OF_POINTS = 3;
    
    /** the text description of the second color menu item. */
    public static final String WIN_ICON_DIR = "images/tetris_dribbble.png";
    
    /** the UW Tacoma icon. */
    public static final Icon ICON = new ImageIcon(WIN_ICON_DIR);
    
    /** the number of invisible row on the top of the board. */
    public static final int UNSHOWN_ROW = 4;
    
    /** the serial number. */
    private static final long serialVersionUID = 8336701545355590180L;
    
    /** The board data from Observable board. */
    private List<Block[]> myBoardData;
    
    /** the width of this panel. */
    private int myWidth;
    
    /** the height of this panel. */
    private int myHeight;

    /** the width of a gem. */
    private int myGemWidth;
    
    /** the error of this panel. */
    private int myOffSet;

    
    /**
     * the constructor.
     * @param theWidth the width of this panel
     * @param theHeight the height of this panel
     */
    public VisualBoard(final int theWidth, final int theHeight) {
        super();
        myWidth = theWidth;
        myHeight = theHeight;
        setPreferredSize(new Dimension(myWidth, myHeight));
        setBackground(Color.LIGHT_GRAY);
    }
    
    /**
     * repaint the panel.
     */
    @Override
    protected void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        
        final Graphics2D g2d = (Graphics2D) theG;

        //If the board data if not null. read the board data row by row
        if (myBoardData != null) {
            myGemWidth = myWidth / NUM_OF_SAUARE;
            myOffSet = myGemWidth / SPECIAL_EFFECT_GAP;
            for (int i = myBoardData.size() - UNSHOWN_ROW; i >= 0; i--) {
                for (int j = myBoardData.get(i).length - 1; j >= 0; j--) {
                    
                    // set the x, and y for the shapes
                    final int x = (myWidth / NUM_OF_SAUARE) * (NUM_OF_SAUARE - 1 - j);
                    final int y = (NUM_OF_ROW - 1 - i) * (myWidth / myBoardData.get(i).length);
                    
                    // if the data is null, draw a white square
                    if (myBoardData.get(i)[j] == null) {
                        theG.setColor(Color.WHITE);
                        theG.drawRect(x, y, myWidth / myBoardData.get(i).length
                                   , myWidth / myBoardData.get(i).length);
                    } else { // if the data is not null                      
                        final Color color = getColor(myBoardData.get(i)[j]);
                        theG.setColor(color);
                        
                        // draw some shapes to build a beautiful gem
                        final int[] p1 = {x, x, x + myGemWidth};
                        final int[] p2 = {y, y + myGemWidth, y};
                        theG.fillPolygon(p1, p2, NUM_OF_POINTS);
                        
                        theG.setColor(color.darker().darker());

                        final int[] p3 = {x, x + myGemWidth, x + myGemWidth};
                        final int[] p4 = {y + myGemWidth, y, y + myGemWidth};
                        theG.fillPolygon(p3, p4, NUM_OF_POINTS);
                        
                        //set up a gradient color
                        final Color endColor = Color.white;
                        final Color startColor = color;
                        final int startX = 0;
                        final int startY = 0;
                        final int endX = 10;
                        final int endY = 10;
                        final GradientPaint gradient = new GradientPaint(
                                                            new Point(startX, startY)
                                                            , startColor
                                                            , new Point(endX, endY)
                                                            , endColor
                                                            , true);
                        g2d.setPaint(gradient);
                        
                        g2d.fillRect(x + myOffSet, y + myOffSet
                                      , myGemWidth - (myOffSet * 2)
                                      , myGemWidth - (myOffSet * 2));
                        theG.setColor(color.darker());
                        theG.drawLine(x, y, x + myOffSet, y + myOffSet);
                    }
                }
            }
        }
    }
    
    /**
     * Get the color present the block.
     * 
     * @param theBlock the block that needs a color
     * @return the color of the block
     */
    private Color getColor(final Block theBlock) {
        // return a color that matches any of these
        final Color color;
        switch (theBlock) {
            case I: color = Color.CYAN;
                  break;
            case J: color = Color.ORANGE;
                  break;
            case L: color = Color.BLUE;
                  break;
            case O: color = Color.YELLOW;
                  break;
            case S: color = Color.GREEN;
                  break;
            case T: color = Color.PINK;
                  break;
            case Z: color = Color.RED;
                  break;
            default: color = Color.WHITE;
              break;
        } 
        return color;
        
    }

    /**
     * this method call by the observable class.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void update(final Observable theObservable, final Object theObject) {
        // if the object is an ArrayList
        // assign it's value to my data board
        if (theObject instanceof ArrayList) {
            myBoardData = (ArrayList<Block[]>) theObject;
            repaint();

        }
    }

    /**
     * this method update the size of this panel.
     * @param theWidth the new width of this panel
     * @param theHeight the new height of this panel
     */
    public void setWinSize(final int theWidth, final int theHeight) {
        myWidth = theWidth;
        myHeight = theHeight;
        setPreferredSize(new Dimension(myWidth, myHeight));
        repaint();
    }
}
