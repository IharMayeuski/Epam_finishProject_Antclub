package by.epam.club.tool.need_to_delete_other_view;/*
package by.epam.club.tool.need_to_delete_other_view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class RequestContent2 {

    private final static Logger logger = LogManager.getLogger();

    private Map<String, String[]> requestParameters;
    private Map<String, Object> requestAttributes;
    private Map<String, String> requestHeaders;
    private Map<String, Object> sessionAttributes;
    private Collection<Part> requestParts;
    private Map<String, String> servletContextInitParameters;
    private Map<String, Object> servletContextAttributes;
    private List<String> removedRequestAttributes;
    private List<String> removedSessionAttributes;
    private List<String> removedServletContextAttributes;

    public RequestContent2(HttpServletRequest httpServletRequest) {
        requestAttributes = new HashMap<>();
        requestHeaders = new HashMap<>();
        sessionAttributes = new HashMap<>();
        servletContextInitParameters = new HashMap<>();
        servletContextAttributes = new HashMap<>();
        removedRequestAttributes = new ArrayList<>();
        removedSessionAttributes = new ArrayList<>();
        removedServletContextAttributes = new ArrayList<>();
        extractValues(httpServletRequest);
    }

    public void extractValues(HttpServletRequest httpServletRequest) {
        requestParameters = httpServletRequest.getParameterMap();
        Enumeration<String> requestAttributeNames = httpServletRequest.getAttributeNames();
        String currentRequestAttributeName;
        while (requestAttributeNames.hasMoreElements()) {
            currentRequestAttributeName = requestAttributeNames.nextElement();
            requestAttributes.put(currentRequestAttributeName, httpServletRequest.getAttribute(currentRequestAttributeName));
        }
        Enumeration<String> requestHeaderNames = httpServletRequest.getHeaderNames();
        String currentRequestHeaderName;
        while (requestHeaderNames.hasMoreElements()) {
            currentRequestHeaderName = requestHeaderNames.nextElement();
            requestHeaders.put(currentRequestHeaderName, httpServletRequest.getHeader(currentRequestHeaderName));
        }
        HttpSession httpSession = httpServletRequest.getSession(true);
        Enumeration<String> sessionAtrributeNames = httpSession.getAttributeNames();
        String currentSessionAtrributeName;
        while (sessionAtrributeNames.hasMoreElements()) {
            currentSessionAtrributeName = sessionAtrributeNames.nextElement();
            sessionAttributes.put(currentSessionAtrributeName, httpSession.getAttribute(currentSessionAtrributeName));
        }
        String contentType = httpServletRequest.getContentType();
        if (contentType != null && contentType.contains(MULTIPART_FORM_DATA)) {
            try {
                requestParts = httpServletRequest.getParts().stream().filter(part -> part.getContentType() != null).collect(Collectors.toList());
            } catch (ServletException e) {
                logger.error("Servlet getResultBySetOfToursId parts error", e);
            } catch (IOException e) {
                logger.error("Reading/Writing part error", e);
            }
        }
        ServletContext servletContext = httpServletRequest.getServletContext();
        Enumeration<String> servletContextInitParameterNames = servletContext.getInitParameterNames();
        String currentServletContextParameterName;
        while (servletContextInitParameterNames.hasMoreElements()) {
            currentServletContextParameterName = servletContextInitParameterNames.nextElement();
            servletContextInitParameters.put(currentServletContextParameterName, servletContext.getInitParameter(currentServletContextParameterName));
        }
        Enumeration<String> servletContextAttributeNames = servletContext.getAttributeNames();
        String currentServletContextAttributeName;
        while (servletContextAttributeNames.hasMoreElements()) {
            currentServletContextAttributeName = servletContextAttributeNames.nextElement();

            servletContextAttributes.put(currentServletContextAttributeName, servletContext.getAttribute(currentServletContextAttributeName));
        }

    }

    public void insertValues(HttpServletRequest httpServletRequest) {
        requestAttributes.forEach((key, value) -> httpServletRequest.setAttribute(key, value));
        if (removedRequestAttributes != null && !removedRequestAttributes.isEmpty()) {
            removedRequestAttributes.forEach(attributeName -> httpServletRequest.removeAttribute(attributeName));
        }
        HttpSession session = httpServletRequest.getSession(true);
        sessionAttributes.forEach((key, value) -> session.setAttribute(key, value));
        if (removedSessionAttributes != null && !removedSessionAttributes.isEmpty()) {
            removedSessionAttributes.forEach(attributeName -> session.removeAttribute(attributeName));
        }
        ServletContext servletContext = httpServletRequest.getServletContext();
        servletContextInitParameters.forEach((key, value) -> servletContext.setInitParameter(key, value));
        servletContextAttributes.forEach((key, value) -> servletContext.setAttribute(key, value));
        if (removedServletContextAttributes != null && !removedServletContextAttributes.isEmpty()) {
            removedServletContextAttributes.forEach(attributeName -> servletContext.removeAttribute(attributeName));
        }
    }

    public Object getRequestParameter(String parameterName) {
        Object result = null;
        if (parameterName != null && requestParameters.containsKey(parameterName)) {
            String[] parameters = requestParameters.get(parameterName);
            if (parameters.length == 1) {
                result = parameters[0];
            } else {
                result = parameters;
            }
        }
        return result;
    }

    public Object getRequestAttribute(String attributeName) {
        Object result = null;
        if (attributeName != null && requestAttributes.containsKey(attributeName)) {
            result = requestAttributes.get(attributeName);
        }
        return result;
    }

    public Object getServletContextAttribute(String servletContextAttributeName) {
        Object result = null;
        if (servletContextAttributeName != null && servletContextAttributes.containsKey(servletContextAttributeName)) {
            result = servletContextAttributes.get(servletContextAttributeName);
        }
        return result;
    }

    */
