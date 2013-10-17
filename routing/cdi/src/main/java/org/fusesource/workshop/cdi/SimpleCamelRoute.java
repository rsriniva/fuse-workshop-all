package org.fusesource.workshop.cdi;

import javax.inject.Inject;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.fusesource.workshop.cdi.beans.Service;

@ContextName("first")
public class SimpleCamelRoute extends RouteBuilder {

    @Inject
    private Service service;

    @Override
    public void configure() throws Exception {

        from("timer:foo?period=10s")
           .setBody(constant("Charles"))
            .to("direct:serviceCall");

        from("direct:serviceCall")
                .bean(service);

    }

}