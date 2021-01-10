package org.example.web;

import org.example.model.Invoice;
import org.example.service.InvoiceService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@Validated
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // GET Method
    @RequestMapping(value="/invoices", method = RequestMethod.GET)
    public List<Invoice> invoices() {
        return invoiceService.findAll();
    }

    // Mit validation Bean in ApplicationLauncher Class
    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestParam("user_id") @NotBlank String userId,
                                 @RequestParam @Min(10) @Max(100) Integer amount,
                                 @RequestParam ("my_msg") String myMsg) {
        return invoiceService.create(userId, amount ,myMsg);
    }
}
