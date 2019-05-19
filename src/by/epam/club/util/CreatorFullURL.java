package by.epam.club.util;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

public class CreatorFullURL {
	public static String create(HttpServletRequest request) {
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
