import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.File;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip; 
import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

/**
 * This is the GameFrame class which extends the JFrame class from java swing.
 * This class is used to initialize the window for your game.
 * Written by Daniel Eror
 */

public class GameFrame extends JFrame
{
    private final JPanel panel;

    private GameComponent game;

    /**
     * The GameFrame constructor creates a new GameFrame object, calling the JFrame super class naming it "Minesweeper".
     * It also creates a new JPanel and calls a method to initialize all variables
     */
    public GameFrame()
    {
        super("Minesweeper");

        panel = new JPanel();

        game = new GameComponent();
        panel.add(game);
        panel.setBackground(Color.BLACK);
        this.add(panel);//adds panel to frame
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);//stops the program when the window closes
        this.pack();
        this.setVisible(true);
    }

    /**
     * The run method calls the start method of the GameFrame's GameComponent instance.
     * With my additions, it also sets up the music!
     */
    public void run() throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        AudioInputStream music = AudioSystem.getAudioInputStream(new File("pictionary-main-menu-by-tim-follin.wav").getAbsoluteFile());
        Clip player = AudioSystem.getClip();
        player.open(music);
        player.loop(Clip.LOOP_CONTINUOUSLY);
        
        game.start();
    }
}
