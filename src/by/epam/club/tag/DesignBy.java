package by.epam.club.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import static by.epam.club.entity.Parameter.UNKNOWN_MISTAKE_MESSAGE;

/**
 * Here I created special tag for next creating 'footer' on .jsp page
 *
 * @author Maeuski Igor
 * @version 1.0
 */

@SuppressWarnings("serial")
public class DesignBy extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(DesignBy.class);
    private static final String TAG = "<em> © Epam, Minsk 2019 Designed by Maevski Igor </em>";
    /**
     *
     * @return information and print own tag
     */
    @Override
    public int doStartTag() {
         JspWriter out = pageContext.getOut();
        try {
            out.write(TAG);
        } catch (IOException e) {
            LOGGER.warn(UNKNOWN_MISTAKE_MESSAGE, e);
        }
        return SKIP_BODY;
    }
    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
