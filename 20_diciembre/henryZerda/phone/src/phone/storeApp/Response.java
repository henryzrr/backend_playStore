package phone.storeApp;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Response {
    private int status;
    private List<App> data;


    public Response(int status, List<App> data) {
        this.status = status;
        this.data = data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(List<App> data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public List<App>  getData() {
        return data;
    }


}
