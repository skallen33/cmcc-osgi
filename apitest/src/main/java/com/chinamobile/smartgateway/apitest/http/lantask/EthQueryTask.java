package com.chinamobile.smartgateway.apitest.http.lantask;

import com.chinamobile.smartgateway.apitest.http.WebProc;
import com.chinamobile.smartgateway.apitest.http.task.TestTask;
//import com.chinamobile.smartgateway.apitest.http.task.lantask.BaseTestTask;
import com.chinamobile.smartgateway.apitest.util.Debug;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.framework.BundleContext;
//import org.osgi.util.tracker.ServiceTracker;

public class EthQueryTask
        extends BaseTestTask
        implements TestTask
{
    public TestTask.TestStatus run(BundleContext context, JSONObject testItem)
            throws JSONException
    {
        Debug.log("EthQueryTask start run");
//        Debug.log("context:"+context);
//        ServiceTracker serviceTracker = new ServiceTracker(context, EthQueryService.class.getName(), null);
//        Debug.log("serviceTracker:"+serviceTracker);
//        serviceTracker.open();

        JSONObject json = null;
        String subName = null;
        Debug.log("testItem:"+testItem.toString());
        JSONArray subItemArray = testItem.getJSONArray("SubItems");
        Debug.log("subItemArray:"+subItemArray.toString());
        TestTask.TestStatus status = TestTask.TestStatus.SUCC;
        String result = "";
        for (int i = 0; i < subItemArray.length(); i++)
        {
            json = subItemArray.getJSONObject(i);
            Debug.log("json:"+json.toString());
            JSONObject f_respJson = new JSONObject();
            subName = json.getString("Name");
            f_respJson.put("Name", subName);
            if (Debug.isEnablelog()) {
                Debug.log("EthQueryTask Test:"+json.toString());
            }
            if (subName.equals("EthQueryTask Test"))
            {
                /*EthQueryService service = (EthQueryService)serviceTracker.getService();
                JSONObject subInput = json.getJSONObject("InputParam");
                if (service != null)
                {
                    String portIndex = subInput.getString("portIndex");
                    String step = subInput.getString("step");
                    StringBuffer sBuffer = new StringBuffer();
                    portIndex = "".equals(portIndex) ? "1" : portIndex;
                    int port = 1;
                    try
                    {
                        port = Integer.parseInt(portIndex);
                    }
                    catch (Exception localException)
                    {
                        if ("step1".equals(step)) {
                            port = 1;
                        } else {
                            port = 2;
                        }
                    }
                    if ("step1".equals(step))
                    {
                        result = service.getLANEthernetInfo(port);
                        JSONObject jsonObject = new JSONObject(result);
                        sBuffer.append("Eternet information LAN");
                        sBuffer.append(port).append(":<br/>");

                        int jsonResult = jsonObject.getInt("Result");
                        if (jsonResult == 0)
                        {
                            sBuffer.append("Enable:").append(jsonObject.getString("Enable")).append("<br/>");
                            sBuffer.append("Status:").append(jsonObject.getString("Status")).append("<br/>");
                            sBuffer.append("BitRate:").append(jsonObject.getString("BitRate")).append("<br/>");
                            sBuffer.append("DuplexMode:").append(jsonObject.getString("DuplexMode")).append("<br/>");
                        }
                        else
                        {
                            sBuffer.append("get LANEthernet information fail");
                        }
                    }
                    else if ("step2".equals(step))
                    {
                        result = service.getLANEthernetStats(port);
                        JSONObject jsonObject = new JSONObject(result);
                        sBuffer.append("Eternet stats LAN");
                        sBuffer.append(port).append(":<br/>");

                        int jsonResult = jsonObject.getInt("Result");
                        if (jsonResult == 0)
                        {
                            sBuffer.append("BytesSent:").append(jsonObject.getInt("BytesSent")).append("<br/>");
                            sBuffer.append("BytesReceived:").append(jsonObject.getInt("BytesReceived")).append("<br/>");
                            sBuffer.append("PacketsSent:").append(jsonObject.getInt("PacketsSent")).append("<br/>");
                            sBuffer.append("PacketsReceived:").append(jsonObject.getInt("PacketsReceived")).append("<br/>");
                        }
                        else
                        {
                            sBuffer.append("get LANEthernet stats fail");
                        }
                    }
                    else
                    {
                        status = TestTask.TestStatus.FAIL;
                        sBuffer.append("input step error!");
                    }
                    f_respJson.put("FailReason", "");
                    f_respJson.put("ActualOutputParam", sBuffer.toString());
                    f_respJson.put("Result", status.getValue());
                }
                else
                {
                    status = TestTask.TestStatus.FAIL;

                    f_respJson.put("FailReason", "get EthQueryService Failed");
                    f_respJson.put("ActualOutputParam", "get EthQueryService Failed");
                    f_respJson.put("Result", status.getValue());
                }*/
                StringBuffer sBuffer = new StringBuffer();
                sBuffer.append("Enable:").append("Enable11").append("<br/>");
                sBuffer.append("Status:").append("Status22").append("<br/>");
                sBuffer.append("BitRate:").append("BitRate33").append("<br/>");
                sBuffer.append("DuplexMode:").append("DuplexMode44").append("<br/>");
                f_respJson.put("FailReason", "");
                f_respJson.put("ActualOutputParam", sBuffer.toString());
                f_respJson.put("Result", status.getValue());
            }
            else
            {
                f_respJson.put("Result", TestTask.TestStatus.SUCC.getValue());
                f_respJson.put("FailReason", result);
                f_respJson.put("ActualOutputParam", "Finished");
            }
            WebProc.pushResult(f_respJson.toString());
        }
//        serviceTracker.close();
        Debug.log("EthQueryTask finish run");
        return TestTask.TestStatus.SUCC;
    }
}
