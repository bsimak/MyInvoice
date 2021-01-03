package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.example.context.ApplicationConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;

public class ApplicationLauncher {

    static final int defPort = 8080;

    public static void main(String[] args) throws LifecycleException {
        /* use this with java -Dport80xx -jar target/xy
         if Port not provided as Parameter -> use default Port */

        int serverPort = 8080;
        String portProperty = (System.getProperty("server.port"));
        if (portProperty != null) {
            serverPort = Integer.parseInt(portProperty);
        }

        Tomcat tomcat = new Tomcat();

        tomcat.setPort(serverPort);
        tomcat.getConnector();

        Context tomcatCtx = tomcat.addContext("", null);
        // Dispatcher Servlet
        WebApplicationContext appCtx = createApplicationContext(tomcatCtx.getServletContext());
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appCtx);

        // Wrapper servlet = Tomcat.addServlet(ctx, "org.example.web.MyInvoiceServlet", new MyInvoiceServlet());
        Wrapper servlet = Tomcat.addServlet(tomcatCtx, "dispatcherServlet", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");

        tomcat.start();
        System.out.println("Tomcat started.....");
        System.out.println("Listen at Port: " + serverPort);
    }

    // new - added for Dispatcher Servlet
    public static WebApplicationContext createApplicationContext(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ApplicationConfiguration.class);
        ctx.setServletContext(servletContext);
        ctx.refresh();
        ctx.registerShutdownHook();
        return ctx;
    }
}