package phone.storeApp;

public class App {
    private String name;
    private String version;
    private String status;


    public App(String app){
        toApp(app);
    }

    public App(String name, String version, String status) {
        this.name = name;
        this.version = version;
        this.status = status;
    }
    public App() {
    }
    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getStatus() {
        return status;
    }

    public String toJson(){
        return "{" +
                "\"name\":\"" +name+"\","+
                "\"version\":" +version+","+
                "\"status\":"+status+
                "}";
    }

    public void toApp(String app){
        String [] params = app.split(",");
        String  [] name = params[0].split(":");
        String [] version = params[1].split(":");
        String [] status = params[2].split(":");

        String n = name[1].replaceAll("[\"{}\\[\\] ]","");
        String v = version[1].replaceAll("[\" {}\\[\\]]","");
        String s = status[1].replaceAll("[\" {}\\[\\]]","");

        this.name=n;
        this.version=v;
        this.status=s;
    }
}