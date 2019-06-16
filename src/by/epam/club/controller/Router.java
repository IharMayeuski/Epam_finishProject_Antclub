package by.epam.club.controller;

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


   /* private String page = PagePath.INDEX; //todo обратить внимание на другую реализацию

    private DispatchType dispatchType = DispatchType.FORWARD;

    public Router() {
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public DispatchType getDispatchType() {
        return dispatchType;
    }

    public void setRedirect() {
        this.dispatchType = DispatchType.REDIRECT;
    }
}*/