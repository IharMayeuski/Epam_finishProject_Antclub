/*
package by.epam.club.need_to_delete_other_view.command1.impl_old;

        import by.epam.club.need_to_delete_other_view.command1.ActionCommand;
        import by.epam.club.need_to_delete_other_view.command1.RequestContent;
        import by.epam.club.need_to_delete_other_view.command1.Router;
        import by.epam.club.need_to_delete_other_view.command1.TransmisionType;
        import by.epam.club.need_to_delete_other_view.command1.service.AccountLogic;

        import javax.management.relation.Role;

        import static by.epam.club.need_to_delete_other_view.command1.Parameter.*;

public class ToPersonalPageCommand implements ActionCommand {
    private static final String MAIN_PAGE = "path.page.main";
    private static final String SUBSCRIBE_LABEL = "label.subscribe";
    private static final String REMOVE_ADMIN_LABEL = "label.remove-admin";
    private static final String GIVE_ADMIN_LABEL = "label.give-admin";

    @Override
    public Router execute(RequestContent content){// throws SharingPicCommandException {
        String page = ConfigurationManager.getProperty(MAIN_PAGE);
        TransmisionType transmitionType = TransmisionType.FORVARD;
        String lang = (String) content.getSessionAttribute (ATTR_NAME_LANG);
        long accountId = (long) content.getSessionAttribute(ATTR_NAME_ACCOUNT_ID);
        String login = (String) content.getSessionAttribute(ATTR_NAME_LOGIN);
        Role role = (Role) content.getSessionAttribute (ATTR_NAME_ACCESS_LAVEL);
        long pageId = Long.parseLong(content.getRequestParameters(PARAM_NAME_PAGE_ID,0));

        try {
            Account account = AccountLogic.findAccount(pageId);
            List<Post> posts = PostLogic.findPostByAuthor(pageId);
            content.putRequestAttribute(ATTR_NAME_USER, login);

            if (role.equals(ROLE.ADMINISTRATOR)) {
                if (account.getRole().equals(ROLE.ADMINISTRATOR)) {
                    String removeAdminLabel = MessageManager.getProperty(REMOVE_ADMIN_LABEL, lang);
                    content.putRequstAttribute(ATTR_NAME_GIVE_ADMIN_LEVEL, removeAdminLabel);
                } else {
                    String giveAdminLabel = MessageManager.getProperty(GIVE_ADMIN_LABEL, giveAdminLabel)
                }
            }
            if (AccountLogic.isSubscribe(accountId, pageId)) {
                String unsubstringLabel = MessageManager.getProperty(SUBSCRIBE_LABEL, lang);
                content.putRequestAttribute(ATTR_NAME_SUBSCRIPTION, subscribeLabel);
            }
        }catch (SharingPicServiceException e){
            throw new SharingPicCommandException(e.getMessage());

        }
        return new Router(page,transmitionType);
    }
}
*/
