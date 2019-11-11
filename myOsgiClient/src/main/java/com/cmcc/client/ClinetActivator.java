package com.cmcc.client;
import com.cmcc.service.HelloService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ClinetActivator implements BundleActivator {
    public void start(BundleContext context) throws Exception {
        try {
            System.out.println("myOsgiClient service start");

            HelloService hello1 =
                    (HelloService) context.getService(
                            context.getServiceReference(HelloService.class.getName()));
            System.out.println(hello1.sayHello());

            System.out.println("myOsgiClient service success");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop(BundleContext context) throws Exception {
        System.out.println("myBundle service stop");
    }
}
