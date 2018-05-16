/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import inventorymanager.Controllers.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Jordan
 */
public class InventoryManager extends Application {
    //SETTINGS
    private final String title = "Inventory Manager";
    
    //COMPONENTS
    private Stage window;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle(title);
        window.setResizable(false);
        SceneController sceneController = new SceneController(window);
        SceneRegister register = new SceneRegister(sceneController);
        
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
