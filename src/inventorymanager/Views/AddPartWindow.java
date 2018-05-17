package inventorymanager.Views;

import inventorymanager.Settings;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
public class AddPartWindow extends VBox {
    //SETTINGS
    private final double spacing = 10.0;
    private enum Alignment {
        RIGHT,
        LEFT
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
            lblComp;
    private TextField
            txtID,
            txtName,
            txtInv,
            txtPrice,
            txtMax,
            txtMin,
            txtComp;
    private Button btnSave, btnCancel;
    private ArrayList<Pane> rows;
    
    public AddPartWindow() {
        //Set settings
        this.setPadding(new Insets(
                Settings.panePadTop, 
                Settings.panePadRight, 
                Settings.panePadBot, 
                Settings.panePadLeft));
        this.setSpacing(this.spacing);
        
        //Initialize and populate
        this.initializeComponents();
        this.populateBox();
    }
    
    /**
     * INITIALIZE ALL COMPONENTS
     */
    private void initializeComponents() {
        this.rows = new ArrayList<Pane>();
        
        //Labels
        this.lblTitle = new Label("Add Part");
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
        
        this.lblComp = new Label("Company Name");
        this.lblComp.setFont(Font.font(Settings.font, Settings.bodyTextSize));
        
        //Textfields
        this.txtID = new TextField();
        this.txtID.setPromptText("ID");
        
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
        
        this.txtComp = new TextField();
        this.txtComp.setPromptText("Comp Nm");
        
        //Radio buttons
        this.radioGroup = new ToggleGroup();
        
        this.rbInhouse = new RadioButton("In-House");
        this.rbInhouse.setToggleGroup(this.radioGroup);
        this.rbInhouse.setSelected(true);
        
        this.rbOutsourced = new RadioButton("Outsourced");
        this.rbOutsourced.setToggleGroup(this.radioGroup);
        
        //Buttons
        this.btnSave = new Button("Save");
        this.btnSave.setPadding(new Insets(
                Settings.btnPadTop, 
                Settings.btnPadRight, 
                Settings.btnPadBot, 
                Settings.btnPadLeft));
        
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
        this.addTextFieldRow(this.lblComp, this.txtComp);
        this.addButtonsRow(Alignment.RIGHT, this.btnSave, this.btnCancel);
    }
    
    /**
     * POPULATE THE VBOX
     */
    private void populateBox() {
        //Title Row
        this.getChildren().add(this.lblTitle);
        
        //Form fields
        for(Pane pane : rows) {
            this.getChildren().add(pane);
        }
        
        //Buttons Row
        
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
        
        for(RadioButton btn : btns) {
            temp.getChildren().add(btn);
        }
        
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
}
