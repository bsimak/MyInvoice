package org.example.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ApplicationLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
// Injection of Property-Fields in a property file
// Reihenfolge ist wichtig (overwrite) & falls File nicht vorhanden ignore
@PropertySource("classpath:/application.properties")
@PropertySource(value="classpath:/application-${spring.profiles.active}.properties"
    , ignoreResourceNotFound = true)
@EnableWebMvc
public class ApplicationConfiguration {

    // für Validation @ReuestParam -> MyInvoiceDTO
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[] {".html", "*.xhtml"});
        return viewResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/templates");
        templateResolver.setCacheable(false); // phase dev: false, prod: true
        return templateResolver;
    }
}
