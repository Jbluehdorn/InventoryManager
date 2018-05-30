/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager.Views.Partials;

import inventorymanager.Models.Parts.Part;
import inventorymanager.Settings;
import java.util.ArrayList;
import java.util.UUID;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

/**
 *
 * @author Jordan
 */
public class ProductPartTable extends VBox {
    //SETTINGS
    private double spacing = 5.0;
    public enum Type {
        NEW,
        MOD
    }
    
    //COMPONENTS
    private TextField txtSearch;
    private Button btnAdd, btnDel;
    private TableView tblRemainingParts, tblProductParts;
    
    //DATA
    private Type type;
    private ArrayList<Part> remainingParts, productParts;
    
    public ProductPartTable(Type type) {
        this.type = type;
        
        //Settings
        this.setAlignment(Pos.TOP_RIGHT);
        this.setSpacing(this.spacing);
        this.setPadding(new Insets(
                Settings.panePadTop,
                0,
                Settings.panePadBot,
                0
        ));
        
        //Initialization
        this.initializeComponents();
        this.populateTable();
    }
    
    public ProductPartTable(Type type, UUID productID) {
        this(type);
    }
    
    /**
     * INITIALIZES ALL COMPONENTS
     */
    private void initializeComponents() {
        this.txtSearch = new TextField();
        this.txtSearch.setPromptText("Search...");
        
        this.btnAdd = new Button("Add");
        this.btnAdd.setPadding(new Insets(
                Settings.btnPadTop,
                Settings.btnPadRight,
                Settings.btnPadBot,
                Settings.btnPadLeft
        ));
        
        this.btnDel = new Button("Delete");
        this.btnDel.setPadding(new Insets(
                Settings.btnPadTop,
                Settings.btnPadRight,
                Settings.btnPadBot,
                Settings.btnPadLeft
        ));
        
        this.tblProductParts = new TableView();
        this.tblRemainingParts = new TableView();
        
        this.remainingParts = new ArrayList<>();
        this.productParts = new ArrayList<>();
    }
    
    /**
     * POPULATES THE PANE
     */
    private void populateTable() {
        this.loadAllParts();
        
        this.getChildren().addAll(
                this.txtSearch,
                this.tblRemainingParts,
                this.btnAdd,
                this.tblProductParts,
                this.btnDel
        );
    }
    
    /**
     * LOAD ALL PARTS INTO REMAINING PARTS AND PRODUCT PARTS
     */
    private void loadAllParts() {
        this.loadRemainingParts();
        this.loadProductParts();
    }
    
    private void loadRemainingParts() {
        
    }
    
    private void loadProductParts() {
        
    }
}
