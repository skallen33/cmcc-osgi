package com.chinamobile.smartgateway.apitest.http;

import com.chinamobile.smartgateway.apitest.http.WebProc;
import com.chinamobile.smartgateway.apitest.http.task.EventNoticeTask;
import com.chinamobile.smartgateway.apitest.http.task.EventNoticeTaskImp;
import com.chinamobile.smartgateway.apitest.http.task.TestTask;
import com.chinamobile.smartgateway.apitest.http.task.TestTaskFactory;
import com.chinamobile.smartgateway.apitest.util.Debug;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.framework.BundleContext;

public class ExecuteThread
        extends Thread
{
    private static BundleContext _context;
    private JSONObject jsonObj;
    public int bigTeamResult = TestTask.TestStatus.NOTRUN.getValue();
    public volatile boolean bThreadAlive = true;
    public volatile boolean bCurrentTaskRunning = false;
    private static EventNoticeTask task;

    public static BundleContext get_context()
    {
        return _context;
    }

    public static void set_context(BundleContext context)
    {
        _context = context;
    }

    public JSONObject getJsonObj()
    {
        return this.jsonObj;
    }

    public void setJsonObj(JSONObject jsonObj)
    {
        this.jsonObj = jsonObj;
    }

    public int getBigTeamResult()
    {
        return this.bigTeamResult;
    }

    public void setBigTeamResult(int bigTeamResult)
    {
        this.bigTeamResult = bigTeamResult;
    }

    public boolean isbThreadAlive()
    {
        return this.bThreadAlive;
    }

    public void setbThreadAlive(boolean bThreadAlive)
    {
        this.bThreadAlive = bThreadAlive;
    }

    public static void setTask(EventNoticeTask task)
    {
        task = task;
    }

    public boolean isbCurrentTaskRunning()
    {
        return this.bCurrentTaskRunning;
    }

    public void setbCurrentTaskRunning(boolean bCurrentTaskRunning)
    {
        if (Debug.isEnablelog()) {
            Debug.log("bCurrentTaskRunning set to " + bCurrentTaskRunning);
        }
        this.bCurrentTaskRunning = bCurrentTaskRunning;
    }

    public ExecuteThread(BundleContext context)
    {
        set_context(context);
        setTask(new EventNoticeTaskImp());
    }

    public static EventNoticeTask getTask()
    {
        return task;
    }

    public void killCurrentTestTask()
            throws JSONException
    {
        synchronized (this)
        {
            if (isbCurrentTaskRunning())
            {
                if (Debug.isEnablelog()) {
                    Debug.log("KillCurrentTestTask:" + this.jsonObj.getJSONObject("TestItem").getString("Name"));
                }
                setbCurrentTaskRunning(false);
                notify();
            }
        }
    }

    public void postTestItemObject(JSONObject jsonObj)
    {
        synchronized (this)
        {
            while (isbCurrentTaskRunning()) {
                try
                {
                    wait();
                }
                catch (InterruptedException localInterruptedException) {}
            }
            this.jsonObj = jsonObj;
            setbCurrentTaskRunning(true);
            notifyAll();
        }
    }

    private String executeTestTerm(JSONObject jsonObj)
            throws JSONException
    {
        JSONObject testItem = jsonObj.getJSONObject("TestItem");
        String testCase = testItem.getString("Name");

        this.bigTeamResult = TestTask.TestStatus.NOTRUN.getValue();
        if (Debug.isEnablelog()) {
            Debug.log("executeTestTerm:" + testCase);
        }
        TestTask.TestStatus status = TestTaskFactory.buildTask(testCase).run(_context, testItem);

        this.bigTeamResult = status.getValue();

        return "";
    }

    public void run()
    {
        while (this.bThreadAlive) {
            try
            {
                synchronized (this)
                {
                    while (!isbCurrentTaskRunning()) {
                        wait();
                    }
                    try
                    {
                        executeTestTerm(this.jsonObj);
                    }
                    catch (JSONException e)
                    {
                        if (Debug.isEnablelog()) {
                            Debug.log(e);
                        }
                        this.bigTeamResult = TestTask.TestStatus.FAIL.getValue();
                        JSONObject testItem = this.jsonObj.getJSONObject("TestItem");
                        String testCase = testItem.getString("Name");
                        JSONObject f_respJson = new JSONObject();
                        f_respJson.put("Name", testCase);
                        f_respJson.put("ActualOutputParam", "Please refresh test page");
                        f_respJson.put("Result", this.bigTeamResult);
                        f_respJson.put("FailReason", "The Format of Input Parameter is error");
                        WebProc.pushResult(f_respJson.toString());
                    }
                    setbCurrentTaskRunning(false);
                    notifyAll();
                }
            }
            catch (Throwable e)
            {
                Debug.log(e);
            }
        }
    }
}
