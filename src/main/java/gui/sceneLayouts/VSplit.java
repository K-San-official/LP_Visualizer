package gui.sceneLayouts;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class VSplit extends HBox {

    /**
     * Constructor
     */
    public VSplit() {
        this.setSpacing(20);
        this.setAlignment(Pos.CENTER);

        HBox hBox1 = new HBox();
        hBox1.setPrefWidth(200);
        hBox1.setStyle("-fx-background-color: #336699;");

        HBox hBox2 = new HBox();
        hBox2.setPrefWidth(200);

        hBox2.setStyle("-fx-background-color: #550000;");

        this.getChildren().addAll(hBox1, hBox2);
    }
}
