package com.chinamobile.smartgateway.apitest.util;

import com.chinamobile.smartgateway.apitest.ApitestActivator;
import com.cmcc.apitest.util.Debug;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.framework.BundleContext;

public class GlobalData
//        extends AbsSetMsgConfig
{
    private static BundleContext context = null;
    private static String wanIndex = "0";
    private static String wanIndex1 = "0";

    public static String getWanIndex1()
    {
        return wanIndex1;
    }

    public static void setWanIndex1(String wanIndex1)
    {
        wanIndex1 = wanIndex1;
    }

    private static String wanName = "0";
    private static String wanName1 = "0";
    private static String lanDeviceMac = "0";
    private static String layer3ForwardIndex = "0";
    private static String layer6ForwardIndex = "0";
    private static String forwardRuleIndex = "0";
    private static String mirrorRuleIndex = "0";
    private static String guestSSIDIndex = "0";
    private static String usbDeviceId = "0";
    private static String usbDeviceHandle = "0";
    private static String wlanSSIDIndex = "0";
    private static String addWanIndex = "0";
    private static String hostIP = "0";
    private static String submask = "0";
    private static String gatewayName = "0";
    private static String ssidEnable = "0";
    private static String ssidEncryptionMode = "0";
    private static String ssidPassword = "0";
    private static String ssidName = "0";
    private static int ssidIndex = 1;
    private static String advertisementEnabled = "0";
    private static boolean flag = false;
    private static String httpPassword = "0";
    private static String httpRight = "0";
    private static String ftpRight = "0";
    private static String ftpAnonymous = "0";
    private static String sambaRight = "0";
    private static String sambaAnonymous = "0";
    private static String lanDeviceName = "0";
    private static String transmitPower = "20";
    private static String frequencyWidth = "20M";
    private static String standard = "0";
    private static String oldlocalIPv4 = "0";
    private static String oldlocalIPv6 = "0";
    private static String oldsmmark = "0";
    private static String oldDHCPStartIP = "0";
    private static String oldDHCPEndIP = "0";
    private static int usb_serial_devid = 0;
    private static int usb_storage_devid = 0;
    private static String usb_serial_handle = "0";
    private static String DevicePassWord = "12345678";
    private static String Province = "BEJ";
    private static String limit = "6";
    private static String times = "3";
    private static String download = "http://9.72.10.154:891/download.rar";
    private static String upload = "http://9.72.10.154:891/upload.rar";

    public static String getDownload()
    {
        return download;
    }

    public static void setDownload(String download)
    {
        download = download;
    }

    public static String getUpload()
    {
        return upload;
    }

    public static void setUpload(String upload)
    {
        upload = upload;
    }

    public static int getSsidIndex()
    {
        return ssidIndex;
    }

    public static void setSsidIndex(int ssidIndex)
    {
        ssidIndex = ssidIndex;
    }

    public static String getLanDeviceName()
    {
        return lanDeviceName;
    }

    public static void setLanDeviceName(String lanDeviceName)
    {
        lanDeviceName = lanDeviceName;
    }

    public static String getTransmitPower()
    {
        return transmitPower;
    }

    public static void setTransmitPower(String transmitPower)
    {
        transmitPower = transmitPower;
    }

    public static String getFrequencyWidth()
    {
        return frequencyWidth;
    }

    public static void setFrequencyWidth(String frequencyWidth)
    {
        frequencyWidth = frequencyWidth;
    }

    public static String getOldlocalIPv4()
    {
        return oldlocalIPv4;
    }

    public static void setOldlocalIPv4(String oldlocalIPv4)
    {
        oldlocalIPv4 = oldlocalIPv4;
    }

    public static String getOldlocalIPv6()
    {
        return oldlocalIPv6;
    }

    public static void setOldlocalIPv6(String oldlocalIPv6)
    {
        oldlocalIPv6 = oldlocalIPv6;
    }

    public static String getOldsmmark()
    {
        return oldsmmark;
    }

    public static void setOldsmmark(String oldsmmark)
    {
        oldsmmark = oldsmmark;
    }

    public static String getOldDHCPStartIP()
    {
        return oldDHCPStartIP;
    }

    public static void setOldDHCPStartIP(String oldDHCPStartIP)
    {
        oldDHCPStartIP = oldDHCPStartIP;
    }

    public static String getOldDHCPEndIP()
    {
        return oldDHCPEndIP;
    }

    public static void setOldDHCPEndIP(String oldDHCPEndIP)
    {
        oldDHCPEndIP = oldDHCPEndIP;
    }

    public static String getWanName1()
    {
        return wanName1;
    }

    public static void setWanName1(String wanName1)
    {
        wanName1 = wanName1;
    }

    public static int getUsb_serial_devid()
    {
        return usb_serial_devid;
    }

    public static void setUsb_serial_devid(int usb_serial_devid)
    {
        usb_serial_devid = usb_serial_devid;
    }

    public static int getUsb_storage_devid()
    {
        return usb_storage_devid;
    }

    public static void setUsb_storage_devid(int usb_storage_devid)
    {
        usb_storage_devid = usb_storage_devid;
    }

    public static String getStandard()
    {
        return standard;
    }

    public static void setStandard(String standard)
    {
        standard = standard;
    }

    public GlobalData(BundleContext context)
    {
//        super(context);
        setContext(context);
    }

    public static String getHttpRight()
    {
        return httpRight;
    }

    public static void setHttpRight(String httpRight)
    {
        httpRight = httpRight;
    }

    public static String getFtpRight()
    {
        return ftpRight;
    }

    public static void setFtpRight(String ftpRight)
    {
        ftpRight = ftpRight;
    }

    public static String getFtpAnonymous()
    {
        return ftpAnonymous;
    }

    public static void setFtpAnonymous(String ftpAnonymous)
    {
        ftpAnonymous = ftpAnonymous;
    }

    public static String getSambaRight()
    {
        return sambaRight;
    }

    public static void setSambaRight(String sambaRight)
    {
        sambaRight = sambaRight;
    }

    public static String getSambaAnonymous()
    {
        return sambaAnonymous;
    }

    public static void setSambaAnonymous(String sambaAnonymous)
    {
        sambaAnonymous = sambaAnonymous;
    }

    public static String getHttpPassword()
    {
        return httpPassword;
    }

    public static void setHttpPassword(String httpPassword)
    {
        httpPassword = httpPassword;
    }

    public static boolean isFlag()
    {
        return flag;
    }

    public static void setFlag(boolean flag)
    {
        flag = flag;
    }

    public static String getAdvertisementEnabled()
    {
        return advertisementEnabled;
    }

    public static void setAdvertisementEnabled(String advertisementEnabled)
    {
        advertisementEnabled = advertisementEnabled;
    }

    public static String getSsidName()
    {
        return ssidName;
    }

    public static void setSsidName(String ssidName)
    {
        ssidName = ssidName;
    }

    public static String getSsidPassword()
    {
        return ssidPassword;
    }

    public static void setSsidPassword(String ssidPassword)
    {
        ssidPassword = ssidPassword;
    }

    public static String getSsidEncryptionMode()
    {
        return ssidEncryptionMode;
    }

    public static void setSsidEncryptionMode(String ssidEncryptionMode)
    {
        ssidEncryptionMode = ssidEncryptionMode;
    }

    public static String getSsidEnable()
    {
        return ssidEnable;
    }

    public static void setSsidEnable(String ssidEnable)
    {
        ssidEnable = ssidEnable;
    }

    public static String getGatewayName()
    {
        return gatewayName;
    }

    public static void setGatewayName(String gatewayName)
    {
        gatewayName = gatewayName;
    }

    public static String getSubmask()
    {
        return submask;
    }

    public static void setSubmask(String submask)
    {
        submask = submask;
    }

    public static String getHostIP()
    {
        return hostIP;
    }

    public static void setHostIP(String hostIP)
    {
        hostIP = hostIP;
    }

    public static String getAddWanIndex()
    {
        return addWanIndex;
    }

    public static void setAddWanIndex(String addWanIndex)
    {
        if (Debug.isEnablelog()) {
            Debug.log("setAddWanIndex called, value = " + addWanIndex);
        }
        addWanIndex = addWanIndex;
    }

    public static BundleContext getContext()
    {
        return context;
    }

    public static void setContext(BundleContext context)
    {
        context = context;
    }

    public static String getWanIndex()
    {
        return wanIndex;
    }

    public static void setWanIndex(String wanIndex)
    {
        if (Debug.isEnablelog()) {
            Debug.log("setWanIndex called, value = " + wanIndex);
        }
        wanIndex = wanIndex;
    }

    public static String getWanName()
    {
        return wanName;
    }

    public static void setWanName(String wanName)
    {
        if (Debug.isEnablelog()) {
            Debug.log("setWanName called, value = " + wanName);
        }
        wanName = wanName;
    }

    public static String getLanDeviceMac()
    {
        return lanDeviceMac;
    }

    public static void setLanDeviceMac(String lanDeviceMac)
    {
        if (Debug.isEnablelog()) {
            Debug.log("setLanDeviceMac called, value = " + lanDeviceMac);
        }
        lanDeviceMac = lanDeviceMac;
    }

    public static String getLayer3ForwardIndex()
    {
        return layer3ForwardIndex;
    }

    public static void setLayer3ForwardIndex(String layer3ForwardIndex)
    {
        if (Debug.isEnablelog()) {
            Debug.log(
                    "setLayer3ForwardIndex called, value = " + layer3ForwardIndex);
        }
        layer3ForwardIndex = layer3ForwardIndex;
    }

    public static String getLayer6ForwardIndex()
    {
        return layer6ForwardIndex;
    }

    public static void setLayer6ForwardIndex(String layer6ForwardIndex)
    {
        if (Debug.isEnablelog()) {
            Debug.log(
                    "setLayer6ForwardIndex called, value = " + layer6ForwardIndex);
        }
        layer6ForwardIndex = layer6ForwardIndex;
    }

    public static String getForwardRuleIndex()
    {
        return forwardRuleIndex;
    }

    public static void setForwardRuleIndex(String forwardRuleIndex)
    {
        if (Debug.isEnablelog()) {
            Debug.log(
                    "setForwardRuleIndex called, value = " + forwardRuleIndex);
        }
        forwardRuleIndex = forwardRuleIndex;
    }

    public static String getMirrorRuleIndex()
    {
        return mirrorRuleIndex;
    }

    public static void setMirrorRuleIndex(String mirrorRuleIndex)
    {
        if (Debug.isEnablelog()) {
            Debug.log("setMirrorRuleIndex called, value = " + mirrorRuleIndex);
        }
        mirrorRuleIndex = mirrorRuleIndex;
    }

    public static String getGuestSSIDIndex()
    {
        return guestSSIDIndex;
    }

    public static void setGuestSSIDIndex(String guestSSIDIndex)
    {
        if (Debug.isEnablelog()) {
            Debug.log("setGuestSSIDIndex called, value = " + guestSSIDIndex);
        }
        guestSSIDIndex = guestSSIDIndex;
    }

    public static String getUsbDeviceId()
    {
        return usbDeviceId;
    }

    public static void setUsbDeviceId(String usbDeviceId)
    {
        if (Debug.isEnablelog()) {
            Debug.log("setUsbDeviceId called, value = " + usbDeviceId);
        }
        usbDeviceId = usbDeviceId;
    }

    public static String getUsbDeviceHandle()
    {
        if (Debug.isEnablelog()) {
            Debug.log("getUsbDeviceHandle called ,value = " + usbDeviceHandle);
        }
        return usbDeviceHandle;
    }

    public static void setUsbDeviceHandle(String usbDeviceHandle)
    {
        if (Debug.isEnablelog()) {
            Debug.log("setUsbDeviceHandle called, value = " + usbDeviceHandle);
        }
        usbDeviceHandle = usbDeviceHandle;
    }

    public static String getWlanSSIDIndex()
    {
        return wlanSSIDIndex;
    }

    public static void setWlanSSIDIndex(String wlanSSIDIndex)
    {
        wlanSSIDIndex = wlanSSIDIndex;
    }

    public static void initialGlobalData()
    {
        context = ApitestActivator.getContext();

        getWanIndexAndName();
        try
        {
            getLanDevMac();
        }
        catch (JSONException e)
        {
            if (Debug.isEnablelog()) {
                Debug.log("getLanDevMac exception:" + e.getMessage());
            }
        }
        iniGuestSSIDIndex();

        getLanHostIPAndSubmask();

        getPassword();

        getHTTPRightInfoPassword();

        getDevicePassWordInfo();
        getProvinceInfo();
    }

    private static void getProvinceInfo()
    {
        /*String registrationInfo = "";
        ServiceReference<?> sf = context.getServiceReference(RegistrationService.class.getName());
        if (sf != null)
        {
            RegistrationService service = (RegistrationService)context.getService(sf);
            if (service != null) {
                try
                {
                    registrationInfo = service.getPasswordRegistrationInfo();
                }
                catch (Throwable e)
                {
                    if (Debug.isEnablelog()) {
                        Debug.log(
                                "getPasswordRegistrationInfo:" + e.getMessage());
                    }
                    return;
                }
            }
            if ((registrationInfo != null) && (!registrationInfo.isEmpty())) {
                try
                {
                    JSONObject jsonObject = new JSONObject(registrationInfo);
                    int ret = jsonObject.getInt("Result");
                    if (ret == 0)
                    {
                        setProvince(jsonObject.getString("Province"));
                        setLimit(jsonObject.getString("Limit"));
                        setTimes(jsonObject.getString("Times"));
                    }
                }
                catch (Exception e)
                {
                    if (Debug.isEnablelog()) {
                        Debug.log(e.getMessage());
                    }
                }
            }
        }*/
    }

    private static void getDevicePassWordInfo()
    {
        /*String DevPassword = "";
        ServiceReference<?> sf = context.getServiceReference(DeviceSecretInfoQueryService.class.getName());
        if (sf != null)
        {
            DeviceSecretInfoQueryService service = (DeviceSecretInfoQueryService)context.getService(sf);
            if (service != null) {
                DevPassword = service.getDevicePassword();
            }
            if (DevPassword != null)
            {
                setDevicePassWord(DevPassword);
            }
            else
            {
                DevPassword = "12345678";
                setDevicePassWord(DevPassword);
            }
        }*/
    }

    private static void getHTTPRightInfoPassword()
    {
        /*String str = "";
        String str1 = "";
        String str2 = "";
        ServiceReference<?> sf = context.getServiceReference(DeviceAccessRightQueryService.class.getName());
        if (sf != null)
        {
            DeviceAccessRightQueryService service = (DeviceAccessRightQueryService)context.getService(sf);
            if (service != null)
            {
                str = service.getHTTPRightInfo();
                str1 = service.getFTPRightInfo();
                str2 = service.getSambaRightInfo();
                if (Debug.isEnablelog()) {
                    Debug.log("getHTTPRightInfo" + str);
                }
            }
        }
        if ((str != null) && (!str.isEmpty())) {
            try
            {
                JSONObject jsonObject = new JSONObject(str);
                int ret = jsonObject.getInt("Result");
                if (ret == 0)
                {
                    setHttpRight(jsonObject.getInt("Right"));
                    setHttpPassword(jsonObject.getString("Password"));
                }
            }
            catch (Exception e)
            {
                if (Debug.isEnablelog()) {
                    Debug.log(e.getMessage());
                }
            }
        }
        if ((str1 != null) && (!str1.isEmpty())) {
            try
            {
                JSONObject jsonObject = new JSONObject(str1);
                int ret = jsonObject.getInt("Result");
                if (ret == 0)
                {
                    setFtpRight(jsonObject.getInt("Right"));
                    setFtpAnonymous(jsonObject.getString("Anonymous"));
                }
            }
            catch (Exception e)
            {
                if (Debug.isEnablelog()) {
                    Debug.log(e.getMessage());
                }
            }
        }
        if ((str2 != null) && (!str2.isEmpty())) {
            try
            {
                JSONObject jsonObject = new JSONObject(str2);
                int ret = jsonObject.getInt("Result");
                if (ret == 0)
                {
                    setSambaRight(jsonObject.getInt("Right"));
                    setSambaAnonymous(jsonObject.getString("Anonymous"));
                }
            }
            catch (Exception e)
            {
                if (Debug.isEnablelog()) {
                    Debug.log(e.getMessage());
                }
            }
        }*/
    }

    private static void getPassword()
    {
        /*String password = "";
        ServiceReference<?> sf = context.getServiceReference(WlanSecretQueryService.class.getName());
        if (sf != null)
        {
            WlanSecretQueryService service = (WlanSecretQueryService)context.getService(sf);
            if (service != null)
            {
                password = service.getWLANSSIDPassword(1);
                if (Debug.isEnablelog()) {
                    Debug.log("getWLANSSIDPassword" + password);
                }
            }
        }
        setSsidPassword(password);*/
    }

    private static void getLanHostIPAndSubmask()
    {
        /*String hostIpInfo = "";
        String dhcpInfo = "";
        ServiceReference<?> sf = context.getServiceReference(LANIPInfoQueryService.class.getName());
        if (sf != null)
        {
            LANIPInfoQueryService service = (LANIPInfoQueryService)context.getService(sf);
            if (service != null)
            {
                hostIpInfo = service.getLANHostIP();
                dhcpInfo = service.getLANDHCPInfo();
                if (Debug.isEnablelog()) {
                    Debug.log("gethostIP" + hostIpInfo);
                }
            }
        }
        if ((hostIpInfo != null) && (!hostIpInfo.isEmpty())) {
            try
            {
                JSONObject jsonObject = new JSONObject(hostIpInfo);
                JSONObject ducpJsonObject = new JSONObject(dhcpInfo);

                int ret = jsonObject.getInt("Result");
                if (ret == 0)
                {
                    String localIpv4 = jsonObject.getString("LocalIPv4");
                    String localIpv6 = jsonObject.getString("LocalIPv6");
                    String submask = jsonObject.getString("Submask");
                    if ((!"".equals(localIpv4)) || (!"".equals(localIpv6)))
                    {
                        oldlocalIPv4 = localIpv4;
                        oldlocalIPv6 = localIpv6;
                        oldsmmark = submask;
                    }
                    if (!"".equals(ducpJsonObject.toString()))
                    {
                        oldDHCPStartIP = ducpJsonObject.getString("DHCPStartIP");
                        oldDHCPEndIP = ducpJsonObject.getString("DHCPEndIP");
                    }
                    setHostIP(localIpv4);
                    setSubmask(submask);
                }
            }
            catch (Exception e)
            {
                if (Debug.isEnablelog()) {
                    Debug.log(e.getMessage());
                }
            }
        }*/
    }

    private static void iniGuestSSIDIndex()
    {
        /*String ssidInfo = "";
        String wLANSSIDInfo = "";
        ServiceReference<?> sf = context.getServiceReference(WlanQueryService.class.getName());
        if (sf != null)
        {
            WlanQueryService service = (WlanQueryService)context.getService(sf);
            if (service != null)
            {
                ssidInfo = service.getGuestSSIDInfo();
                wLANSSIDInfo = service.getWLANSSIDInfo(ssidIndex);
                setFlag(service.getWLANHardwareSwitch("2.4G"));
                if (Debug.isEnablelog()) {
                    Debug.log("getGuestSSIDInfo" + ssidInfo);
                }
            }
        }
        if ((wLANSSIDInfo != null) && (!wLANSSIDInfo.isEmpty())) {
            try
            {
                JSONObject jsonObject = new JSONObject(wLANSSIDInfo);
                int ret = jsonObject.getInt("Result");
                if (ret == 0)
                {
                    String enable = jsonObject.getString("Enable");
                    String encryptionMode = jsonObject.getString("EncryptionMode");
                    String ssidName = jsonObject.getString("SSIDName");
                    String ssidAdvertisementEnabled = jsonObject.getString("SSIDAdvertisementEnabled");
                    String standard = jsonObject.getString("Standard");

                    setSsidEnable(enable);
                    setSsidEncryptionMode(encryptionMode);
                    setSsidName(ssidName);
                    setAdvertisementEnabled(ssidAdvertisementEnabled);
                    setStandard(standard);
                }
            }
            catch (Exception e)
            {
                if (Debug.isEnablelog()) {
                    Debug.log(e.getMessage());
                }
            }
        }
        if ((ssidInfo != null) && (!ssidInfo.isEmpty())) {
            try
            {
                JSONObject jsonObject = new JSONObject(ssidInfo);
                int ret = jsonObject.getInt("Result");
                if (ret == 0)
                {
                    JSONArray jsonArray = jsonObject.getJSONArray("List");
                    if (jsonArray.length() > 0)
                    {
                        JSONObject guest = (JSONObject)jsonArray.get(0);
                        String guestSSIDIndex = guest.getString("SSIDIndex");
                        setGuestSSIDIndex(guestSSIDIndex);
                    }
                }
            }
            catch (Exception e)
            {
                if (Debug.isEnablelog()) {
                    Debug.log(e.getMessage());
                }
            }
        }*/
    }

    private static void getLanDevMac()
            throws JSONException
    {
        /*String hosts = "";
        String gateName = "";
        ServiceReference<?> sf = context.getServiceReference(LANHostsInfoQueryService.class.getName());
        if (sf != null)
        {
            LANHostsInfoQueryService service = (LANHostsInfoQueryService)context.getService(sf);
            if (service != null)
            {
                hosts = service.getLANHostInfoByMAC(new String[0]);
                gateName = service.getGatewayName();
                setGatewayName(gateName);
                if (Debug.isEnablelog()) {
                    Debug.log("getLANHostInfoByMAC  = " + hosts);
                }
            }
        }
        if ((hosts != null) && (!hosts.isEmpty()))
        {
            JSONObject jsonObject = new JSONObject(hosts);
            int ret = jsonObject.getInt("Result");
            if (ret == 0) {
                try
                {
                    JSONArray jsonArray = jsonObject.getJSONArray("List");
                    if (jsonArray.length() > 0) {
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject lanDev = (JSONObject)jsonArray.get(i);
                            String ssid = lanDev.getString("ConnectInterface");
                            if ((ssid != null) && (ssid.startsWith("SSID")))
                            {
                                String mac = lanDev.getString("MAC");
                                if (Debug.isEnablelog()) {
                                    Debug.log("mac = " + mac);
                                }
                                setLanDeviceMac(mac);
                                lanDeviceName = lanDev.getString("DeviceName");
                                int index = ssid.indexOf("SSID");
                                ssidIndex = index +
                                        4 == ssid.length() -
                                        1 ? Integer.parseInt(ssid.substring(index +
                                                4,
                                        ssid.length())) : 1;
                                break;
                            }
                        }
                    }
                }
                catch (JSONException e)
                {
                    if (Debug.isEnablelog()) {
                        Debug.log(e.getMessage());
                    }
                }
            }
        }*/
    }

    private static void getWanIndexAndName()
    {
        /*String wanIfList = "";

        ServiceReference<?> sf = context.getServiceReference(AccessInfoQueryService.class.getName());
        if (sf != null)
        {
            AccessInfoQueryService service = (AccessInfoQueryService)context.getService(sf);
            if (service != null) {
                wanIfList = service.getWANIfList();
            }
        }
        if (!wanIfList.isEmpty()) {
            try
            {
                if (Debug.isEnablelog()) {
                    Debug.log("wanIfList = " + wanIfList);
                }
                JSONObject jsonObject = new JSONObject(wanIfList);
                int ret = jsonObject.getInt("Result");
                if (ret == 0)
                {
                    JSONArray jsonArray = jsonObject.getJSONArray("List");
                    if (jsonArray.length() > 0)
                    {
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject js = (JSONObject)jsonArray.get(i);
                            if ((js.getString("Name").contains("OTHER")) || (js.getString("Name").contains("IPTV")) || (js.getString("Name").contains("OTT")))
                            {
                                setWanIndex1(js.getInt("Index"));
                                break;
                            }
                        }
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject js = (JSONObject)jsonArray.get(i);
                            if (js.getString("Name").contains("TR069"))
                            {
                                int wanIndex = js.getInt("Index");
                                String wanName = js.getString("Name");

                                setWanIndex(wanIndex);
                                setWanName(wanName);

                                break;
                            }
                        }
                        for (int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject js = (JSONObject)jsonArray.get(i);
                            if (js.getString("Name").contains("INTERNET"))
                            {
                                String wanName = js.getString("Name");

                                setWanName1(wanName);
                                break;
                            }
                        }
                        if (Debug.isEnablelog()) {
                            Debug.log(Debug.buildLogMessage(new String[] { "wanidnex = ",
                                    wanIndex,

                                    ", wanname = ",
                                    wanName }));
                        }
                    }
                }
                else if (Debug.isEnablelog())
                {
                    Debug.log("get wanIfList failed !");
                }
            }
            catch (JSONException e)
            {
                if (Debug.isEnablelog()) {
                    Debug.log(e.getMessage());
                }
            }
        }*/
    }

    public String SetMsgProcess(String msgContent)
    {
        if (msgContent.indexOf("USB_DEV_ACTION") == -1) {
            return "not usb event";
        }
        if (Debug.isEnablelog()) {
            Debug.log("Global Data recieve Event :" + msgContent);
        }
        try
        {
            JSONObject obj = new JSONObject(msgContent);
            int devId = obj.getInt("DevId");

            String devType = obj.getString("DevType");
            String actionType = obj.getString("ActionType");
            if ("USB_DEV_PULL".equals(actionType))
            {
                devId = 0;
                if (getUsb_serial_devid() == getUsb_storage_devid())
                {
                    setUsb_serial_devid(0);
                    setUsb_storage_devid(0);
                }
            }
            if (("USB_SERIAL".equals(devType)) ||
                    ("USB_CDC_ACM".equals(devType)) ||
                    ("USB_STORAGE".equals(devType)))
            {
                if (getUsb_serial_devid() == 0) {
                    setUsb_serial_devid(devId);
                }
                if (getUsb_storage_devid() == 0) {
                    setUsb_storage_devid(devId);
                }
            }
            if ("USB_STORAGE".equals(devType)) {
                setUsb_storage_devid(devId);
            }
            if (("USB_SERIAL".equals(devType)) || ("USB_CDC_ACM".equals(devType))) {
                setUsb_serial_devid(devId);
            }
        }
        catch (Exception e)
        {
            if (Debug.isEnablelog()) {
                Debug.log(e.getMessage());
            }
            return "";
        }
        return "";
    }

    public static String getUsb_serial_handle()
    {
        return usb_serial_handle;
    }

    public static void setUsb_serial_handle(String usb_serial_handle)
    {
        usb_serial_handle = usb_serial_handle;
    }

    public static String getDevicePassWord()
    {
        return DevicePassWord;
    }

    public static void setDevicePassWord(String devicePassWord)
    {
        DevicePassWord = devicePassWord;
    }

    public static String getProvince()
    {
        return Province;
    }

    public static void setProvince(String province)
    {
        Province = province;
    }

    public static String getTimes()
    {
        return times;
    }

    public static void setTimes(String times)
    {
        times = times;
    }

    public static String getLimit()
    {
        return limit;
    }

    public static void setLimit(String limit)
    {
        limit = limit;
    }
}
