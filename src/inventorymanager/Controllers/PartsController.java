package inventorymanager.Controllers;

import inventorymanager.Settings;
import inventorymanager.Views.Parts.AddModifyPartWindow;
import inventorymanager.Views.Parts.AddModifyPartWindow.*;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * CONTROLLER FOR inventorymanager.Models.Parts.Part
 */
public class PartsController {
    
    /**
     * FORMS
     */
    public static void addForm() {
        Pane addPartPane = new AddModifyPartWindow(Type.ADD);
        
        Stage stage = new Stage();
        stage.setTitle("Add Part");
        stage.setScene(new Scene(addPartPane, Settings.skinnyWindowWidth, Settings.windowHeight));
        stage.show();
    }
}
