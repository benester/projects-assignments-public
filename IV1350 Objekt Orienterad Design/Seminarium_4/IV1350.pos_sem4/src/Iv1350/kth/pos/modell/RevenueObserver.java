package Iv1350.kth.pos.modell;
/**
 * Notified when a new payment is made
 */
public interface RevenueObserver {
    /**
     *Method called when the running total is updated
     * @param totalRevenue the value it updated to.
     */
    void TotalRevenueUpdated(double totalRevenue);
}
