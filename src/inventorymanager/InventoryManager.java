/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import inventorymanager.Controllers.SceneController;
import inventorymanager.Views.MainWindow;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
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
