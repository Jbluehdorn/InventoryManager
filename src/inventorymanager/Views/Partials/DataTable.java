package inventorymanager.Views.Partials;

import inventorymanager.Controllers.InventoryController;
import inventorymanager.Controllers.PartsController;
import inventorymanager.Interfaces.IInventoryItem;
import inventorymanager.Interfaces.IObserver;
import inventorymanager.Models.Parts.Part;
import inventorymanager.Models.Product;
import inventorymanager.Settings;
import java.util.ArrayList;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
    private Type type;
    private String typeLabel;
    public enum Type {
        PARTS,
        PRODUCTS
    }
    
    //COMPONENTS
    private HBox boxSearch, boxCrudBtns;
    private VBox boxCenter;
    private AnchorPane anchorTop;
    private Label lblPrimary;
    private Button btnAdd, btnMod, btnDel;
    private TextField txtSearch;
    private TableView table;
    
    //DATA
    FilteredList<IInventoryItem> filteredItems;
    
    public DataTable(Type type) {
        //Set the type
        this.type = type;
        switch(this.type) {
            case PARTS:
                this.typeLabel = "Parts";
                break;
            case PRODUCTS:
                this.typeLabel = "Products";
                break;
        }
        
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
        this.lblPrimary = new Label(this.typeLabel);
        this.lblPrimary.setFont(Font.font(Settings.font, Settings.subheaderTextSize));
        
        this.anchorTop = new AnchorPane();
        
        this.boxSearch = new HBox();
        this.boxSearch.setSpacing(5);
        this.boxSearch.setAlignment(Pos.BASELINE_RIGHT);
        
        this.txtSearch = new TextField();
        this.txtSearch.setPromptText("Search...");
        this.txtSearch.textProperty().addListener((obs, oldVal, newVal) -> {
            this.filterTable();
        });
        
        this.boxCenter = new VBox();
        this.boxCenter.setSpacing(5);
        this.boxCenter.setPadding(new Insets(
                Settings.panePadTop, 
                0, 
                Settings.panePadBot, 
                0
        ));
        
        this.table = new TableView();
        TableColumn colID = new TableColumn(this.typeLabel);
        colID.setCellValueFactory(
                new PropertyValueFactory<IInventoryItem, UUID>("ID")
        );
        TableColumn colName = new TableColumn(this.typeLabel + " Name");
        colName.setCellValueFactory(
                new PropertyValueFactory<IInventoryItem, String>("name")
        );
        TableColumn colInv = new TableColumn("Inventory Level");
        colInv.setCellValueFactory(
                new PropertyValueFactory<IInventoryItem, String>("inStock")
        );
        TableColumn colPrice = new TableColumn("Price per Unit");
        colPrice.setCellValueFactory(
                new PropertyValueFactory<IInventoryItem, String>("price")
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
        this.btnAdd.setOnAction(e -> this.openAddForm());
        
        this.btnMod = new Button("Modify");
        this.btnMod.setPadding(new Insets(
                Settings.btnPadTop, 
                Settings.btnPadRight, 
                Settings.btnPadBot, 
                Settings.btnPadLeft
        ));
        this.btnMod.setOnAction(e -> this.openModForm());
        
        this.btnDel = new Button("Delete");
        this.btnDel.setPadding(new Insets(
                Settings.btnPadTop, 
                Settings.btnPadRight, 
                Settings.btnPadBot, 
                Settings.btnPadLeft
        ));
        this.btnDel.setOnAction(e -> this.openDeleteForm());
    }
    
    /**
     * POPULATES THE TOP SECTION
     */
    private void populateTop() {
        this.boxSearch.getChildren().addAll(this.txtSearch);
        
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
        switch(this.type) {
            case PARTS:
                ObservableList<IInventoryItem> parts = FXCollections.observableArrayList((ArrayList<Part>)InventoryController.getParts());
                this.filteredItems = new FilteredList<>(parts, p -> true);
                this.table.setItems(this.filteredItems);
                break;
        }
    }
    
    /**
     * FILTERS THE TABLE 
     */
    private void filterTable() {
        String searchTerm = this.txtSearch.getText().toLowerCase();
        
        this.filteredItems.setPredicate(item -> {
            return item.getName().toLowerCase().contains(searchTerm) ||
                    Double.toString(item.getPrice()).contains(searchTerm) ||
                    Integer.toString(item.getInStock()).contains(searchTerm);
        });
    }
    
    /**
     * OPENS THE CORRESPEONDING ADDFORM
     */
    private void openAddForm() {
        switch(this.type) {
            case PARTS:
                PartsController.showAddForm();
                break;
        }
    }
    
    /**
     * OPENS THE CORRESPONDING MOD FORM
     */
    private void openModForm() {
        switch(this.type) {
            case PARTS:
                UUID partID = ((TableView<Part>)this.table).getSelectionModel().getSelectedItem().getID();
                PartsController.showModForm(partID);
                break;
        }
    }
    
    /**
     * OPENS THE CORRESPONDING DELETE FORM
     */
    private void openDeleteForm() {
        switch(this.type) {
            case PARTS:
                UUID partID = ((TableView<Part>)this.table).getSelectionModel().getSelectedItem().getID();
                PartsController.showDeleteForm(partID);
                break;
        }
    }

    @Override
    public void update() {
        this.populateTable();
    }
}
