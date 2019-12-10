package com.chinamobile.smartgateway.apitest.http;

import com.chinamobile.smartgateway.apitest.http.TestItems;
import com.chinamobile.smartgateway.apitest.http.ExecuteThread;
import com.chinamobile.smartgateway.apitest.util.Debug;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.framework.BundleContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class WebProc extends HttpServlet
{
    private static Queue<String> respMsgQueue = new ConcurrentLinkedQueue();
    private ExecuteThread et = null;
    private static int m_currentAckID;
    private static int m_RetItemNum;

    public WebProc(BundleContext bundleContext)
    {
        this.et = new ExecuteThread(bundleContext);
        this.et.setDaemon(true);
        this.et.start();
    }
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
                Debug.log(e);
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
                    respMsgQueue.clear();
                    setM_currentAckID(0);
                    setM_RetItemNum(0);
                    JSONObject testItem = jsonObj.getJSONObject("TestItem");
                    String testCase = testItem.getString("Name");
//                    if (("StopApiThroughPut Test".equals(testCase)) ||
//                            ("StopUsbTask Test".equals(testCase)))
//                    {
//                        APIThroughPutTestTask.setStop(true);
//                        UsbServiceTask.setUsbTaskRun(false);
//                        ps.write(returnStr.getBytes("utf-8"));
//                        if (Debug.isEnablelog()) {
//                            Debug.log("StopApiThroughPut Test and stopUsbTask Test");
//                        }
//                        return;
//                    }
//                    APIThroughPutTestTask.setStop(true);
//                    UsbServiceTask.setUsbTaskRun(false);
                    this.et.killCurrentTestTask();
                    this.et.postTestItemObject(jsonObj);
                }
                else if ("QUERY_TEST_RESULT".equals(cmdType))
                {
                    String testTerm = jsonObj.getString("ItemName");
                    String methodName = jsonObj.getString("SubItemName");

                    int i = 0;
                    if (jsonObj.has("QueryId")) {
                        i = jsonObj.getInt("QueryId");
                    }
                    if (Debug.isEnablelog()) {
                        Debug.log("doGet subName:" + testTerm + "--" + methodName);
                    }
                    jsonRet = timerSearchCommon(i, testTerm);
                }
                jsonRet.put("CmdType", cmdType);
                jsonRet.put("SequenceId", "0x00000001");
                jsonRet.put("Status", "0");
                returnStr = jsonRet.toString();
            }
            catch (JSONException e)
            {
                Debug.log(e);
            }
        }
        if (Debug.isEnablelog()) {
            Debug.log("returnStr = [" + returnStr + "]");
        }
        ps.write(returnStr.getBytes("utf-8"));
    }

    private JSONObject timerSearchCommon(int ackID, String itemName)
            throws JSONException
    {
        JSONObject respJson = getRespJsonObj(ackID, itemName);
        JSONArray subItemResults = getAllRespResultJsons();
        JSONObject testResult = (JSONObject)respJson.get("TestResult");
        testResult.put("SubItems", subItemResults);

        return respJson;
    }

    private JSONObject getRespJsonObj(int ackID, String itemName)
    {
        JSONObject respJson = new JSONObject();
        if (ackID == m_currentAckID)
        {
            while (m_RetItemNum > 0)
            {
                if (respMsgQueue.size() > 0) {
                    respMsgQueue.poll();
                }
                m_RetItemNum -= 1;
            }
            if (respMsgQueue.size() > 0) {
                m_currentAckID += 1;
            }
        }
        try
        {
            respJson.put("SequenceId", 1);
            JSONObject testResultJson = new JSONObject();
            testResultJson.put("SyncFlag", "0");
            testResultJson.put("RestartTest", "0");
            testResultJson.put("CurrItemName", itemName);
            testResultJson.put("CurrSubItemName", itemName);
            testResultJson.put("QueryId", m_currentAckID);
            testResultJson.put("NextQueryTime", 5000);
            if (this.et != null) {
                testResultJson.put("Result", this.et.bigTeamResult);
            } else {
                testResultJson.put("Result", 0);
            }
            respJson.put("TestResult", testResultJson);
        }
        catch (JSONException e)
        {
            if (Debug.isEnablelog()) {
                Debug.log(e);
            }
        }
        return respJson;
    }

    private static synchronized JSONArray getAllRespResultJsons()
    {
        JSONArray subItemResuts = new JSONArray();
        if (respMsgQueue != null)
        {
            m_RetItemNum = respMsgQueue.size();
            Iterator<String> pItor = respMsgQueue.iterator();
            while (pItor.hasNext())
            {
                String resultStr = (String)pItor.next();
                if (resultStr != null) {
                    try
                    {
                        JSONObject result = new JSONObject(resultStr);
                        subItemResuts.put(result);
                    }
                    catch (JSONException e)
                    {
                        if (Debug.isEnablelog()) {
                            Debug.log(e);
                        }
                    }
                }
            }
        }
        return subItemResuts;
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        doPost(request, response);
    }

    public static synchronized void pushResult(String strResult)
    {
        if (respMsgQueue != null) {
            respMsgQueue.add(strResult);
        }
    }
    public static int getM_currentAckID()
    {
        return m_currentAckID;
    }

    public static void setM_currentAckID(int m_currentAckID)
    {
        m_currentAckID = m_currentAckID;
    }

    public static int getM_RetItemNum()
    {
        return m_RetItemNum;
    }

    public static void setM_RetItemNum(int m_RetItemNum)
    {
        m_RetItemNum = m_RetItemNum;
    }

}
