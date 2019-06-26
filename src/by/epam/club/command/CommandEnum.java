package by.epam.club.command;


import by.epam.club.command.forward.*;
import by.epam.club.command.redirect.*;

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

    USER_LETTER {
        {
            this.command = new UserLetterCommand();
        }
    },

    GO_TO_NEW_TYPENEWS{
        {
            this.command = new ToNewTypeNewsCommand();
        }
    },

    GO_ADMIN_CONTROL{
        {
            this.command = new ToAdminControlCommand();
        }
    };
    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
