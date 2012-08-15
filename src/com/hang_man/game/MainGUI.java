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

    /** The number of times the player has incorrectly guessed a letter
     *  in this game. */
    private int wrongGuesses = 0;

    /** The number of letters the player has correctly guessed. For example,
     *  in the word "_ _ b _ b _", the player has guessed two letters correctly. */
    private int correctLettersGuessed = 0;

    /** The word that the player has to guess to win the game. */
    private String word;

    /** wordLetter*: A series of letters used to indicate the word
     *               the player is attempting to guess.*/
    private JLabel wordLetter1 = new JLabel("_");
    private JLabel wordLetter2 = new JLabel("_");
    private JLabel wordLetter3 = new JLabel("_");
    private JLabel wordLetter4 = new JLabel("_");
    private JLabel wordLetter5 = new JLabel("_");
    private JLabel wordLetter6 = new JLabel("_");
    private JLabel wordLetter7 = new JLabel("_");
    private JLabel wordLetter8 = new JLabel("_");
    private JLabel wordLetter9 = new JLabel("_");
    
    /** An array that holds all the labels used to display a word. */
    private JLabel[] wordLabels = {wordLetter1,
				   wordLetter2,
				   wordLetter3,
				   wordLetter4,
				   wordLetter5,
				   wordLetter6,
				   wordLetter7,
				   wordLetter8,
				   wordLetter9};

    /** Various images that represent a person being hung. The number of
     *  these present at any given time represents the amount of wrong guesses
     *  the player has made. */
    private JLabel faceLeft   = new JLabel(new ImageIcon("../images/faceLeft.png"));
    private JLabel faceRight  = new JLabel(new ImageIcon("../images/faceRight.png"));
    private JLabel torsoLeft  = new JLabel(new ImageIcon("../images/torsoLeft.png"));
    private JLabel torsoRight = new JLabel(new ImageIcon("../images/torsoRight.png"));
    private JLabel leftFoot   = new JLabel(new ImageIcon("../images/leftFoot.png"));
    private JLabel rightFoot  = new JLabel(new ImageIcon("../images/rightFoot.png"));

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

	addComponent(wordLetter1, 0, 3, topPanel, constraints);
	addComponent(wordLetter2, 1, 3, topPanel, constraints);
	addComponent(wordLetter3, 2, 3, topPanel, constraints);
	addComponent(wordLetter4, 3, 3, topPanel, constraints);
	addComponent(wordLetter5, 4, 3, topPanel, constraints);
	addComponent(wordLetter6, 5, 3, topPanel, constraints);
	addComponent(wordLetter7, 6, 3, topPanel, constraints);
	addComponent(wordLetter8, 7, 3, topPanel, constraints);
	addComponent(wordLetter9, 8, 3, topPanel, constraints);

	// TODO: Switch this to a large vertical separator.
	addComponent(new JLabel("|"), 9, 4, topPanel, constraints);

	addComponent(new Letter('a', this), 0, 5, topPanel, constraints);
	addComponent(new Letter('b', this), 1, 5, topPanel, constraints);
	addComponent(new Letter('c', this), 2, 5, topPanel, constraints);
	addComponent(new Letter('d', this), 3, 5, topPanel, constraints);
	addComponent(new Letter('e', this), 4, 5, topPanel, constraints);
	addComponent(new Letter('f', this), 5, 5, topPanel, constraints);
	addComponent(new Letter('g', this), 6, 5, topPanel, constraints);
	addComponent(new Letter('h', this), 7, 5, topPanel, constraints);
	addComponent(new Letter('i', this), 8, 5, topPanel, constraints);

	addComponent(new JLabel("|"), 9, 5, topPanel, constraints);

	addComponent(new Letter('j', this), 0, 6, topPanel, constraints);
	addComponent(new Letter('k', this), 1, 6, topPanel, constraints);
	addComponent(new Letter('l', this), 2, 6, topPanel, constraints);
	addComponent(new Letter('m', this), 3, 6, topPanel, constraints);
	addComponent(new Letter('n', this), 4, 6, topPanel, constraints);
	addComponent(new Letter('o', this), 5, 6, topPanel, constraints);
	addComponent(new Letter('p', this), 6, 6, topPanel, constraints);
	addComponent(new Letter('q', this), 7, 6, topPanel, constraints);
	addComponent(new Letter('r', this), 8, 6, topPanel, constraints);

	addComponent(new JLabel("|"), 9, 6, topPanel, constraints);

	addComponent(new Letter('s', this), 0, 7, topPanel, constraints);
	addComponent(new Letter('t', this), 1, 7, topPanel, constraints);
	addComponent(new Letter('u', this), 2, 7, topPanel, constraints);
	addComponent(new Letter('v', this), 3, 7, topPanel, constraints);
	addComponent(new Letter('w', this), 4, 7, topPanel, constraints);
	addComponent(new Letter('x', this), 5, 7, topPanel, constraints);
	addComponent(new Letter('y', this), 6, 7, topPanel, constraints);
	addComponent(new Letter('z', this), 7, 7, topPanel, constraints);

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
	try {
	    BufferedReader reader = new BufferedReader(new FileReader("../src/com/hang_man/game/wordList.txt"));

	    // Variable to count the number of lines in the file we're reading.
	    int fileLength = 0;

	    while (reader.readLine() != null)
		fileLength++;

	    // TODO: Ideal code layout:
	    // TODO: Break sub-parts into methods here.
	    // TODO: Another possible way of doing this: On the first read of the file,
	    //       store each line in a string array. Then, select a random item in that array.
	    Random roller = new Random();

	    // roller.nextInt(fileLength) + 1 contains a random number
	    // from 1 to the length of the file.
	    // e.g. for a file with 10 lines, that expression would return
	    //      random number between 1 and 10, inclousive.
	    int desiredWordLine = roller.nextInt(fileLength) + 1;

	    // Reset the file reader to start reading from the first line.
	    reader.close();
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
	    reader.close();

	} catch (FileNotFoundException e) {
	    System.out.println("File not found.");
	} catch (IOException e) {
	    System.out.println("IOException.");
	}

	// Draw the word on the board.
	drawWord(word);
    }

    /** Given a string desiredWord, draw the relevant amount of empty
     *  spaces for that word on the board. */
    public void drawWord(String desiredWord) {
	System.out.println(desiredWord.length());
	for (int i = 0; i < 9; i++) {
	    if (i < desiredWord.length())
		System.out.println(desiredWord.charAt(i));
	    // Make the character's label empty.
	    else
		wordLabels[i].setText("");
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
	    guessLetter(src.getLetter());
	    if (correctLettersGuessed == word.length()) {    
		JOptionPane.showMessageDialog(topPanel, "You win!");
	    }
	}
    }

    /** Given a char guess, see if that char occurs
     *  in the word the player is attempting to guess.
     *  If it does, show the player that they're correct.
     *  otherwise, penalize the player. */
    // TODO: Just take a String as a parameter instead?
    public void guessLetter(char guess) {
	if (word.indexOf(Character.toString(guess)) == -1 )
	    wrongGuess();
	else {
	    int index = word.indexOf(Character.toString(guess));
	    while (index != -1) {
		correctLettersGuessed++;
		System.out.println(index);
		// Enable the guessed character at all correct indexes.
		wordLabels[index].setText(Character.toString(guess));
		index = word.indexOf(Character.toString(guess), index + 1);
	    }
	}
    }

    /** When the player guesses wrong, increment the number of
     *  wrong guesses and increase the number of hang man body parts
     *  displayed. */
    public void wrongGuess() {
	if (wrongGuesses == 0) {
	    faceLeft.setVisible(true);
	    faceRight.setVisible(true);
	}
	if (wrongGuesses == 1) {
	    torsoLeft.setVisible(true);
	    torsoRight.setVisible(true);
	}
	else if (wrongGuesses == 2) {
	    leftFoot.setVisible(true);
	}
	// Here the player loses. Only 4 wrong guesses are allowed.
	else if (wrongGuesses == 3) {
	    rightFoot.setVisible(true);
	    JOptionPane.showMessageDialog(topPanel, "You lose!");
	}
	
	wrongGuesses++;
    }
}
