package Iv1350.kth.pos.view;

import Iv1350.kth.pos.controller.Controller;
import Iv1350.kth.pos.integration.ItemDTO;

import java.util.Scanner;

public class View {

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

        for(;;) {
            boolean moreItems = true;
            Scanner scanner = new Scanner(System.in);
            System.out.println("GUI: ");
            System.out.println("1. Start new sale");
            int switchcase =  scanner.nextInt();

            switch(switchcase){
                case 1: {
                    cntrl.startNewSale();
                }
                while(moreItems == true) {
                    System.out.println("GUI:");
                    System.out.println("1. Scan item");
                    System.out.println("2. No more items to scan");
                    switchcase = scanner.nextInt();


                    switch (switchcase) {
                        case 1: {
                            System.out.println("Scan itemidentifyer");
                            int itemid = scanner.nextInt();
                            ItemDTO item = cntrl.isItemIdentifyerValid(itemid);
                            System.out.println(item);
                            System.out.println("Running Total: "+cntrl.getRunningTotal());
                            break;
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
            payment = scanner.nextDouble();
            cntrl.addPayment(payment);
            break;
        }
    }
}

