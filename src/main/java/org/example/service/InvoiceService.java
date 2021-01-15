package org.example.service;

import org.example.model.Invoice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Component
public class InvoiceService {

    private final JdbcTemplate jdbcTemplate;

    private final UserService userService;
    private final String cdnUrl;
    private final String message;

    public InvoiceService(UserService userService,
                          JdbcTemplate jdbcTemplate,
                          @Value("${cdn.url}") String cdnUrl,
                          @Value("${message}") String message) {
        this.userService = userService;
        this.cdnUrl = cdnUrl;
        this.message = message;
        this.jdbcTemplate = jdbcTemplate;
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
        return jdbcTemplate.query("select id, user_id,pdf_url, amount, message " +
                "from invoices", (resultSet, rowNum)-> {
            Invoice invoice = new Invoice();
            invoice.setId(resultSet.getObject("id").toString());
            invoice.setPdfUrl(resultSet.getString("pdf_url"));
            invoice.setUserId(resultSet.getString("user_id"));
            invoice.setAmount(resultSet.getInt("amount"));
            invoice.setMessage(resultSet.getString("message"));
            return invoice;
        });

    }
    public Invoice create(String userId, Integer amount, String message) {
        String generatedPdfUrl = cdnUrl + "images/default/sample.pdf";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into invoices (user_id, pdf_url, amount, message) values (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, userId);
            ps.setString(2,generatedPdfUrl);
            ps.setInt(3,amount);
            ps.setString(4,message);
            return ps;
        }, keyHolder);

        String uuid;
        uuid = !keyHolder.getKeys().isEmpty() ?
                keyHolder.getKeys().values().iterator().next().toString() : null;
        Invoice invoice = new Invoice();
        invoice.setId(uuid);
        invoice.setPdfUrl(generatedPdfUrl);
        invoice.setAmount(amount);
        invoice.setUserId(userId);
        invoice.setMessage(message);
        return invoice;
    }
}