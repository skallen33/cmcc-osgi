package com.cmcc.service;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class HelloServiceActivator implements BundleActivator {
    public void start(BundleContext context) throws Exception {
        System.out.println("register hello service");
        context.registerService(HelloService.class.getName(), new HelloServiceImpl(),null);
        System.out.println("register hello service success");
    }

    public void stop(BundleContext context) throws Exception {

    }
}
