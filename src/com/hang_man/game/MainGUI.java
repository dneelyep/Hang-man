package com.hang_man.game;

import javax.swing.JFrame;
import javax.swing.JLabel;

/** MainGUI.java - Main class for the hang man game. This class
 *                 handles setting up the GUI and coordinating game
 *                 state changes.
 *
 * @author Daniel Neel */
public class MainGUI extends JFrame { 

    /** When the game is started, create a new MainGUI
     *  to set up GUI components. */
    public static void main(String args[]) {
	new MainGUI();
    }

    /** When a MainGUI object is created, set relevant properties for
     *  MainGUI's frame, call the method responsible for creating components,
     *  and make the frame visible. */
    public MainGUI() {
        setSize(400, 300);
	setTitle("Hang man");
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	initControls();

	pack();
	setVisible(true);
    }

    /** Create all controls that go in the interface, and place
     *  them inside this MainGUI. */
    private void initControls() {
	add(new JLabel("Testing"));
    }

11x7
}
