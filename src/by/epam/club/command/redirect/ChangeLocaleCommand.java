package by.epam.club.command.redirect;

import by.epam.club.command.ActionCommand;
import by.epam.club.controller.RequestContent;
import by.epam.club.controller.*;
import by.epam.club.entity.Parameter;

public class ChangeLocaleCommand implements ActionCommand {

    @Override
    public Router execute(RequestContent content){
        TransmisionType transmitionType = TransmisionType.FORWARD;
        String newLocale = Parameter.EN_PARAM;
        int locale = Integer.parseInt(content.getRequestParameters(Parameter.LOCALE_PARAM, 0));
       String pageFromJsp = content.getRequestParameters(Parameter.PATH_PARAM,0);
        if (locale == 2) {
            newLocale = Parameter.RUS_PARAM;
        }
        content.putSessionAttribute(Parameter.LOCAL_PARAM, newLocale);
        return new Router(pageFromJsp, transmitionType);

    }
}

