public class Response {
    private int status;
    private String data;

    public Response(int status, String data) {
        this.status = status;
        this.data = data;
    }


    public String toJson(){
        return "{" +
                "\"status\": " +status+","+
                "\"data\":" +data+
                "}";
    }
}
