package Iv1350.kth.pos.startup;

import Iv1350.kth.pos.integration.ExternalAccountingSystem;
import Iv1350.kth.pos.integration.InventoryRegistry;
import Iv1350.kth.pos.integration.Printer;
import Iv1350.kth.pos.view.View;
import Iv1350.kth.pos.controller.Controller;

public class Main {

    public static void main(String[] args){

        Printer printer = new Printer();
        InventoryRegistry invreg = new InventoryRegistry();
        ExternalAccountingSystem exAcc = new ExternalAccountingSystem();
        Controller cntrl = new Controller(printer, invreg,exAcc);
        View view = new View(cntrl);


        view.afterStartup();

    }

}
