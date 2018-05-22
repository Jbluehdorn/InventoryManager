package inventorymanager.Views;

import inventorymanager.Views.Partials.DataTable;
import inventorymanager.Views.Partials.DataTable.*;
import inventorymanager.Settings;
import javafx.application.Platform;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class MainWindow extends BorderPane {
    //SETTINGS
    private final int middleVGap = 8, middleHGap = 10;
    
    //COMPONENTS
    private Label lblTop;
    private HBox boxTop, boxBottom;
    private GridPane paneMiddle;
    private DataTable partsTable;
    private DataTable productsTable;
    private Button btnExit;
    
    public MainWindow() {
        this.setPadding(new Insets(Settings.panePadTop, Settings.panePadRight, Settings.panePadBot, Settings.panePadLeft));
        this.initializeComponents();
        this.populateTop();
        this.populateCenter();
        this.populateBottom();
    }   
    
    /**
     * INITIALIZE ALL COMPONENTS
     */
    private void initializeComponents() {
        this.lblTop = new Label("Inventory Management System");
        this.lblTop.setFont(Font.font(Settings.font, Settings.headerTextSize));
                
        this.boxTop = new HBox();
        this.boxBottom = new HBox();
        
        this.paneMiddle = new GridPane();
        this.paneMiddle.setPadding(new Insets(Settings.panePadTop, 0, Settings.panePadBot, 0));
        this.paneMiddle.setVgap(middleVGap);
        this.paneMiddle.setHgap(middleHGap);
        
        this.partsTable = new DataTable(Type.PARTS);
        
        this.productsTable = new DataTable(Type.PRODUCTS);
        
        this.btnExit = new Button("Exit");
        this.btnExit.setPadding(new Insets(Settings.btnPadTop, Settings.btnPadRight, Settings.btnPadBot, Settings.btnPadLeft));
        this.btnExit.setOnAction(e -> Platform.exit());
    }
    
    /**
     * POPULATES THE TOP SECTION
     */
    private void populateTop() {
        this.boxTop.getChildren().add(lblTop);
        this.setTop(this.boxTop);
    }
    
    /**
     * POPULATES THE CENTER SECTION
     */
    private void populateCenter() {
        GridPane.setConstraints(this.partsTable, 0, 0);
        GridPane.setConstraints(this.productsTable, 1, 0);
        
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(50);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        
        this.paneMiddle.getColumnConstraints().addAll(col1, col2);
        this.paneMiddle.getChildren().addAll(this.partsTable, this.productsTable);
        
        this.setCenter(this.paneMiddle);
    }
    
    /**
     * POPULATES THE BOTTOM SECTION
     */
    private void populateBottom() {
        this.boxBottom.setAlignment(Pos.BASELINE_RIGHT);
        
        this.boxBottom.getChildren().addAll(this.btnExit);
        
        this.setBottom(this.boxBottom);
    }
}