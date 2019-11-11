package com.cmcc.apitest;

import org.osgi.framework.BundleContext;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

public class HttpServiceTracker extends ServiceTracker {
    public HttpServiceTracker(BundleContext context) {
        super(context, HttpService.class.getName(), null);
    }

    public HttpServiceTracker(BundleContext context, ServiceReference reference,
                              ServiceTrackerCustomizer customizer) {
        super(context, reference, customizer);
    }

    public HttpServiceTracker(BundleContext context, String clazz,
                              ServiceTrackerCustomizer customizer) {
        super(context, clazz, customizer);
    }

    public HttpServiceTracker(BundleContext context, Filter filter,
                              ServiceTrackerCustomizer customizer) {
        super(context, filter, customizer);
    }

    public HttpServiceTracker(BundleContext context, Class clazz,
                              ServiceTrackerCustomizer customizer) {
        super(context, clazz, customizer);
    }
    @Override
    public Object addingService(ServiceReference reference) {
        System.out.println("Add Http Service!");
        HttpService httpService = (HttpService) this.context.getService(reference);
        try {
            httpService.registerResources("/apitest", "/webs",  new ONTHttpContext(this.context.getBundle()));
            httpService.registerServlet("/apitest/cmcc.cmd", new WebProc(), null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpService;
    }
    @Override
    public void removedService(ServiceReference reference, Object service) {
        System.out.println("Remove Http Service!");
        HttpService httpService = (HttpService) service;
        try {
            httpService.unregister("/apitest/cmcc.cmd");
            httpService.unregister("/apitest");

            super.removedService(reference, service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void open() {
        System.out.println("open Service!");
        super.open();
    }
}
