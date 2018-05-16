/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import inventorymanager.Controllers.SceneController;
import inventorymanager.Models.Parts.*;
import inventorymanager.Models.*;
import inventorymanager.Controllers.*;
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
        
        this.populateTestData();

        
        SceneRegister register = new SceneRegister();
        window.setScene(SceneController.getScene("main"));
        
        
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void populateTestData() {
        Part part1 = new Inhouse(532, "Banana", 3.50, 20, 1, 200, 8732);
        Part part2 = new Inhouse(983, "Orange", 3.20, 15, 1, 200, 6419);
        Part part3 = new Outsourced(187, "Gun", 125.99, 7, 5, 20, "Canada Corp.");
        Part part4 = new Outsourced(692, "Butter", 1.25, 50, 20, 100, "Butter Inc.");
        
        PartsController.add(part1);
        PartsController.add(part2);
        PartsController.add(part3);
        PartsController.add(part4);
    }
    
}
