package inventorymanager.Views.Partials;

import inventorymanager.Util.StringUtility;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

/**
 * DATATABLE COMPONENT FOR PARTS AND PRODUCTS
 */
public class DataTable extends BorderPane {
    //SETTINGS
    private final int padTop = 10, padRight = 10, padBot = 10, padLeft = 10;
    private String type;
    private String font = "Arial";
    private int headerTextSize = 20;
    private int bodyTextSize = 12;
    
    //COMPONENTS
    private HBox boxTop;
    private Label lblPrimary;
    
    public DataTable(String type) {
        if(!type.toLowerCase().equals("parts") && !type.toLowerCase().equals("products"))
            throw new IllegalArgumentException("TYPE MUST BE PARTS OR PRODUCTS. '" + type + "' GIVEN.");
        
        //Set the type
        this.type = type.toLowerCase();
        
        //Create pane settings
        this.setPadding(new Insets(padTop, padRight, padBot, padLeft));
        this.setBorder(new Border(new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        
        //Create and place components
        this.initializeComponents();
        this.populateTop();
    }
    
    /**
     * INITIALIZES ALL COMPONENTS
     */
    private void initializeComponents() {
        this.lblPrimary = new Label(StringUtility.capitalizeFirst(this.type));
        this.lblPrimary.setFont(Font.font(this.font, this.headerTextSize));
        
        this.boxTop = new HBox();
    }
    
    /**
     * POPULATES THE TOP SECTION
     */
    private void populateTop() {
        this.boxTop.getChildren().addAll(this.lblPrimary);
        this.setTop(this.boxTop);
    }
}
