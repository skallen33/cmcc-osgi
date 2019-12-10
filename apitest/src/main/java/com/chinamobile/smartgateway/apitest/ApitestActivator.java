package com.chinamobile.smartgateway.apitest;

import com.chinamobile.smartgateway.apitest.http.HttpServiceTracker;
import com.chinamobile.smartgateway.apitest.util.Debug;
import com.chinamobile.smartgateway.apitest.util.GlobalData;
import com.chinamobile.smartgateway.commservices.UsbService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

import java.io.File;

public class ApitestActivator implements BundleActivator {
    private static ServiceTracker serviceTracker = null;
    private static BundleContext context;

//    private static TrafficForwardService trafficFowardService = null;
//    private static TrafficMonitoringConfigService trafficMonitoringService = null;
    private static ServiceTracker httpServiceTracker = null;
//    private static List<InterfaceInfo> apis = new ArrayList();

    public static BundleContext getContext()
    {
        return context;
    }

    public static void setContext(BundleContext context)
    {
        context = context;
    }

    public void start(BundleContext bundleContext) throws Exception {
        context = bundleContext;
        Debug.log("apitest plugin start");

        File file = new File("/var/felix-temp");

        boolean log_exist = file.exists();
        boolean flag = true;
        if (!log_exist) {
            flag = new File("/var/felix-temp").mkdirs();
        }
        if (flag) {
            printLog(" start");
        } else {
            Debug.log("mkdirs failed");
        }
        try {
//            serviceTracker = new HttpServiceTracker(context);
//            serviceTracker.open();

//            serviceTracker = new ServiceTracker(context, UsbService.class.getName(), null);
//            serviceTracker.open();
//
//            UsbService usbService = (UsbService)serviceTracker.getService();
//            if (usbService != null) {
//                usbService.usbRegister(bundleContext, 15);
//            }
//            new GlobalData(bundleContext);
//
//            GlobalData.initialGlobalData();
            if (Debug.isEnablelog()) {
                Debug.log("httpservlet start regist");
            }
            httpServiceTracker = new HttpServiceTracker(bundleContext);

            httpServiceTracker.open();
            if (Debug.isEnablelog()) {
                Debug.log("httpservlet regist end");
            }
//            new MsgNoticeHandler(context);

//            readExcelAPIs();
//            if (Debug.isEnablelog()) {
//                Debug.log("excel interface num:" + apis.size());
//            }
        } catch (Exception e) {
            Debug.log(e);
        }

        Debug.log("register ServiceTracker success");
    }


    public void stop(BundleContext bundleContext) throws Exception {
        Debug.log("apitest plugin stop");
        if (serviceTracker != null)
        {
//            UsbService usbService = (UsbService)serviceTracker.getService();
//            if (usbService != null) {
//                usbService.usbUnregister(bundleContext);
//            }
            serviceTracker.close();
        }
        if (httpServiceTracker != null)
        {
            if (Debug.isEnablelog()) {
                Debug.log("httpservlet unregisted");
            }
            httpServiceTracker.close();
        }
//        apis.clear();
        setContext(null);
    }

    public static void printLog(String log)
    {
        if (Debug.isEnablelog()) {
            Debug.log(Debug.buildLogMessage(
                    new String[] { "[", Debug.getCurrentDate(), "]", "apitest", log }));
        }
        if (Debug.isEnablelog()) {
            Debug.log("-----------------------------------");
        }
    }

}
