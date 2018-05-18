package inventorymanager.Controllers;

import inventorymanager.Interfaces.IObserver;
import inventorymanager.Models.*;
import inventorymanager.Models.Parts.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

/**
 * Controls the inventory data flow
 */
public class InventoryController {
    private static Inventory inventory = new Inventory();
    public static ArrayList<IObserver> observers = new ArrayList<IObserver>();
    
    /**
     * PRODUCT CRUD
     */
    public static void addProduct(Product prod) {
        inventory.addProduct(prod);
        notifyObservers();
    }
    
    public static boolean removeProduct(UUID id) {
        boolean success = inventory.removeProduct(id);
        notifyObservers();
        return success;
    }
    
    public static Collection<Product> getProducts() {
        return inventory.getProducts();
    }
    
    public static Product getProduct(UUID id) {
        return inventory.lookupProduct(id);
    }
    
    public static void updateProduct(Product prod) {
        //TODO flesh out update method
        
        notifyObservers();
    }
    
    /**
     * PART CRUD
     */
    public static void addPart(Part part) {
        inventory.addPart(part);
        notifyObservers();
    }
    
    public static boolean removePart(UUID id) {
        boolean success = inventory.removePart(id);
        notifyObservers();
        return success;
    }
    
    public static Collection<Part> getParts() {
        return inventory.getParts();
    }
    
    public static Part getPart(UUID id) {
        return inventory.lookupPart(id);
    }
    
    public static void updatePart(Part part) {
        //TODO flesh out update method
        
        notifyObservers();
    }
    
    /**
     * OBSERVABLE METHODS
     */
    public static void attach(IObserver obs) {
        observers.add(obs);
    }
    
    public static void detatch(IObserver obs) {
        observers.remove(obs);
    }
    
    public static void notifyObservers() {
        for(IObserver obs : observers){
            obs.update();
        }
    }
}
