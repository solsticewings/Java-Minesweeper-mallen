/**
* A representation of a grid of mine and non-mine squares in the game Minesweeper.
* By Micah Allen for CS2100-B
*/
import java.awt.*;
import java.util.Random;

public class Grid{
   //fields
   private Square[][] grid;
   private int width;
   private int height;
   private int numMines;
   private int leftMargin;
   private int topMargin;
   private int squareSize;
   private int numFlaggedSquares;
   private boolean mineUncovered;
   
   //constructor
   
   /**
   * Creates a new Grid with the specified values for height, width, numMines,
   * topMargin, and leftMargin, and default values for squareSize, numFlaggedSquares,
   * and mineUncovered. Finally, initializes grid with height and width, and calls createGrid().
   * @param height The height of the Grid in squares, an int.
   * @param width The width of the Grid in squares, an int.
   * @param numMines The number of mines hidden in the Grid, an int.
   * @param topMargin The number of pixels between the Grid and the top of the window, an int.
   * @param leftMargin The number of pixels between the Grid and the left of the window, an int.
   */
   public Grid(int height, int width, int numMines, int topMargin, int leftMargin){
      this.height = height;
      this.width = width;
      this.numMines = numMines;
      this.topMargin = topMargin;
      this.leftMargin = leftMargin;
      
      squareSize = 30;
      numFlaggedSquares = 0;
      mineUncovered = false; 
      
      grid = new Square[height][width];
      createGrid();
   }
   
   /**
   * Flags the specified square, if it's covered, and
   * changes the numFlaggedSquares field accordingly.
   * @param r The row of the square, an int.
   * @param c The column of the square, an int.
   */
   public void flag(int r, int c){
      if(!grid[r][c].isUncovered()){
         grid[r][c].toggleFlag();
         if(grid[r][c].isFlagged()){
            numFlaggedSquares++;
         }else{
            numFlaggedSquares--;
         }
      }
   }
   
   /**
   * Uncovers the square at the specified location. If it's a mine, uncovers
   * the entire grid, including flagged squares. If it's a number square with no
   * mines around it, it also uncovers its neighbors.
   * @param r The row of the square, an int.
   * @param c The column of the square, an int.
   * @return The number of neighbors of the square, or -1 if it's a mine.
   */
   public int uncoverSquare(int r, int c){
      grid[r][c].uncover(); //uncover selected square
      if(grid[r][c].isMine() && !hasWon()){ //if it's a mine, uncover all squares
         for(Square[] line: grid){
            for(Square s: line){
               if(s.isFlagged()){
                  s.toggleFlag();
               }
               s.uncover();
            }
         }
      }
      if(grid[r][c].getNeighbors() == 0){ //uncover neighbors
         if(r != 0 && c != 0 && (grid[r - 1][c - 1] instanceof NumberSquare)){
            grid[r - 1][c - 1].uncover();
         }
         if(r != 0 && (grid[r - 1][c] instanceof NumberSquare)){
            grid[r - 1][c].uncover();
         }
         if(r != 0 && c != width - 1 && (grid[r - 1][c + 1] instanceof NumberSquare)){
            grid[r - 1][c + 1].uncover();
         }
         if(c != 0 && (grid[r][c - 1] instanceof NumberSquare)){
            grid[r][c - 1].uncover();
         }
         if(c != width - 1 && (grid[r][c + 1] instanceof NumberSquare)){
            grid[r][c + 1].uncover();
         }
         if(r != height - 1 && c != 0 && (grid[r + 1][c - 1] instanceof NumberSquare)){
            grid[r + 1][c - 1].uncover();
         }
         if(r != height - 1 && (grid[r + 1][c] instanceof NumberSquare)){
            grid[r + 1][c].uncover();
         }
         if(r != height - 1 && c != width - 1 && (grid[r + 1][c + 1] instanceof NumberSquare)){
            grid[r + 1][c + 1].uncover();
         }
      }
      return grid[r][c].getNeighbors();
   }
   
