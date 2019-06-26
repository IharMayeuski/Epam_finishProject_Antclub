package by.epam.club.tools.need_to_delete_other_view.for_using;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;

public class RequestContent_myne {

    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;

    public RequestContent_myne() {
        requestParameters = new HashMap<>();
        requestAttributes = new HashMap<>();
        sessionAttributes = new HashMap<>();
    }

    public void extractValues(HttpServletRequest request) {
        Enumeration<String> attrNames = request.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String name = attrNames.nextElement();
            Object attr = request.getAttribute(name);
            requestAttributes.put(name, attr);
        }

        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String name = paramNames.nextElement();
            String param[] = request.getParameterValues(name);
            requestParameters.put(name, param);
        }

        Enumeration<String> sessionAttrNames = request.getSession().getAttributeNames();
        while (sessionAttrNames.hasMoreElements()) {
            String name = sessionAttrNames.nextElement();
            Object sessionAttr = request.getSession().getAttribute(name);
            sessionAttributes.put(name, sessionAttr);
        }
    }

    public void insertValues(HttpServletRequest request) {// todo метод под вопросом
        sessionAttributes.forEach((k, v) -> request.setAttribute(k, v));
        requestAttributes.forEach((k, v) -> request.setAttribute(k, v));
        requestParameters.forEach((k,v) ->request.setAttribute(k,v));

        if (request.getSession(false) != null) {
            sessionAttributes.forEach((k, v) -> request.getSession().setAttribute(k, v));
            requestAttributes.forEach((k, v) -> request.getSession().setAttribute(k, v));
            requestParameters.forEach((k,v) ->request.getSession().setAttribute(k,v));
        }

    }

    public String getRequestParameters(String key, int parameterValueIndex) {
        String[] parameterValues = requestParameters.get(key);
        if (parameterValues == null || parameterValues.length == 0) {
            return null;
        } else {
            return parameterValues[parameterValueIndex];
        }
    }

    public Object getRequestAttribute(String attributeName) {
        Object result = null;
        if (attributeName != null && requestAttributes.containsKey(attributeName)) {
            result = requestAttributes.get(attributeName);
        }
        return result;
    }

    public Object getSessionAttributes(String sessionAttributesName) {
        Object result = null;
        if (sessionAttributesName != null && sessionAttributes.containsKey(sessionAttributesName)) {
            result = sessionAttributes.get(sessionAttributesName);
        }
        return result;
    }

    public boolean setRequestAttribute(String contentName, Object object) {
        boolean result = false;
        if (contentName != null && !contentName.isEmpty() && object != null) {
            result = (this.requestAttributes.put(contentName, object) != null);
        }
        return result;
    }

    public boolean setSessionAttribute(String contentName, Object object) {
        boolean result = false;
        if (contentName != null && !contentName.isEmpty() && object != null) {
            result = (this.sessionAttributes.put(contentName, object) != null);
        }
        return result;
    }

    public boolean setRequestParameters(String contentName, String[] object) {
        boolean result = false;

        if (contentName != null && !contentName.isEmpty() && object != null) {
            result = (this.requestParameters.put(contentName, object) != null);
        }
        return result;
    }
}
