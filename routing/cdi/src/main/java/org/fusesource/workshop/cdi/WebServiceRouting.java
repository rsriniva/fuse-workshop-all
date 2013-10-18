package org.fusesource.workshop.cdi;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.fusesource.poc.routes.WebServiceToPojoUsingQueue;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ContextName("cxf")
public class WebServiceRouting {

    @Inject
    private CamelContext ctx;

    private WebServiceToPojoUsingQueue cxfRoute;

    public WebServiceRouting() {
        cxfRoute = new WebServiceToPojoUsingQueue();
    }

    @PostConstruct
    private void loadAndStart() throws Exception {
       System.out.print(">> Context loaded");
       ctx.addRoutes(cxfRoute);
       ctx.start();
    }

}
