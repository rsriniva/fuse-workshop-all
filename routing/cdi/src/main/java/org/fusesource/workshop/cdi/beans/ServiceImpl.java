package org.fusesource.workshop.cdi.beans;

public class ServiceImpl implements Service {

    public String sayHello(String name) {
        System.out.println("Saying hello to " + name);
        return "Hey " + name;
    }

}
