package inventorymanager.Interfaces;

import java.util.UUID;

/**
 * Interface for all inventory items
 */
public interface IInventoryItem {
    public void setName(String name);
    public String getName();
    public void setPrice(double price);
    public double getPrice();
    public void setInStock(int inStock);
    public int getInStock();
    public void setMin(int min);
    public int getMin();
    public void setMax(int max);
    public int getMax();
    public void setID(UUID ID);
    public UUID getID();
}
