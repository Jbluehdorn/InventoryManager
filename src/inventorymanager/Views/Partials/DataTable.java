package inventorymanager.Views.Partials;

import inventorymanager.Controllers.InventoryController;
import inventorymanager.Controllers.PartsController;
import inventorymanager.Interfaces.IObserver;
import inventorymanager.Models.Parts.Part;
import inventorymanager.Settings;
import inventorymanager.Util.StringUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * DATATABLE COMPONENT FOR PARTS AND PRODUCTS
 */
public class DataTable extends BorderPane implements IObserver {
    //SETTINGS
    private String type;
    
    //COMPONENTS
    private HBox boxSearch, boxCrudBtns;
    private VBox boxCenter;
    private AnchorPane anchorTop;
    private Label lblPrimary;
    private Button btnSearch, btnAdd, btnMod, btnDel;
    private TextField txtSearch;
    private TableView table;
    
    public DataTable(String type) {
        if(!type.toLowerCase().equals("parts") && !type.toLowerCase().equals("products"))
            throw new IllegalArgumentException("TYPE MUST BE PARTS OR PRODUCTS. '" + type + "' GIVEN.");
        
        //Set the type
        this.type = type.toLowerCase();
        
        //Create pane settings
        this.setPadding(new Insets(
                Settings.panePadTop, 
                Settings.panePadRight, 
                Settings.panePadBot,
                Settings.panePadLeft
        ));
        this.setBorder(new Border(new BorderStroke(
                Color.GRAY, 
                BorderStrokeStyle.SOLID, 
                new CornerRadii(5), 
                BorderWidths.DEFAULT
        )));
        
        //Subscribe to changes
        InventoryController.attach(this);
        
        //Create and place components
        this.initializeComponents();
        this.populateTop();
        this.populateCenter();
    }
    
    /**
     * INITIALIZES ALL COMPONENTS
     */
    private void initializeComponents() {
        this.lblPrimary = new Label(StringUtility.capitalizeFirst(this.type));
        this.lblPrimary.setFont(Font.font(Settings.font, Settings.subheaderTextSize));
        
        this.anchorTop = new AnchorPane();
        
        this.boxSearch = new HBox();
        this.boxSearch.setSpacing(5);
        this.boxSearch.setAlignment(Pos.BASELINE_RIGHT);
        
        this.btnSearch = new Button("Search");
        
        this.txtSearch = new TextField();
        this.txtSearch.setPromptText("Search...");
        
        this.boxCenter = new VBox();
        this.boxCenter.setSpacing(5);
        this.boxCenter.setPadding(new Insets(
                Settings.panePadTop, 
                0, 
                Settings.panePadBot, 
                0
        ));
        
        this.table = new TableView();
        TableColumn colID = new TableColumn(StringUtility.capitalizeFirst(this.type) + " ID");
        colID.setCellValueFactory(
                new PropertyValueFactory<>(this.type.equals("parts") ? "partID" : "productID")
        );
        TableColumn colName = new TableColumn(StringUtility.capitalizeFirst(this.type) + " Name");
        colName.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        TableColumn colInv = new TableColumn("Inventory Level");
        colInv.setCellValueFactory(
                new PropertyValueFactory<>("instock")
        );
        TableColumn colPrice = new TableColumn("Price per Unit");
        colPrice.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        this.table.getColumns().addAll(colID, colName, colInv, colPrice);
        this.table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        this.boxCrudBtns = new HBox();
        this.boxCrudBtns.setSpacing(5);
        this.boxCrudBtns.setAlignment(Pos.BASELINE_RIGHT);
        
        this.btnAdd = new Button("Add");
        this.btnAdd.setPadding(new Insets(
                Settings.btnPadTop, 
                Settings.btnPadRight, 
                Settings.btnPadBot, 
                Settings.btnPadLeft
        ));
        this.btnAdd.setOnAction(e -> {
            if(this.type.equals("parts")) {
                PartsController.addForm();
            }
        });
        
        this.btnMod = new Button("Modify");
        this.btnMod.setPadding(new Insets(
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
    }
    
    /**
     * POPULATES THE TOP SECTION
     */
    private void populateTop() {
        this.boxSearch.getChildren().addAll(this.btnSearch, this.txtSearch);
        
        this.anchorTop.getChildren().addAll(this.lblPrimary, this.boxSearch);
        AnchorPane.setLeftAnchor(this.lblPrimary, 0.0);
        AnchorPane.setRightAnchor(this.boxSearch, 0.0);
        
        this.setTop(this.anchorTop);
    }
    
    /**
     * POPULATES THE CENTER SECTION
     */
    private void populateCenter() {
        this.populateTable();
        this.boxCrudBtns.getChildren().addAll(this.btnAdd, this.btnMod, this.btnDel);
        
        this.boxCenter.getChildren().addAll(this.table, this.boxCrudBtns);
        
        this.setCenter(this.boxCenter);
    }
    
    /**
     * POPULATES THE TABLE WITH THE CORRECT DATA
     */
    private void populateTable() {
        if(this.type.equals("parts")) {
            ObservableList<Part> parts = FXCollections.observableArrayList(InventoryController.getParts());
            this.table.setItems(parts);
        }
    }

    @Override
    public void update() {
        this.populateTable();
    }
}
