package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * This class represents a window where the user can input the
 * values to create a new LP.
 */
public class InputWindow extends Stage {

    // Variables
    int windowWidth = 700;
    int windowHeight = 400;
    double[][] tableau;
    int numberDecisionVariables = 0;
    int numberConstraints = 0;

    /**
     * Constructor
     */
    public InputWindow() {
        this.setTitle("Add new linear program");
        BorderPane borderPane = new BorderPane();

        HBox topRow = lpTopRow();
        GridPane grid = lpGridLayout();
        borderPane.setCenter(grid);

        this.setScene(new Scene(borderPane, windowWidth, windowHeight));
        this.show();
    }

    private HBox lpTopRow() {
        HBox hBox = new HBox();
        TextField decVarField = new TextField("3");
        TextField constrField = new TextField("3");
        Button setTableauButton = new Button("Set");
        setTableauButton.setOnAction(e -> {
            numberDecisionVariables = (double) decVarField.get;
        });

        hBox.getChildren().addAll(new Text("Decision Variables: "), new Text("Constraints"));
        return hBox;
    }

    private GridPane lpGridLayout() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 0, 10));
        Button testButton = new Button("Test");
        testButton.setOnAction(e -> System.out.println("Test worked"));

        // Add text labels
        gridPane.add(new Text("Maximize"), 0, 0);
        gridPane.add(new Text("Subject to"), 0, 1);
        gridPane.add(testButton, 1, 0);

        return gridPane;
    }

    private void initTableauMatrix() {
        tableau = new double[numberDecisionVariables][numberConstraints];
    }
}
