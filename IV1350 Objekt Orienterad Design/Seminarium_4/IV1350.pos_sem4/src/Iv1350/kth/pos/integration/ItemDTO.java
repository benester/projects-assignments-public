package Iv1350.kth.pos.integration;

/**
 * The item data transfer object. Used so send item information between classes.
 */
public class ItemDTO {
    private int itemIdentifyer;
    private String itemName;
    private int vatRate;
    private double itemPrice;
    final String STRING_DEVIDER = "\n";

    /**
     * constructor for an Item dto
     * @param itemIdentifyer    The identifyer of a new item
     * @param itemName  the name of a new item
     * @param vatRate   the vatrate of a new item
     * @param itemPrice the item price of a new item
     */
    public ItemDTO(int itemIdentifyer, String itemName, int vatRate, double itemPrice){
        this.itemIdentifyer = itemIdentifyer;
        this.itemName = itemName;
        this.vatRate = vatRate;
        this.itemPrice = itemPrice;
    }

    /**
     * the method retrevis item price for a speciffic item
     * @return the price of desierd item
     */
    public double getItemPrice() {
        return itemPrice;
    }

    /**
     * The method retrevies the item identifyer of specified item
     * @return the item identifyer of specified item
     */
    public int getItemIdentifyer() {
        return itemIdentifyer;
    }

    /**
     * gets the vat rate for specified item
     * @return the vat rate for specified item
     */
    public int getVatRate() {
        return vatRate;
    }

    /**
     * gets the name for specified item.
     * @return the name of specified item
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * Formats the item information for the graphfic user interface
     * @return A formatted string for the GUI to display of an item
     */
    public String toString(){

        return "Item name: " + getItemName() + STRING_DEVIDER + "Item price: " + getItemPrice() + " kr" + STRING_DEVIDER + "Item VAT: " + getVatRate() + "%";
    }
}
