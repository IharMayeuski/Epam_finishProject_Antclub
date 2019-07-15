package by.epam.club.command;

import by.epam.club.command.forward.admin.*;
import by.epam.club.command.forward.global.*;
import by.epam.club.command.forward.user.*;
import by.epam.club.command.redirect.*;

/**
 * Enum for defining command for working after identification parameter
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public enum CommandEnum {

    GO_TO_REGISTRATION_PAGE {
        {
            this.command = new RegistrationPageCommand();
        }
    },

    GO_TO_DEFAULT_PAGE {
        {
            this.command = new DefaultPageCommand();
        }
    },

    NEW_PASSWORD {
        {
            this.command = new NewPasswordCommand();
        }
    },

    I_AM_GUEST {
        {
            this.command = new ToBeGuestCommand();
        }
    },
    FIND_USER {
        {
            this.command = new AuthorizationCommand();
        }
    },

    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    CHANGE_LOCALE {
        {
            this.command = new ChangeLocaleCommand();
        }
    },
    ARTICLE {
        {
            this.command = new FindArticleCommand();
        }
    },

    FIND_USER_BY_LOGIN {
        {
            this.command = new FindUserByLoginCommand();
        }
    },

    ACCOUNT_UPDATE {
        {
            this.command = new AccountUpdateCommand();
        }
    },

    ACCOUNT_DELETE {
        {
            this.command = new DeleteAccountCommand();
        }
    },

    PROFILE_USER {
        {
            this.command = new UserProfileCommand();
        }
    },
    SEND_LETTER {
        {
            this.command = new SendLetterCommand();
        }
    },

    GO_TO_NEW_TYPENEWS{
        {
            this.command = new ToNewTypeNewsCommand();
        }
    },

    GO_TO_NEW_ARTICLE{
        {
            this.command = new ToNewTypeArticleCommand();
        }
    },
    GO_TO_NEW_COMMENT{
        {
            this.command = new ToNewCommentCommand();
        }
    },
    ADD_NEW_COMMENT{
        {
            this.command = new AddNewCommentCommand();
        }
    },
    GO_ADMIN_CONTROL{
        {
            this.command = new ToAdminControlCommand();
        }
    },
    BLOCKED_USER{
        {
            this.command = new BlockedUserCommand();
        }
    },
    UNBLOCKED_USER{
        {
            this.command = new UnBlockedUserCommand();
        }
    },
    DELETE_USER{
        {
            this.command = new DeleteUserCommand();
        }
    },
    UNDELETE_USER{
        {
            this.command = new UnDeleteUserCommand();
        }
    },
    MARK_USER{
        {
            this.command = new MarkUserCommand();
        }
    },
    MARK_ADMIN{
        {
            this.command = new MarkAdminCommand();
        }
    },

    DELETE_PICTURE{
        {
            this.command = new DeletePictureCommand();
        }
    },
    DELETE_ARTICLE{
        {
            this.command = new DeleteArticleCommand();
        }
    },
    DELETE_COMMENT{
        {
            this.command = new DeleteCommentCommand();
        }
    },
    UPDATE_PAGE_COMMENT{
        {
            this.command = new ToUpdateCommentCommand();
        }
    },
    UPDATE_COMMENT{
        {
            this.command = new UpdateCommentCommand();
        }
    },
    TO_UPDATE_ARTICLE{
        {
            this.command = new ToUpdateArticleCommand();
        }
    },
    UPDATE_ARTICLE_COMMAND{
        {
            this.command = new UpdateArticleCommand();
        }
    },
    ADD_PIC_TO_ARTICLE{
        {
            this.command = new PicToArticleCommand();
        }
    },
    DELETE_TYPE{
        {
            this.command = new DeleteTypeCommand();
        }
    },
    DELETE_LETTER{
        {
            this.command = new DeleteLetterCommand();
        }
    },
    UNDELETE_TYPE{
        {
            this.command = new UndeleteTypeCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
