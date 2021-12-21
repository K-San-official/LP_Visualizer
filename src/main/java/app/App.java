package app;

import controller.MainController;

/**
 * This class launches the program and acts like a start-trigger.
 */
public class App {

    /**
     * Creates a new controller instance to cause a chain reaction
     * that starts all necessary components.
     * @param args
     */
    public static void main(String[] args) {
        MainController controller = new MainController();
    }

}
