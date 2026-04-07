/**
* A representation of a basic square in the game Minesweeper.
* By Micah Allen, for CS2100-B
*/
import java.awt.*;

public abstract class Square{
   //fields
   private int row = 0; //default of zero
   private int col = 0; //default of zero
   private boolean uncovered, flagged;
   private Color backgroundColor, coveredColor, borderColor, flagColor;
   
   //constructor
   
   /**
   * Creates a new Square object at the specified row and column, with both
   * uncovered and flagged set to false, and initializing all Colors save for backgroundColor.
   * @param row The value to set the row field to, an int.
   * @param col The value to set the col field to, an it.
   */
   public Square(int row, int col){
      this.row = row;
      this.col = col;
      uncovered = false;
      flagged = false;
      coveredColor = Color.GREEN;
      borderColor = Color.WHITE;
      flagColor = Color.RED;
   }
   
   //setters and getters
   
   /**
   * Getter for row field.
   * @return The value of row
   */
   public int getRow(){
      return row;
   }
   
   /**
   * Getter for col field.
   * @return The value of col.
   */
   public int getColumn(){
      return col;
   }
   
   /**
   * Getter for flagged field.
   * @return if the square is flagged or not.
   */
   public boolean isFlagged(){
      return flagged;
   }
   
   /**
   * Getter for uncovered field.
   * @return if the square is uncovered or not.
   */
   public boolean isUncovered(){
      return uncovered;
   }
   
   /**
   * Switches the state of flagged between false and true.
   */
   public void toggleFlag(){
      flagged = (!flagged);
   }
   
   /**
   * Uncovers the square if it isn't flagged. Does nothing otherwise.
   */
   public void uncover(){
      if(!flagged){
         uncovered = true;
      }
   }
   
   /**
   * Sets backgroundColor to the specified Color.
   * @param c The Color to set backgroundColor to.
   */
   public void setBackgroundColor(Color c){
      backgroundColor = c;
   }
   
   /**
   * Sets coveredColor to the specified Color.
   * @param c The Color to set coveredColor to.
   */
   public void setCoveredColor(Color c){
      coveredColor = c;
   }
   
   /**
   * Sets borderColor to the specified Color.
   * @param c The Color to set borderColor to.
   */
   public void setBorderColor(Color c){
      borderColor = c;
   }
   
   /**
   * Sets flagColor to the specified Color.
   * @param c The Color to set flagColor to.
   */
   public void setFlagColor(Color c){
      flagColor = c;
   }
   
   /**
   * Returns the value of the backgroundColor field.
   * @return backgroundColor, a Color.
   */
   public Color getBackgroundColor(){
      return backgroundColor;
   }
   
   /**
   * Returns the value of the coveredColor field.
   * @return coveredColor, a Color.
   */
   public Color getCoveredColor(){
      return coveredColor;
   }
   
   /**
   * Returns the value of the borderColor field.
   * @return borderColor, a Color.
   */
   public Color getBorderColor(){
      return borderColor;
   }
   
   /**
   * Returns the value of the flagColor field.
   * @return flagColor, a Color.
   */
   public Color getFlagColor(){
      return flagColor;
   }
   
   //abstract methods
   public abstract int getNeighbors();
   
   public abstract boolean isMine();
   
   public abstract void draw(Graphics2D g2, int size, int leftMargin, int topMargin);
   
}