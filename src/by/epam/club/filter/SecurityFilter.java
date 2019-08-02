package by.epam.club.filter;

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

import static by.epam.club.command.CommandEnum.*;
import static by.epam.club.entity.Parameter.*;

/**
 * This filter can give for difference roles difference opportunity in using any command
 *
 * @author Maevskiy Igor
 * @see GenericFilter
 */
@WebFilter(urlPatterns = {"/*"})
public class SecurityFilter extends GenericFilter {
    private static final Logger LOGGER = LogManager.getLogger(SecurityFilter.class);
    private static final Set<String> ALLOWED_GUEST_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList(GO_TO_DEFAULT_PAGE.toString(), LOGOUT.toString(), CHANGE_LOCALE.toString(),
                    ARTICLE.toString() , NEW_PASSWORD.toString())));

    private static final Set<String> ALLOWED_USER_PATHS = new HashSet<>(
            Arrays.asList(GO_TO_DEFAULT_PAGE.toString(),  FIND_USER.toString(), LOGOUT.toString(), CHANGE_LOCALE.toString(),
                    ARTICLE.toString(), FIND_USER_BY_LOGIN.toString(), ACCOUNT_UPDATE.toString() , ACCOUNT_DELETE.toString(),
                    SEND_LETTER.toString() , GO_TO_NEW_TYPENEWS.toString(), GO_TO_NEW_ARTICLE.toString(), GO_TO_NEW_COMMENT.toString(),
                    ADD_NEW_COMMENT.toString(), DELETE_PICTURE.toString(),PROFILE_USER.toString(),
                    DELETE_ARTICLE.toString(), DELETE_COMMENT.toString(), ADD_PIC_TO_ARTICLE.toString(), UPDATE_COMMENT.toString(),
                    UPDATE_PAGE_COMMENT.toString(), TO_UPDATE_ARTICLE.toString(), UPDATE_ARTICLE_COMMAND.toString(),
                    UPDATE_PAGE_COMMENT.toString(), DELETE_LETTER.toString()));

    private static final Set<String> ALLOWED_ADMIN_PATHS = new HashSet<>(
            Arrays.asList(GO_TO_DEFAULT_PAGE.toString() , NEW_PASSWORD.toString(), I_AM_GUEST.toString(), LOGOUT.toString(),
                    CHANGE_LOCALE.toString(), ARTICLE.toString(), FIND_USER_BY_LOGIN.toString(), ACCOUNT_UPDATE.toString() , ACCOUNT_DELETE.toString(), PROFILE_USER.toString(),
                    SEND_LETTER.toString() , GO_TO_NEW_TYPENEWS.toString(), GO_TO_NEW_ARTICLE.toString(), GO_TO_NEW_COMMENT.toString(),
                    ADD_NEW_COMMENT.toString(), GO_ADMIN_CONTROL.toString(), BLOCKED_USER.toString(), UNBLOCKED_USER.toString(),
                    DELETE_USER.toString(), UNDELETE_USER.toString(), MARK_USER.toString(), MARK_ADMIN.toString(), DELETE_PICTURE.toString(),
                    DELETE_ARTICLE.toString(), DELETE_COMMENT.toString(), ADD_PIC_TO_ARTICLE.toString(), DELETE_TYPE.toString(),
                    UNDELETE_TYPE.toString(), TO_UPDATE_ARTICLE.toString(), UPDATE_ARTICLE_COMMAND.toString(), UPDATE_PAGE_COMMENT.toString(),
                    UPDATE_COMMENT.toString(), DELETE_LETTER.toString()));

    private static final Set<String> ALLOWED_UNKNOWN_PATHS = new HashSet<>(
            Arrays.asList(GO_TO_DEFAULT_PAGE.toString(), I_AM_GUEST.toString(), CHANGE_LOCALE.toString() ,FIND_USER.toString(),
                    NEW_PASSWORD.toString(), REGISTRATION.toString(), GO_TO_REGISTRATION_PAGE.toString()));

    /**
     * @param servletRequest for input in filter
     * @param servletResponse for input in filter
     * @param filterChain used to intercept servlet initialization
     * @throws IOException in the case of exception on the method doFilter
     *
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        String type = (String) session.getAttribute(ROLE_PARAM);
        String command = httpServletRequest.getParameter(PARAM_NAME_COMMAND);
        String userRole = (type == null ? UNKNOWN_PARAM: type);
        try {
            if (command != null) {
                switch (userRole) {
                    case ADMIN_PARAM:
                        if (!ALLOWED_ADMIN_PATHS.contains(command.toUpperCase())) {
                            ((HttpServletResponse) servletResponse).sendRedirect(httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + httpServletRequest.getServletPath());
                            return;
                        }
                        break;
                    case USER_PARAM:
                        if (!ALLOWED_USER_PATHS.contains(command.toUpperCase())) {
                            ((HttpServletResponse) servletResponse).sendRedirect(httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + httpServletRequest.getServletPath());
                            return;
                        }
                        break;
                    case GUEST_PARAM:
                        if (!ALLOWED_GUEST_PATHS.contains(command.toUpperCase())) {
                            ((HttpServletResponse) servletResponse).sendRedirect(httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + httpServletRequest.getServletPath());
                            return;
                        }
                        break;
                   default:
                        if (!ALLOWED_UNKNOWN_PATHS.contains(command.toUpperCase())) {
                            ((HttpServletResponse) servletResponse).sendRedirect(httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + httpServletRequest.getServletPath());
                            return;
                        }
                        break;
                }
            }
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Exception e) {
            LOGGER.warn(" Exception in SecurityFilter ", e);
            ((HttpServletResponse) servletResponse).sendRedirect(httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + httpServletRequest.getContextPath() + httpServletRequest.getServletPath());
        }
    }
}