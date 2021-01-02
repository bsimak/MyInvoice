package org.example.web;

import org.example.model.Invoice;
import org.example.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MyInvoiceController {
    private final InvoiceService invoiceService;

    public MyInvoiceController (InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/invoices")
    // for GET
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }
}
