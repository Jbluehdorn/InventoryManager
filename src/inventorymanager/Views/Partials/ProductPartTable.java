/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager.Views.Partials;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jordan
 */
public class ProductPartTable extends VBox {
    
    public ProductPartTable() {
        
        this.initializeComponents();
        this.populateTable();
    }
    
    /**
     * INITIALIZES ALL COMPONENTS
     */
    private void initializeComponents() {
        
    }
    
    /**
     * POPULATES THE PANE
     */
    private void populateTable() {
        this.getChildren().add(new Label("Hello!"));
    }
}
