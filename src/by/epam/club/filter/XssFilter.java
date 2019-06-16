package by.epam.club.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maevskiy on 01.06.2019 10:20
 *
 * @author Maevskiy Igor
 */
@WebFilter(
        filterName = "XssFilter",
        urlPatterns = "/*")

public class XssFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        filterChain.doFilter(new AntClubHttpRequestWrapper(httpRequest), httpResponse);
    }

    private class AntClubHttpRequestWrapper extends HttpServletRequestWrapper {
        private static final String EMPTY_STRING = "";
        private static final String OPENING_ANGLE_BRACKETS = "<";
        private static final String CLOSING_ANGLE_BRACKETS = ">";

        public AntClubHttpRequestWrapper(HttpServletRequest httpRequest) {
            super(httpRequest);
        }

        public String getParameter(String name) {
            String param = super.getParameter(name);
            if (param != null) {
                param = param.replaceAll(OPENING_ANGLE_BRACKETS, EMPTY_STRING);
                param = param.replaceAll(CLOSING_ANGLE_BRACKETS, EMPTY_STRING);
            }
            return param;
        }

        public String[] getParameterValues(String name) {

            String[] values = super.getParameterValues(name);
            for (int i = 0; i < values.length; i++) {
                if (values[i] != null) {
                    values[i] = values[i].replace(OPENING_ANGLE_BRACKETS, EMPTY_STRING);
                    values[i] = values[i].replace(CLOSING_ANGLE_BRACKETS, EMPTY_STRING);
                }
            }
            return values;
        }
    }
}


