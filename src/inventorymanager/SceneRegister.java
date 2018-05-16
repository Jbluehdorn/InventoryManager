package inventorymanager;

import inventorymanager.Controllers.SceneController;
import inventorymanager.Views.*;
import javafx.scene.Scene;

/**
 * This class registers all scenes 
 */
public class SceneRegister {
    private SceneController controller;
    private final int h = 300, w = 500;
    
    public SceneRegister(SceneController controller) {
        this.controller = controller;
        this.registerScenes();
    }
    
    private void registerScenes() {
        MainWindow mainWindow = new MainWindow();
        AddPartWindow addPartWindow = new AddPartWindow();
        
        this.controller.addScene("main", new Scene(mainWindow, w*2, h));
        this.controller.addScene("addPart", new Scene(addPartWindow, w, h));
        
        SceneController.activate("main");
    }
}
