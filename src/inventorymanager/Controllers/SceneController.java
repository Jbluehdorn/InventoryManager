package inventorymanager.Controllers;

import inventorymanager.Settings;
import java.util.HashMap;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class SceneController {
    private static HashMap<String, Pane> paneMap = new HashMap<>();

    public static void addScene(String name, Pane pane) {
        paneMap.put(name, pane);
    }
    
    public static void removeScreen(String name) {
        paneMap.remove(name);
    }
    
    public static Scene getScene(String name,int h, int w) {
        int height, width;
        
        height = h != 0 ? h : Settings.windowHeight;
        width = w != 0 ? w : Settings.windowWidth;
        
        return new Scene(paneMap.get(name), width, height);
    }
}