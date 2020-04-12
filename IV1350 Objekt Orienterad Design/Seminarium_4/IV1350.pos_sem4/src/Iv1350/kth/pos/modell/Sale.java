package Iv1350.kth.pos.modell;
import Iv1350.kth.pos.integration.Item;
import Iv1350.kth.pos.integration.ItemDTO;
import Iv1350.kth.pos.integration.Printer;
import java.util.ArrayList;

/**
 * This class hold the information about an ongoing sale.
 */
public class Sale {
    private ArrayList<Item> items;
    private double runningTotal = 0;
    private CashRegister cashreg;
    private double totalVAT = 0;
    private CashPayment payment;

    private RevenueObserver obs;

    public void addObserver(RevenueObserver obs){
        this.obs = obs;
    }

    private void notifyObserver(double revenue){
        obs.TotalRevenueUpdated(revenue);
    }

    /**
     * constructor for the Sale class, stores sale information in arraylist, and creates instans of the cashregister
     */
    public Sale(CashRegister cashreg){
        this.items = new ArrayList<>(1);
        this.cashreg = cashreg;
    }

    /**
     * adds a scanned item to the sale, if its not a valid item it will not be added
     * @param item An item retreived from the inventory system
     */
    public void addScannedItem(ItemDTO item){
        if(item != null) {
            boolean duplicate = false;
            for(int i = 0; i < items.size();i++) {
                if (item.getItemIdentifyer() == items.get(i).getItemID()) {
                    items.get(i).addAmountOfItem();
                    duplicate = true;
                }
            }
            if(!duplicate){
                Item newItem = new Item(item, 1);
                this.items.add(newItem);
            }
            updaterunningTotal();
        }
    }

     private void updaterunningTotal(){
        this.runningTotal = cashreg.calculateRunningTotal(this.items);
        updateTotalVAT();
    }

     private void updateTotalVAT(){
        this.totalVAT = cashreg.getTotalVat(this.items);
        //payment.calculateTotalVat(this.items);
    }

    /**
     * A method that retrevies the running total
     * @return the running total of the sale
     */
    public double getRunningTotal(){ return this.runningTotal; }

    /**
     * retreives the total VAT for a purchase
     * @return  the total vat of a purchase
     */
    double getTotalVAT(){return this.totalVAT;}

    /**
     * a method that retreives the amount of change a customer should recive after payment
     * @return  the change the costumer should resive
     */
    double getChange(){
       return payment.getChange(this.runningTotal);
    }

    CashPayment getPayment(){
        return this.payment;
    }

    /**
     * Adds the information from GUI about how much the costumer paid to the payment object
     * @param payment stores information about how much the custer paid.
     */
    public void pay(CashPayment payment){
        this.payment = payment;
        updateCashInRegister();
        notifyObserver(cashreg.getChangeInreg());
    }

    private void updateCashInRegister(){
        cashreg.updateCashInRegistry(payment, runningTotal);
    }

    /**
     * retreives all items in a sale
     * @return the arraylist with all the items in the sale
     */
    public ArrayList<Item> getItems(){
        return this.items;
    }

    /**
     * Prints the receipt by creating a new receipt and sending it to the printer
     * @param printer  the object handling the printer
     * @return the receipt, to send to the controller so it can send it to the external accounting system
     */
    public Receipt printReceipt(Printer printer){
        Receipt receipt = new Receipt(this);
        printer.activatePrinter(receipt);
        return receipt;
    }
}