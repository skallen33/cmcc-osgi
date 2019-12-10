package com.chinamobile.smartgateway.apitest.http;

import com.chinamobile.smartgateway.apitest.util.Debug;
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
        Debug.log("Add Http Service!");
        HttpService httpService = (HttpService) this.context.getService(reference);
        try {
            httpService.registerResources("/apitest", "/webs",  new ONTHttpContext(this.context.getBundle()));
            httpService.registerServlet("/apitest/cmcc.cmd", new WebProc(this.context), null, null);
        } catch (Exception e) {
            Debug.log(e);
        }
        return httpService;
    }
    @Override
    public void removedService(ServiceReference reference, Object service) {
        Debug.log("Remove Http Service!");
        HttpService httpService = (HttpService) service;
        try {
            httpService.unregister("/apitest/cmcc.cmd");
            httpService.unregister("/apitest");

            super.removedService(reference, service);
        } catch (Exception e) {
            Debug.log(e);
        }
    }

    @Override
    public void open() {
        System.out.println("open Service!");
        super.open();
    }
}
