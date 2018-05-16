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
        
        SceneController.addScene("main", new Scene(mainWindow, Settings.windowWidth*2, Settings.windowHeight));
        SceneController.addScene("addPart", new Scene(addPartWindow, Settings.windowWidth, Settings.windowHeight));
    }
}
