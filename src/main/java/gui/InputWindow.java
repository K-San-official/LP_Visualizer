package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;


/**
 * This class represents a window where the user can input the
 * values to create a new LP.
 */
public class InputWindow extends Stage {

    // Variables
    GridPane grid;
    List<TextField> coefficientValues;
    int windowWidth = 700;
    int windowHeight = 400;
    double[][] tableau;
    int numberDecisionVariables = 0;
    int numberConstraints = 0;

    /**
     * Constructor
     */
    public InputWindow() {
        coefficientValues = new ArrayList<>();
        this.setTitle("Add new linear program");
        BorderPane borderPane = new BorderPane();

        HBox topRow = lpTopRow();
        grid = lpGridLayout();
        borderPane.setTop(topRow);
        borderPane.setCenter(grid);

        this.setScene(new Scene(borderPane, windowWidth, windowHeight));
        this.show();
    }

    /**
     * Creates the top row to set the size of an LP.
     * @return
     */
    private HBox lpTopRow() {
        HBox hBox = new HBox();

        TextField decVarField = new TextField("3");
        decVarField.setPrefWidth(30);

        TextField constrField = new TextField("3");
        constrField.setPrefWidth(30);

        Button setTableauButton = new Button("Set");
        setTableauButton.setOnAction(e -> {
            numberDecisionVariables = Integer.parseInt(decVarField.getCharacters().toString());
            numberConstraints = Integer.parseInt(constrField.getCharacters().toString());
            System.out.println(numberDecisionVariables);
            System.out.println(numberConstraints);
            initTableauMatrix();
            createInputGrid();
        });
        hBox.getChildren().addAll(
                new Text("Decision Variables: "),
                decVarField,
                new Text("Constraints: "),
                constrField,
                setTableauButton);
        hBox.setSpacing(20);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setStyle("-fx-background-color: #227799;");
        return hBox;
    }

    /**
     * Creates a new TextField, that only accepts double values.
     * @return
     */
    private TextField doubleTextField() {
        TextField textField = new TextField();
        Pattern validEditingState = Pattern.compile("-?(([1-9][0-9]*)|0)(\\.[0-9]*)?");
        UnaryOperator<TextFormatter.Change> filter = c -> {
            String text = c.getControlNewText();
            if (validEditingState.matcher(text).matches()) {
                return c;
            }
            else {
                return null;
            }
        };

        StringConverter<Double> converter = new StringConverter<Double>() {

            @Override
            public String toString(Double d) {
                return d.toString();
            }

            @Override
            public Double fromString(String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s)) {
                    return 0.0;
                }
                else {
                    return Double.valueOf(s);
                }
            }
        };
        TextFormatter<Double> textFormatter = new TextFormatter<Double>(converter, 0.0, filter);
        textField.setTextFormatter(textFormatter);

        return textField;
    }

    private GridPane lpGridLayout() {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 0, 10));
        Button testButton = new Button("Test");
        testButton.setOnAction(e -> System.out.println("Test worked"));

        return gridPane;
    }

    /**
     * Initializes the new tableau based on the number of constraints and dec. variables.
     */
    private void initTableauMatrix() {
        tableau = new double[numberDecisionVariables+1][numberConstraints+1];
    }

    /**
     * Creates the content for the input grid.
     */
    private void createInputGrid() {
        // Objective function
        grid.add(new Text("Maximize"), 0, 0);
        for (int i = 0; i < numberDecisionVariables; i++) {
            TextField newTextField = new TextField("0");
            newTextField.setPrefWidth(30);
            coefficientValues.add(newTextField);
            grid.add(newTextField, i * 3 + 1, 0);
            grid.add(new Text("x" + (i + 1)), i * 3  + 2, 0);
            if (i != numberConstraints - 1) {
                grid.add(new Text("+"), i * 3 + 3, 0);
            }
        }

        // Constraints
        grid.add(new Text("Subject to"), 0, 1);
        for (int j = 0; j < numberConstraints; j++) {
            for (int i = 0; i < numberConstraints; i++) {
                TextField newTextField = new TextField("0");
                newTextField.setPrefWidth(30);
                coefficientValues.add(newTextField);
                grid.add(newTextField, i * 3 + 1, j + 1);
                grid.add(new Text("x" + (i + 1)), i * 3  + 2,  j + 1);
                if (i != numberConstraints - 1) {
                    grid.add(new Text("+"), i * 3 + 3, j + 1);
                }
                else {
                    grid.add(new Text("<="), i * 3 + 3, j + 1);
                }
            }
            TextField newRHS = new TextField("0");
            newRHS.setPrefWidth(30);
            coefficientValues.add(newRHS);
            grid.add(newRHS, numberDecisionVariables * 4, j + 1);
        }

        // Non-negative constraints
        grid.add(new Text("and"), 0, numberConstraints + 1);
        for (int i = 0; i < numberDecisionVariables; i++) {
            grid.add(new Text("x" + (i + 1)), i + 1, numberConstraints + 1);
        }
        grid.add(new Text("<="), numberDecisionVariables + 1, numberConstraints + 1);
        grid.add(new Text("0"), numberDecisionVariables + 2, numberConstraints + 1);
    }
}
