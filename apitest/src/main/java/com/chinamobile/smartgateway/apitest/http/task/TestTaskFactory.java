package com.chinamobile.smartgateway.apitest.http.task;

import com.chinamobile.smartgateway.apitest.http.lantask.EthQueryTask;
import com.chinamobile.smartgateway.apitest.util.Debug;

public class TestTaskFactory
{
    public static TestTask buildTask(String name)
    {
        TestTask task = null;
        if ("EthQueryTask Test".equals(name))
        {
            task = new EthQueryTask();
        }
        /*if ("LANHost Event Test".equals(name))
        {
            task = new LANHostEventTestTask();
        }
        else if ("UsbService Event Test".equals(name))
        {
            task = new USBEventTestTask();
        }
        else if ("VOIP Event Test".equals(name))
        {
            task = new VoipEventTestTask();
        }
        else if ("Traffic Mirror Event Test".equals(name))
        {
            task = new TrafficMirrorEventTestTask();
        }
        else if ("Permission Test".equals(name))
        {
            task = new PermissionTestTask();
        }
        else if ("API Test".equals(name))
        {
            task = new APITestTask();
        }
        else if ("Traffic Monitoring Event Test".equals(name))
        {
            task = new TrafficMonitoringEventTestTask();
        }
        else if ("API ThroughPut Test".equals(name))
        {
            APIThroughPutTestTask.setStop(false);
            task = new APIThroughPutTestTask();
        }
        else if ("Traffic QoS Event Test".equals(name))
        {
            task = new TrafficQoSTestEventTask();
        }
        else if ("Traffic Forward Event Test".equals(name))
        {
            task = new TrafficFowardEventTestTask();
        }
        else if ("Traffic Detail Process Test".equals(name))
        {
            task = new TrafficDetailProcessTask();
        }
        else if ("Reset ONT Test".equals(name))
        {
            task = new ResetONTTask();
        }
        else if ("StopApiThroughPut Test".equals(name))
        {
            task = new StopAPIThroughTestTask();
        }
        else if ("LanHostsInfoQuery Test".equals(name))
        {
            task = new LanHostsInfoQueryTask();
        }
        else if ("LanNetworkNameConfig Test".equals(name))
        {
            task = new LanNetworkNameConfigTask();
        }
        else if ("LanHostSpeedLimit Test".equals(name))
        {
            task = new LanHostSpeedLimitTask();
        }
        else if ("LanHostSpeedQuery Test".equals(name))
        {
            task = new LanHostSpeedQueryTask();
        }
        else if ("LanNetworkAccessConfig Test".equals(name))
        {
            task = new LanNetworkAccessConfigTask();
        }
        else if ("WlanQuery Test".equals(name))
        {
            task = new WlanQueryTask();
        }
        else if ("WlanSecretQueryTask Test".equals(name))
        {
            task = new WlanSecretQueryTask();
        }
        else if ("WlanConfigTask Test".equals(name))
        {
            task = new WlanConfigTask();
        }
        else if ("EthQueryTask Test".equals(name))
        {
            task = new EthQueryTask();
        }
        else if ("EthConfigTask Test".equals(name))
        {
            task = new EthConfigTask();
        }
        else if ("LANIPInfoQueryTask Test".equals(name))
        {
            task = new LANIPInfoQueryTask();
        }
        else if ("LANIPConfigTask Test".equals(name))
        {
            task = new LANIPConfigTask();
        }
        else if ("PortMappingQueryTask Test".equals(name))
        {
            task = new PortMappingQueryTask();
        }
        else if ("PortMappingConfigTask Test".equals(name))
        {
            task = new PortMappingConfigTask();
        }
        else if ("UsbServiceTask Test".equals(name))
        {
            UsbServiceTask.setUsbTaskRun(true);
            task = new UsbServiceTask();
        }
        else if ("VoIPInfoQueryService Test".equals(name))
        {
            task = new VoIPInfoQuery();
        }
        else if ("Static Layer3 Forwarding Evevt Test".equals(name))
        {
            task = new StaticLayer3ForwardingEventTestTask();
        }
        else if ("Transfer Query Evevt Test".equals(name))
        {
            task = new TransferQueryEventTestTask();
        }
        else if ("VLAN Bind Evevt Test".equals(name))
        {
            task = new VLANBindEventTestTask();
        }
        else if ("DeviceInfoQueryService Test".equals(name))
        {
            task = new DeviceInfoQueryTask();
        }
        else if ("DeviceSecretInfoQueryService Test".equals(name))
        {
            task = new DeviceSecretInfoQueryTask();
        }
        else if ("DeviceAccessRightQueryService Test".equals(name))
        {
            task = new DeviceAccessRightQueryTask();
        }
        else if ("DeviceAccessRightConfigService Test".equals(name))
        {
            task = new DeviceAccessRightConfigTask();
        }
        else if ("DeviceConfigService Test".equals(name))
        {
            task = new DeviceConfigServiceTask();
        }
        else if ("AccessInfoQueryService Test".equals(name))
        {
            task = new AccessInfoQueryServiceTestTask();
        }
        else if ("WanConfigService Test".equals(name))
        {
            task = new WanConfigServiceTestTask();
        }
        else if ("IPPingDiagnosticsService Test".equals(name))
        {
            task = new IPPingDiagnosticsServiceTestTask();
        }
        else if ("TraceRouteDiagnosticsService Test".equals(name))
        {
            task = new TraceRouteDiagnosticsServiceTestTask();
        }
        else if ("IPDetectService Test".equals(name))
        {
            task = new IPDetectServiceTestTask();
        }
        else if ("L2TPVPNService Test".equals(name))
        {
            task = new L2TPVPNServiceTestTask();
        }
        else if ("HttpDownloadDiagnosticsService Test".equals(name))
        {
            task = new HttpDownloadDiagnosticsServiceTestTask();
        }
        else if ("HttpUploadDiagnosticsService Test".equals(name))
        {
            task = new HttpUploadDiagnosticsServiceTestTask();
        }
        else
        {
            task = new DefaultTestTask();
        }*/
        if (Debug.isEnablelog()) {
            Debug.log("buildTask:[" + name + "]  clazz:" + task.getClass().getSimpleName());
        }
        return task;
    }
}
