package com.hang_man.game;

import javax.swing.JButton;
import javax.swing.ImageIcon;

/** Letter.java - A button that represents a letter a player can click
 *                to guess a letter in the current word.
 *
 * @author Daniel Neel */
public class Letter extends JButton {
    /** The letter this Letter object represents. */
    private char letter;

    /** Create a new Letter object, with letter newLetter */
    public Letter(char newLetter, MainGUI gui) {
	super(Character.toString(newLetter));
	//	super(new ImageIcon(newLetter + ".png"));
	letter = newLetter;
	setRolloverIcon(new ImageIcon(letter + "Rollover.png"));
	setActionCommand(Character.toString(letter));
	addActionListener(gui);
    }

    /** Get the character this Letter represents. */
    public char getLetter() {
	return letter;
    }
}
