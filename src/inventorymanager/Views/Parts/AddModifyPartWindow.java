package inventorymanager.Views.Parts;

import inventorymanager.Controllers.InventoryController;
import inventorymanager.Models.Parts.Inhouse;
import inventorymanager.Models.Parts.Outsourced;
import inventorymanager.Models.Parts.Part;
import inventorymanager.Settings;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This window shows the "Add Part" dialog
 */
public class AddModifyPartWindow extends VBox {
    //SETTINGS
    private final double spacing = 10.0;
    private enum Alignment {
        RIGHT,
        LEFT
    }
    public enum Type {
        ADD,
        MODIFY
    }
    
    //COMPONENTS
    private ToggleGroup radioGroup;
    private RadioButton
            rbInhouse,
            rbOutsourced;
    private Label 
            lblTitle,
            lblID,
            lblName,
            lblInv,
            lblPrice,
            lblMax,
            lblMin,
            lblAdditional;
    private TextField
            txtID,
            txtName,
            txtInv,
            txtPrice,
            txtMax,
            txtMin,
            txtAdditional;
    private Button btnSave, btnCancel;
    private ArrayList<Pane> rows;
    
    //DATA
    private Part modifyPart;
    private Type windowType;
    private String windowTitle;
    
    public AddModifyPartWindow(Type type) {
        //Set settings
        this.setPadding(new Insets(
                Settings.panePadTop, 
                Settings.panePadRight, 
                Settings.panePadBot, 
                Settings.panePadLeft));
        this.setSpacing(this.spacing);
        this.windowType = type;
        
        switch(this.windowType) {
            case ADD:
                this.windowTitle = "Add Part";
                break;
            case MODIFY:
                this.windowTitle = "Modify Part";
                break;
        }
        
        //Initialize and populate
        this.initializeComponents();
        this.populateBox();
    }
    
    public AddModifyPartWindow(Type type, Part part) {
        this(type);
        this.modifyPart = part;
        this.populateFields();
    }
    
    /**
     * INITIALIZE ALL COMPONENTS
     */
    private void initializeComponents() {
        this.rows = new ArrayList<>();
        
        //Labels
        this.lblTitle = new Label(this.windowTitle);
        this.lblTitle.setFont(Font.font(Settings.font, Settings.headerTextSize));
        
        this.lblID = new Label("ID");
        this.lblID.setFont(Font.font(Settings.font, Settings.bodyTextSize));
        
        this.lblName = new Label("Name");
        this.lblName.setFont(Font.font(Settings.font, Settings.bodyTextSize));
        
        this.lblInv = new Label("Inv");
        this.lblInv.setFont(Font.font(Settings.font, Settings.bodyTextSize));
        
        this.lblPrice = new Label("Price/Cost");
        this.lblPrice.setFont(Font.font(Settings.font, Settings.bodyTextSize));
        
        this.lblMax = new Label("Max");
        this.lblMax.setFont(Font.font(Settings.font, Settings.bodyTextSize));
        
        this.lblMin = new Label("Min");
        this.lblMin.setFont(Font.font(Settings.font, Settings.bodyTextSize));
        
        this.lblAdditional = new Label("Machine ID");
        this.lblAdditional.setFont(Font.font(Settings.font, Settings.bodyTextSize));
        
        //Textfields
        this.txtID = new TextField();
        this.txtID.setPromptText("Auto Gen - Disabled");
        this.txtID.setDisable(true);
        
        this.txtName = new TextField();
        this.txtName.setPromptText("Part Name");
        
        this.txtInv = new TextField();
        this.txtInv.setPromptText("Inv");
        
        this.txtPrice = new TextField();
        this.txtPrice.setPromptText("Price/Cost");
        
        this.txtMax = new TextField();
        this.txtMax.setPromptText("Max");
        
        this.txtMin = new TextField();
        this.txtMin.setPromptText("Min");
        
        this.txtAdditional = new TextField();
        this.txtAdditional.setPromptText("Mach ID");
        
        //Radio buttons
        this.radioGroup = new ToggleGroup();
        this.radioGroup.selectedToggleProperty().addListener((ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) -> {
            setAdditionalField();
        });
        
        this.rbInhouse = new RadioButton("In-House");
        this.rbInhouse.setToggleGroup(this.radioGroup);
        this.rbInhouse.setUserData("inhouse");
        this.rbInhouse.setSelected(true);
        
        this.rbOutsourced = new RadioButton("Outsourced");
        this.rbOutsourced.setUserData("outsourced");
        this.rbOutsourced.setToggleGroup(this.radioGroup);
        
        //Buttons
        this.btnSave = new Button("Save");
        this.btnSave.setPadding(new Insets(
                Settings.btnPadTop, 
                Settings.btnPadRight, 
                Settings.btnPadBot, 
                Settings.btnPadLeft));
        this.btnSave.setOnAction(e -> this.save());
        
        this.btnCancel = new Button("Cancel");
        this.btnCancel.setPadding(new Insets(
                Settings.btnPadTop, 
                Settings.btnPadRight, 
                Settings.btnPadBot, 
                Settings.btnPadLeft));
        this.btnCancel.setOnAction(e -> this.closeWindow());
        
        //Initialize rows
        this.addRadioRow(this.rbInhouse, this.rbOutsourced);
        this.addTextFieldRow(this.lblID, this.txtID);
        this.addTextFieldRow(this.lblName, this.txtName);
        this.addTextFieldRow(this.lblInv, this.txtInv);
        this.addTextFieldRow(this.lblPrice, this.txtPrice);
        this.addTextFieldRow(this.lblMax, this.txtMax);
        this.addTextFieldRow(this.lblMin, this.txtMin);
        this.addTextFieldRow(this.lblAdditional, this.txtAdditional);
        this.addButtonsRow(Alignment.RIGHT, this.btnSave, this.btnCancel);
    }
    
