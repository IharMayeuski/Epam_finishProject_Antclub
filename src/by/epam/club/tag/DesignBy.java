package by.epam.club.tag;

import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


@SuppressWarnings("serial")
public class DesignBy extends TagSupport {

    @Override
    public int doStartTag() {
        String time = "<em> © Epam, Minsk 2019 Designed by Maevski Igor </em>";

        JspWriter out = pageContext.getOut();
        try {
            out.write(time);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
