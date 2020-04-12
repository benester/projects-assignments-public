package Iv1350.kth.pos.controller;
import Iv1350.kth.pos.integration.*;
import Iv1350.kth.pos.modell.*;

/**
 * The controller does not handle logic, only gathers information from different classes and sends the information to where its needed
 */
public class Controller {
    private InventoryRegistry invreg;
    private Printer printer;
    private ExternalAccountingSystem exAcc;
    private Sale sale;
    private CashRegister cashreg;
    private RevenueObserver obs;

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
     * Initiates a new sale, and sends observer to the next layer
     */
    public void startNewSale(){
        this.sale = new Sale(cashreg);
        System.out.println("A new sale has started");
        sale.addObserver(obs);
    }

    /**
     * This method looks for a item that corisponds to an enterd item ID
     * @param itemID Is the item id that the users enters
     * @return The found item from the inventory system.
     * @throws NoItemWithIDException If item with specified id is not found, this exception is thrown
     * @throws DataBaseErrorException thrown if database exception is caught, to generalize the exception for the user
     */
    public ItemDTO isItemIdentifyerValid(int itemID)throws NoItemWithIDException{
        ItemDTO validItem;
        try {
            validItem = invreg.isItemIDValid(itemID);
            sale.addScannedItem(validItem);
            return validItem;
        }
        catch(InvalidItemIDException exc){
            System.out.println("DEVELOPER LOG: " + exc);
            throw new NoItemWithIDException("No item with this itentifier was found");
        }
        catch(DataBaseConnectionErrorException exc){
            System.out.println("DEVELOPER LOG: " +  exc);
            throw new DataBaseErrorException("Could not establish a stable connection to server, please try again");
        }
    }

    /**
     * this method retrevies the running total for the ongoing purchase
     * @return returns the running total to the user
     */
    public double getRunningTotal(){
       return sale.getRunningTotal();
    }

    /**
     * this method, is to create an observer to be used in the model package
     * @param obs The targeted observer
     */
    public void createRevenueObserver(RevenueObserver obs){
        this.obs = obs;
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
        invreg.updateStock(sale.getItems());
    }
}