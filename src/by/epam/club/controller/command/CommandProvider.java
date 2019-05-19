package by.epam.club.controller.command;


import by.epam.club.controller.command.impl.ToDefaultPage;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<String, Commander> commands = new HashMap<>();

    private static final CommandProvider instance = new CommandProvider();

    private CommandProvider() {
        commands.put("default_page", new ToDefaultPage());

     }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Commander getCommand(String commandName) {
        return commands.get(commandName);
    }
}
