package inventorymanager.Views;

import inventorymanager.Controllers.InventoryController;
import inventorymanager.Models.Parts.Part;
import inventorymanager.Models.Product;
import inventorymanager.Settings;
import inventorymanager.Views.Partials.ProductPartTable;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * WINDOW FOR MODIFYING AND ADDING PARTS
 */
public class AddModifyProductWindow extends BorderPane {
    //SETTINGS
    private final double formHGap = 5.0;
    private final double formVGap = 4.0;
    public enum Type {
        ADD,
        MODIFY
    };
    
    //COMPONENTS
    private Label 
            lblMain,
            lblID,
            lblName,
            lblInv,
            lblPrice,
            lblMax,
            lblMin;
    private TextField 
            txtID,
            txtName,
            txtInv,
            txtPrice,
            txtMax,
            txtMin;
    private VBox boxLeftCol, boxRightCol;
    private HBox boxBtns;
    private GridPane gridProductForm, gridCenter;
    private ProductPartTable tblProductPart;
    private Button btnSave, btnCancel;
    
    //DATA
    private Type type;
    private String windowLabel;
    
    public AddModifyProductWindow(Type type) {
        //Settings
        this.type = type;
        this.setPadding(new Insets(
                Settings.panePadTop,
                Settings.panePadRight,
                Settings.panePadBot,
                Settings.panePadLeft
        ));
        
        switch(this.type) {
            case ADD:
                this.windowLabel = "Add Product";
                break;
            case MODIFY:
                this.windowLabel = "Modify Product";
                break;
        }
        
        
        //Create Components
        this.initializeComponents();
        this.createColumnConstraints();
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
        
        this.gridCenter = new GridPane();
        
        this.btnSave = new Button("Save");
        this.btnSave.setPadding(new Insets(
                Settings.btnPadTop,
                Settings.btnPadRight,
                Settings.btnPadBot,
                Settings.btnPadLeft
        ));
        this.btnSave.setOnAction(e -> this.save());
        Platform.runLater(()->this.btnSave.requestFocus()); //Prevents text field from gaining focus on startup
        
        this.btnCancel = new Button("Cancel");
        this.btnCancel.setPadding(new Insets(
                Settings.btnPadTop,
                Settings.btnPadRight,
                Settings.btnPadBot,
                Settings.btnPadLeft
        ));
        this.btnCancel.setOnAction(e -> this.closeWindow());
        
        this.boxBtns = new HBox();
        this.boxBtns.setSpacing(this.formHGap);
        
        //FORM INITIALIZATION
        this.lblID = new Label("ID");
        this.lblName = new Label("Name");
        this.lblInv = new Label("Inv");
        this.lblPrice = new Label("Price");
        this.lblMax = new Label("Max");
        this.lblMin = new Label("Min");
        
        this.txtID = new TextField();
        this.txtID.setPromptText("Auto Gen - Disabled");
        this.txtID.setDisable(true);
        
        this.txtName = new TextField();
        this.txtName.setPromptText("Product Name");
        
        this.txtInv = new TextField();
        this.txtInv.setPromptText("Inv");
        
        this.txtPrice = new TextField();
        this.txtInv.setPromptText("Inv");
        
        this.txtPrice = new TextField();
        this.txtPrice.setPromptText("Price");
        
        this.txtMax = new TextField();
        this.txtMax.setPromptText("Max");
        
        this.txtMin = new TextField();
        this.txtMin.setPromptText("Min");
        
        this.gridProductForm = new GridPane();
        
        //PRODUCT PART INITIALIZATION
        switch(this.type) {
            case ADD:
                this.tblProductPart = new ProductPartTable(ProductPartTable.Type.NEW);
                break;
        }
    }
    
    /**
     * SPACES COLUMNS CORRECTLY
     */
    private void createColumnConstraints() {
        ColumnConstraints col1Cons, col2Cons;
        col1Cons = new ColumnConstraints();
        col2Cons = new ColumnConstraints();
        
        col1Cons.setPercentWidth(40);
        col2Cons.setPercentWidth(60);
        
        this.gridCenter.getColumnConstraints().addAll(col1Cons, col2Cons);
    }
    
    /**
     * POPULATES THE WINDOW WITH ALL COMPONENTS
     */
    private void populatePane() {
        this.populateTop();
        this.populateCenter();
        this.populateBottom();
    }
    
