package by.epam.club.service;

import by.epam.club.service.impl.UserServiceImpl;

public class ServiceProviderCommand {

    private static final ServiceProviderCommand instance = new ServiceProviderCommand();

    private ServiceProviderCommand() {}

    private UserService service = new UserServiceImpl();

    public UserService getService() {
        return service;
    }

    public static ServiceProviderCommand getInstance() {  return instance;
    }
}