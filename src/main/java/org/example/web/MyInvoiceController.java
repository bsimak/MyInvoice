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

    /* Request Mapping Version
   @RequestMapping(value="/invoices", method = RequestMethod.POST)
    public Invoice createInvoice(@RequestParam("user_id") String userId,
                                 @RequestParam Integer amount,
                                 @RequestParam String myMsg) {
        return invoiceService.create(userId,amount,myMsg);
    }
     */
    /*
    @PostMapping("/invoices/{userId}/{amount}/{myMsg}")
    public Invoice createInvoice(@PathVariable String userId,
                                 @PathVariable Integer amount,
                                 @PathVariable String myMsg) {
        return invoiceService.create(userId,amount,myMsg);
    }
     */
    @PostMapping("invoices")
    public Invoice createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.create(invoiceDTO.getUserId(),
                invoiceDTO.getAmount(), invoiceDTO.getMyHello());
    }
}
