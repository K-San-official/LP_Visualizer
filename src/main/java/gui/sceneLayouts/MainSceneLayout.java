package gui.sceneLayouts;

import gui.menu.TopMenuBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * This class represents the main scene of the Application
 * and holds all the displayed content
 */
public class MainSceneLayout extends BorderPane {

    public MainSceneLayout() {
        // Add top menu
        TopMenuBar topMenuBar = new TopMenuBar();
        VBox topMenuVBox = new VBox(topMenuBar);
        this.setTop(topMenuVBox);

        // Add main grid
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(0, 10, 0, 10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Placeholder elements to fill the grid
        VBox topLeft = new VBox();
        topLeft.setStyle("-fx-background-color: #227799;");
        topLeft.setPrefSize(200, 200);

        VBox topRight = new VBox();
        topRight.setStyle("-fx-background-color: #229977;");
        topRight.setPrefSize(200, 200);

        VBox bottomLeft = new VBox();
        bottomLeft.setStyle("-fx-background-color: #997722;");
        bottomLeft.setPrefSize(200, 200);

        VBox bottomRight = new VBox();
        bottomRight.setStyle("-fx-background-color: #992277;");
        bottomRight.setPrefSize(200, 200);

        // Add elements to grid
        gridPane.add(topLeft, 0, 0);
        gridPane.add(topRight, 1, 0);
        gridPane.add(bottomLeft, 0, 1);
        gridPane.add(bottomRight, 1, 1);

        // Add grid layout to main layout
        this.setCenter(gridPane);
    }
}
