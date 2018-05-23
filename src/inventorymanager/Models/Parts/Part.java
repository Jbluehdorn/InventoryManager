package inventorymanager.Models.Parts;

import inventorymanager.Interfaces.IInventoryItem;
import java.util.UUID;

public abstract class Part implements IInventoryItem {
    private UUID partID;
    private String name;
    private double price;
    private int inStock;
    private int min;
    private int max;
    
    public Part() {};
    
    public Part(String name, double price, int inStock, int min, int max) {
        this.partID = UUID.randomUUID();
        this.name = name;
        this.price = price;
        this.inStock = inStock;
        this.min = min;
        this.max = max;
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
        return this.inStock;
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

    //Getter and Setter for partID
    public void setID(UUID partID) {
        this.partID = partID;
    }

    public UUID getID() {
        return this.partID;
    }
}