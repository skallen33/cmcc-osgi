package com.cmcc.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

public class LoginServlet extends HttpServlet
{
    /**
     * http://localhost:8080/login?jsonCfg=chenlong
     * @param request
     * @param response
     * @throws IOException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        String jsonCtx = request.getParameter("jsonCfg");

        response.setContentType("text/html;charset=UTF-8");
        OutputStream ps = response.getOutputStream();

        String returnStr = "thanks "+jsonCtx;

        ps.write(returnStr.getBytes("utf-8"));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException
    {
        doPost(request, response);
    }
}
