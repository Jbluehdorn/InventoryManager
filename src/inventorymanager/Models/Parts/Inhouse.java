package inventorymanager.Models.Parts;

public class Inhouse extends Part {
    private int machineID;

    public Inhouse() {
        super();
    }
    
    public Inhouse(int partID, String name, double price, int inStock, int min, int max, int machineID) {
        super(partID, name, price, inStock, min, max);
        this.machineID = machineID;
    }
    
    //Getter and Setter for machineID
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    public int getMachineID() {
        return this.machineID;
    }
}