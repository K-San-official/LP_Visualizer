package gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class represents a window where the user can input the
 * values to create a new LP.
 */
public class InputWindow extends Stage {

    // Variables
    int windowWidth = 300;
    int windowHeight = 300;

    /**
     * Constructor
     */
    public InputWindow() {
        this.setTitle("Add new linear program");
        this.setScene(new Scene(new BorderPane(), windowWidth, windowHeight));
    }
}
