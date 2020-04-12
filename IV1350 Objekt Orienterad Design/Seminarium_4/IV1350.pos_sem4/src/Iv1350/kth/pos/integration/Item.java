package Iv1350.kth.pos.integration;

/**
 * Item is an exstention of itemDTO, so that an amount can be given to a specific itemDTO
 */
public class Item {
    private ItemDTO item;
    private int amountOfItem;

    public Item(ItemDTO item, int amountOfItem){
        this.item = item;
        this.amountOfItem = amountOfItem;
    }

    /**
     * gets the item id of specified item from the itemDTO class
     * @return  the item id from specified item
     */
    public int getItemID(){
       return item.getItemIdentifyer();
    }

    /**
     * retreives the amout of items present in sale of specific type
     * @return  the amount of certain items in sale
     */
    public int getAmountOfItem(){
        return amountOfItem;
    }

    /**
     * Formats all the item in the sale for the printer, to display name, amount and price
     * @return  the formated string of all items in sale, for the printer
     */
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("\n" + this.getAmountOfItem() + "X :" + this.item.getItemName() +", Price : "+ this.item.getItemPrice());
        return builder.toString();
    }

    /**
     * get the itemDTO from an instans of the Item class
     * @return the itemDTO from an object of the type Item
     */
    public ItemDTO getItemDTO(){
        return this.item;
    }

    /**
     * When an item is scanned, but is all ready present in the sale, the quantity of this item increese
     */
    public void addAmountOfItem(){
        this.amountOfItem++;
    }

    /**
     * set the amount of items present in the Item object
     * @param amount the specified amount of items of certain type the object will be corrected to
     */
    public void setAmount(int amount){
        this.amountOfItem = amount;
    }
}
