package org.fusesource.workshop.cdi.beans;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;

@Decorator
public class ServiceDecorator implements Service {

    @Inject @Delegate
    private Service service;

    public String sayHello(String name) {
        System.out.println("Before calling " + service);
        return service.sayHello(name);
    }

}
