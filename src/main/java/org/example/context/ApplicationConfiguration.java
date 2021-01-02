package org.example.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ApplicationLauncher;
import org.example.service.InvoiceService;
import org.example.service.UserService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)

// Injection of Property-Fields in a property file
// Reihenfolge ist wichtig (overwrite) & falls File nicht vorhanden ignore
@PropertySource("classpath:/application.properties")
@PropertySource(value="classpath:/application-${spring.profiles.active}.properties"
    , ignoreResourceNotFound = true)

public class ApplicationConfiguration {

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
