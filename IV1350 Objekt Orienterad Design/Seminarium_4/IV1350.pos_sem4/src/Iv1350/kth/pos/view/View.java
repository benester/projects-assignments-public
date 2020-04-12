package Iv1350.kth.pos.view;

import Iv1350.kth.pos.controller.Controller;
import Iv1350.kth.pos.controller.DataBaseErrorException;
import Iv1350.kth.pos.controller.NoItemWithIDException;
import Iv1350.kth.pos.integration.InvalidItemIDException;
import Iv1350.kth.pos.integration.ItemDTO;

import javax.naming.directory.InvalidAttributeIdentifierException;
import java.util.Scanner;

/**
 * Simulated GUI for the user of this program.
 */
public class View{

    private Controller cntrl;

    /**
     * constructor for the view
     * @param cntrl the object cntrl, so that the GUI has acces to the controllers methods.
     */
    public View (Controller cntrl) {
        this.cntrl = cntrl;
    }

    /**
     * simulates the running of the actual POS system. and acts as a GUI for the user.
     */
    public void afterStartup() {

        cntrl.createRevenueObserver(new TotalRevenueView());

        for(;;) {
            boolean moreItems = true;
            System.out.println("GUI: ");
            System.out.println("1. Start new sale");
            int switchcase = inputCleanerInt();

            switch(switchcase){
                case 1: {
                    cntrl.startNewSale();
                }
                while(moreItems == true) {
                    System.out.println("GUI:");
                    System.out.println("1. Scan item");
                    System.out.println("2. No more items to scan");
                    switchcase = inputCleanerInt();


                    switch (switchcase) {
                        case 1: {
                            System.out.println("Scan itemidentifyer");
                            try {
                                int itemid = inputCleanerInt();
                                ItemDTO item = cntrl.isItemIdentifyerValid(itemid);
                                System.out.println(item);
                                System.out.println("Running Total: "+cntrl.getRunningTotal());
                                break;
                            }
                            catch(NoItemWithIDException e){
                                System.out.println("User Interface: " + e.getMessage());
                                break;
                            }
                            catch(DataBaseErrorException e){
                                System.out.println("User Interface: " + e.getMessage());
                                break;
                            }

                        }
                        case 2: {
                            moreItems = false;
                            break;
                        }
                    }
                }
                break;
            }
            System.out.println("Total cost: "+ cntrl.getRunningTotal());
            double payment = 0;
            System.out.println("Enter what costumer has paid: " );
            payment = inputCleanerDouble();
            cntrl.addPayment(payment);
        }
    }

    private int inputCleanerInt(){
        int returnValue;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        try{
             returnValue = Integer.valueOf(input);
        }
        catch(NumberFormatException exc){
             returnValue = 0;
             System.out.println("Not Valid Input, please enter an Integer");
        }
        return returnValue;
    }
    private double inputCleanerDouble(){
        double returnValue = 0;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean notCorrectInput = true;
        while(notCorrectInput){

            try {
                notCorrectInput = false;
                returnValue = Double.valueOf(input);
            }
            catch(NumberFormatException exc) {
                notCorrectInput = true;
                System.out.println("Not a correct value!, please enter a correct amount: ");
                input = scanner.nextLine();
            }
        }
        return returnValue;
    }

}