    /**
     * POPULATE THE VBOX
     */
    private void populateBox() {
        //Title Row
        this.getChildren().add(this.lblTitle);
        
        //Form fields
        rows.forEach((pane) -> {
            this.getChildren().add(pane);
        });
    }
    
    /**
     * POPULATES ALL FORM FIELDS 
     */
    private void populateFields() {
        if(this.windowType != Type.MODIFY)
            return;
        
        this.txtID.setText(this.modifyPart.getPartID().toString());
        this.txtName.setText(this.modifyPart.getName());
        this.txtInv.setText(Integer.toString(this.modifyPart.getInstock()));
        this.txtPrice.setText(Double.toString(this.modifyPart.getPrice()));
        this.txtMax.setText(Integer.toString(this.modifyPart.getMax()));
        this.txtMin.setText(Integer.toString(this.modifyPart.getMin()));
        
        //Prevent switching part type
        this.radioGroup.getToggles().forEach((toggle) -> {
            ((RadioButton)toggle).setDisable(true);
        });
        
        switch(this.modifyPart.getClass().getSimpleName()) {
            case "Inhouse":
                this.txtAdditional.setText(Integer.toString(((Inhouse)this.modifyPart).getMachineID()));
                this.rbInhouse.setSelected(true);
                break;
            case "Outsourced":
                this.txtAdditional.setText(((Outsourced)this.modifyPart).getCompanyName());
                this.rbOutsourced.setSelected(true);
                break;
        }
    }

    /**
     * DETERMINES SAVE TYPE AND THENS SAVES
     */
    private void save() {
        switch(this.windowType) {
            case ADD:
                this.saveNewPart();
                break;
            case MODIFY:
                this.saveModifyPart();
                break;
        }
        
        this.closeWindow();
    }
    
