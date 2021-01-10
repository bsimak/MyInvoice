package org.example.web;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class InvoiceControllerRESTonly {
/*
    private final InvoiceService invoiceService;

    public InvoiceControllerRESTonly(InvoiceService invoiceService) {
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

    // POST Method
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
    // Ergebnisse aus dem RequestBody werden als Parameter übergeben
    /*
    @PostMapping("/invoices")
    public Invoice createInvoice(@RequestBody @Valid InvoiceDTO invoiceDTO) {
        return invoiceService.create(invoiceDTO.getUserId(),
                invoiceDTO.getAmount(), invoiceDTO.getMyHello());
    }
    */
}
