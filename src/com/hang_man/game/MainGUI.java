package com.hang_man.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.util.Random;

/** MainGUI.java - Main class for the hang man game. This class
 *                 handles setting up the GUI and coordinating game
 *                 state changes.
 *
 * @author Daniel Neel */
public class MainGUI extends JFrame implements ActionListener { 

    /** Panel that acts as a container to hold all 
     *  other GUI components. */
    private JPanel topPanel = new JPanel(new GridBagLayout());

    /** When the game is started, create a new MainGUI
     *  to set up GUI components. */
    public static void main(String args[]) {
	new MainGUI();
    }

    /** When a MainGUI object is created, set relevant properties for
     *  MainGUI's frame, call the method responsible for creating components,
     *  and make the frame visible. */
    public MainGUI() {
	super("Hang man");
        setSize(400, 300);
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	initControls();
	setGameWord();

	pack();
	setVisible(true);
    }

    /** Create all controls that go in the interface, and place
     *  them inside this MainGUI. */
    private void initControls() {
	add(topPanel);

	JLabel faceLeft   = new JLabel(new ImageIcon("../images/faceLeft.png"));
	JLabel faceRight  = new JLabel(new ImageIcon("../images/faceRight.png"));
	JLabel torsoLeft  = new JLabel(new ImageIcon("../images/torsoLeft.png"));
	JLabel torsoRight = new JLabel(new ImageIcon("../images/torsoRight.png"));
	JLabel leftFoot   = new JLabel(new ImageIcon("../images/leftFoot.png"));
	JLabel rightFoot  = new JLabel(new ImageIcon("../images/rightFoot.png"));

	// Since the player hasn't made any mistakes yet, the cartoon being hanged
	// is not yet visible.
	faceLeft.setVisible(false);
	faceRight.setVisible(false);
	torsoLeft.setVisible(false);
	torsoRight.setVisible(false);
	leftFoot.setVisible(false);
	rightFoot.setVisible(false);	

	GridBagConstraints constraints = new GridBagConstraints();
	addComponent(new JLabel("|"), 9, 0, topPanel, constraints);
	addComponent(new JLabel(new ImageIcon("../images/nooseTopLeft.png")),   10, 0, topPanel, constraints);
	addComponent(new JLabel(new ImageIcon("../images/nooseTopMid.png")),    11, 0, topPanel, constraints);
	addComponent(new JLabel(new ImageIcon("../images/nooseTopCorner.png")), 12, 0, topPanel, constraints);

	addComponent(new JLabel("|"), 9, 1, topPanel, constraints);
	addComponent(faceLeft, 10, 1, topPanel, constraints);
	addComponent(faceRight, 11, 1, topPanel, constraints);
	addComponent(new JLabel(new ImageIcon("../images/nooseTopRight.png")), 12, 1, topPanel, constraints);

	addComponent(new JLabel("|"), 9, 2, topPanel, constraints);
	addComponent(torsoLeft, 10, 2, topPanel, constraints);
	addComponent(torsoRight, 11, 2, topPanel, constraints);
	addComponent(new JLabel(new ImageIcon("../images/nooseRight.png")), 12, 2, topPanel, constraints);

	addComponent(new JLabel("|"),              9, 3, topPanel, constraints);
	addComponent(leftFoot,  10, 3, topPanel, constraints);
	addComponent(rightFoot, 11, 3, topPanel, constraints);
	addComponent(new JLabel(new ImageIcon("../images/nooseBase.png")),  12, 3, topPanel, constraints);

	addComponent(new JLabel("X"), 0, 4, topPanel, constraints);
	addComponent(new JLabel("X"), 1, 4, topPanel, constraints);
	addComponent(new JLabel("X"), 2, 4, topPanel, constraints);
	addComponent(new JLabel("X"), 3, 4, topPanel, constraints);
	addComponent(new JLabel("X"), 4, 4, topPanel, constraints);
	addComponent(new JLabel("X"), 5, 4, topPanel, constraints);
	addComponent(new JLabel("X"), 6, 4, topPanel, constraints);
	addComponent(new JLabel("X"), 7, 4, topPanel, constraints);
	addComponent(new JLabel("X"), 8, 4, topPanel, constraints);

	addComponent(new JLabel("|"), 9, 4, topPanel, constraints);

	addComponent(new Letter('A', this), 0, 5, topPanel, constraints);
	addComponent(new Letter('B', this), 1, 5, topPanel, constraints);
	addComponent(new Letter('C', this), 2, 5, topPanel, constraints);
	addComponent(new Letter('D', this), 3, 5, topPanel, constraints);
	addComponent(new Letter('E', this), 4, 5, topPanel, constraints);
	addComponent(new Letter('F', this), 5, 5, topPanel, constraints);
	addComponent(new Letter('G', this), 6, 5, topPanel, constraints);
	addComponent(new Letter('H', this), 7, 5, topPanel, constraints);
	addComponent(new Letter('I', this), 8, 5, topPanel, constraints);

	addComponent(new JLabel("|"), 9, 5, topPanel, constraints);

	addComponent(new Letter('J', this), 0, 6, topPanel, constraints);
	addComponent(new Letter('K', this), 1, 6, topPanel, constraints);
	addComponent(new Letter('L', this), 2, 6, topPanel, constraints);
	addComponent(new Letter('M', this), 3, 6, topPanel, constraints);
	addComponent(new Letter('N', this), 4, 6, topPanel, constraints);
	addComponent(new Letter('O', this), 5, 6, topPanel, constraints);
	addComponent(new Letter('P', this), 6, 6, topPanel, constraints);
	addComponent(new Letter('Q', this), 7, 6, topPanel, constraints);
	addComponent(new Letter('R', this), 8, 6, topPanel, constraints);

	addComponent(new JLabel("|"), 9, 6, topPanel, constraints);

	addComponent(new Letter('S', this), 0, 7, topPanel, constraints);
	addComponent(new Letter('T', this), 1, 7, topPanel, constraints);
	addComponent(new Letter('U', this), 2, 7, topPanel, constraints);
	addComponent(new Letter('V', this), 3, 7, topPanel, constraints);
	addComponent(new Letter('W', this), 4, 7, topPanel, constraints);
	addComponent(new Letter('X', this), 5, 7, topPanel, constraints);
	addComponent(new Letter('Y', this), 6, 7, topPanel, constraints);
	addComponent(new Letter('Z', this), 7, 7, topPanel, constraints);

	addComponent(new JLabel("|"), 9, 7, topPanel, constraints);

	constraints.gridwidth = 3;
	constraints.fill = GridBagConstraints.HORIZONTAL;
	JButton quit = new JButton("Quit");
	quit.addActionListener(this);
	addComponent(quit, 10, 7, topPanel, constraints);
    }

