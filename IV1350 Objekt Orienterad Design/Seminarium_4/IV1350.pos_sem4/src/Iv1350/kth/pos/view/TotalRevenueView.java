package Iv1350.kth.pos.view;

import Iv1350.kth.pos.modell.RevenueObserver;

/**
 * The view that the observer calls. Will update information as soon as the observer updates it.
 */
public class TotalRevenueView implements RevenueObserver {
    /**
     * Displays the total revenue since the program started
     * @param revenueFromSales The amount of revenue from previous sales.
     */
    @Override
    public void TotalRevenueUpdated(double revenueFromSales) {
        System.out.println("--------Observer-------- \n");
        System.out.println("Total revenue is: "+ revenueFromSales+":-");
        System.out.println("\n------------------------"+"\n");
    }
}