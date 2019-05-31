package by.epam.club.tool.need_to_delete_other_view.for_using;

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