   public void createGrid(){
      Random r = new Random();
      int minesToAdd = numMines;
      while(minesToAdd > 0){ //randomly add specified number of mines
         int tempY = r.nextInt(height);
         int tempX = r.nextInt(width);
         grid[tempY][tempX] = new MineSquare(tempY, tempX);
         minesToAdd--;
      }
      for(int i = 0; i < height; i++){ //go through columns to select row
         for(int j = 0; j < width; j++){ //go through rows to select square
            int neighborMines = 0;
            if(!(grid[i][j] instanceof MineSquare)){ //make sure the mine isn't being overwritten
               if(j != 0 && i != 0 && (grid[i - 1][j - 1] instanceof MineSquare)){ //top left
                  neighborMines++;
               }
               if(i != 0 && (grid[i - 1][j] instanceof MineSquare)){ //left
                  neighborMines++;
               }
               if(i != 0 && j != width - 1 && (grid[i - 1][j + 1] instanceof MineSquare)){ //bottom left
                  neighborMines++;
               }
               if(j != 0 && (grid[i][j - 1] instanceof MineSquare)){ //top
                  neighborMines++;
               }
               if(j != width - 1 && (grid[i][j + 1] instanceof MineSquare)){ //bottom
                  neighborMines++;
               }
               if(i != height - 1 && j != 0 && (grid[i + 1][j - 1] instanceof MineSquare)){ //top right
                  neighborMines++;
               }
               if(i != height - 1 && (grid[i + 1][j] instanceof MineSquare)){ //right
                  neighborMines++;
               }
               if(i != height - 1 && j != width - 1 && (grid[i + 1][j + 1] instanceof MineSquare)){ //bottom right
                  neighborMines++;
               }
               grid[i][j] = new NumberSquare(neighborMines, i, j); //make a new NumberSquare
            }
         }
      }
      }
   
   /**
   * Responds appropriately based on where the user clicked.
   * @param mouseX The x coordinate of the click, an int.
   * @param mouseY The y coordinate of the click, an int.
   * @param action The action to perform, a String.
   * @param started If the game has started or not.
   * @return started
   */ 
   public boolean userMove(int mouseX, int mouseY, String action, boolean started){
      boolean inGrid = (mouseX > leftMargin && mouseX < squareSize * width + leftMargin) &&
                                 (mouseY > topMargin && mouseY < squareSize * height + topMargin); //determine if click was in the grid
      if(inGrid){
         int clickedRow = (mouseX - leftMargin) / squareSize;
         int clickedCol = (mouseY - topMargin) / squareSize;
         if(action.equals("uncover")){
            started = true;
            if((uncoverSquare(clickedCol, clickedRow) == -1) && !hasWon()){
               mineUncovered = true;
            }
         }else if(action.equals("flag")){
            flag(clickedCol, clickedRow);
         }
      }
      return started;
   }  
   
   /**
   * Checks if the player has fulfilled the win conditions.
   * @return true if the player has accomplished their goals, false otherwise.
   */
   public boolean hasWon(){
      for(Square[] line: grid){ //go through the grid
         for(Square s: line){
            if(s.isMine()){
               if(!s.isFlagged()){
                  return false; //if a mine is unflagged, then the player isn't done.
               }
            }else{
               if(!s.isUncovered()){
                  return false; //if a number square is covered, then the player isn't done
               }
            }
         }
      }
      return true; //if it gets through the above, the player has won.
   }
   
   /**
   * Draws each square in the grid.
   * @param g2 The Graphics2D object to pass in.
   */
   public void draw(Graphics2D g2){
      for(Square[] line: grid){
         for(Square s: line){
            s.draw(g2, squareSize, leftMargin, topMargin);
         }
      }
   }
   
   /**
   * Returns the value of numFlaggedSquares.
   * @return The field numFlaggedSquares, an int
   */
   public int getNumFlaggedSquares(){
      return numFlaggedSquares;
   }
   
   /**
   * Returns the value of mineUncovered
   * @return The field mineUncovered, a boolean
   */
   public boolean hasMineBeenUncovered(){
      return mineUncovered;
   }
   
   /**
   * Returns the value of numMines
   * @return The field numMines, an int
   */
   public int getNumMines(){
      return numMines;
   }      
}