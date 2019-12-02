public class JsonResponse {
    private boolean success;
    private String message;
    private String data;

    public JsonResponse(boolean success, String message, String data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public String  toJson(){
        return "{" +
                "\"success\":\"" +success+"\","+
                "\"message\":\""+message +"\","+
                "\"data\":"+ data+""+
                "}";
    }
}
