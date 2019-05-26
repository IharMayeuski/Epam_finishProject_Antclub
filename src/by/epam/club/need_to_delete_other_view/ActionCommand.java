package by.epam.club.need_to_delete_other_view;

public interface ActionCommand {
    Router execute(RequestContent content);// throws SharingPicCommandException;
}
