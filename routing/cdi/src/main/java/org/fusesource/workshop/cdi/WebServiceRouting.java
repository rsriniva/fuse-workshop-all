package org.fusesource.workshop.cdi;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.fusesource.poc.routes.WebServiceToPojoUsingQueue;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class WebServiceRouting {

    @Inject @ContextName("cxf")
    private CamelContext ctx;

    @Inject
    private WebServiceToPojoUsingQueue cxfRoute;

    @PostConstruct
    private void loadAndStart() throws Exception {
       System.out.print(">> Context loaded");
       ctx.addRoutes(cxfRoute);
       ctx.start();
    }

}
