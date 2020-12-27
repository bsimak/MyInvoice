package org.example.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ApplicationLauncher;
import org.example.service.InvoiceService;
import org.example.service.UserService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
public class ApplicationConfiguration {
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
