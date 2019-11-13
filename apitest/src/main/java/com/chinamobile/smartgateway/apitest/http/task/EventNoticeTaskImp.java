package com.chinamobile.smartgateway.apitest.http.task;

public class EventNoticeTaskImp
        implements EventNoticeTask
{
    private String msgConent = "";
    private String usbContent = "";
    private volatile boolean bhasMsg = false;

    public String filter(EventNoticeTask.EventType type)
    {
        String ret = "";
        long timeout = 0L;
        this.msgConent = "";
        synchronized (this)
        {
            timeout = 60000L;
            if (type == EventNoticeTask.EventType.Traffic) {
                timeout = 150000L;
            }
            if (!this.bhasMsg) {
//                if (!UsbServiceTask.isUsbTaskRun()) {
//                    try
//                    {
//                        wait(timeout);
//                    }
//                    catch (InterruptedException localInterruptedException1) {}
//                } else {
                    try
                    {
                        wait(30000L);
                    }
                    catch (InterruptedException localInterruptedException2) {}
//                }
            }
            if (type == EventNoticeTask.EventType.USB)
            {
                if (this.msgConent.indexOf("USB_DEV_ACTION") > -1) {
                    ret = this.msgConent;
                }
            }
            else if (type == EventNoticeTask.EventType.VOIP)
            {
                if (this.msgConent.indexOf("NOTIFY_VOICE_CALL_INFO") > -1) {
                    ret = this.msgConent;
                }
            }
            else if (type == EventNoticeTask.EventType.LANHost)
            {
                if ((this.msgConent.indexOf("LAN_DEV") > -1) || (this.msgConent.indexOf("WLAN_DEV") > -1)) {
                    ret = this.msgConent;
                }
            }
            else if (type == EventNoticeTask.EventType.Traffic)
            {
                if (this.msgConent.indexOf("NOTIFY_DEST_ADDRESS") > -1) {
                    ret = this.msgConent;
                }
            }
            else if (type == EventNoticeTask.EventType.NOTIFY_HTTP_TRAFFIC_PROCESS) {
                if (this.msgConent.indexOf("NOTIFY_HTTP_TRAFFIC_PROCESS") > -1) {
                    ret = this.msgConent;
                }
            }
            if ("".equals(ret)) {
                ret = "Time out";
            }
            this.bhasMsg = false;

            this.msgConent = "";

            notifyAll();
//            if (UsbServiceTask.isUsbTaskRun()) {
//                return this.usbContent;
//            }
            return ret;
        }
    }

    public void setEventMsg(String msg)
    {
        synchronized (this)
        {
            while (this.bhasMsg) {
                try
                {
                    wait();
                }
                catch (InterruptedException localInterruptedException) {}
            }
            this.msgConent += msg;
            this.usbContent = msg;
        }
    }
}
