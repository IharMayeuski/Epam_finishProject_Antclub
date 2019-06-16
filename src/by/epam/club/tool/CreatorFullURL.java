package by.epam.club.tool;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class CreatorFullURL {
	public String create(HttpServletRequest request) {
		String url = "";
		Enumeration<String> paramNames = request.getParameterNames();
		String paramName;
		String paramValue;
		
		while (paramNames.hasMoreElements()) {
			paramName = paramNames.nextElement();
			paramValue = request.getParameter(paramName);
			url += paramName+"="+paramValue+"&";
		}
		return request.getRequestURL()+ "?" + url;
	}

}
