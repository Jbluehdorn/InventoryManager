package inventorymanager.Models.Parts;

public class Inhouse extends Part {
    private int machineID;

    //Getter and Setter for machineID
    public void setMachineID(int machineID) {
        this.machineID = machineID;
    }

    public int getMachineID() {
        return this.machineID;
    }
}