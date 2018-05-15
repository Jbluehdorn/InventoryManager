package inventorymanager.Models;

import java.util.ArrayList;

import inventorymanager.Models.Parts.*;

public class Inventory {
    private ArrayList<Product> products;
    private ArrayList<Part> allParts;

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
    public boolean removeProduct(int productID) {
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
     * FINDS A PRODUCT
     * 
     * PARAMS: productID - the productID of the product to search
     * RETURNS: InventoryManager.Models.Product
     */
    public Product lookupProduct(int productID) {
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
     * RETURNS: removal succesful
     */
    public boolean deletePart(int partID) {
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
     * FINDS A PART IN ALLPARTS
     * 
     * PARAMS: partID - the partID of the Part to find
     * RETURNS: InventoryManager.Models.Parts.Part
     */
    public Part lookupPart(int partID) {
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
}