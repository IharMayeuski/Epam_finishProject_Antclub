package by.epam.club.tool.need_to_delete_other_view;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;

public class RequestContent {
 //   private HttpSession session;
    private HashMap<String, Object> requestAttributes;
    private HashMap<String, String[]> requestParameters;
    private HashMap<String, Object> sessionAttributes;

  /*  public HttpSession getSession() {
        return session;
    }*/

    public HashMap<String, Object> getRequestAttributes() {
        return requestAttributes;
    }

    public HashMap<String, String[]> getRequestParameters() {
        return requestParameters;
    }

    public HashMap<String, Object> getSessionAttributes() {
        return sessionAttributes;
    }

    public RequestContent() {
        requestParameters = new HashMap<>();
        requestAttributes = new HashMap<>();
        sessionAttributes = new HashMap<>();
    }

    public void extractValues(HttpServletRequest request) {//throws SharingPicCommandException {
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
  //      session = request.getSession(false);
    }

    public void insertAttributes(HttpServletRequest request) {
        sessionAttributes.forEach((k, v) -> request.setAttribute(k, v));
        if (request.getSession(false) != null) {
            sessionAttributes.forEach((k, v) -> request.getSession().setAttribute(k, v));
        }
    }
}