    /**
     * SAVES THE NEW PART AND CLOSES THE WINDOW
     */
    private void saveNewPart() {
        String type = this.radioGroup.getSelectedToggle().getUserData().toString();
        Part part = null;

        switch(type) {
            case "inhouse":
                part = new Inhouse(
                    this.txtName.getText(),
                    Double.parseDouble(this.txtPrice.getText()),
                    Integer.parseInt(this.txtInv.getText()),
                    Integer.parseInt(this.txtMax.getText()),
                    Integer.parseInt(this.txtMin.getText()),
                    Integer.parseInt(this.txtAdditional.getText())
                ); 
                break;
            case "outsourced":
                part = new Outsourced(
                    this.txtName.getText(),
                    Double.parseDouble(this.txtPrice.getText()),
                    Integer.parseInt(this.txtInv.getText()),
                    Integer.parseInt(this.txtMax.getText()),
                    Integer.parseInt(this.txtMin.getText()),
                    this.txtAdditional.getText()
                );
                break;
        }
        
        InventoryController.addPart(part);
    }

    /**
     * SAVES A MODIFIED PART
     */
    private void saveModifyPart() {
        this.modifyPart.setName(this.txtName.getText());
        this.modifyPart.setInStock(Integer.parseInt(this.txtInv.getText()));
        this.modifyPart.setPrice(Double.parseDouble(this.txtPrice.getText()));
        this.modifyPart.setMax(Integer.parseInt(this.txtMax.getText()));
        this.modifyPart.setMin(Integer.parseInt(this.txtMin.getText()));
        
        switch(this.modifyPart.getClass().getSimpleName()) {
            case "Inhouse":
                ((Inhouse)this.modifyPart).setMachineID(Integer.parseInt(this.txtAdditional.getText()));
                break;
            case "Outsourced":
                ((Outsourced)this.modifyPart).setCompanyName(this.txtAdditional.getText());
                break;
        }
        
        InventoryController.updatePart(this.modifyPart);
    }
    
    /**
     * CLOSES THE WINDOW COMPLETELY
     */
    private void closeWindow() {
        Stage window = (Stage)this.getScene().getWindow();
        window.getScene().setRoot(new Region()); //Clears the root Node from being in two different scenes
        window.close();
    }
    
    /**
     * ADDS THE LABEL AND TEXT FIELD TO THE ROWS
     */
    private void addTextFieldRow(Label label, TextField field) {
        AnchorPane temp = new AnchorPane();
        
        temp.getChildren().addAll(label, field);
        AnchorPane.setLeftAnchor(label, 0.0);
        AnchorPane.setRightAnchor(field, 0.0);
        temp.setPadding(new Insets(
                0,
                Settings.panePadRight * 2,
                0,
                Settings.panePadLeft * 2
        ));
        
        this.rows.add(temp);
    }
    
    /**
     * CREATES A ROW OF RADIO BUTTONS
     */
    private void addRadioRow(RadioButton... btns) {
        HBox temp = new HBox();
        
        temp.getChildren().addAll(Arrays.asList(btns));
        
        temp.setPadding(new Insets(
                0,
                Settings.panePadRight * 2,
                0,
                Settings.panePadLeft * 2
        ));
        temp.setSpacing(this.spacing);
        
        this.rows.add(temp);
    }
    
    /**
     * CREATES A ROW OF BUTTONS
     */
    private void addButtonsRow(Alignment alignment, Button... btns) {
        AnchorPane temp = new AnchorPane();
        HBox container = new HBox();
        
        container.setSpacing(this.spacing);
        container.getChildren().addAll(btns);
        
        switch(alignment) {
            case LEFT:
                AnchorPane.setLeftAnchor(container, this.spacing);
                break;
            case RIGHT:
                AnchorPane.setRightAnchor(container, this.spacing);
                break;
        }
        
        temp.getChildren().add(container);
        
        this.rows.add(temp);
    }
    
    /**
     * UPDATES THE ADDITIONAL INFO FIELD ACCORDING TO TYPE
     */
    private void setAdditionalField() {
        String type = this.radioGroup.getSelectedToggle().getUserData().toString();
        
        switch(type) {
            case "inhouse":
                this.lblAdditional.setText("Machine ID");
                this.txtAdditional.setPromptText("Mach ID");
                break;
            case "outsourced":
                this.lblAdditional.setText("Company Name");
                this.txtAdditional.setPromptText("Comp Nm");
                break;
        }
    }
}
