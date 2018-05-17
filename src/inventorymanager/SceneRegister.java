package inventorymanager;

import inventorymanager.Controllers.SceneController;
import inventorymanager.Views.*;
import javafx.scene.Scene;

/**
 * This class registers all scenes 
 */
public class SceneRegister {
    public SceneRegister() {
        this.registerScenes();
    }
    
    private void registerScenes() {
        MainWindow mainWindow = new MainWindow();
        AddPartWindow addPartWindow = new AddPartWindow();
        
        SceneController.addScene("main", mainWindow);
        SceneController.addScene("addPart", addPartWindow);
    }
}
