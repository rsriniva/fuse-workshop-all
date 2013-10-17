package org.fusesource.poc.routes;

import org.apache.camel.Endpoint;
import org.apache.camel.EndpointInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;

public class FileToPoJoUsingQueue extends RouteBuilder {


    @EndpointInject(ref = "fileUri")
    Endpoint fileEndpoint;

    @EndpointInject(ref = "activeMqQueueUri")
    Endpoint activeMqQueueEndpoint;


    @Override
    public void configure() throws Exception {

        JaxbDataFormat jaxb = new JaxbDataFormat("org.fusesource.workshop.service");

        // Consume file and sending it to a Queue
        from(fileEndpoint)
        .id("fromFileToQueue")
        .convertBodyTo(String.class)
        .log(">>> File received : ${body}")
        .beanRef("feedbackBean", "createRequest")
        .log(">>> DocumentId created")
        .to(activeMqQueueEndpoint);

        // Consume message from queue for file and log info
        from(activeMqQueueEndpoint)
        .id("fromQueueToLog")
        .beanRef("feedbackBean", "clientReply")
        .log(">>> Incident response created")
        .marshal(jaxb)
        .convertBodyTo(String.class)
        .log(">>> Incident created : ${body}");


    }

}
