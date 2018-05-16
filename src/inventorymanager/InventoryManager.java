/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorymanager;

import inventorymanager.Controllers.SceneController;
import inventorymanager.Views.MainWindow;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Jordan
 */
public class InventoryManager extends Application {
    Stage window;
    Scene scene1, scene2;
    
    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        SceneController sceneController = new SceneController(window);
                
        Label label1 = new Label("First scene");
        Button btn1 = new Button("Go to Scene 2");
        btn1.setOnAction(e -> SceneController.activate("Scene 2"));
        
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, btn1);
        scene1 = new Scene(layout1, 200, 200);
        
        Label lbl2 = new Label("Second scene");
        Button btn2 = new Button("Go to scene 1");
        btn2.setOnAction(e -> SceneController.activate("Scene 1"));
        
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(lbl2, btn2);
        scene2 = new Scene(layout2, 600, 300);
        
        MainWindow windowMain = new MainWindow();
        
        sceneController.addScene("Scene 1", scene1);
        sceneController.addScene("Scene 2", scene2);
        sceneController.addScene("Main", new Scene(windowMain, 300, 300));
        
        SceneController.activate("Main");
        
        window.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
