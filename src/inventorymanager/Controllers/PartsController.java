package inventorymanager.Controllers;

import inventorymanager.Models.Parts.Part;
import java.util.ArrayList;

/**
 * CONTROLLER FOR inventorymanager.Models.Parts.Part
 */
public class PartsController {
    private static ArrayList<Part> parts = new ArrayList<Part>();
    
    public static void add(Part part) {
        parts.add(part);
    }
    
    public static boolean remove(Part part) {
        return parts.remove(part);
    }
    
    public static ArrayList<Part> get() {
        return parts;
    }
    
    public static Part get(int partID) {
        int partIndex = -1;
        
        for(int i = 0; i < parts.size(); i++) {
            if(parts.get(i).getPartID() == partID) {
                partIndex = i;
                break;
            }
        }
        
        if(partIndex >= 0) {
            return parts.get(partIndex);
        } else {
            throw new IndexOutOfBoundsException("PART NOT FOUND");
        }
    }
}
