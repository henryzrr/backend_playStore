
public class Response {
    private int status;
    private String data;

    public Response(String response) {
        toObject(response);
    }

    private void toObject(String response) {
        String [] params = response.split("data:");
        String  [] status = params[0].split(":");
        data = params[1];
        String s = status[1].replaceAll("[\"{}\\[\\] ]","");
        this.status = Integer.parseInt(s);
    }


}
