package by.epam.club.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
/**
 * Class for creating site's content with request parameters, request attributes, session attributes
 *
 * @author Maeuski Igor
 * @version 1.0
 */
public class RequestContent {
    private HttpSession session;
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    public RequestContent() {
        requestParameters = new HashMap<>();
        requestAttributes = new HashMap<>();
        sessionAttributes = new HashMap<>();
    }
    /**
     * @param request send request with parameters for creating Enumeration collections of content
     */
    void extractValues(HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            String[] param = request.getParameterValues(name);
            requestParameters.put(name, param);
        }
       Enumeration<String> attrNames = request.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String name = attrNames.nextElement();
            Object attr = request.getAttribute(name);
            requestAttributes.put(name, attr);
        }
        Enumeration<String> sessionAttrNames = request.getSession().getAttributeNames();
        while (sessionAttrNames.hasMoreElements()) {
            String names = sessionAttrNames.nextElement();
            Object sessionAttr = request.getSession().getAttribute(names);
            sessionAttributes.put(names, sessionAttr);
        }
        session = request.getSession(false);
    }
    /**
     * @param request for attributes of session and request
     */
    void insertAttributes(HttpServletRequest request) {
        for (Map.Entry<String, Object> entry : requestAttributes.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            request.setAttribute(key, value);
        }
        if (request.getSession(false) != null) {
            for (Map.Entry<String, Object> entry : sessionAttributes.entrySet()) {
                String k = entry.getKey();
                Object v = entry.getValue();
                request.getSession().setAttribute(k, v);
            }
        }
    }

    /**
     * @param name the name of attribute
     * @param attr the real attribute
     */
    public void putRequestAttribute(String name, Object attr) {
        requestAttributes.put(name, attr);
    }
    /**
     * @param name the name of attribute
     * @param attr the real attribute
     */
    public void putSessionAttribute(String name, Object attr) {
        sessionAttributes.put(name, attr);
    }

    /**
     * @param name the name of attribute
     * @param index the index of attribute
     * @return parameter by name
     */
    public String getRequestParameters(String name, int index) {
        return requestParameters.get(name) == null ? null : requestParameters.get(name)[index];
    }

    /**
     * @param name of attribute for checking
     * @return attribute by name
     */
    public Object getSessionAttribute(String name) {
        return sessionAttributes.get(name);
    }

    public void invalidateSession() {
        if (session != null) {
            session.invalidate();
        }
    }

    public ServletContext getContext() {
        return session.getServletContext();
    }
}