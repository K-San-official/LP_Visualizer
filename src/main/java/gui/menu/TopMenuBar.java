package gui.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * This class represents the top menu bar for the application.
 */
public class TopMenuBar extends MenuBar {

    // Variables

    // --- File ---
    Menu fileMenu;
    MenuItem newMenuItem;
    MenuItem saveMenuItem;
    MenuItem openMenuItem;

    // --- Edit ---
    Menu editMenu;
    MenuItem placeholderMenuItem;

    // --- View ---
    Menu viewMenu;
    MenuItem sizeMenuItem;

    /**
     * Constructor
     */
    public TopMenuBar() {

        // --- File ---
        fileMenu = new Menu("File");
        newMenuItem = new MenuItem("New");
        newMenuItem.setOnAction(e -> openInputWindow());
        saveMenuItem = new MenuItem("Save");
        openMenuItem = new MenuItem("Open");
        fileMenu.getItems().addAll(newMenuItem, saveMenuItem, openMenuItem);

        // --- Edit ---
        editMenu = new Menu("Edit");
        placeholderMenuItem = new MenuItem("Placeholder");
        editMenu.getItems().addAll(placeholderMenuItem);

        // --- View ---
        viewMenu = new Menu("View");
        sizeMenuItem = new MenuItem("Size");
        viewMenu.getItems().addAll(sizeMenuItem);

        this.getMenus().addAll(fileMenu, editMenu, viewMenu);
    }

    private void openInputWindow() {

    }
}
