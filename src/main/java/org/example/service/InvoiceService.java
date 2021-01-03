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
    private final List<Invoice> invoices = new CopyOnWriteArrayList<>();
    private final String cdnUrl;
    private final String myMsg;

    public InvoiceService(UserService userService, @Value("${cdn.url}") String cdnUrl, @Value("${my.hello}") String myMsg) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
        this.myMsg = myMsg;
        System.out.println(this.myMsg);
    }
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
    public Invoice create(String userId, Integer amount, String myMsg){
        User user = userService.findById(userId);
        if (user == null) {
            throw new IllegalStateException();
        }
    //  TODO real pdf creation and storing it on network server
        Invoice invoice = new Invoice(userId,amount, cdnUrl+"/images/default/sample.pdf", myMsg);

        invoices.add(invoice);
        return invoice;
    }
}