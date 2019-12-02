public class Response {
    private boolean success;
    private String data;
    private String message;
    public Response(String response){
        getParams(response);
    }

    private void getParams(String response) {
        int indexSuccess = response.indexOf("success")+10;
        int indexData=response.indexOf("data")+7;
        int indexMessage= response.indexOf("message")+11;

        char c='a';
        String word="";

        while (response.charAt(indexSuccess)!='"'){
            word+=response.charAt(indexSuccess++);
        }
        success=Boolean.getBoolean(word);

        word="";
        while (response.charAt(indexMessage)!='"'){
            word+=response.charAt(indexMessage++);
        }
        message=word;
        word="";
        data = response.substring(indexData,response.length()-2);

    }

    public boolean isSuccess() {
        return success;
    }

    public String getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
