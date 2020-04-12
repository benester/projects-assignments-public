package Iv1350.kth.pos.integration;

import java.util.ArrayList;

public class InventoryRegistry {
    private ArrayList<Item> itemsarr;

    /**
     * The constructor for the inventory system, creates a few items to simmulate that they are already there in the "real" database
     */
    public InventoryRegistry() {
        this.itemsarr = new ArrayList<>();

        ItemDTO milkDTO = new ItemDTO(001, "Milk", 32, 55);
        Item milk = new Item(milkDTO, 5);

        ItemDTO cheddarDTO = new ItemDTO(002, "Cheddar", 5, 9.90);
        Item cheddar = new Item(cheddarDTO, 9);

        ItemDTO snusDTO = new ItemDTO(003, "Snus", 17, 38.40);
        Item snus = new Item(snusDTO, 15);

        itemsarr.add(milk);
        itemsarr.add(snus);
        itemsarr.add(cheddar);
    }

    /**
     * Cheacks if an item ID is valid and either returns the itemDTO for the object if found, or a null object if not
     * @param itemID    the desired item ID to find coresponding itemDTO
     * @return either returns the itemDTO for the item ID or a nullobject if not found
     */
    public ItemDTO isItemIDValid(int itemID) {
        for (int i = 0; i < itemsarr.size(); i++) {
            if (itemsarr.get(i).getItemID() == itemID) {
                return itemsarr.get(i).getItemDTO();
            }
        }
        return null;
    }
    /**
     * Updates the inventory after a sale, and removes the amount of sold items from the quantity for that item
     * @param items The items sold during the sale.
     */
    public void updateStock(ArrayList<Item> items){
        for(int i = 0; i<this.itemsarr.size();i++){
            for(int k = 0; i<items.size();i++){
                if(this.itemsarr.get(i).getItemDTO().getItemIdentifyer() == items.get(k).getItemDTO().getItemIdentifyer()){
                    this.itemsarr.get(i).setAmount(this.itemsarr.get(i).getAmountOfItem()-items.get(k).getAmountOfItem());
                }
            }
        }
    }
}
