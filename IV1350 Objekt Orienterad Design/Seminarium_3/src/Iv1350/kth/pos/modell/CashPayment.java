package Iv1350.kth.pos.modell;

public class CashPayment {
    private double paidAmt;

    /**
     * Constructor for the CashPayment class, creates an object that stores the amount the customer paid
     * @param paidAmt
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
