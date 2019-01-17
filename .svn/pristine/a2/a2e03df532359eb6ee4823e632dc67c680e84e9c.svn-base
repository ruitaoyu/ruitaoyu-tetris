/*
 * TCSS 305 - Assignment 6: Tetris
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import model.Block;
import model.Point;
import model.TetrisPiece;

/**
 * The next piece panel of the Tetris game.
 * @author Ruitao Yu
 * @version December 5 2017
 */
public class NextBoard extends JPanel implements Observer  {

    /** the number of square in a row. */
    public static final int NUM_OF_SAUARE = 10;
    
    /** the height divider. */
    public static final int HEIGHT_DIVIDER = 15;
    
    /** the gap makes the gem looks good. */
    public static final int SPECIAL_EFFECT_GAP = 7;
    
    /** the number of points. */
    public static final int NUM_OF_POINTS = 3;
    
    /** the number of points. */
    public static final int DIVIDS_TO_3 = 3;

    /** the serial number. */
    private static final long serialVersionUID = 1795811520178303277L;
    
    /** the size of the board. */
    
    /** the next piece. */
    private TetrisPiece myNextPiece;
    
    /** the height of this panel. */
    private int myHeight;

    /** the width of a gem. */
    private int myGemWidth;
    
    /** the error of this panel. */
    private int myOffSet;

    /**
     * the constructor.
     * @param theHeight the height of this panel
     */
    public NextBoard(final int theHeight) {
        super();
        myHeight = theHeight;
        setPreferredSize(new Dimension(myHeight / DIVIDS_TO_3, myHeight / DIVIDS_TO_3));
        setBackground(Color.WHITE);
    }
    
    /**
     * repaint the panel.
     */
    @Override
    protected void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        final Graphics2D g2d = (Graphics2D) theG;
        
        final int fontSize = (int) (myHeight / 3 / HEIGHT_DIVIDER);
        theG.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
        
        // if my next piece is not null
        if (myNextPiece != null) {
            // get a new width
            myGemWidth = myHeight / DIVIDS_TO_3 / NUM_OF_SAUARE;
            myOffSet = myGemWidth / SPECIAL_EFFECT_GAP;
            
            // draw a string and get the block color
            theG.drawString("NEXT PIECE", myGemWidth * NUM_OF_POINTS, myGemWidth);
            final Color color = getColor(myNextPiece.getBlock());
            final model.Point[] pList = myNextPiece.getPoints();
            
            // calculate the off set for different piece
            int offSetX = 0;
            int offSetY = 0;
            if (myNextPiece.getBlock() == Block.L) {
                pList[0] =  new Point(0, 0);
                offSetY = myGemWidth;
            } else if (myNextPiece.getBlock() == Block.J) {
                pList[0] =  new Point(2, 0);
                offSetY = myGemWidth;
            } else if (myNextPiece.getBlock() == Block.T) {
                pList[0] =  new Point(1, 0);
                offSetY = myGemWidth;
            } else if (myNextPiece.getBlock() == Block.O) {
                offSetX = -myGemWidth * 2 / (NUM_OF_POINTS + 1);
            } else if (myNextPiece.getBlock() == Block.I) {
                offSetX = myGemWidth * 2 / (NUM_OF_POINTS * 2 - 1);
                offSetY = -myGemWidth / 2;
            }
            
            // for each point of this piece
            // draw gems associate with it's points
            for (final model.Point p: pList) {
                final int x = myHeight / 3 / myNextPiece.getWidth() 
                                + p.x() * myGemWidth + offSetX;
                final int y = myHeight / 3 / (NUM_OF_POINTS + 1)
                                + p.y() * myGemWidth + offSetY;
                theG.setColor(color);
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
                                                                 startX, startY
                                                                 , startColor
                                                                 , endX, endY
                                                                 , endColor
                                                                 , true);
                
                // set the center square has gradient color.
                g2d.setPaint(gradient);
                
                theG.fillRect(x + myOffSet, y + myOffSet
                              , myGemWidth - (myOffSet * 2)
                              , myGemWidth - (myOffSet * 2));
                theG.setColor(color.darker());
                theG.drawLine(x, y, x + myOffSet, y + myOffSet);
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
    @Override
    public void update(final Observable theObservable, final Object theObject) {
        // if the object is a piece
        // update my next piece and repaint
        if (theObject instanceof TetrisPiece) {
            myNextPiece = (TetrisPiece) theObject;
            repaint();
        }
    }

    /**
     * this method update the size of this panel.
     * @param theHeight the new height of this panel
     */
    public void setWinSize(final int theHeight) {
        myHeight = theHeight;
        setPreferredSize(new Dimension(myHeight / DIVIDS_TO_3, myHeight / DIVIDS_TO_3));
        repaint();
    }
}
