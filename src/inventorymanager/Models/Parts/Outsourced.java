package inventorymanager.Models.Parts;

public class Outsourced extends Part {
    private String companyName;

    public Outsourced() {
        super();
    }
    
    public Outsourced(String name, double price, int inStock, int min, int max, String companyName) {
        super(name, price, inStock, min, max);
        this.companyName = companyName;
    }
    
    //Getter and Setter for companyName
    public void setCompanyName(String commpanyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return this.companyName;
    }
}