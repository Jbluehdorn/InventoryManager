package inventorymanager.Models;

import inventorymanager.Interfaces.IInventoryItem;
import java.util.*;
import inventorymanager.Models.Parts.*;

public class Product implements IInventoryItem {
    private ArrayList<Part> associatedParts;
    private UUID productID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    public Product(String name, double price, int inStock, int min, int max) {
        this.productID = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
    }

    /**
     * ADDS PART TO ARRAYLIST
     * 
     * PARAMS: part - the part to be added
     * RETURNS: void
     */
    public void addAssociatedPart(Part part) {
        this.associatedParts.add(part);
    }

    /**
     * REMOVES THE PART FROM THE ARRAYLIST
     * 
     * PARAMS: partID - the partID of the part to be removed
     * RETURNS: removal success
     */
    public boolean removeAssociatedPart(UUID partID) {
        int partIndex = -1;

        for(int i = 0; i < this.associatedParts.size(); i++) {
            if(this.associatedParts.get(i).getID() == partID) {
                partIndex = i;
                break;
            }
        }

        if(partIndex >= 0) {
            this.associatedParts.remove(partIndex);
            return true;
        } else {
            throw new IndexOutOfBoundsException("PART NOT FOUND");
        }
    }

    /**
     * FIND THE PART IN THE ARRAYLIST
     * 
     * PARAMS: partID - the partID of the part to be found
     * RETURNS: InventoryManager.Models.Parts.Part
     */
    public Part lookupAssociatedPart(UUID partID) {
        int partIndex = -1;

        for(int i = 0; i < this.associatedParts.size(); i++) {
            if(this.associatedParts.get(i).getID() == partID) {
                partIndex = i;
                break;
            }
        }

        if(partIndex >= 0) {
            return this.associatedParts.get(partIndex);
        } else {
            throw new IndexOutOfBoundsException("NO MATCHING PART FOUND");
        }
    }

    //Getter and Setter for name
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    //Getter and Setter for price
    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return this.price;
    }

    //Getter and Setter for inStock
    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public int getInStock() {
        return inStock;
    }

    //Getter and Setter for min
    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {
        return this.min;
    }

    //Getter and Setter for max
    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return this.max;
    }
    
    //Getter and Setter for productID
    public void setID(UUID productID) {
        this.productID = productID;
    }

    public UUID getID() {
        return this.productID;
    }
}