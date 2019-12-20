package notificationServer;

import java.util.List;

public class Response {
    private int status;
    private String data;

    public Response(int status, String data) {
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }

    public String toJson(){
        return "";
    }
}
