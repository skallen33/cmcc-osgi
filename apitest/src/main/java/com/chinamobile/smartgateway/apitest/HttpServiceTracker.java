package com.chinamobile.smartgateway.apitest;

import com.chinamobile.smartgateway.apitest.http.WebProc;
import com.chinamobile.smartgateway.apitest.http.ONTHttpContext;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.util.tracker.ServiceTracker;

public class HttpServiceTracker extends ServiceTracker {
    public HttpServiceTracker(BundleContext context) {
        super(context, HttpService.class.getName(), null);
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
