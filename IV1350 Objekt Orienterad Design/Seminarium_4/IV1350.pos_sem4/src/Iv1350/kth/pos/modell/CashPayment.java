package Iv1350.kth.pos.modell;

/**
 * CashPayment is used to send information between classes in model, about how much the costumer paid and how much change he should get.
 * This class is cash related.
 */
public class CashPayment {
    private double paidAmt;

    /**
     * Constructor for the CashPayment class, creates an object that stores the amount the customer paid
     * @param paidAmt the amount a costumer paid
     */
    public CashPayment (double paidAmt){
        this.paidAmt = paidAmt;
    }

    double getPaidAmt(){
        return this.paidAmt;
    }

    double getChange(double totalCost){

        return this.paidAmt - totalCost;
    }
}
