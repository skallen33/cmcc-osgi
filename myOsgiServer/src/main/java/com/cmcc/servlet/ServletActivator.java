package com.cmcc.servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class ServletActivator implements BundleActivator {
    private ServiceTracker httpServiceTracker;

    public void start(final BundleContext bundleContext) throws Exception {
        /*httpServiceTracker = new ServiceTracker(bundleContext, HttpService.class.getName(), null) {
            @Override
            public Object addingService(ServiceReference ref) {
                HttpService httpService = (HttpService) bundleContext.getService(ref);
                try {
                    httpService.registerServlet("/servlet-test", new ExampleServlet(), null, null);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return httpService;
            }

            public void removedService(ServiceReference ref, Object service) {
                try {
                    ((HttpService) service).unregister("/servlet-test");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
        httpServiceTracker.open();*/
    }

    public void stop(BundleContext bundleContext) throws Exception {
//        httpServiceTracker.close();
    }
}
