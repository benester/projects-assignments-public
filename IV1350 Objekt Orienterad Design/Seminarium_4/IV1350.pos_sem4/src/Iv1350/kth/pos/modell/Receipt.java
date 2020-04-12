package Iv1350.kth.pos.modell;

import java.time.LocalDateTime;

/**
 * The receipt of the sale, is created once the sale has ended.
 */
public class Receipt {
    private final Sale sale;
    private final String LINE_DEVIDER = "\n\n";

    Receipt(Sale sale){
        this.sale = sale;
    }

    /**
     * Will structure the information to be displayed on the receipt in a fashionable manor, and work as a
     * placeholder for the formating done by an actual printer.
     */
    public String toString(){
        StringBuilder stringbuilder = new StringBuilder();

        stringbuilder.append("==============================");
        stringbuilder.append(LINE_DEVIDER);
        stringbuilder.append("Receipt Store ABC CORP");
        stringbuilder.append(LINE_DEVIDER);

        stringbuilder.append("Time of sale: ");
        LocalDateTime timeOfSale = LocalDateTime.now();
        stringbuilder.append(timeOfSale);
        stringbuilder.append(LINE_DEVIDER);

        stringbuilder.append("Items: ");
        stringbuilder.append(sale.getItems().toString().substring(1).replaceFirst("]",""));
        stringbuilder.append(LINE_DEVIDER);


        stringbuilder.append("Total cost: " + sale.getRunningTotal());
        stringbuilder.append(LINE_DEVIDER);

        stringbuilder.append("Total VAT: " + sale.getTotalVAT());
        stringbuilder.append(LINE_DEVIDER);

        stringbuilder.append("Paid amount: " + sale.getPayment().getPaidAmt());
        stringbuilder.append(LINE_DEVIDER);

        stringbuilder.append("Change : " + sale.getChange());
        stringbuilder.append(LINE_DEVIDER);

        stringbuilder.append("==========================");

        return stringbuilder.toString();
    }
}
