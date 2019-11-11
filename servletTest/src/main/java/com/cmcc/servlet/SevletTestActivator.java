package com.cmcc.servlet;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;
import org.osgi.util.tracker.ServiceTrackerCustomizer;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

public class SevletTestActivator implements BundleActivator/*, ServiceListener*/ {
    /*private ServiceReference ref;
    private Servlet servlet;
    private static BundleContext context;
    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        servlet = new LoginServlet();
        registerServlet();
        context.addServiceListener(this,
                "(objectClass=" + HttpService.class.getName() + ")");
    }
    public void stop(BundleContext bundleContext) throws Exception {
        try {
            unregisterServlet();
        } catch (Throwable t) {
            t.printStackTrace();
        }

        servlet = null;
        context = null;
        ref = null;
    }

    private void registerServlet() {
        if (ref == null) {
            ref = context.getServiceReference(HttpService.class.getName());
        }

        if (ref == null) {
            System.out.println("HttpService is null");
            return;
        }

        try {
            HttpService http = (HttpService) context.getService(ref);
            http.registerServlet("/login", servlet, null, null);
            http.registerResources("/page", "page", null);
            System.out.println("已启动用户登录验证web模块，请通过/page/login.htm访问");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void unregisterServlet() {
        if (ref != null) {
            try {
                HttpService http = (HttpService) context.getService(ref);
                http.unregister("/login");
                http.unregister("/page");
                System.out.println("已卸载用户登录验证web模块！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void serviceChanged(ServiceEvent event) {
        switch (event.getType()) {
            case ServiceEvent.REGISTERED:
                registerServlet();
                break;

            case ServiceEvent.UNREGISTERING:
                unregisterServlet();
                break;
        }
    }*/

    private ServiceTracker httpServiceTracker;
    private BundleContext ctx;
    private HttpService httpService;

    private ServiceTrackerCustomizer createHttpServiceCustomizer(){
        return new ServiceTrackerCustomizer(){
            public Object addingService(ServiceReference arg0) {
                System.out.println("adding service");
                Object service = ctx.getService(arg0);
                SevletTestActivator.this.httpService = (HttpService) service;
                try {
                    SevletTestActivator.this.httpService.registerServlet("/login",new LoginServlet(),null,null);
                } catch (ServletException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (NamespaceException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return service;
            }

            public void modifiedService(ServiceReference arg0, Object arg1) {
            }

            public void removedService(ServiceReference arg0, Object arg1) {
                System.out.println("removed service");
                if(arg1 != SevletTestActivator.this.httpService){
                    return;
                }
                SevletTestActivator.this.httpService.unregister("/login");
                SevletTestActivator.this.httpService = null;
            }
        };
    }

    public void start(BundleContext arg0) throws Exception {
        System.out.println("start...");
        ctx = arg0;

        System.out.println(HttpService.class.getName());
        System.out.println(ctx.getServiceReference(HttpService.class.getName()));
//        System.out.println(ctx.getService(ctx.getServiceReference(HttpService.class.getName())));


        ServiceTrackerCustomizer cus = createHttpServiceCustomizer();
        httpServiceTracker = new ServiceTracker(arg0,HttpService.class.getName(),
                cus);
        httpServiceTracker.open();
        System.out.println("start success");
    }

    public void stop(BundleContext arg0) throws Exception {
        httpServiceTracker.close();
    }
}
