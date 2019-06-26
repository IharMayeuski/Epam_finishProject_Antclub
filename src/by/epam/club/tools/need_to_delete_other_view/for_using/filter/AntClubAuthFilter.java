package by.epam.club.tools.need_to_delete_other_view.for_using.filter;/*
package by.epam.club.tool.need_to_delete_other_view.for_using.filter;

import by.epam.club.bundlemanager.ConfigurationManager;
import by.epam.club.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.relation.Role;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "AntClubAuthFilter", urlPatterns = "/*")
public class AntClubAuthFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(AntClubAuthFilter.class);
    private static final String PUBLIC_ACTION_PROPERTY_KEY = "action.public";
    private static final String ADMIN_ACTION_PROPERTY_KEY = "action.admin";
    private static final String COMMA = ",";
    private static final String SEPARATOR = "/";
    private static final String NOT_AUTH_MESSAGE = "not authorization";
    private static final String BAD_ACCESS_LEVEL = "user attempt use admin action";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        try {
            if (httpRequest.getParameter("command") != null) {
                if (!isPublicAccessAction(httpRequest.getParameter("command"))) {
                    if (httpRequest.getSession(false) == null) {
                        throw new DaoException(NOT_AUTH_MESSAGE);
                    } else {
                        if (httpRequest.getSession(false).getAttribute(ATTR_NAME_ACCOUNT_ID) == null) {
                            throw new DaoException(NOT_AUTH_MESSAGE);
                        }
                    }
                }
                if (isAdminAccessAction(httpRequest.getParameter("command")) &&
                        httpRequest.getSession(false).getAttribute(ATTR_NAME_ACCESS_LEVEL).equals(Role.USER)) {
                    throw new DaoException(BAD_ACCESS_LEVEL);
                }
            }
            filterChain.doFilter(httpRequest, httpResponse);
        } catch (DaoException e) {
            LOGGER.warn(e.getMessage());
            httpResponse.sendRedirect(httpRequest.getContextPath() + SEPARATOR);
        }
    }

    private boolean isPublicAccessAction(String action) {
        String publicActionProperty = ConfigurationManager.getProperty(PUBLIC_ACTION_PROPERTY_KEY);
        List<String> publicActions = Arrays.asList(publicActionProperty.split(COMMA));
        publicActions.forEach(a -> a = a.trim());
        return publicActions.contains(action);
    }

    private boolean isAdminAccessAction(String action) {
        String adminActionProperty = ConfigurationManager.getProperty(ADMIN_ACTION_PROPERTY_KEY);
        List<String> adminActions = Arrays.asList(adminActionProperty.split(COMMA));
        adminActions.forEach(a -> a = a.trim());
        return adminActions.contains(action);
    }
}
*/
