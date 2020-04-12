package Iv1350.kth.pos.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    private ItemDTO itemdto;
    private int amountOfItem;
    private Item item;


    @Before
    public void setUp() throws Exception {
        itemdto = new ItemDTO(2,"milk",32,55);
        amountOfItem = 4;
        item = new Item(itemdto, amountOfItem);
    }

    @After
    public void tearDown() throws Exception {
        itemdto = null;
        amountOfItem = 0;
        item = null;
    }

    @Test
    public void getItemID() {
        int itemId = item.getItemID();
        assertEquals(2,itemId);
    }

    @Test
    public void getAmountOfItem() {
        int amount = item.getAmountOfItem();
        assertEquals(4,amount);
    }

    /*Test to see if itemdto get function is working*/
    @Test
    public void getItemDTO() {
        ItemDTO expectedDTO = itemdto;
        assertEquals(expectedDTO, item.getItemDTO());
    }

    /*Tests that the function does what it shall, aka add one to the amount of items in the Item instans*/
    @Test
    public void addAmountOfItem() {
        int expectedAmount = item.getAmountOfItem() + 1;
        item.addAmountOfItem();
        assertEquals(expectedAmount, item.getAmountOfItem());
    }

    /*tests the set amount method should change the value to a specified one*/

    @Test
    public void setAmount() {
        item.setAmount(7);
        assertEquals(7,item.getAmountOfItem());
    }
}