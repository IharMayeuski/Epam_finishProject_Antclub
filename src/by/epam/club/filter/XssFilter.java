package by.epam.club.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.club.entity.Parameter.*;

/**
 * This firter we use for checking any script
 *
 * @author Maevskiy Igor
 * @see GenericFilter
 */
@WebFilter(
        filterName = "XssFilter",
        urlPatterns = "/*")

public class XssFilter implements Filter {
    /**
     *
     * @param servletRequest received this parameter after every command
     * @param servletResponse use for sending HttpServletResponse
     * @param filterChain used to intercept servlet initialization
     * @throws ServletException this method cant throw this exception
     * @throws IOException this method cant throw IOException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        filterChain.doFilter(new AntClubHttpRequestWrapper(httpRequest), httpResponse);
    }

    private class AntClubHttpRequestWrapper extends HttpServletRequestWrapper {
        private AntClubHttpRequestWrapper(HttpServletRequest httpRequest) {
            super(httpRequest);
        }

        /**
         * @param name give parameter for checking
         * @return parameter after deleting some brackets (if its are in the parameter 'name')
         */
        public String getParameter(String name) {
            String param = super.getParameter(name);
            if (param != null) {
                param = param.replaceAll(OPENING_ANGLE_BRACKETS_PARAM, EMPTY_STRING_PARAM);
                param = param.replaceAll(CLOSING_ANGLE_BRACKETS_PARAM, EMPTY_STRING_PARAM);
            }
            return param;
        }
        /**
         * @param name give parameter for checking
         * @return arrat parameters after deleting some brackets (if its are in the parameter 'name')
         */
        public String[] getParameterValues(String name) {
            String[] values = super.getParameterValues(name);
            for (int i = 0; i < values.length; i++) {
                if (values[i] != null) {
                    values[i] = values[i].replace(OPENING_ANGLE_BRACKETS_PARAM, EMPTY_STRING_PARAM);
                    values[i] = values[i].replace(CLOSING_ANGLE_BRACKETS_PARAM, EMPTY_STRING_PARAM);
                }
            }
            return values;
        }
    }
}


