/*
package by.epam.club.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.club.exception.ControllerException;

public class ChangeLocaleCommand implements Commander {
	private static final String DEFAULT_PAGE = "/WEB-INF/jsp/default.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ControllerException {
		String newLocale;
		HttpSession session;

		newLocale = request.getParameter("locale");


		session = request.getSession(true);
		session.setAttribute("local", newLocale);

		String url = (String) request.getSession(false).getAttribute("prev_request");

		System.out.println("Searching url = "+url);

		if (url==null) {
			url = "http://localhost:8081/Antclub/controller?command=default_page";
		}

		try {
			response.sendRedirect(url);
		} catch (IOException e) {
			throw new ControllerException(e);
		}
	}

}
*/