/*public File getRequestPart(String requestPartName) {
        File result = null;
        if (requestPartName != null && requestParts != null && !requestParts.isEmpty() && requestParts.stream().anyMatch(part -> part.getName().equals(requestPartName))) {
            try {
                File file = File.createTempFile(TEMP, MP3.toString());
                requestParts.stream().filter(part -> {
                    String fileName = part.getHeader(CONTENT_DISPOSITION);
                    String fileExtension = fileName.substring(fileName.lastIndexOf(DOT)).replaceAll(QUOTE, EMPTY_STRING);
                    long fileSize = part.getSize();
                    return (fileExtension.equals(MP3.toString()) && fileSize <= Song.DEFAULT_MAX_FILE_SIZE);//1024 * 1024 * 16
                }).findFirst().ifPresent(part -> {
                    try (InputStream inputStream = part.getInputStream()) {
                        FileUtils.copyInputStreamToFile(inputStream, file);
                    } catch (IOException e) {
                        logger.error("Reading/Writing input stream error due to ", e);
                    }
                });
                if (FileUtils.sizeOf(file) != 0) {
                    result = file;
                }
            } catch (IOException e) {
                logger.error("Creating file error due to ", e);
            }
        }
        return result;
    }
*//*

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

    public boolean setServletContextAttribute(String contentName, Object object) {
        boolean result = false;
        if (contentName != null && !contentName.isEmpty() && object != null) {
            result = (this.servletContextAttributes.put(contentName, object) != null);
        }
        return result;
    }

    public boolean removeServletContextAttribute(String servletContextAttributeName) {
        boolean result = false;
        if (servletContextAttributeName != null && servletContextAttributes.containsKey(servletContextAttributeName)) {
            removedServletContextAttributes.add(servletContextAttributeName);
            result = (servletContextAttributes.remove(servletContextAttributeName) != null);
        }
        return result;
    }

    public boolean removeRequestAttribute(String requestAttributeName) {
        boolean result = false;
        if (requestAttributeName != null && requestAttributes.containsKey(requestAttributeName)) {
            removedRequestAttributes.add(requestAttributeName);
            result = (requestAttributes.remove(requestAttributeName) != null);
        }
        return result;
    }

    public boolean removeSessionAttribute(String sessionAttributeName) {
        boolean result = false;
        if (sessionAttributeName != null && sessionAttributes.containsKey(sessionAttributeName)) {
            removedSessionAttributes.add(sessionAttributeName);
            result = (sessionAttributes.remove(sessionAttributeName) != null);
        }
        return result;
    }*/
