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
    private static final Inventory INVENTORY = new Inventory();
    public static ArrayList<IObserver> observers = new ArrayList<IObserver>();
    
    /**
     * PRODUCT CRUD
     */
    public static void addProduct(Product prod) {
        INVENTORY.addProduct(prod);
        notifyObservers();
    }
    
    public static boolean removeProduct(UUID id) {
        boolean success = INVENTORY.removeProduct(id);
        notifyObservers();
        return success;
    }
    
    public static Collection<Product> getProducts() {
        return INVENTORY.getProducts();
    }
    
    public static Product getProduct(UUID id) {
        return INVENTORY.lookupProduct(id);
    }
    
    public static void updateProduct(Product prod) {
        INVENTORY.updateProduct(prod);
        
        notifyObservers();
    }
    
    /**
     * PART CRUD
     */
    public static void addPart(Part part) {
        INVENTORY.addPart(part);
        notifyObservers();
    }
    
    public static boolean removePart(UUID id) {
        boolean success = INVENTORY.removePart(id);
        notifyObservers();
        return success;
    }
    
    public static Collection<Part> getParts() {
        return INVENTORY.getParts();
    }
    
    public static Part getPart(UUID id) {
        return INVENTORY.lookupPart(id);
    }
    
    public static void updatePart(Part part) {
        INVENTORY.updatePart(part);
        
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
        observers.forEach((obs) -> {
            obs.update();
        });
    }
}
