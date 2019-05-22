package by.epam.club.command;


import by.epam.club.command.impl.*;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<String, Commander> commands = new HashMap<>();

    private static final CommandProvider instance = new CommandProvider();

    private CommandProvider() {
        commands.put("default_page", new ToDefaultPage());
        commands.put("to_admin_page", new ToAdminPage());
        commands.put("to_user_page", new ToUserPage());
        commands.put("to_guest_page", new ToGuestPage());
        commands.put("go_to_registrationPage", new ToRegistrationPage());

        commands.put("logOut", new LogOut());

        commands.put("find_user", new AuthorizationUser());
        commands.put("create_new_user", new RegistrationUser());

    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Commander getCommand(String commandName) {
        return commands.get(commandName);
    }
}
