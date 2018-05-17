package inventorymanager.Views;

import inventorymanager.Controllers.PartsController;
import inventorymanager.Controllers.SceneController;
import inventorymanager.Models.Parts.Inhouse;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This window shows the "Add Part" dialog
 */
public class AddPartWindow extends VBox {
    private final int padTop = 10, padRight = 10, padBot = 10, padLeft = 10;
    private Pane parent;
    
    public AddPartWindow() {
        this.setPadding(new Insets(padTop, padRight, padBot, padLeft));
        
        TextField txtTest = new TextField();
        
        Button btn = new Button("Thing");
        btn.setOnAction(e -> {
            PartsController.add(new Inhouse(1232, "Car", 200.00, 2, 1, 4, 7321));
        });
        
        this.getChildren().addAll(btn, txtTest);
    }
}
