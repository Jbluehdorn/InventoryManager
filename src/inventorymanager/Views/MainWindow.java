package inventorymanager.Views;

import java.util.HashMap;
import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class MainWindow extends BorderPane {
    //SETTINGS
    private final int padTop = 10, padRight = 10, padBot = 10, padLeft = 10;
    private final String font = "Arial";
    private final int headerTextSize = 24;
    private final int bodyTextSize = 12;
    
    //COMPONENTS
    private Label topLabel;
    private HBox topBox;
    
    public MainWindow() {
        this.setPadding(new Insets(padTop, padRight, padBot, padLeft));
        this.initializeComponents();
        this.populateTop();
    }   
    
    /**
     * INITIALIZE ALL COMPONENTS
     */
    private void initializeComponents() {
        this.topLabel = new Label("Inventory Management System");
        this.topLabel.setFont(Font.font(font, headerTextSize));
                
        this.topBox = new HBox();
    }
    
    /**
     * POPULATES THE TOP SECTION
     */
    private void populateTop() {
        this.topBox.getChildren().add(topLabel);
        this.setTop(this.topBox);
    }
}