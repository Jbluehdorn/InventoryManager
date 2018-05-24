package inventorymanager.Views;

import inventorymanager.Settings;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * WINDOW FOR MODIFYING AND ADDING PARTS
 */
public class AddModifyProductWindow extends GridPane {
    //SETTINGS
    private final double spacing = 10.0;
    public enum Type {
        ADD,
        MODIFY
    };
    
    //DATA
    private Type windowType;
    
    public AddModifyProductWindow(Type type) {
        this.windowType = type;
        this.setPadding(new Insets(
                Settings.panePadTop,
                Settings.panePadRight,
                Settings.panePadBot,
                Settings.panePadLeft
        ));
        
        Button btn = new Button("Click this shit!");
        
        this.add(btn, 0, 0);
    }
    
}
