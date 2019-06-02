package by.epam.club.command;


import by.epam.club.command.impl.*;

public enum CommandEnum {

    GO_TO_REGISTRATION_PAGE {
        {
            this.command = new RegistrationPage();
        }
    },

    GO_TO_DEFAULT_PAGE {
        {
            this.command = new DefaultPage();
        }
    },

    GO_TO_GUEST_PAGE {
        {
            this.command = new GuestPage();
        }
    },
    GO_TO_ADMIN_PAGE {
        {
            this.command = new AdminPage();
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
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
