import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Response {
    private int status;
    private List<App> data;

    public Response(String response) {
        data = new LinkedList<>();
        toObject(response);
    }

    public int getStatus() {
        return status;
    }

    public List<App>  getData() {
        return data;
    }

    private void toObject(String response) {
        String [] params = response.split("data\":");
        String  [] status = params[0].split(":");
        String st = status[1].replaceAll("[\"{}\\[\\] ,]","");

        params[1]=params[1].replaceAll("[\\[\\]]","");
        String [] lines  = params[1].split("},");
        if(lines[0].length()>1){
            for (String s: lines
            ) {
                App app = new App(s);
                data.add(app);
            }
        }
        this.status = Integer.parseInt(st);
    }


}
