package inventorymanager.Controllers;

import inventorymanager.Models.Parts.Part;
import inventorymanager.Settings;
import inventorymanager.Views.Parts.AddModifyPartWindow;
import inventorymanager.Views.Parts.AddModifyPartWindow.*;
import java.util.UUID;
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
        //Add part pane
        Pane addPartPane = new AddModifyPartWindow(Type.ADD);
        
        //Create window
        Stage stage = new Stage();
        stage.setTitle("Add Part");
        stage.setScene(new Scene(addPartPane, Settings.skinnyWindowWidth, Settings.windowHeight));
        stage.show();
    }
    
    public static void modForm(UUID partID) {
        //Part to be modified
        Part part = InventoryController.getPart(partID);
        
        //Modify part pane
        Pane modPartPane = new AddModifyPartWindow(Type.MODIFY, part);
        
        //Create window
        Stage stage = new Stage();
        stage.setTitle("Modify Part");
        stage.setScene(new Scene(modPartPane, Settings.skinnyWindowWidth, Settings.windowHeight));
        stage.show();
    }
}
