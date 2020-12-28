package org.example.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.context.ApplicationConfiguration;
import org.example.model.Invoice;
import org.example.service.InvoiceService;
import org.example.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MyInvoiceServlet extends HttpServlet {

    private UserService userService;
    private ObjectMapper objectMapper;
    private InvoiceService invoiceService;

    @Override
    public void init() throws ServletException{
        AnnotationConfigApplicationContext ctx
                = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        // is only needed that @PreDestroy works properly with IDE
        ctx.registerShutdownHook();

        this.userService = ctx.getBean(UserService.class);
        this.objectMapper = ctx.getBean(ObjectMapper.class);
        this.invoiceService = ctx.getBean(InvoiceService.class);

        System.out.println("im servlet - init");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        if (request.getRequestURI().equalsIgnoreCase("/invoices")) {
            String userId = request.getParameter("user_id");
            Integer amount = Integer.valueOf(request.getParameter("amount"));

            Invoice invoice = invoiceService.create(userId, amount);

            response.setContentType("application/json; charset=UTF-8");
            String json = new ObjectMapper().writeValueAsString(invoice);
            response.getWriter().print(json);
        }
        else {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
        if (request.getRequestURI().equalsIgnoreCase("/")) {
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().print(
                    "<html>\n" +
                            "<body>\n" +
                            "<h1>Hello Barbara</h1>\n" +
                            "<p>You are working on your first Spring App</p>\n" +
                            "</body>\n" +
                            "</html>\n");
        }
        else if (request.getRequestURI().equalsIgnoreCase("/invoices"))
        {
            response.setContentType("application/json; charset=UTF-8");
            List<Invoice> invoices = invoiceService.findAll();

           System.out.println(objectMapper.writeValueAsString(invoices));
            // (2)
            response.getWriter().print(objectMapper.writeValueAsString(invoices));
            // (3)
        }
    }
}
