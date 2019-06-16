package by.epam.club.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * Created by Maevskiy on 05.06.2019 20:29
 *
 * @author Maevskiy Igor
 */
@WebFilter(
        urlPatterns = {"/*"},
        initParams = {
        @WebInitParam(name = "encoding", value = "UTF-8",
                        description = "Encoding Parameter")})
public class EncodingFilter extends GenericFilter {
    private String code;

    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter("encoding");
    }

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
