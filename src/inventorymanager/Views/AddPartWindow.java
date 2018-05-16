package inventorymanager.Views;

import inventorymanager.Controllers.SceneController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * This window shows the "Add Part" dialog
 */
public class AddPartWindow extends VBox {
    private final int padTop = 10, padRight = 10, padBot = 10, padLeft = 10;
    
    public AddPartWindow() {
        this.setPadding(new Insets(padTop, padRight, padBot, padLeft));
        
        Button btn = new Button("Thing");
        btn.setOnAction(e -> SceneController.activate("main"));
        
        this.getChildren().add(btn);
    }
}
