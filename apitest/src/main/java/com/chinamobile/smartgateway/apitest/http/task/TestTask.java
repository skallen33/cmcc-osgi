package com.chinamobile.smartgateway.apitest.http.task;

import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.framework.BundleContext;

public abstract interface TestTask
{
    public abstract TestStatus run(BundleContext paramBundleContext, JSONObject paramJSONObject)
            throws JSONException;

    public static enum TestStatus
    {
        SUCC(1),  FAIL(-1),  NOTRUN(0);

        private int value;

        private TestStatus(int v)
        {
            this.value = v;
        }

        public int getValue()
        {
            return this.value;
        }
    }
}
