package inventorymanager.Controllers;

import inventorymanager.Settings;
import inventorymanager.Views.MainWindow;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jordan
 */
public class HomeController {
    //SETTINGS
    private final static String TITLE = "Inventory Manager";
    
    /**
     * RETURNS THE PRIMARY WINDOW FOR THE APPLICATION
     */
    public static void MainWindow() {
        Pane mainWindow = new MainWindow();
        
        Stage stage = new Stage();
        stage.setTitle(TITLE);
        stage.setScene(new Scene(mainWindow, Settings.windowWidth * 2, Settings.windowHeight));
        stage.setResizable(false);
        stage.show();
    }
}
