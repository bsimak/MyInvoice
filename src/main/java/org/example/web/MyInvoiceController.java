package org.example.web;

import org.example.model.Invoice;
import org.example.service.InvoiceService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyInvoiceController {

    private final InvoiceService invoiceService;

    public MyInvoiceController (InvoiceService invoiceService) {

        this.invoiceService = invoiceService;
    }

    @RequestMapping(value="/invoices", method = RequestMethod.GET)
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }

   @RequestMapping(value="/invoices", method = RequestMethod.POST)
    public Invoice createInvoice(@RequestParam("user_id") String userId,
                                 @RequestParam Integer amount,
                                 @RequestParam String myMsg) {
        return invoiceService.create(userId,amount,myMsg);
    }
}
