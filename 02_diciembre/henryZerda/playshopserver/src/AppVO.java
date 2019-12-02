public class AppVO {
    private String nombre;
    private String version;

    public AppVO(App app) {
        this.nombre = app.getNombre();
        this.version = app.getVersion();
    }

    public String getNombre() {
        return nombre;
    }

    public String getVersion() {
        return version;
    }



    public String toJson(){
        return "{" +
                "\"nombre\":\"" +nombre+"\","+
                "\"version\":\"" +version+"\""+
                "}";
    }
}
