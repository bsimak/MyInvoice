package org.example.service;

import org.example.context.Application;
import org.example.model.Invoice;
import org.example.model.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {

    private final UserService userService;

    private List<Invoice> invoices = new CopyOnWriteArrayList<>(); // (1)

    public InvoiceService(UserService userService) {
        this.userService = userService;
    }

    public List<Invoice> findAll() {
        // Debugging : System.out.println("in findAll");
        return invoices;
    }
    public Invoice create(String userId, Integer amount){
        User user = userService.findById(userId);
        if (user == null) {
            throw new IllegalStateException();
        }
    //  TODO real pdf creation and storing it on network server

        Invoice invoice = new Invoice(userId,amount,"http://www.africau.edu/images/default/sample.pdf");

        /* Debugging old style
        System.out.print("Create org.example.model.Invoice: ");
        System.out.println(invoice);
        */
        invoices.add(invoice);
        return invoice;
    }
}