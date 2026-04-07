/**
 * This is the driver for the Minesweeper game.
 * Its main method creates a GameFrame object, and calls its run method.
 * Written by Daniel Eror.
 */
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 
import java.io.IOException;


public class Minesweeper {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        GameFrame frame = new GameFrame();
        frame.run();
    }

}
