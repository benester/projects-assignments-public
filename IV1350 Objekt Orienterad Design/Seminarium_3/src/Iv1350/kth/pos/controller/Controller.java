package Iv1350.kth.pos.controller;
import Iv1350.kth.pos.integration.ExternalAccountingSystem;
import Iv1350.kth.pos.integration.InventoryRegistry;
import Iv1350.kth.pos.integration.ItemDTO;
import Iv1350.kth.pos.integration.Printer;
import Iv1350.kth.pos.modell.CashPayment;
import Iv1350.kth.pos.modell.CashRegister;
import Iv1350.kth.pos.modell.Receipt;
import Iv1350.kth.pos.modell.Sale;

public class Controller {
    private InventoryRegistry invreg;
    private Printer printer;
    private ExternalAccountingSystem exAcc;
    private Sale sale;
    private CashRegister cashreg;

    /**
     * Custructor for the controller class
     * @param printer is the object handling the printer
     * @param invreg is the objecet handling the inventory
     * @param exAcc is the object handling the external acc system
     */
    public Controller(Printer printer, InventoryRegistry invreg, ExternalAccountingSystem exAcc){
        this.invreg = invreg;
        this.printer = printer;
        this.exAcc = exAcc;
        this.cashreg = new CashRegister();
    }

    /**
     * Initiates a new sale
     */
    public void startNewSale(){
        this.sale = new Sale();
        System.out.println("A new sale has started");
    }

    /**
     * This method looks for a item that corisponds to an enterd item ID
     * @param itemID Is the item id that the users enters
     * @return The found item from the inventory system.
     */
    public ItemDTO isItemIdentifyerValid(int itemID){
        ItemDTO validItem = invreg.isItemIDValid(itemID);
            sale.addScannedItem(validItem);
            return validItem;
    }

    /**
     * this method retrevies the running total for the ongoing purchase
     * @return returns the running total to the user
     */
    public double getRunningTotal(){
       return sale.getRunningTotal();
    }

    /**
     * this method handels payments, and sends the information of payment to relevant classes
     * @param paidAmt is the amount the costumer has paid
     */
    public void addPayment (double paidAmt){
        CashPayment payment = new CashPayment(paidAmt);
        sale.pay(payment);
        Receipt receipt = sale.printReceipt(printer);
        exAcc.logSale(receipt);
        sale.updateCashinreg();
        invreg.updateStock(sale.getItems());
    }
}