package org.example.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.InvoiceService;
import org.example.service.UserService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ApplicationConfiguration {
    @Bean
    @Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public UserService userService(){
        return new UserService();
    }
    @Bean
    public InvoiceService invoiceService(UserService userService){
        return new InvoiceService(userService);
    }
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
