package Iv1350.kth.pos.integration;
import Iv1350.kth.pos.controller.DataBaseErrorException;
import Iv1350.kth.pos.controller.NoItemWithIDException;
import Iv1350.kth.pos.integration.InventoryRegistry;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class InventoryRegistryTest {

    private ItemDTO itemdto;
    private Item validTtem;
    private Item boughtItem;
    private ArrayList<Item> itemsarr;
    private int invalidIdentifyer = 5;
    private int validID = 2;
    private InventoryRegistry inventory;
    private ArrayList<Item> pruchsedItems;


    @Before
    public void setUp() throws Exception {
        inventory = new InventoryRegistry();
        itemdto = new ItemDTO(2,"mjölk",32,17);
        validTtem = new Item(itemdto, 5);
        boughtItem = new Item(itemdto, 2);
        itemsarr = new ArrayList<>(1);
        itemsarr.add(validTtem);
        pruchsedItems = new ArrayList<>(1);
        pruchsedItems.add(boughtItem);
    }

    @After
    public void tearDown() throws Exception {
        itemdto = null;
        validTtem = null;
        inventory = null;
        itemsarr = null;
    }

    /* for testing the get function to see if correct dto is retreived*/
    @Test
    public void invalidItemID() {
        boolean cheacker = (validTtem.getItemID()==invalidIdentifyer)?true:false;
        Assert.assertFalse("This item does not exist",cheacker );
    }
    /*get functions other case*/
    @Test
    public void validItemID(){
        boolean cheacker = (validTtem.getItemID()==validID)?true:false;
        Assert.assertTrue("This item does exist in inv", cheacker);
    }

        /*Test retreving a item, with wrong input ID, thorws exception*/
    @Test
    public void inventoryCheckOfItemInvalidId(){
        try{
            ItemDTO item = inventory.isItemIDValid(6);
            fail("Could retrive item with invalid identifier");
        }
        catch(InvalidItemIDException exc){
            assertTrue("Method does not throw correct exception, when item not found in database", InvalidItemIDException.class.equals(exc.getClass()));
        }
    }

    /*Test retreving a item, with correct input ID*/
    @Test
        public void inventoryCheckOfItemValidID() {
        try {
            ItemDTO item = inventory.isItemIDValid(2);
            Assert.assertThat("Returned item is not of type itemDTO", item, CoreMatchers.isA(ItemDTO.class));
        }
        catch(InvalidItemIDException exc){
            fail("Throws exception when itemID is valid");
        }
    }

    /* Test for the simulated database exception*/
    @Test
        public void inventoryDataBaseError(){
        try {
            ItemDTO item = inventory.isItemIDValid(4);
        }
        catch(DataBaseConnectionErrorException exc){
            assertTrue("Did not throw expected exception", DataBaseConnectionErrorException.class.equals(exc.getClass()));
        }
        catch(InvalidItemIDException e){
            fail("should not throw invalidItemException");
        }
    }

    /*Seeks expected behavior from the method, that is, that the method updates the amount of milk in the inventory*/
    @Test
    public void updateStock() {

        inventory.updateStock(pruchsedItems);

        assertEquals(5,itemsarr.get(0).getAmountOfItem());
    }
}