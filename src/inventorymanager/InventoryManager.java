/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import inventorymanager.Models.Parts.*;
import inventorymanager.Controllers.*;
import java.util.ArrayList;
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
        this.populateTestData();
        
        HomeController.MainWindow();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    public static void populateTestData() {
        ArrayList<Part> parts = new ArrayList<>();
        parts.add(new Inhouse("Banana", 3.50, 20, 1, 200, 8732));
        parts.add(new Inhouse("Orange", 3.20, 15, 1, 200, 6419));
        parts.add(new Outsourced("Gun", 125.99, 7, 5, 20, "Canada Corp."));
        parts.add(new Outsourced("Butter", 1.25, 50, 20, 100, "Butter Inc."));
        
        for(Part part : parts) {
            InventoryController.addPart(part);
        }
    }
    
}
