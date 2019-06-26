/*

package by.epam.club.filter;

import by.epam.club.entity.Parameter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@WebFilter(urlPatterns = {"/*"})
class CommandSecurityFilter extends GenericFilter {

    private static final String GO_TO_REGISTRATION_PAGE = "go_to_registration_page";
    private static final String GO_TO_DEFAULT_PAGE = "go_to_default_page";
    private static final String  I_AM_GUEST = "i_am_guest";
    private static final String FIND_USER = "find_user";
    private static final String REGISTRATION = "registration";
    private static final String LOGOUT = "logout";
    private static final String CHANGE_LOCALE = "change_locale";
    private static final String ARTICLE  = "article";
    private static final String CONFIRM_DELETE = "confirm_delete";
    private static final String ACCOUNT_DELETE = "account_delete";
    private static final String COMMAND = "command";

    private static final Set<String> ALLOWED_GUEST_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(GO_TO_DEFAULT_PAGE, I_AM_GUEST, GO_TO_REGISTRATION_PAGE, REGISTRATION, LOGOUT, CHANGE_LOCALE, ARTICLE)));

    private static final Set<String> ALLOWED_USER_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(GO_TO_DEFAULT_PAGE, LOGOUT, FIND_USER, LOGOUT, CHANGE_LOCALE, ARTICLE, CONFIRM_DELETE, ACCOUNT_DELETE)));

    private static final Set<String> ALLOWED_ADMIN_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(GO_TO_DEFAULT_PAGE, FIND_USER, LOGOUT, CHANGE_LOCALE, ARTICLE, CONFIRM_DELETE, ACCOUNT_DELETE)));

    private static final Set<String> ALLOWED_NOONE_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(FIND_USER, GO_TO_REGISTRATION_PAGE, GO_TO_DEFAULT_PAGE , LOGOUT, I_AM_GUEST, REGISTRATION, CHANGE_LOCALE)));

    private static Logger logger = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute(Parameter.ROLE_PARAM);
        try {
            if (request.getParameter(COMMAND) != null) {
                if (role == null) {
                    if (!ALLOWED_GUEST_PATHS.contains(request.getParameter(COMMAND))) {
                        response.sendRedirect(request.getContextPath() +request.getServletPath());
                    }
                } else if (role.equals(Parameter.USER_PARAM)) {
                    if (!ALLOWED_USER_PATHS.contains(request.getParameter(COMMAND))) {
                        response.sendRedirect(request.getContextPath() +request.getServletPath());
                    }
                } else if (role.equals(Parameter.ADMIN_PARAM)) {
                    if (!ALLOWED_ADMIN_PATHS.contains(request.getParameter(COMMAND))) {
                        response.sendRedirect(request.getContextPath() +request.getServletPath());
                    }
                }else  {
                    if (!ALLOWED_NOONE_PATHS.contains(request.getParameter(COMMAND))) {
                        response.sendRedirect(request.getContextPath() +request.getServletPath());
                    }
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {// FIXME: 6/6/2019
            logger.error(" Exception in SecurityFilter ", e);
            e.printStackTrace();
           response.sendRedirect(request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() +"/default.jsp");
        }
    }
}

*/
