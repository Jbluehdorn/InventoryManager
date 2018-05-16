package inventorymanager.Views;

import inventorymanager.Views.Partials.DataTable;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class MainWindow extends BorderPane {
    //SETTINGS
    private final int padTop = 10, padRight = 10, padBot = 10, padLeft = 10;
    private final int middleVGap = 8, middleHGap = 10;
    private final String font = "Arial";
    private final int headerTextSize = 24;
    private final int bodyTextSize = 12;
    
    //COMPONENTS
    private Label topLabel;
    private HBox topBox;
    private GridPane middlePane;
    private DataTable partsTable;
    private DataTable productsTable;
    
    public MainWindow() {
        this.setPadding(new Insets(padTop, padRight, padBot, padLeft));
        this.initializeComponents();
        this.populateTop();
        this.populateCenter();
    }   
    
    /**
     * INITIALIZE ALL COMPONENTS
     */
    private void initializeComponents() {
        this.topLabel = new Label("Inventory Management System");
        this.topLabel.setFont(Font.font(font, headerTextSize));
                
        this.topBox = new HBox();
        
        this.middlePane = new GridPane();
        this.middlePane.setPadding(new Insets(padTop, padRight, padBot, padLeft));
        this.middlePane.setVgap(middleVGap);
        this.middlePane.setHgap(middleHGap);
        
        this.partsTable = new DataTable("parts");
        
        this.productsTable = new DataTable("products");
    }
    
    /**
     * POPULATES THE TOP SECTION
     */
    private void populateTop() {
        this.topBox.getChildren().add(topLabel);
        this.setTop(this.topBox);
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
        
        this.middlePane.getColumnConstraints().addAll(col1, col2);
        this.middlePane.getChildren().addAll(this.partsTable, this.productsTable);
        
        this.setCenter(this.middlePane);
    }
}