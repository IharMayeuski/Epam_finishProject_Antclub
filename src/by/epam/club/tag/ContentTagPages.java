package by.epam.club.tag;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import javax.servlet.jsp.JspException;

import static by.epam.club.entity.Parameter.UNKNOWN_MISTAKE_MESSAGE;

/**
 * The class is created own tag for choosing page in the case of long pages
 *
 * @author Maeuski Igor
 * @version 1.0
 */

// FIXME: 7/11/2019 не работает тэг еще

public class ContentTagPages extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(ContentTagPages.class);
    private int pageNumber;
    private int maxPages;

    public void setPageNumber(String inputPageNumber){
        this.pageNumber = Integer.parseInt(inputPageNumber);
    }

    public void setMaxPages(String inputMaxPages) {
        this.maxPages = Integer.parseInt(inputMaxPages);
    }

    /**
     * @return url with number of page
     */
    @Override
    public int doStartTag()  {
        JspWriter out = pageContext.getOut();
        try {
            out.write("<a href=\"controller?command=article\">  |< </a>\n");
            if (pageNumber - 2 >= 1) {
                out.write("<a href=\"controller?command=article&page=" + (pageNumber - 2) + "\">" + (pageNumber - 2) + "</a>\n");
            }
            if (pageNumber - 1 >= 1) {
                out.write("<a href=\"controller?command=article&page=" + (pageNumber - 1) + "\">" + (pageNumber - 1) + "</a>\n");
            }
            out.write("<a href=\"controller?command=article&page=" + pageNumber + "\"> ... </a>");
            if (pageNumber + 1 <= maxPages) {
                out.write("<a href=\"controller?command=article&page=" + (pageNumber + 1) + "\">" + (pageNumber + 1) + "</a>\n");
            }
            if (pageNumber + 2 <= maxPages) {
                out.write("<a href=\"controller?command=article&page=" + (pageNumber + 2) + "\">" + (pageNumber + 2) + "</a>\n");
            }
            out.write("<a href=\"controller?command=article&page=" + maxPages + "\"> >|  </a>");
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
