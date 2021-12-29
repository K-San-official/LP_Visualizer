package gui;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * This class represents a window where the user can input the
 * values to create a new LP.
 */
public class InputWindow extends Stage {

    // Variables
    int windowWidth = 400;
    int windowHeight = 400;

    /**
     * Constructor
     */
    public InputWindow() {
        this.setTitle("Add new linear program");
        GridPane grid = lpGridLayout();

        this.setScene(new Scene(grid, windowWidth, windowHeight));
        this.show();
    }

    private GridPane lpGridLayout() {
        GridPane gridPane = new GridPane();
        TextField textField = new TextField("Input");

        Text text = new Text("Text");
        gridPane.add(textField, 0, 0);
        gridPane.add(text, 1, 0);


        return gridPane;
    }
}
