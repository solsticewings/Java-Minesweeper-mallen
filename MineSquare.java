/**
* A representation of a mine in the game Minesweeper.
* If you click on it and it isn't flagged, you lose.
* To win, you must flag them all.
* By Micah Allen for CS2100-B
*/
import java.awt.*;

public class MineSquare extends Square{
   //constructor
   
   /**
   * Creates a newSquare object at the specified row and column,
   * with a red backgroundColor and default values for other fields.
   * @param row The value to set the row field to, an int. Sent to super.
   * @param col The value to set the col field to, an int. Sent to super.
   */
   public MineSquare(int row, int col){
      super(row, col);
      setBackgroundColor(Color.RED);
   }
   
   /**
   * Returns -1, as the MineSquare is a mine.
   * @return -1
   */
   public int getNeighbors(){
      return -1;
   }
   
   /**
   * Yeah it's a mine.
   * @return true
   */
   public boolean isMine(){
      return true;
   }
   
   /**
   * Draws the MineSquare
   * @param g2 A Graphics2D object
   * @param size The size of the square (based on things like screen size), an int.
   * @param leftMargin Represents the X coordinate to start drawing from, an int.
   * @param topMargin Represents the Y coordinate to start drawing from, an int.
   */
   public void draw(Graphics2D g2, int size, int leftMargin, int topMargin){
      g2.setColor(getBorderColor());
      g2.fillRect(leftMargin + getColumn() * size, topMargin + getRow() * size, size, size); //draw border
      
      int borderWidth = (int)(0.05 * (double)size); //local variable
      
      if(!isUncovered()){ //if it's covered
         g2.setColor(getCoveredColor());
         g2.fillRect(getColumn() * size + leftMargin, getRow() * size + topMargin,
                           size - 2 * borderWidth, size - 2 * borderWidth); //draw covered square
         if(isFlagged()){
            g2.setColor(getFlagColor());
            g2.fillRect(getColumn() * size + leftMargin + size / 3, getRow() * size + topMargin + size / 3,
                           size / 3, size / 3);//draw flag
         }
      }else{//if it's not covered
         g2.setColor(getBackgroundColor());
         g2.fillRect(getColumn() * size + leftMargin, getRow() * size + topMargin,
                           size - 2 * borderWidth, size - 2 * borderWidth); //draw uncovered square
      }
   }
}