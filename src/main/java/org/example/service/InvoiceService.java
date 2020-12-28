package org.example.service;

import org.example.model.Invoice;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class InvoiceService {

    private final UserService userService;
    private final List<Invoice> invoices = new CopyOnWriteArrayList<>(); // (1)
    private final String cdnUrl;

    public InvoiceService(UserService userService, @Value("${cdn.url}") String cdnUrl) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
    }
    
    // in old Spring Versions or if more than one constructor: @Autowired required
    /**
    public InvoiceService(UserService userService) {
        System.out.println("Initialisierung Konstruktor InvoiceService");
        this.userService = userService;
    }
    */

    @PostConstruct
    public void init() {
        System.out.println("Fetch PDF Template from S3...");
        // TODO download from s3 and save locally
    }
    @PreDestroy
    // works only when application is really shutdown
    public void shutdown(){
        System.out.println("Deleting downloaded template");
    }
    // -------- Start of the methods --------

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
       //  Invoice invoice = new Invoice(userId,amount,"http://www.africau.edu/images/default/sample.pdf");

        Invoice invoice = new Invoice(userId,amount, cdnUrl+"/images/default/sample.pdf");
        /* Debugging old style
        System.out.print("Create org.example.model.Invoice: ");
        System.out.println(invoice);
        */
        invoices.add(invoice);
        return invoice;
    }
}