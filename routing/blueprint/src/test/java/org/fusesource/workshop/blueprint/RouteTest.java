package org.fusesource.workshop.blueprint;

import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class RouteTest extends CamelBlueprintTestSupport {

    @Override
    protected String getBlueprintDescriptor() {
        return "OSGI-INF/blueprint/camel-context.xml";
    }

    @EndpointInject(uri = "mock:result")
    public MockEndpoint result;

    @Produce(uri = "direct:start")
    private ProducerTemplate template;

    @Test
    public void testWebService() throws Exception {
        result.expectedBodiesReceived(response);
        template.sendBody(payload);

        result.assertIsSatisfied();
    }

    private final static String payload = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.workshop.fusesource.org\">\n" +
            "   <soapenv:Header/>\n" +
            "   <soapenv:Body>\n" +
            "      <ser:documentId>\n" +
            "         <id>123</id>\n" +
            "      </ser:documentId>\n" +
            "   </soapenv:Body>\n" +
            "</soapenv:Envelope>";

    private final static String response = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><ns2:documentResponse xmlns:ns2=\"http://service.workshop.fusesource.org\"><incidentId>123</incidentId><givenName>Charles</givenName><familyName>Moulliard</familyName><details>&lt;html>&lt;body>Message received !!&lt;/body>&lt;/html></details><email>cmoulliard@gmail.com</email><phone>111 222 333</phone></ns2:documentResponse></soap:Body></soap:Envelope>";

}
