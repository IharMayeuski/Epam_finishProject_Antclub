package by.epam.club.command;


import by.epam.club.command.forward.DefaultPageCommand;
import by.epam.club.command.forward.FindUserByLoginCommand;
import by.epam.club.command.forward.RegistrationPageCommand;
import by.epam.club.command.forward.ToBeGuestCommand;
import by.epam.club.command.impl.*;
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

    NEW_PASSWORD{
        {
            this.command = new NewPasswordCommand();
        }
    },

      I_AM_GUEST {
          {
              this.command = new ToBeGuestCommand();
          }
      },
      /*GO_TO_ADMIN_PAGE{ //todo удалить, если нет необходимости
          {
              this.command=new AdminPage();
          }
      },*/
      FIND_USER {
          {
              this.command = new AuthorizationCommand();
          }
      },

      REGISTRATION{
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

    CONFIRM_DELETE {
        {
            this.command = new ConfirmDeletePageCommand();
        }
    },
    FIND_USER_BY_LOGIN {
        {
            this.command = new FindUserByLoginCommand();
        }
    },

    ACCOUNT_DELETE {
        {
            this.command = new DeleteAccountCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

}
