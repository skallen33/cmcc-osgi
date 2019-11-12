package com.chinamobile.smartgateway.apitest.http;

import com.chinamobile.smartgateway.apitest.util.GlobalData;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TestItems {
    private static List<String> testPackages = new ArrayList();

    static
    {
        testPackages.add("com.chinamobile.smartgateway.transferservices");
        testPackages.add("com.chinamobile.smartgateway.lanservices");
        testPackages.add("com.chinamobile.smartgateway.addressservices");
        testPackages.add("com.chinamobile.smartgateway.voipservices");
        testPackages.add("com.chinamobile.smartgateway.commservices");
        testPackages.add("com.chinamobile.smartgateway.accessservices");
    }
/*
    public static JSONObject getAPITTestTerm()
            throws JSONException
    {
        JSONObject subjson = new JSONObject();

        JSONArray jsonArray = new JSONArray();
        for (String api : testPackages)
        {
            JSONObject apiJsonObj = new JSONObject();
            apiJsonObj.put("Name", api);
            apiJsonObj.put("InputParameter", new JSONObject());
            apiJsonObj.put("OutputParameter", new JSONObject());
            jsonArray.put(apiJsonObj);
        }
        subjson.put("Name", "API Test");
        subjson.put("SubItems", jsonArray);

        subjson.put("Method", "Java");

        return subjson;
    }

    public static JSONObject getpermissionTerm()
            throws JSONException
    {
        JSONObject subjson = new JSONObject();
        subjson.put("Name", "Permission Test");
        subjson.put("Method", "Java");
        JSONArray jsonArray = new JSONArray();
        for (String api : testPackages)
        {
            JSONObject apiJsonObj = new JSONObject();
            apiJsonObj.put("Name", api);
            apiJsonObj.put("InputParameter", new JSONObject());
            apiJsonObj.put("OutputParameter", new JSONObject());
            jsonArray.put(apiJsonObj);
        }
        subjson.put("SubItems", jsonArray);
        return subjson;
    }

    private static JSONObject getTestTerm(String title)
            throws JSONException
    {
        if (title.equals("API Test")) {
            return getAPITTestTerm();
        }
        if (title.equals("Permission Test")) {
            return getpermissionTerm();
        }
        return null;
    }

    public static JSONArray getAllTestTerm()
            throws JSONException
    {
        JSONArray jsonArray = new JSONArray();

        jsonArray.put(getTestTerm("API Test"));

        jsonArray.put(getTestTerm("Permission Test"));

        jsonArray.put(getEventTestTerm("UsbService Event Test"));

        jsonArray.put(getEventTestTerm("VOIP Event Test"));

        jsonArray.put(getEventTestTerm("LANHost Event Test"));

        jsonArray.put(getEventTestTerm("Traffic Mirror Event Test"));

        jsonArray.put(getEventTestTerm("Traffic Monitoring Event Test"));

        jsonArray.put(getEventTestTerm("Traffic QoS Event Test"));

        jsonArray.put(getEventTestTerm("Traffic Forward Event Test"));

        jsonArray.put(getEventTestTerm("API ThroughPut Test"));

        jsonArray.put(getEventTestTerm("Traffic Detail Process Test"));

        jsonArray.put(getResetTestTerm());


        jsonArray.put(getEventTestTerm("AccessInfoQueryService Test"));

        jsonArray.put(getEventTestTerm("WanConfigService Test"));

        jsonArray.put(getEventTestTerm("IPPingDiagnosticsService Test"));

        jsonArray.put(getEventTestTerm("TraceRouteDiagnosticsService Test"));

        jsonArray.put(getEventTestTerm("HttpDownloadDiagnosticsService Test"));

        jsonArray.put(getEventTestTerm("HttpUploadDiagnosticsService Test"));

        jsonArray.put(getTransferQueryTestTerm("Transfer Query Evevt Test"));

        jsonArray.put(getVLANBindTestTerm("VLAN Bind Evevt Test"));

        jsonArray.put(getStaticTestTerm("Static Layer3 Forwarding Evevt Test"));


        jsonArray.put(getEventTestTerm("LanHostsInfoQuery Test"));

        jsonArray.put(getEventTestTerm("LanNetworkNameConfig Test"));

        jsonArray.put(getEventTestTerm("LanHostSpeedLimit Test"));

        jsonArray.put(getEventTestTerm("LanHostSpeedQuery Test"));

        jsonArray.put(getEventTestTerm("LanNetworkAccessConfig Test"));

        jsonArray.put(getEventTestTerm("WlanQuery Test"));

        jsonArray.put(getEventTestTerm("WlanSecretQueryTask Test"));

        jsonArray.put(getTaskTestTerm("WlanConfigTask Test"));

        jsonArray.put(getEventTestTerm("EthQueryTask Test"));

        jsonArray.put(getTaskTestTerm("EthConfigTask Test"));


        jsonArray.put(getEventTestTerm("LANIPInfoQueryTask Test"));

        jsonArray.put(getEventTestTerm("LANIPConfigTask Test"));

        jsonArray.put(getTaskTestTerm("PortMappingQueryTask Test"));

        jsonArray.put(getEventTestTerm("PortMappingConfigTask Test"));


        jsonArray.put(getTaskTestTerm("DeviceInfoQueryService Test"));

        jsonArray.put(getTaskTestTerm("DeviceSecretInfoQueryService Test"));

        jsonArray.put(getEventTestTerm("DeviceAccessRightQueryService Test"));

        jsonArray.put(getEventTestTerm("DeviceAccessRightConfigService Test"));

        jsonArray.put(getEventTestTerm("DeviceConfigService Test"));

        jsonArray.put(getTaskTestTerm("UsbServiceTask Test"));

        jsonArray.put(getTaskTestTerm("VoIPInfoQueryService Test"));

        jsonArray.put(getEventTestTerm("IPDetectService Test"));

        jsonArray.put(getEventTestTerm("L2TPVPNService Test"));

        return jsonArray;
    }

    private static JSONObject getVLANBindTestTerm(String type)
            throws JSONException
    {
        JSONObject subjson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject sub = new JSONObject();
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("enable", "0");
        inputJSONObj.put("bind", "200/200");
        inputJSONObj.put("lanInterface", "LAN1");
        inputJSONObj.put("chearLanInterface", "LAN1");
        inputJSONObj.put("testStep", "step1");
        sub.put("Name", type);
        sub.put("InputParam", inputJSONObj);
        sub.put("OutputParam", new JSONObject());
        jsonArray.put(sub);
        subjson.put("Name", type);
        subjson.put("SubItems", jsonArray);
        subjson.put("Method", "Java");
        return subjson;
    }

    private static JSONObject getTransferQueryTestTerm(String type)
            throws JSONException
    {
        JSONObject subjson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject sub = new JSONObject();
        sub.put("Name", type);
        sub.put("InputParam", new JSONObject());
        sub.put("OutputParam", new JSONObject());
        jsonArray.put(sub);
        subjson.put("Name", type);
        subjson.put("SubItems", jsonArray);
        subjson.put("Method", "Java");
        return subjson;
    }

    private static JSONObject getStaticTestTerm(String type)
            throws JSONException
    {
        JSONObject subjson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject sub = new JSONObject();
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("VLAN1", "300");
        inputJSONObj.put("VLAN2", "400");

        inputJSONObj.put("DestIPAddress1", "192.168.1.0");
        inputJSONObj.put("DestSubnetMask1", "255.255.255.0");
        inputJSONObj.put("GatewayIPAddress1", "192.168.1.1");

        inputJSONObj.put("DestIPAddress2", "10.17.199.0");
        inputJSONObj.put("DestSubnetMask2", "255.255.255.0");
        inputJSONObj.put("GatewayIPAddress2", "10.17.199.254");
        sub.put("Name", type);
        sub.put("InputParam", inputJSONObj);
        sub.put("OutputParam", new JSONObject());
        jsonArray.put(sub);
        subjson.put("Name", type);
        subjson.put("SubItems", jsonArray);
        subjson.put("Method", "Java");
        return subjson;
    }

    private static JSONObject getTaskTestTerm(String type)
            throws JSONException
    {
        JSONObject subjson = new JSONObject();

        JSONArray jsonArray = new JSONArray();

        JSONObject apiJsonObj = new JSONObject();
        apiJsonObj.put("Name", type);
        apiJsonObj.put("InputParameter", new JSONObject());
        apiJsonObj.put("OutputParameter", new JSONObject());
        jsonArray.put(apiJsonObj);

        subjson.put("Name", type);
        subjson.put("SubItems", jsonArray);

        subjson.put("Method", "Java");

        return subjson;
    }

    private static JSONObject getResetTestTerm()
            throws JSONException
    {
        JSONObject subjson = new JSONObject();

        JSONArray jsonArray = new JSONArray();

        JSONObject apiJsonObj = new JSONObject();
        apiJsonObj.put("Name", "Reset ONT");
        apiJsonObj.put("InputParameter", new JSONObject());
        apiJsonObj.put("OutputParameter", new JSONObject());
        jsonArray.put(apiJsonObj);

        subjson.put("Name", "Reset ONT Test");
        subjson.put("SubItems", jsonArray);

        subjson.put("Method", "Java");

        return subjson;
    }

    private static JSONObject buildTrafficMirrorInputObj()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("enable", "0");
        inputJSONObj.put("remoteAddress", "192.168.100.1");
        inputJSONObj.put("remotePort", "0");
        inputJSONObj.put("direction", "ALL");
        inputJSONObj.put("protocol", "TCP");
        inputJSONObj.put("hostMAC", GlobalData.getLanDeviceMac());
        inputJSONObj.put("mirrorToIP", "192.168.1.1");
        inputJSONObj.put("mirrorToPort", 8080);

        return inputJSONObj;
    }

    private static JSONObject buildTrafficMonitoringInputObj()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("destAddressList", "{10.11.11.1/32,10.11.11.0/32}");
        return inputJSONObj;
    }

    private static JSONObject buildTrafficFowardInputObj()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("enable", "0");
        inputJSONObj.put("remoteAddress", "10.11.11.1");
        inputJSONObj.put("remotePort", "0");
        inputJSONObj.put("protocol", "TCP");
        inputJSONObj.put("hostMAC", GlobalData.getLanDeviceMac());
        inputJSONObj.put("forwardToIP", "10.11.10.1");
        inputJSONObj.put("forwardToPort", 8080);

        return inputJSONObj;
    }

    private static JSONObject buildTrafficDetailProcessInputObj()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("enable", "0");
        inputJSONObj.put("remoteAddress", "192.168.1.1");
        inputJSONObj.put("remotePort", 80);
        inputJSONObj.put("direction", "ALL");
        inputJSONObj.put("hostMAC", GlobalData.getLanDeviceMac());
        inputJSONObj.put("methodList", "GET,POST");
        inputJSONObj.put("statuscodeList", "100,200,204");
        inputJSONObj.put("headerList", "Content-Type,Connection");

        return inputJSONObj;
    }

    private static JSONObject buildTrafficQoSInputObj()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("enable", "0");
        inputJSONObj.put("remoteAddress",
                "{10.11.11.0/32,10.11.11.1/32,10.11.11.2/32,10.11.11.3/32,10.11.11.4/32,10.11.11.5/32,10.11.11.6/32,10.11.11.7/32}");
        inputJSONObj.put("remotePort", "0");
        inputJSONObj.put("upSpeed", "{5000,10000,15000,20000,0,0,0,0}");
        return inputJSONObj;
    }

    private static JSONObject buildLanHostsInfoObj()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("lanx", "LAN1");
        inputJSONObj.put("ssidx", "SSID1");
        inputJSONObj.put("teststep", "step1");
        inputJSONObj.put("phonemac", GlobalData.getLanDeviceMac());
        return inputJSONObj;
    }

    private static JSONObject buildLanNetworkNameconfig()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("Mac", "58:1F:28:89:2B:90");
        inputJSONObj.put("networkName", "P");

        return inputJSONObj;
    }

    private static JSONObject buildLanHostSpeedLimit()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("Mac", "58:1F:28:89:2B:90");
        inputJSONObj.put("enable", "0");
        inputJSONObj.put("upSpeed", "1024");
        inputJSONObj.put("downSpeed", "2048");
        return inputJSONObj;
    }

    private static JSONObject buildLanHostQuery()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("Mac", "58:1F:28:89:2B:90");

        return inputJSONObj;
    }

    private static JSONObject buildLanNetworkAccessConfig()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("Mode", "1");
        inputJSONObj.put("hostMAC", "2c:27:d7:e9:d1:23,58:1F:28:89:2B:90");
        inputJSONObj.put("enable", "0");

        return inputJSONObj;
    }

    private static JSONObject buildWlanQuery()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("step", "step1");
        inputJSONObj.put("ssidx", "1,2,3,4");
        inputJSONObj.put("radioType", "2.4G");
        inputJSONObj.put("hostMAC", "2c:27:d7:e9:d1:23,58:1F:28:89:2B:90");

        return inputJSONObj;
    }

    private static JSONObject buildWlanSecretQuery()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("ssidx", "1,2");
        return inputJSONObj;
    }

    private static JSONObject buildEthQuery()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("portIndex", "1");
        inputJSONObj.put("step", "step1");
        return inputJSONObj;
    }

    private static JSONObject buildPortMappingConfig()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("internalClient", "192.168.1.32");
        inputJSONObj.put("externalPort", "21");
        inputJSONObj.put("enable", "0");

        return inputJSONObj;
    }

    private static JSONObject buildLANIPInfoQuery()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("step", "step1");
        return inputJSONObj;
    }

    private static JSONObject buildLANIPConfig()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();

        inputJSONObj.put("localIP", "192.168.1.1");
        inputJSONObj.put("submask", "255.255.255.0");
        inputJSONObj.put("DHCPStartIP", "192.168.1.2");
        inputJSONObj.put("DHCPEndIP", "192.168.1.254");
        return inputJSONObj;
    }

    private static JSONObject buildDeviceInfoQueryService()
    {
        JSONObject inputJSONObj = new JSONObject();
        return inputJSONObj;
    }

    private static JSONObject buildDeviceSecretInfoQueryService()
    {
        JSONObject inputJSONObj = new JSONObject();
        return inputJSONObj;
    }

    private static JSONObject buildDeviceAccessRightQueryService()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("FTPRight", "0");
        inputJSONObj.put("SambaRight", "0");
        inputJSONObj.put("HTTPRight", "0");
        return inputJSONObj;
    }

    private static JSONObject buildDeviceAccessRightConfigService()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("FTPRight", "0");
        inputJSONObj.put("FTPanonymous", "false");
        inputJSONObj.put("SambaRight", "0");
        inputJSONObj.put("Sambaanonymous", "false");
        inputJSONObj.put("HTTPRight", "0");
        inputJSONObj.put("testStep", "step1");
        inputJSONObj.put("password", "123456");
        inputJSONObj.put("deleFTPAccout", "FTPAdmin0");
        inputJSONObj.put("deleSamAccount", "SambaAdmin0");

        return inputJSONObj;
    }

    private static JSONObject buildDeviceConfigService()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("enable", "0");
        return inputJSONObj;
    }

    private static JSONObject buildTraceRouteDiagnosticsService()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("wanIndex", Integer.parseInt(GlobalData.getWanIndex()));
        inputJSONObj.put("host", "192.168.1.1");
        inputJSONObj.put("traceRouteParameter",
                "{'NumberOfTries':3,'Timeout':5000,'DataBlockSize':38,'DSCP':0,'MaxHopCount':30}");
        return inputJSONObj;
    }

    private static JSONObject buildIPPingDiagnosticsService()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("wanIndex", Integer.parseInt(GlobalData.getWanIndex()));
        inputJSONObj.put("host", "192.168.1.1");
        inputJSONObj.put("pingParameter", "{'NumberOfRepetitions':4,'Timeout':10000,'DataBlockSize':56,'DSCP':0}");
        return inputJSONObj;
    }

    private static JSONObject buildAccessInfoQueryService()
            throws NumberFormatException, JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("testStep", "step1");
        inputJSONObj.put("wanIndex", Integer.parseInt(GlobalData.getWanIndex()));
        return inputJSONObj;
    }

    private static JSONObject buildIPdetectService()
            throws NumberFormatException, JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("IP", "192.168.1.1");
        inputJSONObj.put("port", "80");
        inputJSONObj.put("type", "0");
        return inputJSONObj;
    }

    private static JSONObject buildL2TPVPNService()
            throws NumberFormatException, JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("ServerIP", "20.1.20.254");
        inputJSONObj.put("Uname", "iadtest@pppoe");
        inputJSONObj.put("Upass", "iadtest");
        inputJSONObj.put("DestIP", "1.1.1.3");
        inputJSONObj.put("vpnNum", 1);
        return inputJSONObj;
    }

    private static JSONObject buildHttpDownloadDiagnosticsService()
            throws NumberFormatException, JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("wanIndex", Integer.parseInt(GlobalData.getWanIndex()));
        inputJSONObj.put("URL", "http://9.72.10.154:891/download.rar");
        inputJSONObj.put("HttpDownloadParameter", "{'MeasurementOffset':0,'TestDuration ':30,'NumberOfConnections':1}");
        return inputJSONObj;
    }

    private static JSONObject buildHttpUploadDiagnosticsService()
            throws NumberFormatException, JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("wanIndex", Integer.parseInt(GlobalData.getWanIndex()));
        inputJSONObj.put("URL", "http://9.72.10.154:891/upload.rar");
        inputJSONObj.put("HttpUploadParameter", "{'TestFileLength':0,'MeasurementOffset':0,' TestDuration ':30,'NumberOfConnections':1}");
        return inputJSONObj;
    }

    private static JSONObject buildWanConfigService()
            throws JSONException
    {
        JSONObject inputJSONObj = new JSONObject();
        inputJSONObj.put("testStep", "step1");
        inputJSONObj.put("VLANMode", "2");
        inputJSONObj.put("VLANIDMark", "200");
        inputJSONObj.put("ConnectionType", "IP_Routed");



        inputJSONObj.put("802-1pMark", "0");

        inputJSONObj.put("LanInterface-DHCPEnable", "TRUE");
        inputJSONObj.put("IPMode", "1");

        inputJSONObj.put("ExternalIPAddress", "192.168.1.100");
        inputJSONObj.put("SubnetMask", "255.255.255.0");
        inputJSONObj.put("DefaultGateway", "192.168.1.1");
        inputJSONObj.put("DNSServers", "10.11.10.1,10.11.11.1");
        inputJSONObj.put("Username", "");
        inputJSONObj.put("Password", "");
        inputJSONObj.put("AddressingType", "Static");

        inputJSONObj.put("wan1-802-1pMark", "2");
        inputJSONObj.put("wan2-802-1pMark", "3");
        inputJSONObj.put("wan3-802-1pMark", "5");
        return inputJSONObj;
    }

    private static JSONObject getEventTestTerm(String type)
            throws JSONException
    {
        JSONObject subjson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject sub = new JSONObject();

        sub.put("Name", type);
        if (type.indexOf("Traffic") > -1)
        {
            if (type.indexOf("Mirror") > -1) {
                sub.put("InputParam", buildTrafficMirrorInputObj());
            } else if (type.indexOf("Monitoring") > -1) {
                sub.put("InputParam", buildTrafficMonitoringInputObj());
            } else if (type.indexOf("QoS") > -1) {
                sub.put("InputParam", buildTrafficQoSInputObj());
            } else if (type.indexOf("Forward") > -1) {
                sub.put("InputParam", buildTrafficFowardInputObj());
            } else if (type.indexOf("Traffic") > -1) {
                sub.put("InputParam", buildTrafficDetailProcessInputObj());
            }
        }
        else if (type.indexOf("LanHostsInfoQuery") > -1) {
            sub.put("InputParam", buildLanHostsInfoObj());
        } else if (type.indexOf("LanNetworkNameConfig") > -1) {
            sub.put("InputParam", buildLanNetworkNameconfig());
        } else if (type.indexOf("LanHostSpeedLimit") > -1) {
            sub.put("InputParam", buildLanHostSpeedLimit());
        } else if (type.indexOf("LanHostSpeedQuery") > -1) {
            sub.put("InputParam", buildLanHostQuery());
        } else if (type.indexOf("LanNetworkAccessConfig") > -1) {
            sub.put("InputParam", buildLanNetworkAccessConfig());
        } else if (type.indexOf("WlanQuery") > -1) {
            sub.put("InputParam", buildWlanQuery());
        } else if (type.indexOf("WlanSecretQueryTask") > -1) {
            sub.put("InputParam", buildWlanSecretQuery());
        } else if (type.indexOf("EthQueryTask Test") > -1) {
            sub.put("InputParam", buildEthQuery());
        } else if (type.indexOf("PortMappingConfigTask Test") > -1) {
            sub.put("InputParam", buildPortMappingConfig());
        } else if (type.indexOf("LANIPInfoQueryTask Test") > -1) {
            sub.put("InputParam", buildLANIPInfoQuery());
        } else if (type.indexOf("LANIPConfigTask Test") > -1) {
            sub.put("InputParam", buildLANIPConfig());
        } else if (type.indexOf("DeviceInfoQueryService") > -1) {
            sub.put("InputParam", buildDeviceInfoQueryService());
        } else if (type.indexOf("DeviceSecretInfoQueryService") > -1) {
            sub.put("InputParam", buildDeviceSecretInfoQueryService());
        } else if (type.indexOf("DeviceAccessRightQueryService") > -1) {
            sub.put("InputParam", buildDeviceAccessRightQueryService());
        } else if (type.indexOf("DeviceAccessRightConfigService") > -1) {
            sub.put("InputParam", buildDeviceAccessRightConfigService());
        } else if (type.indexOf("DeviceConfigService") > -1) {
            sub.put("InputParam", buildDeviceConfigService());
        } else if (type.indexOf("IPPingDiagnosticsService") > -1) {
            sub.put("InputParam", buildIPPingDiagnosticsService());
        } else if (type.indexOf("TraceRouteDiagnosticsService") > -1) {
            sub.put("InputParam", buildTraceRouteDiagnosticsService());
        } else if (type.indexOf("AccessInfoQueryService") > -1) {
            sub.put("InputParam", buildAccessInfoQueryService());
        } else if (type.indexOf("WanConfigService") > -1) {
            sub.put("InputParam", buildWanConfigService());
        } else if (type.indexOf("IPDetectService") > -1) {
            sub.put("InputParam", buildIPdetectService());
        } else if (type.indexOf("L2TPVPNService") > -1) {
            sub.put("InputParam", buildL2TPVPNService());
        } else if (type.indexOf("HttpDownloadDiagnosticsService") > -1) {
            sub.put("InputParam", buildHttpDownloadDiagnosticsService());
        } else if (type.indexOf("HttpUploadDiagnosticsService") > -1) {
            sub.put("InputParam", buildHttpUploadDiagnosticsService());
        } else {
            sub.put("InputParam", new JSONObject());
        }
        sub.put("OutputParam", new JSONObject());
        jsonArray.put(sub);

        subjson.put("Name", type);
        subjson.put("SubItems", jsonArray);
        subjson.put("Method", "Java");

        return subjson;
    }*/

    public static List<String> getTestPackages()
    {
        return testPackages;
    }
}
