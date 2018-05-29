package inventorymanager.Controllers;

import inventorymanager.Settings;
import inventorymanager.Views.AddModifyProductWindow;
import inventorymanager.Views.AddModifyProductWindow.*;
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
}
