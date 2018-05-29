package inventorymanager.Views;

import inventorymanager.Settings;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * WINDOW FOR MODIFYING AND ADDING PARTS
 */
public class AddModifyProductWindow extends GridPane {
    //SETTINGS
    private final double spacing = 10.0;
    public enum Type {
        ADD,
        MODIFY
    };
    
    //COMPONENTS
    Label lblMain;
    VBox boxLeftCol, boxRightCol;
    
    //DATA
    private Type windowType;
    private String windowLabel;
    
    public AddModifyProductWindow(Type type) {
        //Settings
        this.windowType = type;
        this.setPadding(new Insets(
                Settings.panePadTop,
                Settings.panePadRight,
                Settings.panePadBot,
                Settings.panePadLeft
        ));
        
        switch(this.windowType) {
            case ADD:
                this.windowLabel = "Add Product";
                break;
            case MODIFY:
                this.windowLabel = "Modify Product";
                break;
        }
        
        //Size columns correctly
        this.createColumnConstraints();
        
        //Create Components
        this.initializeComponents();
        this.populatePane();
    }
    
    /**
     * INITIALIZES ALL COMPONENTS
     */
    private void initializeComponents() {
        this.boxLeftCol = new VBox();
        this.boxRightCol = new VBox();
        
        this.lblMain = new Label(this.windowLabel);
        this.lblMain.setFont(Font.font(
                Settings.font, 
                Settings.headerTextSize
        ));
    }
    
    /**
     * SPACES COLUMNS CORRECTLY
     */
    private void createColumnConstraints() {
        ColumnConstraints col1Cons, col2Cons;
        col1Cons = new ColumnConstraints();
        col2Cons = new ColumnConstraints();
        
        col1Cons.setPercentWidth(30);
        col2Cons.setPercentWidth(70);
        
        this.getColumnConstraints().addAll(col1Cons, col2Cons);
    }
    
    /**
     * POPULATES THE WINDOW WITH ALL COMPONENTS
     */
    private void populatePane() {
        Label lbl2 = new Label("Column 2");
        
        //LEFT COLUMN BOX
        GridPane.setConstraints(this.boxLeftCol, 0, 0);
        this.boxLeftCol.getChildren().addAll(
                this.lblMain
        );
        
        //RIGHT COLUMN BOX
        GridPane.setConstraints(this.boxRightCol, 1, 0);
        this.boxRightCol.getChildren().addAll(
                lbl2
        );
        
        this.getChildren().addAll(this.boxLeftCol, this.boxRightCol);
    }
}
