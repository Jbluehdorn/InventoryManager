package inventorymanager.Util;

public class StringUtility {
    public static String capitalizeFirst(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }
}