package gui.sceneLayouts;

import gui.menu.TopMenuBar;
import javafx.scene.layout.BorderPane;
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

        // Add top menu to the main layout
        this.setTop(topMenuVBox);

        VSplit vSplit = new VSplit();
        this.setCenter(vSplit);
    }
}
