import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Name: Micah Allen
 * Class: CS2100-B
 * Instructor: Jackie Horton
 */
public class GameComponent extends JComponent implements MouseListener
{
   private Grid gameGrid;
   private String message;
   private String userProgress;
   private Color textColor;
   private boolean started;
   private boolean gameOver;
   
   /**
   * Creates the GameComponent with default value for all fields.
   */
   public GameComponent()
    {
        setPreferredSize(new Dimension(800,800));
        addMouseListener(this);
        gameGrid = new Grid(10, 12, 10, 350, 350);
        message = "Click any tile to start! ^_^";
        userProgress = gameGrid.getNumFlaggedSquares() + "/" + gameGrid.getNumMines();
        textColor = Color.WHITE;
        started = false;
        gameOver = false;
    }
    
    /**
    * Checks if the player has won, and ends the game and displays a victory message if they have.
    */
    public void checkWin(){
      if(gameGrid.hasWon()){
         gameOver = true;
         message = "You won! :D";
      }
    }

    /**
     * The paintComponent method draws the grid and all messages to the screen
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        //Important - use the variable g2 for everything done in this method, do not use g
        gameGrid.draw(g2);
        g2.setColor(textColor);
        g2.setFont(new Font("Comic Sans MS", Font.BOLD, 50));
        g2.drawString(message, 100, 100);
        g2.drawString(userProgress, 200, 200);
    }

    /**
     * The start method starts the game, starting an infinite loop that repeats until the program ends
     */
    public void start()
    {
        while(true)
        {
            repaint();
            checkWin();
            try {
                Thread.sleep((long) (1000 / 60.0));
            }catch(InterruptedException e) {}
        }
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO: implement this method
        int x = e.getX();
        int y = e.getY();
        if(e.getButton() == MouseEvent.BUTTON1){
            started = gameGrid.userMove(x, y, "uncover", started);
        }else if(e.getButton() == MouseEvent.BUTTON3){
            started = gameGrid.userMove(x, y, "flag", started);
        }
        if(started && !gameOver){
            message = "Find the mines! >:)";
            userProgress =  gameGrid.getNumFlaggedSquares() + "/" + gameGrid.getNumMines();
        }
        if(gameGrid.hasMineBeenUncovered()){
            gameOver = true;
            message = "You lost! X_X";
            userProgress =  gameGrid.getNumFlaggedSquares() + "/" + gameGrid.getNumMines();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // LEAVE EMPTY
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // LEAVE EMPTY
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // LEAVE EMPTY
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // LEAVE EMPTY
    }
}
