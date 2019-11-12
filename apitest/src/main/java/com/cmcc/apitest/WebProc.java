package com.cmcc.apitest;

import com.chinamobile.smartgateway.apitest.http.TestItems;
import com.cmcc.apitest.util.Debug;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

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

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        doPost(request, response);
    }
}
