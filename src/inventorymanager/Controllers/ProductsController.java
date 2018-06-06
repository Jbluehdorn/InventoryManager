package inventorymanager.Controllers;

import inventorymanager.Models.Product;
import inventorymanager.Settings;
import inventorymanager.Views.AddModifyProductWindow;
import inventorymanager.Views.AddModifyProductWindow.*;
import java.util.UUID;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Jordan
 */
public class ProductsController {
    /**
     * FORMS
     */
    public static void showAddForm() {
        //Get the Pane
        Pane addProductPane = new AddModifyProductWindow(Type.ADD);
        
        //Create window
        Stage stage = new Stage();
        stage.setTitle("Add Product");
        stage.setScene(new Scene(addProductPane, Settings.windowWidth * 1.5, Settings.windowHeight));
        stage.show();
    }
    
    public static void showModForm(UUID productID) {
        //Get the product to edit
        Product prod = InventoryController.getProduct(productID);
        
        //Get the Pane
        Pane modProductPane = new AddModifyProductWindow(Type.MODIFY, prod);
        
        //Create window
        Stage stage = new Stage();
        stage.setTitle("Modify Product");
        stage.setScene(new Scene(modProductPane, Settings.windowWidth * 1.5, Settings.windowHeight));
        stage.show();
    }
}
