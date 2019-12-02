public class AppPhone {
    private String nombre;
    private String version;

    public AppPhone(String nombre, String version) {
        this.nombre = nombre;
        this.version = version;
    }

    public String ToJson() {
        return  "{\"nombre\":\""+nombre+"\"," +
                "\"version\":\""+version+"\"}";
    }

}