    /** Helper method to add the given JComponent component 
     *  to a panel at coordinates x, y. */
    public void addComponent(JComponent component, int x, int y, JPanel panel, GridBagConstraints gbc) {
	gbc.gridx = x;
	gbc.gridy = y;
	panel.add(component, gbc);
    }

    /** Set the word that will be guessed at for this game
     *  of hang man. */
    public void setGameWord() {
	String word = "aoesntheoausntaheousntaheousnthaoeu";

	try {
	    BufferedReader reader = new BufferedReader(new FileReader("../src/com/hang_man/game/wordList.txt"));
	    // Variable to count the number of lines in the file we're reading.
	    int fileLength = 0;

	    while (reader.readLine() != null)
		fileLength++;

	    // TODO: Ideal code layout:
	    // TODO: Break sub-parts into methods here.
	    Random roller = new Random();

	    // roller.nextInt(fileLength) + 1 contains a random number
	    // from 1 to the length of the file.
	    // e.g. for a file with 10 lines, that expression would return
	    //      random number between 1 and 10, inclousive.
	    int desiredWordLine = roller.nextInt(fileLength) + 1;

	    // Reset the file reader to start reading from the first line.
	    reader = new BufferedReader(new FileReader("../src/com/hang_man/game/wordList.txt"));

	    // This will land me on the correct line in the file.
	    for (int i = 0; i < desiredWordLine; i++) {
		// Set the game's word to the desired line of the file.
		if (i == desiredWordLine - 1)
		    word = reader.readLine();
		else
		    reader.readLine();
	    }
	    System.out.println("New word: " + word);

	} catch (FileNotFoundException e) {
	    System.out.println("File not found.");
	} catch (IOException e) {
	    System.out.println("IOException.");
	}

	for (int i = 0; i < word.length(); i++) {
	    //	    System.out.println(word.charAt(i));
	}
    }

    /** Whenever an action happens, perform relevant actions. */
    @Override
    public void actionPerformed(ActionEvent event) {
	if (event.getActionCommand().equals("Quit")) {
	    JOptionPane.showMessageDialog(topPanel, "Goodbye.");
	    dispose();
	}
	// If the player didn't click the Quit button, they clicked
	// one of the available letters.
	else {
	    Letter src = (Letter) event.getSource();
	    System.out.println(src.getLetter());
	}
    }
}
