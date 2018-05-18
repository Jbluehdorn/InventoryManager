package inventorymanager.Controllers;

import inventorymanager.Settings;
import javafx.stage.Stage;

/**
 * CONTROLLER FOR inventorymanager.Models.Parts.Part
 */
public class PartsController {
    
    /**
     * FORMS
     */
    public static void addForm() {
        Stage stage = new Stage();
        stage.setTitle("Add Part");
        stage.setScene(SceneController.getScene("addPart", Settings.windowHeight, Settings.skinnyWindowWidth));
        stage.show();
    }
}
