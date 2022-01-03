package gui;

import gui.sceneLayouts.MainSceneLayout;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class handles the GUI for the whole program and represents the main window.
 */
public class MainGUI extends Application {

    // Variables
    public static final boolean DEBUG = false;
    double[][] currentTableau;
    MainSceneLayout mainSceneLayout;
    int windowWidth = 800;
    int windowHeight = 600;

    /**
     * Launches the GUI
     */
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
        mainSceneLayout = new MainSceneLayout(this);

        primaryStage.setScene(new Scene(mainSceneLayout, windowWidth, windowHeight));
        primaryStage.show();
    }

    public void setTableau(double[][] tableau) {
        currentTableau = tableau;
        if (DEBUG)
            printTableau();
    }

    /**
     * Setter
     */
    private void printTableau() {
        System.out.println("--- Tableau: ---");
        for (int i = 0; i < currentTableau.length; i++) {
            for (int j = 0; j < currentTableau[i].length; j++) {
                System.out.print("[" + currentTableau[i][j] + "] ");
            }
            System.out.print("\n");
        }
    }

    /**
     * Getter
     * @return
     */
    public double[][] getTableau() {
        return currentTableau;
    }
}
