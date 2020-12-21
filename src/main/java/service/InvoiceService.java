package service;

import model.Invoice;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class InvoiceService {

    List<Invoice> invoices = new CopyOnWriteArrayList<>(); // (1)

    public List<Invoice> findAll() {
        // Debugging : System.out.println("in findAll");
        return invoices;
    }
    Invoice create(String userId, Integer amount){
    //  TODO real pdf creation and storing it on network server
        Invoice invoice = new Invoice(userId,amount,"http://www.africau.edu/images/default/sample.pdf");
        /** Debugging old style
        System.out.print("Create model.Invoice: ");
        System.out.println(invoice);
        */
        invoices.add(invoice);
        return invoice;
    }
}