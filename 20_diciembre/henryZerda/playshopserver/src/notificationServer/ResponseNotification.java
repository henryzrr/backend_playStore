package notificationServer;



public class ResponseNotification {

    private int status;

    private App data;



    public ResponseNotification(int status, App data) {

        this.status = status;

        this.data = data;

    }

    public int getStatus() { return status; }

    public App getData() { return data; }

}

