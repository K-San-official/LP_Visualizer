package gui;

import gui.menu.TopMenuBar;
import gui.scenes.MainScreen;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class handles the GUI for the whole program.
 */
public class MainGUI extends Application {

    // Variables
    int windowWidth = 800;
    int windowHeight = 600;

    public void launchGUI() {
        String[] args = new String[0];
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set.
     *                     Applications may create other stages, if needed, but they will not be
     *                     primary stages.
     * @throws Exception if something goes wrong
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("LP Visualizer");

        // Create layout to divide window
        BorderPane mainLayout = new MainScreen();

        // Add top menu
        TopMenuBar topMenuBar = new TopMenuBar();
        VBox topMenuVBox = new VBox(topMenuBar);

        // Add top menu to the main layout
        mainLayout.setTop(topMenuVBox);

        /*
        // Sample buttons
        Button centerButton = new Button("Center");
        Button topButton1 = new Button("Top1");
        Button topButton2 = new Button("Top2");
        Button topButton3 = new Button("Top3");
        Button topButton4 = new Button("Top4");
        Button bottomButton = new Button("Bottom");
        Button leftButton = new Button("Left");
        Button rightButton = new Button("Right");

        HBox topBox = new HBox();
        topBox.getChildren().addAll(topButton1, topButton2, topButton3, topButton4);
        topBox.setPadding(new Insets(15, 15, 15, 15));
        topBox.setSpacing(15);
        topBox.setStyle("-fx-background-color: #336699;");

        mainLayout.setCenter(centerButton);
        mainLayout.setTop(topBox);
        mainLayout.setBottom(bottomButton);
        mainLayout.setLeft(leftButton);
        mainLayout.setRight(rightButton);

        // root.getChildren().add(btn);
         */

        primaryStage.setScene(new Scene(mainLayout, windowWidth, windowHeight));
        primaryStage.show();
    }
}
