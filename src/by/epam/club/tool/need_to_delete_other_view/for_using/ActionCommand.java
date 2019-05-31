package by.epam.club.tool.need_to_delete_other_view.for_using;

import by.epam.club.tool.need_to_delete_other_view.RequestContent;

public interface ActionCommand {
    Router execute(RequestContent content);// throws SharingPicCommandException;
}
