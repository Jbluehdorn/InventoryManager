package inventorymanager.Models;

import java.util.ArrayList;

import inventorymanager.Models.Parts.*;
import java.util.Collection;
import java.util.UUID;

public class Inventory {
    private ArrayList<Product> products = new ArrayList<Product>();
    private ArrayList<Part> allParts = new ArrayList<Part>();

    /**
     * ADDS A PRODUCT TO PRODUCTS
     * 
     * PARAMS: product -  the product to add
     * RETURNS: void
     */
    public void addProduct(Product product) {
        this.products.add(product);
    }

    /**
     * REMOVE A PRODUCT FROM PRODUCTS
     * 
     * PARAMS: productID - the productID of the product to remove
     * RETURNS: removal success
     */
    public boolean removeProduct(UUID productID) {
        int productIndex = -1;

        for(int i = 0; i < this.products.size(); i++) {
            if(this.products.get(i).getProductID() == productID) {
                productIndex = i;
                break;
            }
        }

        if(productIndex >= 0) {
            this.products.remove(productIndex);
            return true;
        } else {
            throw new IndexOutOfBoundsException("PRODUCT NOT FOUND");
        }
    }
    
    /**
     * GETS ALL PRODUCTS
     * 
     * PARAMS: none
     * RETURNS: Collection of Products
     */
    public Collection<Product> getProducts() {
        return this.products;
    }

    /**
     * FINDS A PRODUCT
     * 
     * PARAMS: productID - the productID of the product to search
     * RETURNS: InventoryManager.Models.Product
     */
    public Product lookupProduct(UUID productID) {
        int productIndex = -1;

        for(int i = 0; i < this.products.size(); i++) {
            if(this.products.get(i).getProductID() == productID) {
                productIndex = i;
                break;
            }
        }

        if(productIndex >= 0) {
            return this.products.get(productIndex);
        } else {
            throw new IndexOutOfBoundsException("PRODUCT NOT FOUND");
        }
    }
    
    /**
     * UPDATES AN EXISTING PRODUCT
     * 
     * PARAMS: product - the Product to update
     * RETURNS: void
     */
    public void updateProduct(Product product) {
        this.removeProduct(product.getProductID());
        this.addProduct(product);
    }

    /**
     * ADDS A PART TO ALLPARTS
     * 
     * PARAMS: part - the part to be added
     * RETURNS: void
     */
    public void addPart(Part part) {
        this.allParts.add(part);
    }

    /**
     * DELETES A PART FROM ALLPARTS
     * 
     * PARAMS: partID - the partID of the part to be removed
     * RETURNS: removal successful
     */
    public boolean removePart(UUID partID) {
        int partIndex = -1;

        for(int i = 0; i < this.allParts.size(); i++) {
            if(this.allParts.get(i).getPartID() == partID) {
                partIndex = i;
                break;
            }
        }

        if(partIndex >= 0) {
            this.allParts.remove(partIndex);
            return true;
        } else {
            throw new IndexOutOfBoundsException("PART NOT FOUND");
        }
    }
    
    /**
     * GETS ALL PARTS
     * 
     * PARAMS: none
     * RETURNS: Collection of Parts
     */
    public Collection<Part> getParts() {
        return this.allParts;
    }

    /**
     * FINDS A PART IN ALLPARTS
     * 
     * PARAMS: partID - the partID of the Part to find
     * RETURNS: InventoryManager.Models.Parts.Part
     */
    public Part lookupPart(UUID partID) {
        int partIndex = -1;

        for(int i = 0; i < this.allParts.size(); i++) {
            if(this.allParts.get(i).getPartID() == partID) {
                partIndex = i;
                break;
            }
        }

        if(partIndex >= 0) {
            return this.allParts.get(partIndex);
        } else {
            throw new IndexOutOfBoundsException("PART NOT FOUND");
        }
    }
    
    /**
     * UPDATES AN EXISTING PART
     * 
     * PARAMS: part - the Part to update
     * RETURNS: void
     */
    public void updatePart(Part part) {
        this.removePart(part.getPartID());
        this.addPart(part);
    }
}