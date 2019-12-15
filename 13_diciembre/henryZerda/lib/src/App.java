public class App {
    private String name;
    private String version;
    private String status;


    public App(String name, String version, String status) {
        this.name = name;
        this.version = version;
        this.status = status;
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



}