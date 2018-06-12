package inventorymanager.Controllers;

import inventorymanager.Models.Product;
import inventorymanager.Settings;
import inventorymanager.Views.AddModifyProductWindow;
import inventorymanager.Views.AddModifyProductWindow.*;
import java.util.Optional;
import java.util.UUID;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
    
    public static void showDeleteForm(UUID prodID) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        
        Product prod = InventoryController.getProduct(prodID);
        
        //Create alert
        alert.setTitle("Delete");
        alert.setHeaderText("Confirm Delete");
        alert.setContentText("Really delete " + prod.getName()  + "?");
        
        //Get the result
        Optional<ButtonType> result = alert.showAndWait();
        
        //Delete if confirmed
        if(result.get() == ButtonType.OK) {
            InventoryController.removeProduct(prodID);
        }
    }
}
