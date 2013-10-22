package org.fusesource.workshop.cdi.cxf;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.fusesource.poc.pojo.Feedback;
import org.fusesource.poc.routes.WebServiceToPojoUsingQueue;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@ContextName("cxf")
public class CxfRoute extends RouteBuilder {

    //@EndpointInject(uri = "cxf://bean://wsService")
    @Inject
    Endpoint cxfEndpoint;

    @EndpointInject(uri = "activemq:queue:fusesource-ws?replyTo=wsResponse")
    Endpoint activeMqWSQueueEndpoint;

    @Inject Feedback feedbackBean;

    @Override
    public void configure() throws Exception {

        //cxfEndpoint.setCamelContext(getContext());

        // From WebService to PoJo
        from(cxfEndpoint)
                .id("fromWebServiceToQueue")
                .convertBodyTo(org.fusesource.workshop.service.DocumentId.class)
                .inOut(activeMqWSQueueEndpoint)
                .log(">>> WebService called and incident created : ${body}");

        // Consume message from WS queue for Web Service
        from(activeMqWSQueueEndpoint)
                .id("fromQueueToPoJo")
                .log(">>> Web Service Message : ${body}")
                .transform()
                   .method(feedbackBean, "clientReply");

    }

}
