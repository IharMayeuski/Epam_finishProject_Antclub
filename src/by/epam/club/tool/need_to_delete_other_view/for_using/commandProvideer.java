package by.epam.club.tool.need_to_delete_other_view.for_using;/*
package by.epam.club.tool.need_to_delete_other_view.for_using;



    public class commandProvideer {
        public static Optional<Command> defineCommand(String commandName) {
            Optional<Command> current;
            if (commandName == null || commandName.isBlank()) {
                return Optional.empty();
            }
            try {
                CommandType type = CommandType.valueOf(commandName.toUpperCase());
                current = Optional.of(type.getCommand());
            } catch (IllegalArgumentException e) {
                // log.error
                current = Optional.empty();
            }
            return current;
        }

        public static Optional<Command> takeCommand(String commandName) {
            return Arrays.stream(CommandType.values())
                    .filter(o -> o.name().equalsIgnoreCase(commandName))
                    .map(CommandType::getCommand)
                    .findAny();
            // Optional.ofNullable(type.orElse(null).getCommand());
        }
    }
}
*/
