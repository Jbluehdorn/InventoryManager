package inventorymanager.Controllers;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private static HashMap<String, Scene> sceneMap = new HashMap<>();
    private static Stage window;

    public SceneController(Stage window) {
        this.window = window;
    }

    public void addScene(String name, Scene scene) {
        sceneMap.put(name, scene);
    }
    
    public void removeScreen(String name) {
        sceneMap.remove(name);
    }
    
    public static void activate(String name) {
        window.setScene(sceneMap.get(name));
    }
}