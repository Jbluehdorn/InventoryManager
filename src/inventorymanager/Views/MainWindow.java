package inventorymanager.Views;

import inventorymanager.Controllers.SceneController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainWindow extends BorderPane {
    public MainWindow() {
        System.out.println("Initializing main window...");
        Label lbl1 = new Label("Hello world!");
        Button btn = new Button("Clicky!");
        btn.setOnAction(e -> SceneController.activate("Scene 1"));
        
        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(lbl1, btn);
        
        this.setTop(vbox);
    }   
}