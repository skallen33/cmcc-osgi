package com.chinamobile.smartgateway.apitest;

import com.cmcc.apitest.HttpServiceTracker;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class ApitestActivator implements BundleActivator {
    private static ServiceTracker serviceTracker = null;
    private static BundleContext context;

    public static BundleContext getContext()
    {
        return context;
    }

    public static void setContext(BundleContext context)
    {
        context = context;
    }

    public void start(BundleContext context) throws Exception {
        System.out.println("register ServiceTracker start");
        try {
            serviceTracker = new HttpServiceTracker(context);
            serviceTracker.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("register ServiceTracker success");
    }


    public void stop(BundleContext context) throws Exception {

    }
}
