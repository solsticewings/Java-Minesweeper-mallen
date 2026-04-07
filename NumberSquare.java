/**
* A representation of a safe, non-mine square in the game Minesweeper.
* You need to uncover all of them to win, and they show a number representing
* how many mines neighbor them to aid you.
*/
import java.awt.*;

public class NumberSquare extends Square{
   //fields
   private int neighbors;
   private Color numberColor;
   
   //constructor
   
   /**
   * Creates a new NumberSquare at the specified row and column,
   * and with a specified number of neighboring MineSquares. Also sets the numberColor
   * to a particular Color and the backgroundColor to light gray.
   * @param neighbors The int representing the number of neighboring MineSquares.
   * @param row The value to set the row field to, an int. Sent to super.
   * @param col The value to set the col field to, an int. Sent to super.
   */
   public NumberSquare(int neighbors, int row, int col){
      super(row, col);
      this.neighbors = neighbors;
      numberColor = switch(neighbors){
         case 1 -> Color.BLUE;
         case 2 -> Color.GREEN;
         case 3 -> Color.ORANGE;
         case 4 -> Color.CYAN;
         case 5 -> Color.RED;
         case 6 -> Color.YELLOW;
         case 7 -> Color.BLACK;
         default -> Color.MAGENTA; 
      };
      setBackgroundColor(Color.LIGHT_GRAY);
   }
   
   /**
   * Returns the value of the neighbors field.
   * @return The number of neighboring MineSquares, an int.
   */
   public int getNeighbors(){
      return neighbors;
   }
   
   /**
   * These ones aren't mines, have no fear.
   * @return false
   */
   public boolean isMine(){
      return false;
   }
   
   /**
   * Draws the NumberSquare
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
         if(neighbors > 0){
            g2.setColor(numberColor);
            g2.setFont(new Font("Comic Sans MS", Font.BOLD, 15)); //hehe
            g2.drawString(neighbors + "", getColumn() * size + leftMargin + (size / 3),
                                    getRow() * size + topMargin + 2 * (size / 3)); //show number of neighbors
         }
      }
   }

}
