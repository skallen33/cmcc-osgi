package com.chinamobile.smartgateway.apitest.http.lantask;

import com.chinamobile.smartgateway.apitest.http.task.TestTask;
import org.json.JSONException;
import org.json.JSONObject;

public class BaseTestTask
{
    protected JSONObject buildReturnJson(JSONObject jsonObject, TestTask.TestStatus status, String reason)
            throws JSONException
    {
        jsonObject.put("FailReason", "");
        jsonObject.put("ActualOutputParam", reason);
        jsonObject.put("Result", status.getValue());
        return jsonObject;
    }
}
