package inventorymanager.Controllers;

import inventorymanager.Interfaces.IObservable;
import inventorymanager.Interfaces.IObserver;
import inventorymanager.Models.Parts.Part;
import inventorymanager.Settings;
import java.util.ArrayList;
import javafx.stage.Stage;

/**
 * CONTROLLER FOR inventorymanager.Models.Parts.Part
 */
public class PartsController {
    private static ArrayList<Part> parts = new ArrayList<Part>();
    private static ArrayList<IObserver> observers = new ArrayList<IObserver>();
    
    public static void add(Part part) {
        parts.add(part);
        notifyObservers();
    }
    
    public static boolean remove(Part part) {
        boolean success = parts.remove(part);
        notifyObservers();
        return success;
    }
    
    public static ArrayList<Part> get() {
        return parts;
    }
    
    public static Part get(int partID) {
        int partIndex = -1;
        
        for(int i = 0; i < parts.size(); i++) {
            if(parts.get(i).getPartID() == partID) {
                partIndex = i;
                break;
            }
        }
        
        if(partIndex >= 0) {
            return parts.get(partIndex);
        } else {
            throw new IndexOutOfBoundsException("PART NOT FOUND");
        }
    }
    
    public static void addForm() {
        Stage stage = new Stage();
        stage.setTitle("Add Part");
        stage.setScene(SceneController.getScene("addPart", Settings.windowHeight, Settings.windowWidth));
        stage.show();
    }
    
    public static void attach(IObserver observer) {
        observers.add(observer);
    }
    
    public static void detatch(IObserver observer) {
        observers.remove(observer);
    }
    
    public static void notifyObservers() {
        for(IObserver observer : observers) {
            observer.update();
        }
    }
}