    private void populateTop() {
        this.setTop(this.lblMain);
    }
    
    private void populateCenter() {
        this.populateForm();
               
        //LEFT COLUMN BOX
        GridPane.setConstraints(this.boxLeftCol, 0, 0);
        this.boxLeftCol.getChildren().addAll(
                this.gridProductForm
        );
        
        //RIGHT COLUMN BOX
        GridPane.setConstraints(this.boxRightCol, 1, 0);
        this.boxRightCol.getChildren().addAll(
                this.tblProductPart
        );
        
        this.gridCenter.getChildren().addAll(
                this.boxLeftCol, 
                this.boxRightCol
        );
        
        this.setCenter(this.gridCenter);
    }
    
    private void populateBottom() {
        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        this.boxBtns.getChildren().addAll(
                spacer,
                this.btnSave,
                this.btnCancel
        );
        
        this.setBottom(this.boxBtns);
    }
    
    /**
     * POPULATES THE FORM GRIDPANE
     */
    private void populateForm() {
        //SETTINGS
        this.gridProductForm.setPadding(new Insets(
                Settings.panePadTop,
                Settings.panePadRight,
                Settings.panePadBot,
                0
        ));
        this.gridProductForm.setVgap(this.formVGap);
        this.gridProductForm.setHgap(this.formHGap);
        
        //CREATE AND ADD THE COLUMN CONSTRAINTS
        ColumnConstraints col1, col2, col3, col4;
        col1 = new ColumnConstraints();
        col2 = new ColumnConstraints();
        col3 = new ColumnConstraints();
        col4 = new ColumnConstraints();
        
        col1.setPercentWidth(20);
        col2.setPercentWidth(30);
        col3.setPercentWidth(20);
        col4.setPercentWidth(30);
        
        this.gridProductForm.getColumnConstraints().addAll(col1, col2, col3, col4);
        
        //SPECIFY AND ADD THE COMPONENTS
        //--Row 1--
        GridPane.setConstraints(this.lblID, 0, 0);
        
        GridPane.setConstraints(this.txtID, 1, 0);
        GridPane.setColumnSpan(this.txtID, 3);
        
        //--Row 2--
        GridPane.setConstraints(this.lblName, 0, 1);
        
        GridPane.setConstraints(this.txtName, 1, 1);
        GridPane.setColumnSpan(this.txtName, 3);
        
        //--Row 3--
        GridPane.setConstraints(this.lblInv, 0, 2);
        
        GridPane.setConstraints(this.txtInv, 1, 2);
        
        //--Row 4--
        GridPane.setConstraints(this.lblPrice, 0, 3);
        
        GridPane.setConstraints(this.txtPrice, 1, 3);
        
        //--Row 5--
        GridPane.setConstraints(this.lblMax, 0, 4);
        GridPane.setConstraints(this.txtMax, 1, 4);
        GridPane.setConstraints(this.lblMin, 2, 4);
        GridPane.setConstraints(this.txtMin, 3, 4);
        
        this.gridProductForm.getChildren().addAll(
                this.lblID,
                this.txtID,
                this.lblName,
                this.txtName,
                this.lblInv,
                this.txtInv,
                this.lblPrice,
                this.txtPrice,
                this.lblMax,
                this.txtMax,
                this.lblMin,
                this.txtMin
        );
    }
    
    /**
     * CLOSES THE CURRENT WINDOW
     */
    private void closeWindow() {
        Stage window = (Stage)this.getScene().getWindow();
        window.getScene().setRoot(new Region()); //Clears the root Node from being in two different scenes
        window.close();
    }
    
    /**
     * SAVES THE PRODUCT AND CLOSES THE WINDOW
     */
    public void save() {
        switch(this.type) {
            case ADD:
                this.saveNew();
                break;
        }
        
        this.closeWindow();
    }
    
    /**
     * SAVES A NEW PRODUCT
     */
    public void saveNew() {
        Product prod = new Product(
                this.txtName.getText(),
                Double.parseDouble(this.txtPrice.getText()),
                Integer.parseInt(this.txtInv.getText()),
                Integer.parseInt(this.txtMin.getText()),
                Integer.parseInt(this.txtMax.getText()),
                (ArrayList<Part>)this.tblProductPart.getParts()
        );
        
        InventoryController.addProduct(prod);
    }
}
