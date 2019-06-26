package by.epam.club.command;

import by.epam.club.controller.TransmisionType;

public class Router {
    private String path;
    private TransmisionType transmisionType;

    public Router(String path, TransmisionType transmisionType) {
        this.path = path;
        this.transmisionType = transmisionType;
    }

    public String getPath() {
        return path;
    }

    public TransmisionType getTransmisionType() {
        return transmisionType;
    }

}