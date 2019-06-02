package by.epam.club.controller;

public class Router {
    private String path;
    private TransmisionType transmisionType;

    public Router(String path, TransmisionType transmisionType){
        this.path=path;
        this.transmisionType=transmisionType;
    }

    public String getPath (){return path;}

    public TransmisionType getTransmisionType() {
        return transmisionType;
    }
}
