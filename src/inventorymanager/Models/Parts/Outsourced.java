package inventorymanager.Models.Parts;

public class Outsourced extends Part {
    private String companyName;

    //Getter and Setter for companyName
    public void setCompanyName(String commpanyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return this.companyName;
    }
}