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
    public Letter(char newLetter) {
	super(new ImageIcon(newLetter + ".png"));
	setRolloverIcon(new ImageIcon(newLetter + "Rollover.png"));
    }
}
