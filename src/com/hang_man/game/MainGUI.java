package com.hang_man.game;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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

    /** The number of games this player has won. */
    private int wins = 0;

    /** The number of games this player has lost. */
    private int losses = 0;

    /** The widget that displays the player's win count. */
    private JTextField winsLabel = new JTextField(Integer.toString(wins));

    /** The widget that displays the player's loss count. */
    private JTextField lossesLabel = new JTextField(Integer.toString(losses));

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

    /** All the letters that players can click on to guess part of the word. */
    private Letter a = new Letter('a', this);
    private Letter b = new Letter('b', this);
    private Letter c = new Letter('c', this);
    private Letter d = new Letter('d', this);
    private Letter e = new Letter('e', this);
    private Letter f = new Letter('f', this);
    private Letter g = new Letter('g', this);
    private Letter h = new Letter('h', this);
    private Letter i = new Letter('i', this);
    private Letter j = new Letter('j', this);
    private Letter k = new Letter('k', this);
    private Letter l = new Letter('l', this);
    private Letter m = new Letter('m', this);
    private Letter n = new Letter('n', this);
    private Letter o = new Letter('o', this);
    private Letter p = new Letter('p', this);
    private Letter q = new Letter('q', this);
    private Letter r = new Letter('r', this);
    private Letter s = new Letter('s', this);
    private Letter t = new Letter('t', this);
    private Letter u = new Letter('u', this);
    private Letter v = new Letter('v', this);
    private Letter w = new Letter('w', this);
    private Letter x = new Letter('x', this);
    private Letter y = new Letter('y', this);
    private Letter z = new Letter('z', this);

    /** All the letters you can click on, put into
     *  an array for convenient looping. */
    private Letter[] letters = {a, b, c, d, e, f, g, h, i, j,
				k, l, m, n, o, p, q, r, s, t,
				u, v, w, x, y, z};
    
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

	// Player shouldn't be able to edit the # of times they've won.
	winsLabel.setEditable(false);
	lossesLabel.setEditable(false);

	// Since the player hasn't made any mistakes yet, the cartoon being hanged
	// is not yet visible.
	faceLeft.setVisible(false);
	faceRight.setVisible(false);
	torsoLeft.setVisible(false);
	torsoRight.setVisible(false);
	leftFoot.setVisible(false);
	rightFoot.setVisible(false);	

	GridBagConstraints constraints = new GridBagConstraints();
	addComponent(new JLabel(new ImageIcon("../images/nooseTopLeft.png")),   10, 0, topPanel, constraints);
	addComponent(new JLabel(new ImageIcon("../images/nooseTopMid.png")),    11, 0, topPanel, constraints);
	addComponent(new JLabel(new ImageIcon("../images/nooseTopCorner.png")), 12, 0, topPanel, constraints);

	addComponent(faceLeft, 10, 1, topPanel, constraints);
	addComponent(faceRight, 11, 1, topPanel, constraints);
	addComponent(new JLabel(new ImageIcon("../images/nooseTopRight.png")), 12, 1, topPanel, constraints);

	addComponent(torsoLeft, 10, 2, topPanel, constraints);
	addComponent(torsoRight, 11, 2, topPanel, constraints);
	addComponent(new JLabel(new ImageIcon("../images/nooseRight.png")), 12, 2, topPanel, constraints);

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

	// Add the vertical separator between the board and win stats.
	constraints.gridheight = 8;
	constraints.fill = GridBagConstraints.VERTICAL;
	constraints.insets = new Insets(0, 20, 0, 20);
	constraints.gridx = 9;
	constraints.gridy = 0;
	topPanel.add(new JSeparator(SwingConstants.VERTICAL), constraints);
	constraints.gridheight = 1;
	constraints.insets = new Insets(2, 2, 2, 2);

	addComponent(a, 0, 5, topPanel, constraints);
	addComponent(b, 1, 5, topPanel, constraints);
	addComponent(c, 2, 5, topPanel, constraints);
	addComponent(d, 3, 5, topPanel, constraints);
	addComponent(e, 4, 5, topPanel, constraints);
	addComponent(f, 5, 5, topPanel, constraints);
	addComponent(g, 6, 5, topPanel, constraints);
	addComponent(h, 7, 5, topPanel, constraints);
	addComponent(i, 8, 5, topPanel, constraints);

	addComponent(j, 0, 6, topPanel, constraints);
	addComponent(k, 1, 6, topPanel, constraints);
	addComponent(l, 2, 6, topPanel, constraints);
	addComponent(m, 3, 6, topPanel, constraints);
	addComponent(n, 4, 6, topPanel, constraints);
	addComponent(o, 5, 6, topPanel, constraints);
	addComponent(p, 6, 6, topPanel, constraints);
	addComponent(q, 7, 6, topPanel, constraints);
	addComponent(r, 8, 6, topPanel, constraints);

	addComponent(s, 0, 7, topPanel, constraints);
	addComponent(t, 1, 7, topPanel, constraints);
	addComponent(u, 2, 7, topPanel, constraints);
	addComponent(v, 3, 7, topPanel, constraints);
	addComponent(w, 4, 7, topPanel, constraints);
	addComponent(x, 5, 7, topPanel, constraints);
	addComponent(y, 6, 7, topPanel, constraints);
	addComponent(z, 7, 7, topPanel, constraints);

	constraints.gridwidth = 3;
	constraints.fill = GridBagConstraints.HORIZONTAL;
	JButton quit = new JButton("Quit");
	quit.addActionListener(this);
	addComponent(quit, 10, 7, topPanel, constraints);

	constraints.gridwidth = 1;

	addComponent(new JLabel("Wins:"), 10, 5, topPanel, constraints);
	addComponent(new JLabel("Losses:"), 10, 6, topPanel, constraints);

	addComponent(winsLabel, 12, 5, topPanel, constraints);
	addComponent(lossesLabel, 12, 6, topPanel, constraints);
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
	    reader.close();

	} catch (FileNotFoundException e) {
	    System.out.println("File not found.");
	} catch (IOException e) {
	    System.out.println("IOException.");
	}

	// Draw the blank spaces for the word on the board.
	drawWordBlanks(word);
    }

    /** Given a string desiredWord, draw the relevant amount of empty
     *  spaces to the right of that word on the board. */
    public void drawWordBlanks(String desiredWord) {
	for (int i = 0; i < 9; i++) {
	    if (i >= desiredWord.length())
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
	    src.setEnabled(false);
	    guessLetter(Character.toString(src.getLetter()));
	}
    }

    /** Given a String guess, see if that String occurs
     *  in the word the player is attempting to guess.
     *  If it does, show the player that they're correct.
     *  otherwise, penalize the player. */
    // TODO: Making the parameter a String cuts down dup. code, but
    //       is semantically more confusing. Which to do?
    public void guessLetter(String guess) {
	if (word.indexOf(guess) == -1 )
	    wrongGuess();
	else {
	    int index = word.indexOf(guess);
	    while (index != -1) {
		correctLettersGuessed++;
		// Enable the guessed character at all correct indexes.
		wordLabels[index].setText(guess);
		index = word.indexOf(guess, index + 1);
	    }
	}

	if (correctLettersGuessed == word.length()) {
	    wins++;
	    winsLabel.setText(Integer.toString(wins));
	    JOptionPane.showMessageDialog(topPanel, "You win!");
	    resetBoard();
	}
    }

    /** When the player guesses wrong, increment the number of
     *  wrong guesses and increase the number of hang man body parts
     *  displayed. */
    public void wrongGuess() {
	if (wrongGuesses == 0) {
	    faceLeft.setVisible(true);
	    faceRight.setVisible(true);
	    wrongGuesses++;
	}
	else if (wrongGuesses == 1) {
	    torsoLeft.setVisible(true);
	    wrongGuesses++;
	}
	else if (wrongGuesses == 2) {
	    torsoRight.setVisible(true);
	    wrongGuesses++;
	}
	// Here the player loses. Only 4 wrong guesses are allowed.
	else if (wrongGuesses == 3) {
	    leftFoot.setVisible(true);
	    wrongGuesses++;
	}
	else if (wrongGuesses == 4) {
	    rightFoot.setVisible(true);
	    // LEFTOFFHERE: Just added these extra pictures when the player dies.
	    //              Is causing some bugs that I need to fix. I think
	    //              resetBoard() now needs to do a faceLeft/Right.setIcon()
	    //              back to their regular icons.
	    faceLeft.setIcon(new ImageIcon("../images/faceLeftDead.png"));
	    faceRight.setIcon(new ImageIcon("../images/faceRightDead.png"));
	    losses++;
	    lossesLabel.setText(Integer.toString(losses));
	    JOptionPane.showMessageDialog(topPanel, "You lose! The word was " + word + ".");
	    resetBoard();
	}
    }

    /** Select a new word for the game, and reset all GUI components so that
     *  the player can play another game. */
    public void resetBoard() {
	for (JLabel label : wordLabels)
	    label.setText("_");

	for (JButton button : letters)
	    button.setEnabled(true);

	faceLeft.setVisible(false);
	faceRight.setVisible(false);
	torsoLeft.setVisible(false);
	torsoRight.setVisible(false);
	leftFoot.setVisible(false);
	rightFoot.setVisible(false);

	wrongGuesses = 0;
	correctLettersGuessed = 0;

	setGameWord();
    }
}
