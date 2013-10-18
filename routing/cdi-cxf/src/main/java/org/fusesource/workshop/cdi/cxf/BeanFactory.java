package org.fusesource.workshop.cdi.cxf;

import org.apache.camel.component.cxf.CxfEndpoint;
import org.fusesource.poc.pojo.Feedback;

import javax.enterprise.inject.Produces;

public class BeanFactory {

    @Produces
    public CxfEndpoint getWebService() throws ClassNotFoundException {
        CxfEndpoint cxfEndpoint = new CxfEndpoint();
        cxfEndpoint.setAddress("http://localhost:9090/cxf/service");
        cxfEndpoint.setServiceClass("org.fusesource.workshop.service.Documents");
        cxfEndpoint.setLoggingFeatureEnabled(true);
        return cxfEndpoint;
    }

    @Produces
    public Feedback getFeedbackBean() {
        return new Feedback();
    }

}
