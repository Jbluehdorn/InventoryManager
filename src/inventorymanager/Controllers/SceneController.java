package inventorymanager.Controllers;

import java.util.HashMap;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private static HashMap<String, Scene> sceneMap = new HashMap<>();

    public static void addScene(String name, Scene scene) {
        sceneMap.put(name, scene);
    }
    
    public static void removeScreen(String name) {
        sceneMap.remove(name);
    }
    
    public static Scene getScene(String name) {
        return sceneMap.get(name);
    }
}