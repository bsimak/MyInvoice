package org.example.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Profile("dev")
public class DummyInvoiceServiceLoader {
    private final InvoiceService invoiceService;
    public DummyInvoiceServiceLoader(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostConstruct
    public void setup() {
        System.out.println("Creating dev Invoices...");
        invoiceService.create("Sibylle", 50);
        invoiceService.create("Barbara", 20);
    }
}
