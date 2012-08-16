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

    /** This letter's horizontal position on the game board. */
    private int x;

    /** This letter's vertical position on the game board. */
    private int y;

    /** Create a new Letter object, with letter newLetter and
     *  coordinates (x, y). */
    public Letter(char newLetter, int newX, int newY, MainGUI gui) {
	super(Character.toString(newLetter));
	x = newX;
	y = newY;
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

    /** Get this Letter's x-coordinate. */
    public int getXCoord() {
	return x;
    }

    /** Get this Letter's y-coordinate. */
    public int getYCoord() {
	return y;
    }
}
