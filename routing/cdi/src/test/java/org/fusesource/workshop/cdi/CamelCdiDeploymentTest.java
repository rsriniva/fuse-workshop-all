package org.fusesource.workshop.cdi;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

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


@RunWith(Arquillian.class)
public class CamelCdiDeploymentTest {

    @Inject @Uri(value = "seda:foo", context = "first")
    ProducerTemplate producer;

    @Test
    public void testSend() {
        Object response = producer.requestBody("direct:serviceCall", "Mark");

        assertEquals("Hey Mark", response);
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CamelExtension.class.getPackage())
                .addPackage(SimpleCamelRoute.class.getPackage())
                .addPackage(Service.class.getPackage())
                .addAsManifestResource("META-INF/beans.xml", "beans.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "ejb-jar.xml");
    }
}