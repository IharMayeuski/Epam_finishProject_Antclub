package by.epam.club.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

import static by.epam.club.entity.Parameter.ENCODING_PARAM;

/**
 * Filter for encoding in UTF-8 initialize on command and .jsp
 *
 * @author Maevskiy Igor
 * @see GenericFilter
 */
@WebFilter(
        urlPatterns = {"/*"},
        initParams = {
        @WebInitParam(name = "encoding", value = "UTF-8",
                        description = "Encoding Parameter")})
public class EncodingFilter extends GenericFilter {
    private String code;

    /**
     * @param filterConfig for encoding in utf-8
     */
    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter(ENCODING_PARAM);
    }

    /**
     * @param request received this parameter after every command
     * @param response use for sending HttpServletResponse
     * @param filterChain used to intercept servlet initialization
     * @throws ServletException this method cant throw this exception
     * @throws IOException this method cant throw IOException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        code = null;
    }
}
