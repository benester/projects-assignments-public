package Iv1350.kth.pos.modell;

import Iv1350.kth.pos.integration.Item;

import java.util.ArrayList;

/**
 * The cash register in the store, handles all the logic a cash register should handle, Running total, vat and change in registry.
 */
public class CashRegister {
    private double changeInreg;
    private final double INTEGER_TO_PROCENT = 0.01;

    /**
     * Ccnstructor for the cashregister, intiates the value in the registry with 400:- to simulate a real registry
     */
    public CashRegister(){
        this.changeInreg = 0;
    }

    double calculateRunningTotal(ArrayList<Item> items){
        double totalCost = 0;
        for(int i = 0; i < items.size(); i++){
            totalCost += ((items.get(i).getItemDTO().getItemPrice() + calculateVAT(items.get(i)))* items.get(i).getAmountOfItem());
        }
        return totalCost;
    }

    private double calculateVAT(Item item){
        double vat = item.getItemDTO().getItemPrice() * (item.getItemDTO().getVatRate() * INTEGER_TO_PROCENT);
        return vat;
    }

    double getTotalVat(ArrayList<Item>items){
        double totalVAT = 0;
        for(int i = 0; i<items.size(); i++){
            totalVAT = calculateVAT(items.get(i))*items.get(i).getAmountOfItem();
        }
        return totalVAT;
    }
    void updateCashInRegistry(CashPayment payment, double totalCost){
        this.changeInreg+= (payment.getPaidAmt() - payment.getChange(totalCost));
    }

    double getChangeInreg(){
        return changeInreg;
    }
}
