package by.epam.club.command;

import by.epam.club.controller.TransmisionType;

/**
 * Class for routing commands of the application
 *
 * @author Maeuski Igor
 * @version 1.0
 */

public class Router {
    private String path;
    private TransmisionType transmisionType;

    /**
     * @param path the path of the .jsp page
     * @param transmisionType the special method redirect lr forvard
     */
    public Router(String path, TransmisionType transmisionType) {
        this.path = path;
        this.transmisionType = transmisionType;
    }
    /**
     * @return the path of the .jsp page
     */
    public String getPath() {
        return path;
    }

    /**
     * @return the special method redirect lr forvard
     */
    public TransmisionType getTransmisionType() {
        return transmisionType;
    }

}