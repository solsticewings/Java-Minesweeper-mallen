/**
 * This is the driver for the Minesweeper game.
 * Its main method creates a GameFrame object, and calls its run method.
 * Written by Daniel Eror.
 ******************************************
 * Minesweeper Game by Micah Allen for CS2100-B
 * Please test for:
 * - Different numbers in different colors
 * - Submitted early
 * Also, this isn't for credit, but I added music!
 * The background music is the title screen music for the NES game Pictionary by Tim Follin,
 * I was going to add the original game's sound effects, but it ended up being too much of a pain
 * and I'd have to add a bunch of throws statements everywhere.
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
