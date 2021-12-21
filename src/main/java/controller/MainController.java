package controller;

import gui.MainGUI;

/**
 * This class is the connection between front- and back-end.
 * It can be seen as the core of the program.
 */
public class MainController {

    // Variables
    MainGUI mainGUI;

    /**
     * Constructor that triggers the start of the GUI.
     */
    public MainController() {
        mainGUI = new MainGUI();
        mainGUI.launchGUI();
    }

}
