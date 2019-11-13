package com.chinamobile.smartgateway.apitest.http.task;

public abstract interface EventNoticeTask
{
    public static final String TIME_OUT = "Time out";

    public abstract String filter(EventType paramEventType);

    public abstract void setEventMsg(String paramString);

    public static enum EventType
    {
        USB,  VOIP,  LANHost,  Traffic,  NOTIFY_HTTP_TRAFFIC_PROCESS;
    }
}
