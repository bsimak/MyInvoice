package org.example.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.InvoiceService;

public class Application {
    public static final InvoiceService invoiceService = new InvoiceService();
    public static final ObjectMapper objectMapper = new ObjectMapper();
}
