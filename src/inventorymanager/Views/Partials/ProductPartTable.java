package inventorymanager.Views.Partials;

import inventorymanager.Controllers.InventoryController;
import inventorymanager.Models.Parts.Part;
import inventorymanager.Models.Product;
import inventorymanager.Settings;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductPartTable extends VBox {
    //SETTINGS
    private double spacing = 5.0;
    public enum Type {
        NEW,
        MODIFY
    }
    
    //COMPONENTS
    private TextField txtSearch;
    private Button btnAdd, btnDel;
    private TableView tblRemainingParts, tblProductParts;
    
    //DATA
    private Type type;
    private UUID prodID;
    private ObservableList<Part> productParts;
    private FilteredList<Part> remainingParts;
    
    public ProductPartTable(Type type) {
        this(type, null);
    }
    
    public ProductPartTable(Type type, UUID productID) {
        this.prodID = productID;
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
    
    /**
     * INITIALIZES ALL COMPONENTS
     */
    private void initializeComponents() {
        this.txtSearch = new TextField();
        this.txtSearch.setPromptText("Search...");
        this.txtSearch.textProperty().addListener((obs, oldVal, newVal) -> {
            this.filterRemainingParts();
        });
        
        this.btnAdd = new Button("Add");
        this.btnAdd.setPadding(new Insets(
                Settings.btnPadTop,
                Settings.btnPadRight,
                Settings.btnPadBot,
                Settings.btnPadLeft
        ));
        this.btnAdd.setOnAction(e -> this.addPart());
        
        this.btnDel = new Button("Delete");
        this.btnDel.setPadding(new Insets(
                Settings.btnPadTop,
                Settings.btnPadRight,
                Settings.btnPadBot,
                Settings.btnPadLeft
        ));
        this.btnDel.setOnAction(e -> this.removePart());
        
        //TABLE COLUMNS
        TableColumn remColID = new TableColumn("Part ID");
        TableColumn remColName = new TableColumn("Part Name");
        TableColumn remColInv = new TableColumn("Inventory Level");
        TableColumn remColPrice = new TableColumn("Price per Unit");
        TableColumn prodColID = new TableColumn("Part ID");
        TableColumn prodColName = new TableColumn("Part Name");
        TableColumn prodColInv = new TableColumn("Inventory Level");
        TableColumn prodColPrice = new TableColumn("Price per Unit");
        
        remColID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        remColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        remColInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        remColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        prodColID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        prodColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        prodColInv.setCellValueFactory(new PropertyValueFactory<>("inStock"));
        prodColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        remColPrice.setCellFactory(cell -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if(empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        prodColPrice.setCellFactory(cell -> new TableCell<Part, Double>() {
            @Override
            protected void updateItem(Double price, boolean empty) {
                super.updateItem(price, empty);
                if(empty) {
                    setText(null);
                } else {
                    setText(currencyFormat.format(price));
                }
            }
        });
        
        //TABLE VIEWS
        this.tblRemainingParts = new TableView();
        this.tblRemainingParts.getColumns().addAll(remColID, remColName, remColInv, remColPrice);
        this.tblRemainingParts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        this.tblProductParts = new TableView();
        this.tblProductParts.getColumns().addAll(prodColID, prodColName, prodColInv, prodColPrice);
        this.tblProductParts.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
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
        ObservableList<Part> allParts = FXCollections.observableArrayList((ArrayList<Part>) InventoryController.getParts());
        this.remainingParts = new FilteredList<>(allParts, p -> true);
        
        this.tblRemainingParts.setItems(this.remainingParts);
    }
    
    private void loadProductParts() {
        switch(this.type) {
            case NEW:
                this.productParts = FXCollections.observableArrayList(new ArrayList<>());
                break;
            case MODIFY:
                this.productParts = FXCollections.observableArrayList((ArrayList<Part>) InventoryController.getProductParts(this.prodID));
                
                this.removeExistingParts();
                
                break;
            default:
                this.productParts = FXCollections.observableArrayList(new ArrayList<>());
        }
        
        this.tblProductParts.setItems(this.productParts);
        
    }
    
    /**
     * REMOVES PARTS FROM REMAININGPARTS IF THEY EXIST IN THE PRODUCTPARTS
     */
    private void removeExistingParts() {
        this.productParts.forEach((productPart) -> {
           this.remainingParts.forEach((remainingPart) -> {
                if(remainingPart.getID() == productPart.getID()) {
                    this.remainingParts.getSource().remove(remainingPart);
                }
            });
        });
    }
    
    /**
     * FILTERS REMAINING PARTS
     */
    private void filterRemainingParts() {
        String searchTerm = this.txtSearch.getText().toLowerCase();
        
        this.remainingParts.setPredicate(item -> { 
            return item.getName().toLowerCase().contains(searchTerm) ||
                    Double.toString(item.getPrice()).contains(searchTerm) ||
                    Integer.toString(item.getInStock()).contains(searchTerm);
        });
        
    }
    
    /**
     * ADDS PART FROM REMAINING TO PRODUCTPARTS
     */
    private void addPart() {
        Part part = ((TableView<Part>)this.tblRemainingParts).getSelectionModel().getSelectedItem();
        
        this.remainingParts.getSource().remove(part);
        this.productParts.add(part);
    }
    
    /**
     * REMOVES PART FROM PRODUCTPARTS TO REMAINING
     */
    private void removePart() {
        Part part = ((TableView<Part>)this.tblProductParts).getSelectionModel().getSelectedItem();
        
        this.productParts.remove(part);
        ((ObservableList<Part>)this.remainingParts.getSource()).add(part);
    }
    
    /**
     * GET THE PARTS ARRAYLIST
     */
    public Iterable<Part> getParts() {
        return this.productParts.stream().collect(Collectors.toList());
    }
    
   private void printEverything() {
       String lineBreak = "------------------------";
       
       System.out.println("Remaining Parts");
       for(Part part : this.remainingParts) {
           System.out.println(part.getName());
       }
       System.out.println(lineBreak);
       
       System.out.println("Product Parts");
       for(Part part : this.productParts) {
           System.out.println(part.getName());
       }
       System.out.println(lineBreak);
   }
}
