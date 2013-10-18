package org.fusesource.workshop.cdi;

import org.fusesource.poc.routes.WebServiceToPojoUsingQueue;
import org.fusesource.workshop.service.DocumentId;
import org.fusesource.workshop.service.DocumentResponse;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.cdi.Uri;
import org.apache.camel.cdi.internal.CamelExtension;
import org.fusesource.workshop.cdi.beans.Service;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(Arquillian.class)
public class WebServiceCamelCdiDeploymentTest {

    @Inject @Uri(value = "activemq:queue:fusesource-input", context = "cxf")
    ProducerTemplate producer;

    @Test
    public void testSend() {
        DocumentId docId = createRequest("666");
        producer.sendBody("activemq:queue:fusesource-input",docId);
        //assertNotNull(response);
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CamelExtension.class.getPackage())
                .addPackage(WebServiceRouting.class.getPackage())
                .addPackage(WebServiceToPojoUsingQueue.class.getPackage())
                .addPackage(Service.class.getPackage())
                .addAsManifestResource("META-INF/beans.xml", "beans.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "ejb-jar.xml");
    }

    private DocumentId createRequest(String id) {
        DocumentId request = new DocumentId();
        request.setId(id);
        return request;
    }
}