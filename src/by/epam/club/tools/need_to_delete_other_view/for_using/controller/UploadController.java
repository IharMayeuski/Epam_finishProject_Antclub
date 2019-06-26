package by.epam.club.tools.need_to_delete_other_view.for_using.controller;/*
package by.epam.club.tool.need_to_delete_other_view.for_using.controller;

import RequestContent_myne;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "/UploadImage")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 1024 * 1024) //1 MB
public class UploadController extends AntController {
    private static final String CONTENT_TYPE = "image/jpeg";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        processRequest(request, response, new PartsRequestContent());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType(CONTENT_TYPE);
        RequestContent_myne requestContent = new RequestContent_myne();

        try {
            requestContent.extractValues(request);
            ImageLoadCommand loadCommand = ImageLoadCommandFactory.defineCommand(requestContent);
            response.getOutputStream().write(loadCommand.load(requestContent));
        } catch (SharingPicCommandExcetion e) {
            response.sendRedirect(CommandUrlBuilder.
                    TO_ERROR.setParams(PARAM_NAME_ERROR_MESSAGE, e.getMessage()).
                    getUrl());
        }

    }

}


*/
