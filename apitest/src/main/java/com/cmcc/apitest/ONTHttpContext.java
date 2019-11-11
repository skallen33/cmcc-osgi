package com.cmcc.apitest;

import org.osgi.framework.Bundle;
import org.osgi.service.http.HttpContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URL;

public class ONTHttpContext implements HttpContext {
    private Bundle m_bundle;

    public ONTHttpContext(Bundle bundle) {
        this.m_bundle = bundle;
    }

    public String getMimeType(String name) {
        return null;
    }

    public URL getResource(String name) {
        String resource = name;
        if ((name.equals("/webs")) || (name.equals("/webs/index.html"))) {
            resource = "webs/index.htm";
        }
        if (resource.startsWith("/")) {
            resource = name.substring(1);
        }
        URL url = this.m_bundle.getResource(resource);
        if (url == null) {
            url = getClass().getResource(resource);
        }
        return url;
    }

    public boolean handleSecurity(HttpServletRequest request, HttpServletResponse response) {
        return true;
    }
}
