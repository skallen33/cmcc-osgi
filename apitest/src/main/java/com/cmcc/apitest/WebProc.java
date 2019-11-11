package com.cmcc.apitest;

import com.chinamobile.smartgateway.apitest.http.TestItems;
import com.cmcc.apitest.util.Debug;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;

public class WebProc extends HttpServlet
{
    /**
     * http://localhost:8181/apitest/cmcc.cmd?jsonCfg=skallen
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        /*String jsonCtx = request.getParameter("jsonCfg");

        response.setContentType("text/html;charset=UTF-8");
        OutputStream ps = response.getOutputStream();

        String returnStr = "thanks "+jsonCtx;

        ps.write(returnStr.getBytes("utf-8"));*/

        String jsonCtx = request.getParameter("jsonCfg");

        jsonCtx = URLDecoder.decode(jsonCtx, "UTF-8");
        if (Debug.isEnablelog()) {
            Debug.log("JsonCtx:" + jsonCtx);
        }
        response.setContentType("text/html;charset=UTF-8");
        OutputStream ps = response.getOutputStream();

        JSONObject jsonObj = null;
        String returnStr = "";
        try
        {
            jsonObj = new JSONObject(jsonCtx);
        }
        catch (JSONException e)
        {
            if (Debug.isEnablelog()) {
                Debug.log("JSONObject:" + e.toString());
            }
            ps.write(e.toString().getBytes("utf-8"));
            return;
        }
        if (!jsonObj.isNull("CmdType"))
        {
            JSONObject jsonRet = new JSONObject();
            String cmdType = "";
            try
            {
                cmdType = jsonObj.getString("CmdType");
                if ("GET_ALL_TEST_ITEMS".equals(cmdType))
                {
                    JSONArray jsonArray = TestItems.getAllTestTerm();
                    jsonRet.put("TestItems", jsonArray);
                }
                else if ("EXECUTE_TEST".equals(cmdType))
                {
                    /*respMsgQueue.clear();
                    setM_currentAckID(0);
                    setM_RetItemNum(0);
                    JSONObject testItem = jsonObj.getJSONObject("TestItem");
                    String testCase = testItem.getString("Name");
                    if (("StopApiThroughPut Test".equals(testCase)) ||
                            ("StopUsbTask Test".equals(testCase)))
                    {
                        APIThroughPutTestTask.setStop(true);
                        UsbServiceTask.setUsbTaskRun(false);
                        ps.write(returnStr.getBytes("utf-8"));
                        if (Debug.isEnablelog()) {
                            Debug.log("StopApiThroughPut Test and stopUsbTask Test");
                        }
                        return;
                    }
                    APIThroughPutTestTask.setStop(true);
                    UsbServiceTask.setUsbTaskRun(false);
                    this.et.killCurrentTestTask();
                    this.et.postTestItemObject(jsonObj);*/
                }
                else if ("QUERY_TEST_RESULT".equals(cmdType))
                {
                    /*String testTerm = jsonObj.getString("ItemName");
                    String methodName = jsonObj.getString("SubItemName");

                    int i = 0;
                    if (jsonObj.has("QueryId")) {
                        i = jsonObj.getInt("QueryId");
                    }
                    if (Debug.isEnablelog()) {
                        Debug.log("doGet subName:" + testTerm + "--" + methodName);
                    }
                    jsonRet = timerSearchCommon(i, testTerm);*/
                }
                jsonRet.put("CmdType", cmdType);
                jsonRet.put("SequenceId", "0x00000001");
                jsonRet.put("Status", "0");
                returnStr = jsonRet.toString();
            }
            catch (JSONException e)
            {
                Debug.log(e.getMessage() + "---------");
            }
        }
        if (Debug.isEnablelog()) {
            Debug.log("returnStr = [" + returnStr + "]");
        }
        ps.write(returnStr.getBytes("utf-8"));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        doPost(request, response);
    }
}
